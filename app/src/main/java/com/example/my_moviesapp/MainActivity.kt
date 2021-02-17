package com.example.my_moviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_moviesapp.Model.Student
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), StudentSelectionListener {

    private val studentListForAdapter = ArrayList<Student>()
    private val listOfAvailableStudents = arrayListOf(
        Student("Nombre 1", "apellido 1", true),
        Student("nombre 2", "apellido 2", false),
        Student("Nombre 3", "apellido 3", true),
        Student("Nombre 4", "apellido 4", false),
        Student("Nombre 5", "Apellido 5", true))


    private var customAdapter: CustomAdapter? = null

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //populate list

        //define a layout manager
        my_recycler_view.layoutManager =
            LinearLayoutManager(this,
                RecyclerView.VERTICAL, false)

        //create adapter
        customAdapter = CustomAdapter(studentListForAdapter, this)

        //assign adapter
        my_recycler_view.adapter = customAdapter

        add_button.setOnClickListener {
            if(listOfAvailableStudents.isNotEmpty()) {
                val newStudent = listOfAvailableStudents[listOfAvailableStudents.size-1]
                studentListForAdapter.add(newStudent)
                listOfAvailableStudents.removeLast()
                customAdapter!!.notifyDataSetChanged()
            }
        }

    }

    override fun onStudentSelected(studentIndex: Int) {
        val studentSelected = studentListForAdapter[studentIndex]
        val detailIntent = Intent(this, DetailActivity::class.java)

        // detailIntent.putExtra("studentSelected", studentSelected)

        detailIntent.putExtra("index", studentIndex)

        startActivity(detailIntent)

        /* Log.d("Tag","Student Selected id ${studentSelected.name}, " +
                 "Lastname is ${studentSelected.lastName}, " +
                 "and is it enrolled? ${studentSelected.enrolled}")*/
    }

    override fun onStudentSelectedForDelete(studentIndex: Int) {
        listOfAvailableStudents.add(studentListForAdapter[studentIndex])
        studentListForAdapter.removeAt(studentIndex)
        customAdapter!!.notifyDataSetChanged()
    }
}