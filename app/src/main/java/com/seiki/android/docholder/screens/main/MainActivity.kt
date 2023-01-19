package com.seiki.android.docholder.screens.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.ActivityMainBinding
import com.seiki.android.docholder.screens.work.WorkActivity

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.button.setOnClickListener {
            val toWork = Intent(this, WorkActivity::class.java)
            startActivity(toWork)
            finish()
        }

    }
}