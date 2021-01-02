package org.wit.guildmanagerapp.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_character.view.*
import org.wit.guildmanagerapp.R
import org.wit.guildmanagerapp.models.CharacterModel

class CharacterModelAdapter: RecyclerView.Adapter<CharacterModelAdapter.CharacterViewModel>() {

    private var characters = mutableListOf<CharacterModel>()
     var listener: CharacterRecyclerViewListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterViewModel (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_character, parent,  false))


    override fun getItemCount() = characters.size



    override fun onBindViewHolder(holder: CharacterModelAdapter.CharacterViewModel, position: Int) {
        holder.view.text_view_name.text = characters[position].name
        holder.view.text_view_race.text = characters[position].race
        holder.view.text_view_class.text = characters[position].classType

        holder.view.edit_character.setOnClickListener {
            listener?.onRecyclerViewButtonClick(it, characters[position]) }
        holder.view.delete_character.setOnClickListener {
            listener?.onRecyclerViewButtonClick(it, characters[position])
        }
    }

    fun setCharcaters(characters: List<CharacterModel>) {
        this.characters = characters as MutableList<CharacterModel>
        notifyDataSetChanged()
    }

    /*
    function that checks if a character is already in the list before adding
    checks if charDeleted is true or false for deleting and also checks which index the character
    is at for editing. notifyDatasetChaged is then called so the recycler view updates and displays
    the changes

     */
    fun addCharacter(character: CharacterModel) {
        if(!characters.contains(character)) {
            characters.add(character)
            notifyDataSetChanged()
        } else {
            val index = characters.indexOf(character)
            if(character.charDeleted) {
                characters.removeAt(index)
            }else {
                characters[index] = character
            }
        }
        notifyDataSetChanged()
    }

    class CharacterViewModel(val view: View): RecyclerView.ViewHolder(view)
}