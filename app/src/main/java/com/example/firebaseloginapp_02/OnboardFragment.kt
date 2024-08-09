package com.example.firebaseloginapp_02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseloginapp_02.databinding.FragmentOnboardBinding
import com.example.firebaseloginapp_02.module.onboard.OnBoardViewModel


class OnboardFragment : Fragment() {

    lateinit var  onboardViewModel : OnBoardViewModel
    var  binding: FragmentOnboardBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnboardBinding.inflate(inflater,container,false)

        onboardViewModel = ViewModelProvider(this).get(OnBoardViewModel::class.java)

        with(onboardViewModel){

            binding?.model = this
            binding?.lifecycleOwner =  requireActivity()

            onClickLoginLive.observe(requireActivity() , Observer {
                Toast.makeText(requireActivity(),"button clicked ",Toast.LENGTH_LONG).show();
            })

        }

        return binding?.root
    }


}