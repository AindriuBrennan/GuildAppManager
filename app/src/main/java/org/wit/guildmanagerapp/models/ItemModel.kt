package org.wit.guildmanagerapp.models

class Item (
    val id: Long? =0,
    val name: String = "",
    var playersWithItem: ArrayList<String>? = ArrayList()
)