package dev.talosdx.cms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableCaching
@SpringBootApplication
class KotlinCmsApplication

fun main(args: Array<String>) {
    runApplication<KotlinCmsApplication>(*args)
}
