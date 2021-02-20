package com.example.movieapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_detail)
        val nMoovieIndex = 0
        //val nMoovieIndex = intent.getStringExtra("index")
        val name = intent.getStringExtra("name")
        val author = intent.getStringExtra("author")
        val season = intent.getStringExtra("season")
        val description = intent.getStringExtra("description")
        val url = intent.getStringExtra("url")

        val mBtnDelete = findViewById<FloatingActionButton>(R.id.btnDeleteMovie)

        mBtnDelete.setOnClickListener{
            val builder = AlertDialog.Builder(this@DetailActivity)
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        // Delete selected moovie from list
                        MainActivity.mMovie.removeAt(nMoovieIndex)
                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
        }
        val mName = findViewById<TextView>(R.id.nameTextView)
        val mAuthor = findViewById<TextView>(R.id.authorTextView)
        val mSeason = findViewById<TextView>(R.id.seasonTextView)
        val mDescription = findViewById<TextView>(R.id.descriptionTextView)
        val mPoster = findViewById<ImageView>(R.id.posterImageView)
        val mVistaImageView = findViewById<ImageView>(R.id.vistaImageView)

        mName.text = name
        mAuthor.text = author
        mSeason.text = season
        mDescription.text = description

        Glide.with(this)
            .load(url)
            .into(mPoster)

        Glide.with(this)
            .load(url)
            .into(mVistaImageView)
    }

}
