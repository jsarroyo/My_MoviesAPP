package com.example.my_moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.my_moviesapp.Model.Student

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /*  val index = intent.getIntExtra("index", 0)
          val student = MainActivity.studentsList[index]
  */
        val student = intent.getSerializableExtra("studentSelected") as Student

        /* Log.d("Tag","Student Selected id ${student.name}, " +
                 "Lastname is ${student.lastName}, " +
                 "and is it enrolled? ${student.enrolled}, From Detail Activity")*/
    }
}