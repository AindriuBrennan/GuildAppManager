package org.wit.guildmanagerapp.models

data class ItemModel (
    var id: String? ="",
    var name: String = "",
    var playersWithItem: ArrayList<String>? = ArrayList()

) {
    override fun toString(): String {
        return name
    }
}