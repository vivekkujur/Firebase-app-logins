package com.example.firebaseloginapp_02.module.onboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    lateinit var  loginViewModel: LoginViewModel
    var  binding: FragmentLoginBinding? = null

    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth
    // ...
// Initialize Firebase Auth
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
                signInGoogle()

            })
            clickAppleLive.observe(requireActivity() , Observer {
                Toast.makeText(requireActivity(),"Apple", Toast.LENGTH_SHORT).show();
            })
            clickFaceBookLive.observe(requireActivity() , Observer {
                Toast.makeText(requireActivity(),"Facebook", Toast.LENGTH_SHORT).show();
            })

        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("346283658022-4tc2rlbd1etrd0rrjtm14cgcjitvdpha.apps.googleusercontent.com")
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        firebaseAuth = FirebaseAuth.getInstance()


        return binding?.root
    }


    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

    // onActivityResult() function : this is where
    // we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)

            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Log.d("GooglezLogin", "UpdateUI: $e")
        }
    }

    // this is where we update the UI after Google signin takes place
    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
//                SavedPreference.setEmail(this, account.email.toString())
//                SavedPreference.setUsername(this, account.displayName.toString())
                 Log.d("GooglezLogin", "displayName: ${account.email.toString()} - ${account.displayName.toString()} - ${account.idToken}")
            }
        }
    }

    override fun onStart() {
        super.onStart()

    }


}