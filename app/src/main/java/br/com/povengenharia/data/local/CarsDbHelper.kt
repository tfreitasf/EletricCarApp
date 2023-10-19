package br.com.povengenharia.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.povengenharia.data.local.CarsContract.SQL_DELETE_ENTRYS
import br.com.povengenharia.data.local.CarsContract.TABLE_CAR


class CarsDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null ,DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CAR)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRYS)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "DbCar.db"
    }
}

