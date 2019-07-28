package com.seongenie.handycoin.collector.exchange.upbit

import com.google.gson.annotations.SerializedName


data class UpbitMarket (
        @SerializedName("market") var market : String,
        @SerializedName("korean_name") var coinKor : String,
        @SerializedName("english_name") var coin : String
)


data class UpbitTicker (
        @SerializedName("market") var market : String,
        @SerializedName("trade_date") var tradeDate : String,
        @SerializedName("trade_time") var tradeTime : String,
        @SerializedName("trade_timestamp") var tradeTimeStamp : Long,
        @SerializedName("opening_price") var openingPrice : Double,
        @SerializedName("high_price") var highPrice : Double,
        @SerializedName("low_price") var lowPrice : Double,
        @SerializedName("trade_price") var tradePrice : Double,
        @SerializedName("prev_closing_price") var prevClosingPrice : Double,
        @SerializedName("change") var change : String,
        @SerializedName("change_price") var changePrice : Double,
        @SerializedName("change_rate") var changeRate : Double,
        @SerializedName("signed_change_price") var signedChangePrice : Double,
        @SerializedName("signed_change_rate") var signedChangeRate : Double,
        @SerializedName("trade_volume") var tradeVolume : Double,
        @SerializedName("acc_trade_volume") var tradeVolume24h : Double,
        @SerializedName("acc_trade_price_24h") var tradePrice24h : Double,
        var currency : String
)
