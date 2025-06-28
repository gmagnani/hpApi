package com.example.hpapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hpapi.data.Spell

class SpellsAdapter(
    private val spells: List<Spell>,

    private val onItemClicked: (Spell) -> Unit
) : RecyclerView.Adapter<SpellsAdapter.SpellViewHolder>() {


    class SpellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val spellName: TextView = itemView.findViewById(R.id.tv_spell_name)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.spell_list_item, parent, false)
        return SpellViewHolder(view)
    }


    override fun getItemCount(): Int {
        return spells.size
    }


    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val spell = spells[position]
        holder.spellName.text = spell.name ?: "Feitiço sem nome"


        holder.itemView.setOnClickListener {
            onItemClicked(spell)
        }
    }
}