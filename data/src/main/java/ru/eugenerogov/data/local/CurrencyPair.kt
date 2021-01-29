package ru.eugenerogov.data.local

enum class CurrencyPair(val title: String, val pair: String, val urlIcon: String) {
    BTC("BTC/USD", "BTCUSD", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitcoin.svg/1200px-Bitcoin.svg.png"),
    ETH("ETH/USD", "ETHUSD","https://icons.iconarchive.com/icons/cjdowner/cryptocurrency-flat/1024/Ethereum-ETH-icon.png"),
    EOS("EOS/USD", "EOSUSD","https://cdn4.iconfinder.com/data/icons/crypto-currency-and-coin-2/256/eos_eoscoin_coin-512.png")
}