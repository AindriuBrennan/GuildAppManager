package org.wit.guildmanagerapp.activities

import android.view.View
import org.wit.guildmanagerapp.models.CharacterModel

interface CharacterRecyclerViewListener {


    fun onRecyclerViewButtonClick(view: View, character: CharacterModel)


}