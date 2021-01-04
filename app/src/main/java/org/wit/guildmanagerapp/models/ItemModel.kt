package org.wit.guildmanagerapp.models

data class ItemModel(
    var id: String? = "",
    var name: String = "",
    var playersWithItem: ArrayList<String>? = ArrayList(),
    var itemDeleted: Boolean = false

) {
    override fun equals(other: Any?): Boolean {
        return if (other is ItemModel) {
            other.id == id
        } else false
    }

    override fun toString(): String {
        return name
    }
    

}