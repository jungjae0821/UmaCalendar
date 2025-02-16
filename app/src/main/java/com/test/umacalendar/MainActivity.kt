package com.test.umacalendar

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.umacalendar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding 초기화
    private lateinit var binding: ActivityMainBinding

    // 학생 목록
    private val studentList = mutableListOf(
        Student("스페셜 위크", "1995-05-02"),
        Student("사일런스 스즈카", "1994-05-01"),
        Student("토카이 테이오", "1988-04-20"),
        Student("마루젠스키", "1974-05-19"),
        Student("후지 키세키", "1992-04-15"),
        Student("오구리 캡", "1985-03-27"),
        Student("골드 쉽", "2009-03-06"),
        Student("보드카", "2004-04-04"),
        Student("다이와 스칼렛", "2004-05-13"),
        Student("타이키 셔틀", "1994-03-23"),
        Student("그래스 원더", "1995-02-18"),
        Student("히시 아마존", "1991-03-26"),
        Student("메지로 맥퀸", "1987-04-03"),
        Student("엘 콘도르 파사", "1995-03-17"),
        Student("티엠 오페라 오", "1996-03-13"),
        Student("나리타 브라이언", "1991-05-03"),
        Student("심볼리 루돌프", "1981-03-13"),
        Student("에어 그루브", "1993-04-06"),
        Student("아그네스 디지털", "1997-05-15"),
        Student("세이운 스카이", "1995-04-26"),
        Student("타마모 크로스", "1984-05-23"),
        Student("파인 모션", "1999-01-27"),
        Student("비와 하야이데", "1990-03-10"),
        Student("마야노 탑건", "1992-03-24"),
        Student("맨하탄 카페", "1998-03-05"),
        Student("미호노 부르봉", "1989-04-25"),
        Student("메지로 라이언", "1987-04-11"),
        Student("히시 아케보노", "1992-02-27"),
        Student("유키노 비진", "1990-03-10"),
        Student("라이스 샤워", "1989-03-05"),
        Student("아이네스 후진", "1987-04-10"),
        Student("아그네스 타키온", "1998-04-13"),
        Student("어드마이어 베가", "1996-03-12"),
        Student("이나리 원", "1984-05-07"),
        Student("위닝 티켓", "1990-03-21"),
        Student("에어 샤커", "1997-02-26"),
        Student("에이신 플래시", "2007-03-27"),
        Student("카렌짱", "2007-03-31"),
        Student("카와카미 프린세스", "2003-06-05"),
        Student("골드 시티", "1984-04-16"),
        Student("사쿠라 바쿠신 오", "1989-04-14"),
        Student("시킹 더 펄", "1994-04-16"),
        Student("신코 윈디", "1993-04-14"),
        Student("스윕 토쇼", "2001-05-09"),
        Student("슈퍼 크릭", "1985-05-27"),
        Student("스마트 팔콘", "2005-04-04"),
        Student("젠노 롭 로이", "2000-03-27"),
        Student("토센 조던", "2006-02-04"),
        Student("나카야마 페스타", "2006-04-05"),
        Student("나리타 타이신", "1990-06-10"),
        Student("니시노 플라워", "1989-04-19"),
        Student("하루 우라라", "1996-02-27"),
        Student("뱀부 메모리", "1985-05-14"),
        Student("비코 페가수스", "1991-02-08"),
        Student("마블러스 선데이", "1992-05-31"),
        Student("마치카네 후쿠키타루", "1994-05-22"),
        Student("미스터 시비", "1980-04-07"),
        Student("메이쇼 도토", "1996-03-25"),
        Student("메지로 도베르", "1994-05-06"),
        Student("나이스 네이처", "1988-04-16"),
        Student("킹 헤일로", "1995-04-28"),
        Student("마치카네 탄호이저", "1989-05-07"),
        Student("이쿠노 딕터스", "1987-04-16"),
        Student("메지로 파머", "1987-03-21"),
        Student("다이타쿠 헬리오스", "1987-04-10"),
        Student("트윈 터보", "1988-04-13"),
        Student("사토노 다이아몬드", "2013-01-30"),
        Student("키타산 블랙", "2012-03-10"),
        Student("사쿠라 치요노 오", "1985-02-19"),
        Student("시리우스 심볼리", "1982-03-26"),
        Student("메지로 아르당", "1985-03-28"),
        Student("야에노 무테키", "1985-04-11"),
        Student("츠루마루 츠요시", "1995-04-06"),
        Student("메지로 브라이트", "1994-04-19"),
        Student("데어링 택트", "2017-04-15"),
        Student("사쿠라 로렐", "1991-05-08"),
        Student("나리타 탑 로드", "1996-04-04"),
        Student("야마닌 제퍼", "1988-05-27"),
        Student("퓨리오소", "2004-05-01"),
        Student("트랜센드", "2006-03-09"),
        Student("에스포와르 시티", "2005-04-22"),
        Student("노스 플라이트", "1990-04-12"),
        Student("심볼리 크리스 에스", "1999-01-21"),
        Student("타니노 김렛", "1999-05-04"),
        Student("다이이치 루비", "1987-04-15"),
        Student("메지로 라모누", "1983-04-09"),
        Student("애스턴 마짱", "2004-03-05"),
        Student("사토노 크라운", "2012-03-10"),
        Student("슈발 그랑", "2012-03-14"),
        Student("비르시나", "2009-03-05"),
        Student("비블로스", "2013-04-09"),
        Student("단츠 플레임", "1998-04-19"),
        Student("케이에스 미라클", "1988-03-16"),
        Student("정글 포켓", "1998-05-07"),
        Student("빌리브", "1998-04-26"),
        Student("노 리즌", "1999-06-04"),
        Student("스틸 인 러브", "2000-05-02"),
        Student("코파노 리키", "2010-03-24"),
        Student("홋코 타루마에", "2009-05-26"),
        Student("원더 어큐트", "2006-03-14"),
        Student("삼손 빅", "1991-04-14"),
        Student("사운즈 오브 어스", "2011-04-12"),
        Student("로이스 앤 로이스", "1990-03-10"),
        Student("카츠라기 에이스", "1980-04-24"),
        Student("네오 유니버스", "2000-05-21"),
        Student("히시 미라클", "1999-03-31"),
        Student("탭 댄스 시티", "1997-03-16"),
        Student("두라멘테", "2012-03-22"),
        Student("라인 크래프트", "2002-04-04"),
        Student("세자리오", "2002-03-31"),
        Student("에어 메사이어", "2002-02-04"),
        Student("데어링 하트", "2002-03-09"),
        Student("후사이치 판도라", "2003-02-27"),
        Student("부에나 비스타", "2006-03-14"),
        Student("오르페브르", "2008-05-14"),
        Student("젠틸돈나", "2009-02-20"),
        Student("윈 바리아시옹", "2008-04-10"),
        Student("드림 저니", "2004-02-24"),
        Student("칼스톤 라이트 오", "1998-05-03"),
        Student("듀렌달", "1999-05-25"),
        Student("버블검 펠로", "1993-04-11"),
        Student("사쿠라 치토세 오", "1990-05-11"),
        Student("블래스트 원피스", "2015-04-02"),
    )

    // RecyclerView 어댑터
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 하이퍼링크 이벤트 처리
        binding.hyperlinkTextView.apply {
            text = "우마무스메 명부 보러가기"
            paint.isUnderlineText = true // 밑줄 추가
            setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://namu.wiki/w/%ED%8B%80:%EC%9A%B0%EB%A7%88%EB%AC%B4%EC%8A%A4%EB%A9%94%20%ED%94%84%EB%A6%AC%ED%8B%B0%20%EB%8D%94%EB%B9%84%EC%9D%98%20%EB%93%B1%EC%9E%A5%20%EC%9A%B0%EB%A7%88%EB%AC%B4%EC%8A%A4%EB%A9%94"))
                startActivity(intent)
            }
        }

        val calendarView: CalendarView = findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, _ ->
            val formattedDate = "${year}년 ${month + 1}월"
            val selectedDateTextView: TextView = findViewById(R.id.selectedDateText)
            selectedDateTextView.text = formattedDate
        }

        // RecyclerView 초기화
        adapter = StudentAdapter(emptyList()) // 초기에는 빈 리스트를 표시함
        binding.studentRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.studentRecyclerView.adapter = adapter

        // 우마무스메 명부 검색창
        val searchEditText: EditText = findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                searchStudentByName(query)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 달력 이벤트 처리
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "${year}년 ${String.format("%02d", month + 1)}월 ${String.format("%02d", dayOfMonth)}일"
            filterStudentsByDate("${String.format("%02d", month + 1)}-${String.format("%02d", dayOfMonth)}")
            binding.selectedDateText.text = "선택한 날짜: $selectedDate"
        }
    }

    // 우마무스메 검색 이벤트 처리
    private fun searchStudentByName(query: String) {
        if (query.isEmpty()) {
            adapter.updateData(emptyList())
            binding.birthdayTextView.text = ""
            return
        }

        val filteredList = studentList.filter { student ->
            student.name.contains(query, ignoreCase = true)
        }

        if (filteredList.isNotEmpty()) {
            adapter.updateData(filteredList)

            val birthdayInfo = filteredList.joinToString("\n") { "${it.name}: ${it.birthday}" }
            binding.birthdayTextView.text = "검색된 우마무스메 생일:\n$birthdayInfo"
            binding.birthdayTextView.visibility = View.VISIBLE
        } else {
            binding.birthdayTextView.text = "검색된 우마무스메가 없습니다."
            binding.birthdayTextView.visibility = View.VISIBLE
            adapter.updateData(emptyList())
        }
    }

    // 선택된 날짜에 맞는 학생을 필터링함
    private fun filterStudentsByDate(date: String) {
        // 선택된 날짜와 일치하는 생일을 가진 학생만 필터링함
        val filteredList = studentList.filter { student ->
            val studentBirthday = student.birthday.substring(5)  // "MM-dd" 부분 추출
            studentBirthday == date  // MM-dd 부분 비교
        }

        if (filteredList.isNotEmpty()) {
            // 생일이 겹치는 학생들을 RecyclerView에 표시함
            adapter.updateData(filteredList)  // RecyclerView 데이터 업데이트

            // 생일이 있는 경우 이름만 표시함
            val birthdayNames = filteredList.joinToString(", ") { it.name }
            binding.birthdayTextView.text = "생일인 우마무스메: $birthdayNames"

            // 생일이 있을 때 TextView를 보이게 설정함
            binding.birthdayTextView.visibility = android.view.View.VISIBLE
        } else {
            // 생일이 없는 경우에는 "이 날은 생일인 우마무스메가 없습니다" 메시지를 표시함
            binding.birthdayTextView.text = "이 날은 생일인 우마무스메가 없습니다."
            binding.birthdayTextView.visibility = android.view.View.VISIBLE

            // 생일이 없는 날짜를 클릭하면 RecyclerView를 비움
            adapter.updateData(emptyList())
        }
    }
}