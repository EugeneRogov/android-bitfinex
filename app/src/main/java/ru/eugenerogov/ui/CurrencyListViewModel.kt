package ru.eugenerogov.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import org.json.JSONArray
import ru.eugenerogov.ServerHost
import ru.eugenerogov.data.Currency


class CurrencyListViewModel : ViewModel() {
    companion object {
        val TAG: String = CurrencyListViewModel::class.java.simpleName
        private const val NORMAL_CLOSURE_STATUS = 1000
    }

    val currencyList = mutableSetOf<Currency>()

    private var client: OkHttpClient
    private var ws: WebSocket

    init {
        // mock data
        for (i in 0 until 3) {
            val currency = Currency()
            currency.currencyPair = "ETH/USD"
            currency.lastPrice = 1334.0
            currency._24HoursChange = 6.7F
            currencyList += currency
        }

        // init Web Socket connection
        client = OkHttpClient()
        val request: Request = Request.Builder().url(ServerHost.WSS_BITFINEX).build()
        val bitfinexWebSocket = BitfinexWebSocket()
        ws = client.newWebSocket(request, bitfinexWebSocket)

        client.dispatcher.executorService.shutdown()
    }

    override fun onCleared() {
        super.onCleared()
        ws.close(NORMAL_CLOSURE_STATUS, "Bye!")
        Log.i(TAG, "onCleared")
    }

    internal class BitfinexWebSocket : WebSocketListener() {

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
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            Log.i(TAG, "onClosed $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            Log.i(TAG, "onFailure ${response.toString()}")
        }
    }

}