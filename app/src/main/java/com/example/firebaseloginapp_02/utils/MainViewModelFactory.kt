package com.example.firebaseloginapp_02.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseloginapp_02.module.onboard.OnBoardViewModel

class MainViewModelFactory:ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnBoardViewModel() as T
    }
}