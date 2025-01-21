package com.test.umacalendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Student(val name: String, val birthday: String) // Student 클래스 정의

class StudentAdapter(private var studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.studentNameTextView)
        // 생일 텍스트뷰를 사용하지 않음
        // val birthdayTextView: TextView = itemView.findViewById(R.id.studentBirthdayTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.nameTextView.text = student.name
        // 생일 정보는 표시하지 않음
        // holder.birthdayTextView.text = student.birthday
    }

    override fun getItemCount(): Int = studentList.size

    // RecyclerView 데이터 업데이트
    fun updateData(newStudentList: List<Student>) {
        studentList = newStudentList
        notifyDataSetChanged() // 데이터 변경 알림
    }
}