package ae.milch.weatherapp.domain.commands

import ae.milch.weatherapp.data.ForecastRequest
import ae.milch.weatherapp.domain.mappers.ForecastDataMapper
import ae.milch.weatherapp.domain.model.ForecastList

class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}