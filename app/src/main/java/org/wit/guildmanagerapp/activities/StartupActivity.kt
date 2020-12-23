package org.wit.guildmanagerapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.guildmanagerapp.main.MainApp
import org.wit.guildmanagerapp.R

class StartupActivity : AppCompatActivity(), AnkoLogger {
    lateinit var app: MainApp
    private lateinit var signupButton: Button
    private lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)
        info("Landing Scene loaded")
        app = application as MainApp
        signupButton = findViewById(R.id.signupButtonStartup)
        loginButton = findViewById(R.id.loginButtonStartup)


        loginButton.setOnClickListener{
            info("Startup scene to login scene button pressed")
            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
            finish()
        }

        signupButton.setOnClickListener{
            info("Startup scene to sign-up scene button pressed")
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }










    }





}