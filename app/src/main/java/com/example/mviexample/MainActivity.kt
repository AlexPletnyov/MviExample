package com.example.mviexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mviexample.databinding.ActivityMainBinding
import com.example.mviexample.domain.GetCoinsFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, GetCoinsFragment())
            addToBackStack("get_coins_fragment")
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}