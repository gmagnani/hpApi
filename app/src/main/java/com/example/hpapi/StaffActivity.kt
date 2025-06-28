package com.example.hpapi

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hpapi.api.RetrofitClient
import kotlinx.coroutines.launch

class StaffActivity : AppCompatActivity() {

    private lateinit var tvStaffList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)

        tvStaffList = findViewById(R.id.tv_staff_list)

        fetchStaffData()
    }

    private fun fetchStaffData() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getHogwartsStaff()
                if (response.isSuccessful && response.body() != null) {
                    val staffList = response.body()!!
                    val stringBuilder = StringBuilder()
                    staffList.forEach { staff ->
                        stringBuilder.append("Nome: ${staff.name ?: "N/A"}\n")
                        stringBuilder.append("Nomes Alternativos: ${staff.alternate_names?.joinToString() ?: "N/A"}\n")
                        stringBuilder.append("Espécie: ${staff.species ?: "N/A"}\n")
                        stringBuilder.append("Casa: ${staff.house ?: "N/A"}\n")
                        stringBuilder.append("----------------------------\n")
                    }
                    tvStaffList.text = stringBuilder.toString()
                } else {
                    showError("Erro ao buscar professores: ${response.message()}")
                }
            } catch (e: Exception) {
                showError("Falha na conexão: ${e.message}")
            }
        }
    }

    private fun showError(message: String) {
        tvStaffList.text = message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
