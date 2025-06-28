package com.example.hpapi

import android.os.Bundle
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hpapi.api.RetrofitClient
import com.example.hpapi.data.Character
import kotlinx.coroutines.launch

class HouseStudentsActivity : AppCompatActivity() {

    private lateinit var radioGroupHouses: RadioGroup
    private lateinit var tvStudentList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_students)

        radioGroupHouses = findViewById(R.id.rg_houses)
        tvStudentList = findViewById(R.id.tv_student_list)

        radioGroupHouses.setOnCheckedChangeListener { _, checkedId ->
            val selectedHouse = when (checkedId) {
                R.id.rb_gryffindor -> "gryffindor"
                R.id.rb_hufflepuff -> "hufflepuff"
                R.id.rb_ravenclaw -> "ravenclaw"
                R.id.rb_slytherin -> "slytherin"
                else -> ""
            }
            if (selectedHouse.isNotEmpty()) {
                fetchStudentsByHouse(selectedHouse)
            }
        }
    }

    private fun fetchStudentsByHouse(house: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getStudentsByHouse(house)
                if (response.isSuccessful && response.body() != null) {
                    displayStudents(response.body()!!)
                } else {
                    showError("Erro ao buscar estudantes: ${response.message()}")
                }
            } catch (e: Exception) {
                showError("Falha na conexão: ${e.message}")
            }
        }
    }

    private fun displayStudents(students: List<Character>) {
        if (students.isEmpty()) {
            tvStudentList.text = "Nenhum estudante encontrado para esta casa."
            return
        }
        val stringBuilder = StringBuilder()
        students.forEach { student ->
            stringBuilder.append(student.name ?: "Nome desconhecido").append("\n")
        }
        tvStudentList.text = stringBuilder.toString()
    }

    private fun showError(message: String) {
        tvStudentList.text = message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
