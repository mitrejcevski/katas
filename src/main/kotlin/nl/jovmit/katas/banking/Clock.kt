package nl.jovmit.katas.banking

import java.time.LocalDate
import java.time.format.DateTimeFormatter

open class Clock {

    private companion object {
        private val DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }

    open fun todayAsString(): String {
        return today().format(DD_MM_YYYY)
    }

    protected open fun today(): LocalDate = LocalDate.now()
}
