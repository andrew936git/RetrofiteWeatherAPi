package com.example.retrofiteweatherapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val adapter = ViewPagerAdapter(this, Picture.pictureList)
        val viewPagerVP = findViewById<ViewPager2>(R.id.viewPager)
        viewPagerVP.adapter = adapter

    }
}