package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplication.Network.Reclamation_interface
import com.example.myapplication.databinding.ActivityVideoBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AjoutVideo : ComponentActivity() {
    private lateinit var spinner: Spinner
    private lateinit var binding: ActivityVideoBinding
    private lateinit var nom: EditText
    private lateinit var prenom: EditText
    private lateinit var tittre: EditText
    private lateinit var video: EditText
    private lateinit var description: EditText
    private lateinit var reclamationInterface: Reclamation_interface

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nameFocusListener()
        PrenomFocusListener()
        tittreFocusListener()
        videoUrlFocusListener()
        descriptionFocusListener()
        spinner = findViewById(R.id.spinner_video)
        val listItems = listOf("sport           🏋", "nutrution           \uD83C\uDF4F","medical           \uD83D\uDC68\u200D⚕")
        val arrayAdapter =  ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listItems)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@AjoutVideo,"You have selected $selectedItem",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        binding.button3.setOnClickListener() {
            Snackbar.make(it, "video ajouter", Snackbar.LENGTH_LONG).show()
            println(nom.text)
            println(prenom.text)
            println(tittre.text)
            println(video.text)
            println(description.text)

            submitForm()


        }
        nom = findViewById(R.id.nom)
        prenom = findViewById(R.id.prenom)
        tittre = findViewById(R.id.tittre)
        video = findViewById(R.id.video)
        description = findViewById(R.id.description)
    }



    private fun submitForm() {
        binding.nomContainer.error = validateName()
        binding.prenomContainer.error = validatePrenom()
        binding.tittreContainer.error = validateTittre()
        binding.videoContainer.error = validateVideoUrl()
        binding.desContainer.error = validateDescription()

        val validateName = binding.nomContainer.error == null
        val validatePrenom = binding.prenomContainer.error == null
        val validateTittre = binding.tittreContainer.error == null
        val validateVideoUrl = binding.videoContainer.error == null
        val validateDescription = binding.desContainer.error == null
        if ( validateName && validatePrenom && validateTittre && validateVideoUrl && validateDescription)
            resetForm()
        else
            invalideForm()
    }

    private fun invalideForm() {
        var message = ""

        if(binding.nomContainer.error != null)
            message +="\n\nNom:" + binding.nomContainer.error
        if(binding.prenomContainer.error != null)
            message +="\n\nPrenom:" + binding.prenomContainer.error
        if(binding.tittreContainer.error != null)
            message +="\n\nTittre:" + binding.tittreContainer.error
        if(binding.videoContainer.error != null)
            message +="\n\nVideoUrl:" + binding.videoContainer.error
        if(binding.desContainer.error != null)
            message +="\n\nDescription:" + binding.desContainer.error
        AlertDialog.Builder(this)
            .setTitle("Invalide Form")
            .setMessage(message)
            .setPositiveButton("okay"){_,_  ->
            }.show()
    }

    private fun resetForm() {
        var  message = "Nom:" + binding.nom.text
        message += "\nPrenom:" + binding.prenom.text
        message += "\nTittre:" + binding.tittre.text
        message += "\nVideoUrl:" + binding.video.text
        message += "\nDescription:" + binding.description.text
        AlertDialog.Builder(this)
            .setTitle(" Form  submited")
            .setMessage(message)
            .setPositiveButton("okay"){_,_  ->

                binding.nom.text = null
                binding.prenom.text = null
                binding.tittre.text = null
                binding.video.text = null
                binding.description.text = null

                binding.nomContainer.error = getString(R.string.required)
                binding.prenomContainer.error = getString(R.string.required)
                binding.tittreContainer.error = getString(R.string.required)
                binding.videoContainer.error = getString(R.string.required)
                binding.desContainer.error = getString(R.string.required)
            }
            .show()


    }




    private fun nameFocusListener() {
        binding.nom.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                val validationMessage = validateName()
                binding.nomContainer.error = validationMessage
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
                binding.prenomContainer.error = validationMessage
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
                binding.desContainer.error = validationMessage
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
    private fun tittreFocusListener() {
        binding.description.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                val validationMessage = validateTittre()
                binding.tittreContainer.error = validationMessage
            }
        }
    }

    private fun validateTittre(): String? {
        val descriptionText = binding.tittre.text.toString()


        val minLength = 5
        if (descriptionText.length < minLength) {
            return "La description doit contenir au moins $minLength caractères."
        }

        return null
    }
    private fun videoUrlFocusListener() {
        binding.video.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                val validationMessage = validateVideoUrl()
                binding.videoContainer.error = validationMessage
            }
        }
    }

    private fun validateVideoUrl(): String? {
        val videoUrlText = binding.video.text.toString()


        if (videoUrlText.isBlank()) {
            return "L'URL de la vidéo ne peut pas être vide."
        }



        return null
    }



}
