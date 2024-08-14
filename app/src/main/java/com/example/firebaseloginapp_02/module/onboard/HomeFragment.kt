package com.example.firebaseloginapp_02.module.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firebaseloginapp_02.R
import com.example.firebaseloginapp_02.databinding.FragmentHomeBinding
import com.example.firebaseloginapp_02.module.onboard.viewModels.HomeViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

   lateinit var homeViewModel:HomeViewModel
   lateinit var  binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        with(homeViewModel){
            binding.model = this
            binding.lifecycleOwner = this@HomeFragment

            logoutClickLive.observe(viewLifecycleOwner, Observer {
                logout()
            })
        }

        return  binding.root

    }

    fun logout(){
        var pref =  requireActivity().getSharedPreferences("pref",0)
        var editor= pref.edit()
        editor.clear()
        pref.edit().apply()

        Firebase.auth.signOut()

requireActivity().onBackPressed()
    }

}