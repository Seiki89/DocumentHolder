package com.seiki.android.docholder.screens.main

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.seiki.android.docholder.LOG
import com.seiki.android.docholder.databinding.ActivityMainBinding
import com.seiki.android.docholder.model.SettModel
import com.seiki.android.docholder.screens.work.WorkActivity
import com.seiki.android.docholder.screens.work.settings.SettingsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executor


class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private var listSettModel = listOf<SettModel>()
    private val viewModelSet = SettingsViewModel(Application())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        viewModel.initDataBase()
        viewModelSet.getAllSett().observe(this) { listSettModel = it }

        var pass = 0
        var passSwitch = false
        var bioSwitch = false

        CoroutineScope(Dispatchers.Main).launch {
            delay(100)}.invokeOnCompletion {

            if (listSettModel.isEmpty()){startWork()}

            listSettModel.forEach {
                pass = it.pass
                passSwitch = it.switchPass
                bioSwitch = it.switchBio
            }
            if (!passSwitch && !bioSwitch) { startWork() }
            if (!passSwitch) { bind.passCard.visibility = View.GONE}
            if (!bioSwitch) { bind.bioCard.visibility = View.GONE}

            if (!passSwitch && bioSwitch) { bioAuth() }
       }

        bind.btnOkPass.setOnClickListener {
            if (pass.toString() == bind.txtPass.text.toString()){
                startWork()
            }else{
                Toast.makeText(this@MainActivity,"Неверный пароль",Toast.LENGTH_LONG).show()
            }
        }
        bind.authBtn.setOnClickListener { bioAuth() }





    }

    private fun bioAuth() {
        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this@MainActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)

                    bind.authStatusTv.text = "Ошибка авторизации: $errString"
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    startWork()
                    Toast.makeText(this@MainActivity,
                        "Авторизация успешно пройдена!",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()

                    bind.authStatusTv.text = "Авторизация не пройдена"
                    Toast.makeText(this@MainActivity, "Авторизация не пройдена", Toast.LENGTH_SHORT)
                        .show()
                }
            })


        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Вход в DocumentHolder")
            .setSubtitle("Авторизируйтесь по лицу, или отпечатку")
            .setNegativeButtonText("Отмена")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun startWork(){
        val toWork = Intent(this, WorkActivity::class.java)
        startActivity(toWork)
        finish()
    }
}