package com.seongenie.handycoin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan("com.seongenie.handycoin")
@EnableJpaRepositories(basePackages = ["com.seongenie.handycoin.model"])
@EntityScan(basePackages = ["com.seongenie.handycoin.model"])
class HandyCoinApplication
fun main(args: Array<String>) {
//    var context : ConfigurableApplicationContext =
    SpringApplication.run(HandyCoinApplication::class.java, *args)
}
