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
    var itemsCollected: ArrayList<ItemModel>? = ArrayList(),
    var dateCollected: ArrayList<LocalDate>? = ArrayList(),
) {
    override fun equals(other: Any?): Boolean {
        return if(other is CharacterModel) {
            other.id == id
        } else false

    }

}

