package com.seongenie.handycoin

import com.seongenie.handycoin.collector.exchange.cryptopia.Upbit
import com.seongenie.handycoin.domain.BaseCoin
import com.seongenie.handycoin.service.BaseCoinService
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan("com.seongenie.handycoin")
@EnableJpaRepositories(basePackages = arrayOf("com.seongenie.handycoin.domain"))
@EntityScan(basePackages = arrayOf("com.seongenie.handycoin.domain"))
class HandycoinApplication
fun main(args: Array<String>) {
    var context : ConfigurableApplicationContext = SpringApplication.run(HandycoinApplication::class.java, *args)
//    Cryptopia(context.getBean(BaseCoinService::class.java))

    context.getBean(Upbit::class.java).buildApiService().apply {
//        marketProcess()
        val baseCoinService = context.getBean(BaseCoinService::class.java) as BaseCoinService
        val baseCoinList : List<BaseCoin> = baseCoinService.getBaseCoins("UPBIT")

        val builder = StringBuilder()
        baseCoinList.forEach { it ->
            builder.append(it.currency)
            builder.append("-")
            builder.append(it.coin)
            builder.append(",")
        }
        builder.setLength(builder.length - 1)
        var markets = builder.toString()
        tickerProcess(markets)
    }
}
