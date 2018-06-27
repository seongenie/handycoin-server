package com.seongenie.handycoin.service

import com.seongenie.handycoin.controller.basicCoin.BasicCoinView
import com.seongenie.handycoin.domain.BasicCoin
import com.seongenie.handycoin.domain.BasicCoinRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BasicCoinService {

    @Autowired
    lateinit var repository : BasicCoinRepository

    fun createBasicCoin(basicCoinView : BasicCoinView) : BasicCoinView {
        var basicCoin : BasicCoin = BasicCoin()
        BeanUtils.copyProperties(basicCoinView, basicCoin)
        return BasicCoinView(repository.save(basicCoin))
    }

    fun getBasicCoinList() : List<BasicCoinView> {
        var basicCoinList : List<BasicCoin> = repository.findAll()
        return basicCoinList.map { basicCoin -> BasicCoinView(basicCoin) }
    }

}