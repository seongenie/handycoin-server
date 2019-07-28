package com.seongenie.handycoin.service

import com.seongenie.handycoin.controller.baseCoin.BaseCoinView
import com.seongenie.handycoin.controller.baseCoin.ExchangeCoinResponse
import com.seongenie.handycoin.model.BaseCoin
import com.seongenie.handycoin.model.BaseCoinRepository
import com.seongenie.handycoin.model.infra.BaseService
import com.seongenie.handycoin.infra.BaseException
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BaseCoinService : BaseService() {

    @Autowired
    lateinit var repository : BaseCoinRepository

    fun createBaseCoin(baseCoinView : BaseCoinView) {
        var baseCoin : BaseCoin = BaseCoin()
        BeanUtils.copyProperties(baseCoinView, baseCoin)
        repository.addBaseCoin(baseCoin)
    }

    fun getBaseCoinList() : ExchangeCoinResponse {
        var result = ExchangeCoinResponse()
        var baseCoinList : List<BaseCoin> = repository.findAll()
        baseCoinList.forEach { baseCoin ->
            if(result.data.containsKey(baseCoin.exchange)) {
                result.data.get(baseCoin.exchange)?.add(baseCoin.coin)
            } else {
                result.data.put(baseCoin.exchange, arrayListOf(baseCoin.coin))
            }
        }
        return result
    }

    fun getBaseCoins(exchange : String) : List<BaseCoin> {
        val baseCoins = repository.findCoins(exchange)
        return if(baseCoins.isNotEmpty()) baseCoins else throw BaseException()
    }

    fun getBaseExchanges(coin : String) : List<BaseCoin> {
        return repository.findExchanges(coin)
    }
}