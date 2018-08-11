package com.seongenie.handycoin

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
}
