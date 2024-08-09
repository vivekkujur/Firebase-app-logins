package com.example.firebaseloginapp_02.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide



@BindingAdapter("setGlideImage")
fun ImageView.setGlideImage(url:Any?){
    url?.let{
        Glide.with(this.context)
            .load(url ?: "")
            .circleCrop()
            .into(this)
    }
}


