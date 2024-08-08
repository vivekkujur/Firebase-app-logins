package com.example.firebaseloginapp_02.module.homepage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaseloginapp_02.R
import com.example.firebaseloginapp_02.databinding.ActivityOnboardBinding
import com.example.firebaseloginapp_02.module.onboard.OnBoardViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    }
}