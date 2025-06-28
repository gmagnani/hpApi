package com.example.hpapi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SpellDetailActivity : AppCompatActivity() {

    private lateinit var tvSpellName: TextView
    private lateinit var tvSpellDescription: TextView

    // Companion object para definir constantes para as chaves dos "extras" do Intent.
    // Isso evita erros de digitação e torna o código mais legível.
    companion object {
        const val EXTRA_SPELL_NAME = "extra_spell_name"
        const val EXTRA_SPELL_DESC = "extra_spell_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_detail)

        // Inicializa as views
        tvSpellName = findViewById(R.id.tv_spell_detail_name)
        tvSpellDescription = findViewById(R.id.tv_spell_detail_description)

        // Pega os dados passados pelo Intent
        val name = intent.getStringExtra(EXTRA_SPELL_NAME)
        val description = intent.getStringExtra(EXTRA_SPELL_DESC)

        // Define o texto nas views, com um valor padrão caso os dados sejam nulos
        tvSpellName.text = name ?: "Nome não disponível"
        tvSpellDescription.text = description ?: "Descrição não disponível"
    }
}