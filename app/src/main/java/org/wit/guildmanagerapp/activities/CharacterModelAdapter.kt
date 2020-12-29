package org.wit.guildmanagerapp.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_create_player.view.*
import kotlinx.android.synthetic.main.recycler_view_character.view.*
import org.wit.guildmanagerapp.R
import org.wit.guildmanagerapp.models.CharacterModel

class CharacterModelAdapter: RecyclerView.Adapter<CharacterModelAdapter.CharacterViewModel>() {

    private var characters = mutableListOf<CharacterModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterViewModel (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_character, parent,  false))


    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterModelAdapter.CharacterViewModel, position: Int) {
        holder.view.text_view_name.text = characters[position].name
        holder.view.text_view_race.text = characters[position].race
        holder.view.text_view_class.text = characters[position].classType
    }

    fun setCharcaters(characters: List<CharacterModel>) {
        this.characters = characters as MutableList<CharacterModel>
        notifyDataSetChanged()
    }

    class CharacterViewModel(val view: View): RecyclerView.ViewHolder(view)
}