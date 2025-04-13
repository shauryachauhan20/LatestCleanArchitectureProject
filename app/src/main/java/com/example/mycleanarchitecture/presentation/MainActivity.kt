package com.example.mycleanarchitecture.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mycleanarchitecture.R
import com.example.mycleanarchitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.cardviewQuote.setOnClickListener {
            val intent = Intent(this,QuoteActivity::class.java)
            startActivity(intent)
        }

        binding.cardviewQuoteDi.setOnClickListener {
            val intent = Intent(this,QuoteActivityDI::class.java)
            startActivity(intent)
        }
    }
}