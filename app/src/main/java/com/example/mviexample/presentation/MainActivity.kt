package com.example.mviexample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mviexample.R
import com.example.mviexample.presentation.screen.GetCoinsFragment

class MainActivity : AppCompatActivity() {

    val coinsFragment = GetCoinsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, coinsFragment, "get_coins_fragment")
            .commit()
    }
}