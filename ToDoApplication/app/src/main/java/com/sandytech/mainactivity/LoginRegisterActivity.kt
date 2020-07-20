package com.sandytech.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_register.*

class LoginRegisterActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        //dealing with action
        supportActionBar?.title = "Login And Register"


        findViewById<EditText>(R.id.etEmail)
        findViewById<EditText>(R.id.etPassword)

        //FIREBASE INSTANCE
        mAuth = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()

        if(mAuth.currentUser != null) {
            val intent = Intent(this, ToDoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun actionLogin (v: View) {

        mAuth.signInWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
            .addOnCompleteListener(this@LoginRegisterActivity) {

                task ->

                if(task.isSuccessful) {
                    Toast.makeText(this, "Login Successful" + task.exception, Toast.LENGTH_LONG).show()
                    val intent = Intent(this, ToDoActivity::class.java)
                    startActivity(intent)
                }

                else {
                    Toast.makeText(this, "Auth Failed : " + task.exception, Toast.LENGTH_LONG).show()
                }

            }
    }

    fun actionRegister (v: View) {

        mAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
            .addOnCompleteListener(this@LoginRegisterActivity) {
                task ->

                if(task.isComplete) {
                    Toast.makeText(this, "Account is created. Please Login now.", Toast.LENGTH_LONG).show()
                }

                else {
                    Toast.makeText(this, "Auth Failed : " + task.exception, Toast.LENGTH_LONG).show()
                }
            }


    }
}