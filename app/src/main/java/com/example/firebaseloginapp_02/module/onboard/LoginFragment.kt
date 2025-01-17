package com.example.firebaseloginapp_02.module.onboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firebaseloginapp_02.R
import com.example.firebaseloginapp_02.databinding.FragmentLoginBinding
import com.example.firebaseloginapp_02.module.HomeActivity
import com.example.firebaseloginapp_02.module.onboard.viewModels.LoginViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginFragment : Fragment() {

    lateinit var  loginViewModel: LoginViewModel
    var  binding: FragmentLoginBinding? = null

    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth


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
            binding?.lifecycleOwner =  this@LoginFragment

            clickLoginLive.observe(viewLifecycleOwner , Observer {

                //try to  clear view model data
                it?.let{
                    Toast.makeText(requireActivity(),"Login", Toast.LENGTH_SHORT).show();
                  goToHomePage()
                }
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
                binding?.imgFacebookBtn?.performClick()
                Toast.makeText(requireActivity(),"Facebook", Toast.LENGTH_SHORT).show();
            })
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("346283658022-4tc2rlbd1etrd0rrjtm14cgcjitvdpha.apps.googleusercontent.com")
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        firebaseAuth = FirebaseAuth.getInstance()

         var  callbackManager = CallbackManager.Factory.create()
        var buttonFacebookLogin = binding?.imgFacebookBtn
        buttonFacebookLogin?.setReadPermissions("openid","email", "public_profile")
        buttonFacebookLogin?.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("GooglezLogin", "facebook:onSuccess:${loginResult.accessToken.token}")
//                handleFacebookAccessToken()

                var pref =  requireActivity().getSharedPreferences("pref",0)
                var editor= pref.edit()
                editor.putString("token", loginResult.accessToken.token.toString())
                editor.putString("email", "")
                editor.putString("name", "")
                pref.edit().apply()

                goToHomePage()

            }

            override fun onCancel() {
                Log.d("GooglezLogin", "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d("GooglezLogin", "facebook:onError", error)
                // ...
            }
        })

        return binding?.root
    }

    override fun onStop() {
        super.onStop()


    }


    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

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

               var pref =  requireActivity().getSharedPreferences("pref",0)
                var editor= pref.edit()
                editor.putString("token", account.idToken)
                editor.putString("email", account.email.toString())
                editor.putString("name", account.displayName.toString())
                pref.edit().apply()

                goToHomePage()

                 Log.d("GooglezLogin", "displayName: ${account.email.toString()} - ${account.displayName.toString()} - ${account.idToken}")
            }
        }
    }

    fun goToHomePage (){

        val myIntent: Intent = Intent(
            requireActivity(),
            HomeActivity::class.java
        )
        requireActivity().startActivity(myIntent)
    }

    override fun onStart() {
        super.onStart()

        var pref =  requireActivity().getSharedPreferences("pref",0)
        var token= pref.getString("token",null)
        if(token!=null){
            goToHomePage()
        }
    }


}