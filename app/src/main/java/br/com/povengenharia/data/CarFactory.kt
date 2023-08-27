package br.com.povengenharia.data

import br.com.povengenharia.domain.Car

object CarFactory {

    val list = listOf(
        Car(
            1,
            "R$ 300.000,00",
            "300 kWh",
            "300 CV",
            "30 minutos",
            "www.google.com.br"
        ), Car(
            2,
            "R$ 300.000,00",
            "300 kWh",
            "300 CV",
            "30 minutos",
            "www.google.com.br"
        )

    )
}