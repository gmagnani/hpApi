package com.example.hpapi

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.hpapi.api.RetrofitClient
import kotlinx.coroutines.launch

class CharacterByIdActivity : AppCompatActivity() {

    private lateinit var etCharacterId: EditText
    private lateinit var btnSearchCharacter: Button
    private lateinit var ivCharacterPhoto: ImageView
    private lateinit var tvCharacterDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_by_id)

        etCharacterId = findViewById(R.id.et_character_id)
        btnSearchCharacter = findViewById(R.id.btn_search_character)
        ivCharacterPhoto = findViewById(R.id.iv_character_photo)
        tvCharacterDetails = findViewById(R.id.tv_character_details)

        btnSearchCharacter.setOnClickListener {
            val characterId = etCharacterId.text.toString().trim()
            if (characterId.isNotEmpty()) {
                fetchCharacterData(characterId)
            } else {
                Toast.makeText(this, "Por favor, insira um ID.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchCharacterData(id: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getCharacterById(id)
                if (response.isSuccessful && response.body() != null) {
                    val character = response.body()!!.firstOrNull()
                    if (character != null) {
                        ivCharacterPhoto.load(character.image) {
                            placeholder(R.drawable.ic_launcher_background)
                            error(R.drawable.ic_launcher_foreground)
                        }
                        tvCharacterDetails.text = """
                            Nome: ${character.name ?: "N/A"}
                            Espécie: ${character.species ?: "N/A"}
                            Casa: ${character.house ?: "N/A"}
                        """.trimIndent()
                        tvCharacterDetails.visibility = View.VISIBLE
                        ivCharacterPhoto.visibility = View.VISIBLE
                    } else {
                        showError("Personagem não encontrado.")
                    }
                } else {
                    showError("Erro na busca: ${response.message()}")
                }
            } catch (e: Exception) {
                showError("Falha na conexão: ${e.message}")
            }
        }
    }

    private fun showError(message: String) {
        tvCharacterDetails.text = message
        tvCharacterDetails.visibility = View.VISIBLE
        ivCharacterPhoto.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
