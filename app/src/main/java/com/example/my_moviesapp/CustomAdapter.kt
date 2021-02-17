package com.example.my_moviesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.my_moviesapp.Model.Student


class CustomAdapter(private val students: ArrayList<Student>,
                    private val studentSelectionListener: StudentSelectionListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_student_cell,
                parent,
                false)
        return ViewHolder(view, studentSelectionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentStudent = students[position]
        holder.bindCell(currentStudent)
    }

    override fun getItemCount(): Int {
        return students.size
    }


    class ViewHolder(itemView : View,
                     private val studentSelectionListener: StudentSelectionListener) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener,
        View.OnLongClickListener {

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            studentSelectionListener.onStudentSelectedForDelete(adapterPosition)
            return true
        }

        override fun onClick(v: View?) {
            studentSelectionListener.onStudentSelected(adapterPosition)
        }

        fun bindCell(student: Student){
            val nameTextView = itemView.findViewById(R.id.name_text_view) as TextView
            val lastNameTextView = itemView.findViewById(R.id.lastname_text_view) as TextView
            val enrolledImageState = itemView.findViewById(R.id.enrolled_state) as ImageView

            nameTextView.text = student.name
            lastNameTextView.text = student.lastName

            when(student.enrolled){
                true -> enrolledImageState.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.img_yes))
                else -> enrolledImageState.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.img_no))
            }
        }
    }
}