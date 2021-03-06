package ae.milch.weatherapp.domain.mappers

import ae.milch.weatherapp.data.Forecast
import ae.milch.weatherapp.data.ForecastResult
import ae.milch.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import ae.milch.weatherapp.domain.model.Forecast as ModelForecast

class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList =
            ForecastList(forecast.city.name, forecast.city.country,
                    convertForecastListToDomain(forecast.list))

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> =
            list.mapIndexed { i, forecast ->
                val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
                convertForecastItemToDomain(forecast.copy(dt = dt))
            }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast =
            ModelForecast(convertDate(forecast.dt),
                    forecast.weather[0].description,
                    forecast.temp.max.toInt(),
                    forecast.temp.min.toInt(),
                    generateUrl(forecast.weather[0].icon))

    private fun generateUrl(iconCode: String): String =
            "http://openweathermap.org/img/w/$iconCode.png"

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }
}