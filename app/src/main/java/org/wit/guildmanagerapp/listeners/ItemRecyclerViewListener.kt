package org.wit.guildmanagerapp.listeners

import android.view.View

import org.wit.guildmanagerapp.models.ItemModel

interface ItemRecyclerViewListener {

    fun onRecyclerViewButtonClick(view: View, item: ItemModel)
}