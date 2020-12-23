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

class SignupActivity: AppCompatActivity(), AnkoLogger {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signupButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        info("Signup page loaded")

        auth = FirebaseAuth.getInstance()
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        signupButton = findViewById(R.id.signUpButton)
        loginButton = findViewById(R.id.loginButton)

        signupButton.setOnClickListener {
            var email: String = emailEditText.text.toString()
            var password: String = passwordEditText.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Fill Fields to Proceed", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,
                    OnCompleteListener { task ->
                        if(task.isSuccessful) {
                            Toast.makeText(this, "Thank you for Registering!", Toast.LENGTH_SHORT)
                                .show()
                            info("successful sign-up")
                            /*
                           todo: put in scene change here for sucessful signup
                             */

                            finish()
                        } else {
                            info("sign-up failed")
                            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }

        loginButton.setOnClickListener{
            info("sign-up to login scene switch")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }




    }


}