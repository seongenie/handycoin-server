package com.seongenie.handycoin.collector.exchange.cryptopia

import com.google.gson.annotations.SerializedName

class CryptopiaContext<T> (
        @SerializedName("Success") var success: Boolean,
        @SerializedName("Message") var message: String,
        @SerializedName("Data") var data : List<T>
)


class CryptopiaCurrency (
//    Id : Int?,
//    Name : String?,
//    Symbol : String?,
//    Algorithm : String?,
//    WithdrawFee: Double?,
//    MinWithdraw : Double?,
//    MinBaseTrade : Double?,
//    IsTipEnabled: Boolean?,
//    MinTip: Double?,
//    DepositConfirmations: Int?,
//    Status: String?,
//    StatusMessage: String?,
//    ListingStatus: String?
)

data class CryptopiaMarket (
    @SerializedName("TradePairId") var tradePairId: Int?,
    @SerializedName("Label") var label: String,
    @SerializedName("AskPrice") var askPrice: Double?,
    @SerializedName("BidPrice") var bidPrice: Double?,
    @SerializedName("Low") var low: Double?,
    @SerializedName("High") var high: Double?,
    @SerializedName("Volume") var volume: Double?,
    @SerializedName("LastPrice") var lastPrice: Double?,
    @SerializedName("BuyVolume") var buyVolume: Double?,
    @SerializedName("SellVolume") var sellVolume: Double?,
    @SerializedName("Change") var change: Double?,
    @SerializedName("Open") var open: Double?,
    @SerializedName("Close") var close: Double?,
    @SerializedName("BaseVolume") var baseVolume: Double?,
    @SerializedName("BaseBuyVolume") var baseBuyVolume: Double?,
    @SerializedName("BaseSellVolume") var baseSellVolume: Double?
)

