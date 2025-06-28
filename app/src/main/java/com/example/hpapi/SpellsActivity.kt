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


        spellsRecyclerView = findViewById(R.id.rv_spells)

        spellsRecyclerView.layoutManager = LinearLayoutManager(this)


        fetchSpells()
    }

    private fun fetchSpells() {

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getAllSpells()
                if (response.isSuccessful && response.body() != null) {

                    setupRecyclerView(response.body()!!)
                } else {
                    Toast.makeText(this@SpellsActivity, "Erro ao buscar feitiços: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {

                Toast.makeText(this@SpellsActivity, "Falha na conexão: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupRecyclerView(spells: List<Spell>) {

        val adapter = SpellsAdapter(spells) { spell ->

            val intent = Intent(this, SpellDetailActivity::class.java).apply {

                putExtra(SpellDetailActivity.EXTRA_SPELL_NAME, spell.name)
                putExtra(SpellDetailActivity.EXTRA_SPELL_DESC, spell.description)
            }
            startActivity(intent)
        }

        spellsRecyclerView.adapter = adapter
    }
}
