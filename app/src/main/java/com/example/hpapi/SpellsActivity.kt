package com.example.hpapi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hpapi.api.RetrofitClient
import com.example.hpapi.data.Spell
import kotlinx.coroutines.launch

class SpellsActivity : AppCompatActivity() {

    private lateinit var spellsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spells)

        // Inicializa a RecyclerView usando findViewById
        spellsRecyclerView = findViewById(R.id.rv_spells)
        // Define o gerenciador de layout que a RecyclerView usará (linear, neste caso)
        spellsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Inicia a busca pelos dados dos feitiços
        fetchSpells()
    }

    private fun fetchSpells() {
        // Usa uma corrotina no escopo do ciclo de vida da Activity para fazer a chamada de rede
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getAllSpells()
                if (response.isSuccessful && response.body() != null) {
                    // Se a resposta for bem-sucedida, configura a RecyclerView com os dados
                    setupRecyclerView(response.body()!!)
                } else {
                    Toast.makeText(this@SpellsActivity, "Erro ao buscar feitiços: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                // Captura exceções de rede (ex: sem internet)
                Toast.makeText(this@SpellsActivity, "Falha na conexão: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupRecyclerView(spells: List<Spell>) {
        // Cria uma instância do adapter, passando a lista de feitiços e a ação de clique
        val adapter = SpellsAdapter(spells) { spell ->
            // Ação de clique: cria um Intent para abrir a SpellDetailActivity
            val intent = Intent(this, SpellDetailActivity::class.java).apply {
                // Adiciona os dados do feitiço como "extras" no Intent
                putExtra(SpellDetailActivity.EXTRA_SPELL_NAME, spell.name)
                putExtra(SpellDetailActivity.EXTRA_SPELL_DESC, spell.description)
            }
            startActivity(intent)
        }
        // Define o adapter na RecyclerView
        spellsRecyclerView.adapter = adapter
    }
}
