package dev.talosdx.cms.client.openweathermap.v3

import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange


@HttpExchange("data/3.0/")
interface WeatherClient {

    @GetExchange("onecall")
    fun getWeather(weatherRequest: WeatherRequest): WeatherResponse
}