package com.example.firebaseloginapp_02.module.onboard.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel:ViewModel() {

    var logoutClickLive = MutableLiveData<Unit>()

    fun logoutBtnClicked(){

        logoutClickLive.value = Unit
    }
}