package com.example.hpapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hpapi.data.Spell

class SpellsAdapter(
    private val spells: List<Spell>,
    // Define um lambda para ser chamado quando um item for clicado
    private val onItemClicked: (Spell) -> Unit
) : RecyclerView.Adapter<SpellsAdapter.SpellViewHolder>() {

    // Esta classe interna representa a view de cada item da lista.
    // Ela "segura" a referência ao TextView para evitar chamadas repetidas de findViewById.
    class SpellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val spellName: TextView = itemView.findViewById(R.id.tv_spell_name)
    }

    // Chamado quando a RecyclerView precisa de uma nova view.
    // Ele infla o layout do item (spell_list_item.xml).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.spell_list_item, parent, false)
        return SpellViewHolder(view)
    }

    // Retorna a quantidade total de itens na lista.
    override fun getItemCount(): Int {
        return spells.size
    }

    // Chamado para exibir os dados em uma posição específica.
    // Ele pega o feitiço da lista e define o texto e o listener de clique.
    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val spell = spells[position]
        holder.spellName.text = spell.name ?: "Feitiço sem nome"

        // Configura o listener de clique para o item
        holder.itemView.setOnClickListener {
            onItemClicked(spell)
        }
    }
}