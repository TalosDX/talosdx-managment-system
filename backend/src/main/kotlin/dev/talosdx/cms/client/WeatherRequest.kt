package dev.talosdx.cms.client


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherRequest(
    val lat: Double,
    val lon: Double,
    val appid: String,
    //fixme написать десериализатор из списка в строку
    val exclude: String,
    val units: WeatherUnits,
    val lang: WeatherLang,
)

@Serializable
enum class WeatherUnits {
    @SerialName("standard")
    STANDARD,

    @SerialName("metric")
    METRIC,

    @SerialName("imperial")
    IMPERIAL
}

@Serializable
enum class WeatherLang(
    val code: String,
    val alias: List<String> = emptyList(),
) {
    @SerialName("af")
    AFRIKAANS("af"),

    @SerialName("al")
    ALBANIAN("al"),

    @SerialName("ar")
    ARABIC("ar"),

    @SerialName("az")
    AZERBAIJANI("az"),

    @SerialName("bg")
    BULGARIAN("bg"),

    @SerialName("ca")
    CATALAN("ca"),

    @SerialName("cz")
    CZECH("cz"),

    @SerialName("da")
    DANISH("da"),

    @SerialName("de")
    GERMAN("de"),

    @SerialName("el")
    GREEK("el"),

    @SerialName("en")
    ENGLISH("en"),

    @SerialName("eu")
    BASQUE("eu"),

    @SerialName("fa")
    PERSIAN_FARSI("fa"),

    @SerialName("fi")
    FINNISH("fi"),

    @SerialName("fr")
    FRENCH("fr"),

    @SerialName("gl")
    GALICIAN("gl"),

    @SerialName("he")
    HEBREW("he"),

    @SerialName("hi")
    HINDI("hi"),

    @SerialName("hr")
    CROATIAN("hr"),

    @SerialName("hu")
    HUNGARIAN("hu"),

    @SerialName("id")
    INDONESIAN("id"),

    @SerialName("it")
    ITALIAN("it"),

    @SerialName("ja")
    JAPANESE("ja"),

    @SerialName("kr")
    KOREAN("kr"),

    @SerialName("la")
    LATVIAN("la"),

    @SerialName("lt")
    LITHUANIAN("lt"),

    @SerialName("mk")
    MACEDONIAN("mk"),

    @SerialName("no")
    NORWEGIAN("no"),

    @SerialName("nl")
    DUTCH("nl"),

    @SerialName("pl")
    POLISH("pl"),

    @SerialName("pt")
    PORTUGUESE("pt"),

    @SerialName("pt_br")
    PORTUGUES_BRASIL("pt_br"),

    @SerialName("ro")
    ROMANIAN("ro"),

    @SerialName("ru")
    RUSSIAN("ru"),

    @SerialName("sv")
    SWEDISH("sv", listOf("se")),

    @SerialName("sk")
    SLOVAK("sk"),

    @SerialName("sl")
    SLOVENIAN("sl"),

    @SerialName("sp")
    SPANISH("sp", listOf("es")),

    @SerialName("sr")
    SERBIAN("sr"),

    @SerialName("th")
    THAI("th"),

    @SerialName("tr")
    TURKISH("tr"),

    @SerialName("ua")
    UKRAINIAN("ua", listOf("uk")),

    @SerialName("vi")
    VIETNAMESE("vi"),

    @SerialName("zh_cn")
    CHINESE_SIMPLIFIED("zh_cn"),

    @SerialName("zh_tw")
    CHINESE_TRADITIONAL("zh_tw"),

    @SerialName("zu")
    ZULU("zu"),

}
