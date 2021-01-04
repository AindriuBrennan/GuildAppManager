package org.wit.guildmanagerapp.models

import android.content.ClipData
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.util.ArrayList


data class CharacterModel(

    var id: String? = "",
    var name: String = "",
    var race: String? = "",
    var classType: String? = "",
    var itemsCollected: MutableList<String>? = ArrayList(),
    var dateCollected: MutableList<String>? = ArrayList(),
    var charDeleted: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return if(other is CharacterModel) {
            other.id == id
        } else false

    }


    override fun toString(): String {
        return name
    }

}

