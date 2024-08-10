package com.example.firebaseloginapp_02.module.onboard.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel :ViewModel() {


    var emailTxtlive  = MutableLiveData<String>("")
    var passwordTxtLive = MutableLiveData<String>("")
    var clickLoginLive = MutableLiveData<Unit>()
    var clickForgetPassLive = MutableLiveData<Unit>()
    var clickSignupLive = MutableLiveData<Unit>()
    var clickGoogleLive = MutableLiveData<Unit>()
    var clickFaceBookLive = MutableLiveData<Unit>()
    var clickAppleLive = MutableLiveData<Unit>()


    fun onClickLoginBtn(){
        clickLoginLive.value = Unit
    }

    fun onClickForgetPassBtn(){
        clickForgetPassLive.value = Unit
    }

    fun onClickSignUPBtn(){
        clickSignupLive.value = Unit
    }

    fun onClickGoogleBtn(){
        clickGoogleLive.value = Unit
    }

    fun onClickFacebookBtn(){
        clickFaceBookLive.value = Unit
    }

    fun onClickAppleBtn(){
        clickAppleLive.value = Unit
    }



}