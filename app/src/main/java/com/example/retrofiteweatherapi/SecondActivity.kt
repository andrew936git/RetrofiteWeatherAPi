package com.example.retrofiteweatherapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.retrofiteweatherapi.utils.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException



class SecondActivity : AppCompatActivity() {

    private var city = intent?.getStringExtra("city")
    private lateinit var toolbar: Toolbar
    private lateinit var tempTV: TextView
    private lateinit var minTempTV: TextView
    private lateinit var maxTempTV: TextView
    private lateinit var directionTV: TextView
    private lateinit var speedTV: TextView
    private lateinit var humidityTV: TextView
    private lateinit var pressureTV: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        init()
    }

    private fun init(){
        city = intent?.getStringExtra("city")
        toolbar = findViewById(R.id.toolbar)
        toolbar.apply {
            title = city
            setNavigationIcon(R.drawable.ic_exit)
            setNavigationOnClickListener {
                finishAffinity()
                finish()
            }
        }
        tempTV = findViewById(R.id.tempTV)
        minTempTV = findViewById(R.id.minTempTV)
        maxTempTV = findViewById(R.id.maxTempTV)
        directionTV = findViewById(R.id.directionTV)
        speedTV = findViewById(R.id.speedTV)
        humidityTV = findViewById(R.id.humidityTV)
        pressureTV = findViewById(R.id.pressureTV)
        imageView = findViewById(R.id.imageView)

        getCurrentWeather()
    }

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather(
                    city!!,
                    "metric",
                    application.getString(R.string.api_key)
                )
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_LONG).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(applicationContext, "http error ${e.message}", Toast.LENGTH_LONG).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    val data = response.body()
                    tempTV.text = "${data?.main?.temp}°C"
                    minTempTV.text = "${data?.main?.temp_min}°C"
                    maxTempTV.text = "${data?.main?.temp_max}°C"
                    directionTV.text = "${data?.wind?.deg}°"
                    speedTV.text = "${data?.wind?.speed}m/s"
                    humidityTV.text = "${data?.main?.humidity}%"
                    val convertPressure = (data?.main?.pressure?.div(1.33))?.toInt()
                    pressureTV.text = "$convertPressure mm Hg"
                    val iconId = data?.weather?.get(0)?.icon
                    val imageUrl = "https://openweathermap.org/img/wn/$iconId@4x.png"
                    Picasso.get().load(imageUrl).into(imageView)
                }
            }
        }
    }
}