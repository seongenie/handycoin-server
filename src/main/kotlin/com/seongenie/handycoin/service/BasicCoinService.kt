package com.seongenie.handycoin.service

import com.seongenie.handycoin.controller.basicCoin.BasicCoinView
import com.seongenie.handycoin.domain.BasicCoin
import com.seongenie.handycoin.domain.BasicCoinRepository
import com.seongenie.handycoin.domain.infra.BasicService
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired

class BasicCoinService : BasicService {

    @Autowired
    lateinit var repository : BasicCoinRepository

    fun createBasicCoin(basicCoinView : BasicCoinView) {
        var basicCoin : BasicCoin = BasicCoin()
        BeanUtils.copyProperties(basicCoinView, basicCoin)
        repository.addBasicCoin(basicCoin)
    }

    fun getBasicCoinList() : List<BasicCoinView> {
        var basicCoinList : List<BasicCoin> = repository.findAll()
        return basicCoinList.map { basicCoin -> BasicCoinView(basicCoin) }
    }

}