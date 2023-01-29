package com.seiki.android.docholder.screens.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.seiki.android.docholder.databinding.ActivityMainBinding
import com.seiki.android.docholder.screens.work.WorkActivity
import java.util.concurrent.Executor


class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this@MainActivity, executor, object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)

                bind.authStatusTv.text = "Ошибка авторизации: $errString"
            }


            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                startWork()
                Toast.makeText(this@MainActivity, "Авторизация успешно пройдена!", Toast.LENGTH_SHORT).show()

            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()

                bind.authStatusTv.text = "Авторизация не пройдена"
                Toast.makeText(this@MainActivity, "Авторизация не пройдена", Toast.LENGTH_SHORT).show()
            }
        })


        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Вход в DocumentHolder")
            .setSubtitle("Авторизируйтесь по лицу, или отпечатку")
            .setNegativeButtonText("Отмена")
            .build()


        bind.authBtn.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

        biometricPrompt.authenticate(promptInfo)

    }
    private fun startWork(){
        val toWork = Intent(this, WorkActivity::class.java)
        startActivity(toWork)
        finish()
    }
}