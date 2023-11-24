package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplication.Network.Reclamation_interface
import com.example.myapplication.Network.RetrofitBuilder
import com.example.myapplication.databinding.ActivityReclamationBinding
import com.example.myapplication.models.Reclamation
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Reclamation : ComponentActivity() {

    private lateinit var binding: ActivityReclamationBinding
    private lateinit var reclamationInterface: Reclamation_interface
    private lateinit var nom: EditText
    private lateinit var prenom: EditText
    private lateinit var emaill: EditText
    private lateinit var description: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityReclamationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        reclamationInterface = RetrofitBuilder.create()
        emaillFocusListener()
        nameFocusListener()
        PrenomFocusListener()
        descriptionFocusListener()
        binding.button2.setOnClickListener() {


            submitForm()


        }



    }

    private fun submitForm() {
        binding.EmailContainer.helperText = ValidEmail()
        binding.NomContainer.error = validateName()
        binding.PrenomContainer.error = validatePrenom()
        binding.DescriptionContainer.error = validateDescription()
        val validEmail = binding.EmailContainer.helperText == null
        val validateName = binding.NomContainer.error == null
        val validatePrenom = binding.PrenomContainer.error == null
        val validateDescription = binding.DescriptionContainer.error == null
        if (validEmail  && validateName && validatePrenom && validateDescription)
            resetForm()
        else
            invalideForm()

    }

    private fun invalideForm() {
        var message = ""
        if (binding.EmailContainer.helperText != null)
            message +="\n\nEmail:" + binding.EmailContainer.helperText
        if(binding.NomContainer.error != null)
            message +="\n\nNom:" + binding.NomContainer.error
        if(binding.PrenomContainer.error != null)
            message +="\n\nPreom:" + binding.PrenomContainer.error
        if(binding.DescriptionContainer.error != null)
            message +="\n\nDescription:" + binding.DescriptionContainer.error
        AlertDialog.Builder(this)
            .setTitle("Invalide Form")
            .setMessage(message)
            .setPositiveButton("okay"){_,_  ->
            }.show()
    }

    private fun resetForm() {
        var message = "Email:" + binding.email1.text
         message += "\nNom:" + binding.nom.text
        message += "\nPrenom:" + binding.prenom.text
        message += "\nDescription:" + binding.description.text
        AlertDialog.Builder(this)
            .setTitle(" Form  submited")
            .setMessage(message)
            .setPositiveButton("okay"){_,_  ->
                binding.email1.text = null
                binding.nom.text = null
                binding.prenom.text = null
                binding.description.text = null
                binding.EmailContainer.helperText = getString(R.string.required)
                binding.NomContainer.error = getString(R.string.required)
                binding.PrenomContainer.error = getString(R.string.required)
                binding.DescriptionContainer.error = getString(R.string.required)
            }
                .show()


    }

    private fun createReclamation() {
    val nom = binding.nom.text.toString()
        val prenom = binding.prenom.text.toString()
        val emaill = binding.email1.text.toString()
         val  description = binding.description.text.toString()
        val reclamation=
            Reclamation(nom , prenom , emaill , description )
        CoroutineScope(Dispatchers.IO).launch {
            val response = reclamationInterface.create_reclamation(reclamation)
            if (response.isSuccessful) {
                val user = response.body()
                Snackbar.make(binding.root, "user", Snackbar.LENGTH_LONG).show()

            } else {
                withContext(Dispatchers.Main) {
                    Snackbar.make(binding.root, "Error", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun emaillFocusListener() {
        binding.email1.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.EmailContainer.helperText = ValidEmail()
            }
        }


    }

    private fun ValidEmail(): String? {
val emailText = binding.email1.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
return "invalid email adresse"
        }
return null
    }
    private fun nameFocusListener() {
        binding.nom.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                val validationMessage = validateName()
                binding.NomContainer.error = validationMessage
            }
        }
    }
    private fun validateName(): String? {
        val nameText = binding.nom.text.toString()
        if (!nameText.matches(Regex("^[a-zA-Z\\s]+$"))) {
            return "Nom invalide. Utilisez uniquement des lettres et des espaces."
        }

        return null
    }
    private fun PrenomFocusListener() {
        binding.prenom.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                val validationMessage = validatePrenom()
                binding.PrenomContainer.error = validationMessage
            }
        }
    }
    private fun validatePrenom(): String? {
        val nameText = binding.prenom.text.toString()
        if (!nameText.matches(Regex("^[a-zA-Z\\s]+$"))) {
            return "Prenom invalide. Utilisez uniquement des lettres et des espaces."
        }

        return null
    }
    private fun descriptionFocusListener() {
        binding.description.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                val validationMessage = validateDescription()
                binding.DescriptionContainer.error = validationMessage
            }
        }
    }

    private fun validateDescription(): String? {
        val descriptionText = binding.description.text.toString()


        val minLength = 5
        if (descriptionText.length < minLength) {
            return "La description doit contenir au moins $minLength caractères."
        }

        return null
    }



}

