package com.example.movieapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.model.Moovie
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_movie.*

class AddMovie : AppCompatActivity() {

    private lateinit  var tvMovieName: EditText
    private lateinit  var tvMovieAuthor: EditText
    private lateinit  var tvMovieDescription: EditText
    private lateinit  var tvMovieUrlPoster: EditText
    private lateinit  var btnAddMoovie: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        btnAddMoovie = findViewById<View>(R.id.btnAddMovie) as Button
        btnAddMoovie.setOnClickListener {
            fun onClick(v: View?) {
                addMoovie()
            }
        }

        btnAddMoovie = findViewById(R.id.btnAddMovie) as Button
        tvMovieName = findViewById(R.id.txv_add_moovie_name) as EditText
        tvMovieAuthor = findViewById(R.id.txv_add_moovie_author) as EditText
        tvMovieDescription = findViewById(R.id.txv_add_moovie_description) as EditText
        tvMovieUrlPoster = findViewById(R.id.txv_add_UrlImagen) as EditText
    }

    fun addMoovie(){
        val vlcName = tvMovieName.text.toString()
        val vlcAuthor = tvMovieAuthor.text.toString()
        val vlcDescription = tvMovieDescription.text.toString()
        val vlcUrl = tvMovieUrlPoster.text.toString()

        val vloPeli = Moovie( tvMovieName.text.toString(), tvMovieAuthor.text.toString(),1, tvMovieDescription.text.toString(),tvMovieUrlPoster.text.toString())

        MainActivity.mMovie.add(vloPeli)

        Toast.makeText(this,"Moovie has been registered successfully", Toast.LENGTH_LONG).show();
        finish()
    }
}