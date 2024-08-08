package com.example.firebaseloginapp_02.module.onboard

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseloginapp_02.R
import com.example.firebaseloginapp_02.databinding.ActivityOnboardBinding
import com.example.firebaseloginapp_02.utils.MainViewModelFactory

class OnboardActivity : AppCompatActivity() {

    lateinit var  onboardViewModel : OnBoardViewModel
    lateinit var  binding: ActivityOnboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this,R.layout.activity_onboard)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        onboardViewModel = ViewModelProvider(
            this,
            MainViewModelFactory()
        )[OnBoardViewModel::class.java]

//        mainViewModel.quoteLiveData.observe(this, Observer {
//
//            Log.d("LiveData String data", "onCreate: $it ")
//
//        })

        with(onboardViewModel){

            //  bind to xml ui
            binding.model = this
            binding.lifecycleOwner = this@OnboardActivity





        }


    }
}