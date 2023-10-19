package br.com.povengenharia.data.local

import android.provider.BaseColumns

object CarsContract {


    object CarEntry : BaseColumns{
        const val TABLE_NAME = "car"

        const val COLUMN_NAME_CAR_ID = "car_id"
        const val COLUMN_NAME_MODEL = "modelName"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_BATTERY = "battery"
        const val COLUMN_NAME_HORSEPOWER = "horsePower"
        const val COLUMN_NAME_RECHARGE = "recharge"
        const val COLUMN_NAME_URL = "urlPhoto"

    }

    const val TABLE_CAR =
        "CREATE TABLE ${CarEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY,"+
                "${CarEntry.COLUMN_NAME_CAR_ID} TEXT," +
                "${CarEntry.COLUMN_NAME_MODEL} TEXT," +
                "${CarEntry.COLUMN_NAME_PRICE} TEXT," +
                "${CarEntry.COLUMN_NAME_BATTERY} TEXT," +
                "${CarEntry.COLUMN_NAME_HORSEPOWER} TEXT," +
                "${CarEntry.COLUMN_NAME_RECHARGE} TEXT," +
                "${CarEntry.COLUMN_NAME_URL} TEXT)"

    const val SQL_DELETE_ENTRYS =
        "DROP TABLE IF EXISTS ${CarEntry.TABLE_NAME}"


}