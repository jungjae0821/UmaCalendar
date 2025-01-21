package com.test.umacalendar

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.umacalendar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding 초기화
    private lateinit var binding: ActivityMainBinding

    // 학생 목록
    private val studentList = mutableListOf(
        Student("홍길동", "2000-01-15"),
        Student("김철수", "1999-12-01"),
        Student("이영희", "2001-07-23"),
        Student("박민수", "2002-05-11"),
        Student("정지훈", "2000-03-25"),
        Student("한지민", "1998-08-30"),
        Student("이준기", "2000-01-15"),  // 생일이 겹치는 예시
        Student("학생 8", "2002-05-11"),  // 생일이 겹치는 예시
    )

    // RecyclerView 어댑터
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView 초기화
        adapter = StudentAdapter(emptyList()) // 초기에는 빈 리스트를 표시
        binding.studentRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.studentRecyclerView.adapter = adapter

        // 달력에서 날짜를 선택했을 때 이벤트 처리
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "${String.format("%02d", month + 1)}-${String.format("%02d", dayOfMonth)}"
            filterStudentsByDate(selectedDate)  // 날짜 필터링
            binding.selectedDateText.text = "선택된 날짜: $selectedDate"
        }
    }

    // 선택된 날짜에 맞는 학생 필터링
    private fun filterStudentsByDate(date: String) {
        // 선택된 날짜와 일치하는 생일을 가진 학생만 필터링
        val filteredList = studentList.filter { student ->
            val studentBirthday = student.birthday.substring(5)  // "MM-dd" 부분 추출
            studentBirthday == date  // MM-dd 부분 비교
        }

        if (filteredList.isNotEmpty()) {
            // 생일이 겹치는 학생들을 RecyclerView에 표시
            adapter.updateData(filteredList)  // RecyclerView 데이터 업데이트

            // 생일이 있는 경우 이름만 표시
            val birthdayNames = filteredList.joinToString(", ") { it.name }
            binding.birthdayTextView.text = "생일인 사람: $birthdayNames"

            // 생일이 있을 때 TextView를 보이게 설정
            binding.birthdayTextView.visibility = android.view.View.VISIBLE
        } else {
            // 생일이 없는 경우에는 "오늘은 생일인 사람이 없습니다" 메시지 표시
            binding.birthdayTextView.text = "오늘은 생일인 사람이 없습니다."
            binding.birthdayTextView.visibility = android.view.View.VISIBLE

            // 생일이 없는 날짜를 클릭하면 RecyclerView를 비웁니다.
            adapter.updateData(emptyList())
        }
    }
}