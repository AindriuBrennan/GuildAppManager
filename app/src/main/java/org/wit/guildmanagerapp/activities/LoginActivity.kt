package org.wit.guildmanagerapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.guildmanagerapp.R

class LoginActivity: AppCompatActivity(), AnkoLogger {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        info("login page loaded")


        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.loginButton)
        signupButton = findViewById(R.id.signUpButton)
        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener{
            var email: String = emailEditText.text.toString()
            var password: String = passwordEditText.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@LoginActivity, "Fields can't be empty", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener {task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
                        info("successful login")
                        /*
                        todo: put in scene change here for sucessful login
                         */
                    } else {
                        Toast.makeText(this,"Login Details Incorrect", Toast.LENGTH_LONG).show()
                        info("login failed")
                    }
                })
            }
        }

        signupButton.setOnClickListener{
            info("login to sign-up scene switch")
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}