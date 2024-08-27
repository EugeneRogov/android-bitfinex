package ru.eugenerogov.ui.currencylist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.*
import ru.eugenerogov.data.TickerRepository
import ru.eugenerogov.data.local.CurrencyPair
import ru.eugenerogov.data.remote.ServerHost
import ru.eugenerogov.data.remote.Ticker
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    client: OkHttpClient,
    tickerRepository: TickerRepository
) : ViewModel() {
    companion object {
        val TAG: String = CurrencyListViewModel::class.java.simpleName
        private const val NORMAL_CLOSURE_STATUS = 1000
    }

    val tickerListLiveData = tickerRepository.getTickers()

    val tickerList = mutableListOf<Ticker>()

    private var ws: WebSocket

    private var ldTicker = MutableLiveData<Ticker>()
    fun ticker(): LiveData<Ticker> {
        ldTicker = MutableLiveData()
        return ldTicker
    }

    init {
        // mock data
        for (i in 0 until 3) {
            val ticker = Ticker()
            ticker.title = CurrencyPair.values()[i].title
            ticker.urlIcon = CurrencyPair.values()[i].urlIcon
            ticker.lastPrice = 1334.0F
            ticker.dailyChange = 6.8F

            tickerList += ticker
        }

        // init Web Socket connection
        // TODO: getting remote data need create from Repository, this temporary
        val request: Request = Request.Builder().url(ServerHost.WSS_BITFINEX).build()
        ws = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Log.i(TAG, "onOpen")

                webSocket.send(
                    "{\n" +
                            "   \"event\":\"subscribe\",\n" +
                            "   \"channel\":\"ticker\",\n" +
                            "   \"pair\":\"BTCUSD\"\n" +
                            "}"
                )
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                Log.i(TAG, "onMessage $text")

                // TODO: not good filter for catch ticker, this temporary
                if (text.startsWith("[")) {
                    val tickerArray =
                        text.replace("]", "").replace("[", "").filter { !it.isWhitespace() }
                    val ticker = Ticker()
                    ticker.title = CurrencyPair.BTC.title
                    ticker.urlIcon = CurrencyPair.BTC.urlIcon

                    ticker.id = tickerArray[0].toInt()
                    ticker.channelId = tickerArray[1].toInt()
                    ticker.bid = tickerArray[2].toFloat()
                    ticker.bidSize = tickerArray[3].toFloat()
                    ticker.ask = tickerArray[4].toFloat()
                    ticker.askSize = tickerArray[5].toFloat()
                    ticker.dailyChange = tickerArray[6].toFloat()
                    ticker.dailyChangePerc = tickerArray[7].toFloat()
                    ticker.lastPrice = tickerArray[8].toFloat()
                    ticker.volume = tickerArray[9].toFloat()
                    ticker.high = tickerArray[10].toFloat()
                    ticker.low = tickerArray[11].toFloat()

                    tickerList.add(ticker)
                    ldTicker.postValue(ticker)

                    Log.i(TAG, "onMessage ticker $tickerArray")
                }
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                Log.i(TAG, "onClosed $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Log.i(TAG, "onFailure ${response.toString()}")
            }

        })
        client.dispatcher.executorService.shutdown()
    }

    override fun onCleared() {
        super.onCleared()
        ws.close(NORMAL_CLOSURE_STATUS, "Bye!")
    }

}