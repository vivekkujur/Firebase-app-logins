package com.example.firebaseloginapp_02.module.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firebaseloginapp_02.R
import com.example.firebaseloginapp_02.databinding.FragmentLoginBinding
import com.example.firebaseloginapp_02.databinding.FragmentOnboardBinding
import com.example.firebaseloginapp_02.module.onboard.viewModels.LoginViewModel
import com.example.firebaseloginapp_02.module.onboard.viewModels.OnBoardViewModel

class LoginFragment : Fragment() {

    lateinit var  loginViewModel: LoginViewModel
    var  binding: FragmentLoginBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        with(loginViewModel){

            binding?.model = this
            binding?.lifecycleOwner =  requireActivity()

            clickLoginLive.observe(requireActivity() , Observer {
                Toast.makeText(requireActivity(),"Login", Toast.LENGTH_SHORT).show();
                findNavController().navigate(R.id.homeFragment)
            })

            clickForgetPassLive.observe(requireActivity() , Observer {
                Toast.makeText(requireActivity(),"forgot password clicked", Toast.LENGTH_SHORT).show();
            })
            clickSignupLive.observe(requireActivity() , Observer {
                Toast.makeText(requireActivity(),"Signup", Toast.LENGTH_SHORT).show();
            })
            clickGoogleLive.observe(requireActivity() , Observer {
                Toast.makeText(requireActivity(),"Google", Toast.LENGTH_SHORT).show();
            })
            clickAppleLive.observe(requireActivity() , Observer {
                Toast.makeText(requireActivity(),"Apple", Toast.LENGTH_SHORT).show();
            })
            clickFaceBookLive.observe(requireActivity() , Observer {
                Toast.makeText(requireActivity(),"Facebook", Toast.LENGTH_SHORT).show();
            })

        }

        return binding?.root
    }


}