package br.com.povengenharia.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import br.com.povengenharia.data.local.CarsContract.CarEntry.COLUMN_NAME_BATTERY
import br.com.povengenharia.data.local.CarsContract.CarEntry.COLUMN_NAME_CAR_ID
import br.com.povengenharia.data.local.CarsContract.CarEntry.COLUMN_NAME_HORSEPOWER
import br.com.povengenharia.data.local.CarsContract.CarEntry.COLUMN_NAME_MODEL
import br.com.povengenharia.data.local.CarsContract.CarEntry.COLUMN_NAME_PRICE
import br.com.povengenharia.data.local.CarsContract.CarEntry.COLUMN_NAME_RECHARGE
import br.com.povengenharia.data.local.CarsContract.CarEntry.COLUMN_NAME_URL
import br.com.povengenharia.domain.Car

class CarRepository(private val context: Context) {

    fun save(car: Car): Boolean {
        var isSaved = false
        try {

            findCarById(1)

            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(COLUMN_NAME_CAR_ID, car.id)
                put(COLUMN_NAME_MODEL, car.modelName)
                put(COLUMN_NAME_PRICE, car.price)
                put(COLUMN_NAME_BATTERY, car.battery)
                put(COLUMN_NAME_HORSEPOWER, car.horsePower)
                put(COLUMN_NAME_RECHARGE, car.recharge)
                put(COLUMN_NAME_URL, car.urlPhoto)
            }

            val inserted = db?.insert(CarsContract.CarEntry.TABLE_NAME, null, values)

            if (inserted != null) {
                isSaved = true
            }

        } catch (ex: Exception) {
            ex.message?.let {
                Log.e("Erro ao inserir -> ", it)
            }
        }
        return isSaved

    }

    fun findCarById(id: Int): Car {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase

        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_MODEL,
            COLUMN_NAME_PRICE,
            COLUMN_NAME_BATTERY,
            COLUMN_NAME_HORSEPOWER,
            COLUMN_NAME_RECHARGE,
            COLUMN_NAME_URL
        )

        val filter = "$COLUMN_NAME_CAR_ID = ?"
        val filterValues = arrayOf(id.toString())

        val cursos = db.query(
            CarsContract.CarEntry.TABLE_NAME,
            columns,
            filter,
            filterValues,
            null,
            null,
            null

        )
        var itemId: Long = 0
        var modelName = ""
        var price = ""
        var battery = ""
        var horsePower = ""
        var recharge = ""
        var urlPhoto = ""


        with(cursos) {

            while (moveToNext()) {
                itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                Log.d("ID -> ", itemId.toString())
                modelName = getString(getColumnIndexOrThrow(COLUMN_NAME_MODEL))
                Log.d("model -> ", modelName)
                price = getString(getColumnIndexOrThrow(COLUMN_NAME_PRICE))
                Log.d("preco -> ", price)
                battery = getString(getColumnIndexOrThrow(COLUMN_NAME_BATTERY))
                Log.d("bateria -> ", battery)
                horsePower = getString(getColumnIndexOrThrow(COLUMN_NAME_HORSEPOWER))
                Log.d("potencia -> ", horsePower)
                recharge = getString(getColumnIndexOrThrow(COLUMN_NAME_RECHARGE))
                Log.d("recarga -> ", recharge)
                urlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL))
                Log.d("url -> ", urlPhoto)

            }

        }
        cursos.close()
        return Car(
            id = itemId.toInt(),
            modelName = modelName,
            price = price,
            battery = battery,
            horsePower = horsePower,
            recharge = recharge,
            urlPhoto = urlPhoto,
            isFavorite = true
        )


    }

    fun saveIfNotExist(car: Car){
        val car = findCarById(car.id)
        if (car.id == ID_WHEN_NO_CAR ){
            save(car)

        }


    }

    fun getAll(): List<Car>{
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase

        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_MODEL,
            COLUMN_NAME_PRICE,
            COLUMN_NAME_BATTERY,
            COLUMN_NAME_HORSEPOWER,
            COLUMN_NAME_RECHARGE,
            COLUMN_NAME_URL
        )



        val cursos = db.query(
            CarsContract.CarEntry.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null

        )

        val carros = mutableListOf<Car>()


        with(cursos) {

            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                Log.d("ID -> ", itemId.toString())
                val modelName = getString(getColumnIndexOrThrow(COLUMN_NAME_MODEL))
                Log.d("model -> ", modelName)
                val price = getString(getColumnIndexOrThrow(COLUMN_NAME_PRICE))
                Log.d("preco -> ", price)
                val battery = getString(getColumnIndexOrThrow(COLUMN_NAME_BATTERY))
                Log.d("bateria -> ", battery)
                val horsePower = getString(getColumnIndexOrThrow(COLUMN_NAME_HORSEPOWER))
                Log.d("potencia -> ", horsePower)
                val recharge = getString(getColumnIndexOrThrow(COLUMN_NAME_RECHARGE))
                Log.d("recarga -> ", recharge)
                val urlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL))
                Log.d("url -> ", urlPhoto)
                carros.add(
                    Car(
                        id = itemId.toInt(),
                        modelName = modelName,
                        price = price,
                        battery = battery,
                        horsePower = horsePower,
                        recharge = recharge,
                        urlPhoto = urlPhoto,
                        isFavorite = true
                    )
                )

            }

        }
        cursos.close()
        return carros

    }

    companion object{
        const val ID_WHEN_NO_CAR = 0
    }


}