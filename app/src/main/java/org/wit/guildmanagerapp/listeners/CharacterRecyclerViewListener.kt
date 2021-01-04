package org.wit.guildmanagerapp.listeners

import android.view.View
import org.wit.guildmanagerapp.models.CharacterModel

interface CharacterRecyclerViewListener {


    fun onRecyclerViewButtonClick(view: View, character: CharacterModel)


}