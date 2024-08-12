package com.example.firebaseloginapp_02.module.homepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseloginapp_02.R
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

    }
}