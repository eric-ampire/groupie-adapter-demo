package com.zxconnect.android.demo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        buttonConnexion.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty()) {
                editTextEmail.error = "Email invalid"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Mot de passe invalid"
                return@setOnClickListener
            }

            val auth = FirebaseAuth.getInstance()

            progressBar.isVisible = true
            val task = auth.createUserWithEmailAndPassword(email, password)

            task.addOnFailureListener {
                Toast.makeText(baseContext, it.message, Toast.LENGTH_LONG).show()
                progressBar.isVisible = false
            }

            task.addOnSuccessListener {
                Toast.makeText(baseContext, "Operation Sucess", Toast.LENGTH_LONG).show()
                progressBar.isVisible = false
            }
        }
    }
}
