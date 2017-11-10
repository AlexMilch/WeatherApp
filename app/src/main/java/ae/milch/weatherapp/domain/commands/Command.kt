package ae.milch.weatherapp.domain.commands

interface Command<out T> {
    fun execute(): T
}