package com.example.retrofiteweatherapi

import java.io.Serializable

class Picture(val text: String, val image: Int, val isCheck: Boolean): Serializable {

    companion object {
        val pictureList = listOf(
            Picture(
                "Прогноз погоды",
                R.drawable.first,
                true
            ),
            Picture(
                "Введите Ваш город или выберите автоопределение местоположения",
                R.drawable.second,
                false
            )
        )
    }
}