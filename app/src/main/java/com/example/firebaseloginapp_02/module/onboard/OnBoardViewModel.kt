package com.example.firebaseloginapp_02.module.onboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseloginapp_02.R

class OnBoardViewModel :ViewModel() {


    var onClickLoginLive  = MutableLiveData<Unit>()
    var onclickText = MutableLiveData<String>("login")
    var roundAppIcon = R.mipmap.ic_launcher_round


    fun loginOnClicked(){
        onclickText.value = "login button clicked "
        Log.d("buttonclicked ", "onCreateView: function call ")
        onClickLoginLive.value = Unit

    }
}