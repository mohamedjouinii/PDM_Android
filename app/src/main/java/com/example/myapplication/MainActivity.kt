package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.snackbar.Snackbar

class MainActivity : ComponentActivity() {



    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
       installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textForgetPassword.setOnClickListener {
           Snackbar.make(it, "Forget Password soon", Snackbar.LENGTH_LONG).show()
        }

        binding.button.setOnClickListener(){
            Snackbar.make(it, "Login soon", Snackbar.LENGTH_LONG).show()
        }
    }



}
