package com.example.hpapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnSpecificCharacter: Button
    private lateinit var btnStaff: Button
    private lateinit var btnHouseStudents: Button
    private lateinit var btnSpells: Button
    private lateinit var btnExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa as Views
        btnSpecificCharacter = findViewById(R.id.btn_specific_character)
        btnStaff = findViewById(R.id.btn_staff)
        btnHouseStudents = findViewById(R.id.btn_house_students)
        btnSpells = findViewById(R.id.btn_spells)
        btnExit = findViewById(R.id.btn_exit)

        // Configura os listeners de clique
        btnSpecificCharacter.setOnClickListener {
            startActivity(Intent(this, CharacterByIdActivity::class.java))
        }

        btnStaff.setOnClickListener {
            startActivity(Intent(this, StaffActivity::class.java))
        }

        btnHouseStudents.setOnClickListener {
            startActivity(Intent(this, HouseStudentsActivity::class.java))
        }

        btnSpells.setOnClickListener {
            startActivity(Intent(this, SpellsActivity::class.java))
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}