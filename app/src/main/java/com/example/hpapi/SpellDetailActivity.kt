package com.example.hpapi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SpellDetailActivity : AppCompatActivity() {

    private lateinit var tvSpellName: TextView
    private lateinit var tvSpellDescription: TextView


    companion object {
        const val EXTRA_SPELL_NAME = "extra_spell_name"
        const val EXTRA_SPELL_DESC = "extra_spell_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_detail)


        tvSpellName = findViewById(R.id.tv_spell_detail_name)
        tvSpellDescription = findViewById(R.id.tv_spell_detail_description)


        val name = intent.getStringExtra(EXTRA_SPELL_NAME)
        val description = intent.getStringExtra(EXTRA_SPELL_DESC)


        tvSpellName.text = name ?: "Nome não disponível"
        tvSpellDescription.text = description ?: "Descrição não disponível"
    }
}