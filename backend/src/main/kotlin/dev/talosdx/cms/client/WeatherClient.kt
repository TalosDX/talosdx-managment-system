package dev.talosdx.cms.client


interface WeatherClient {

    fun getWeather(weatherRequest: WeatherRequest): WeatherResponse
}