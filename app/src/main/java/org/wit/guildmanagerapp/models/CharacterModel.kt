package org.wit.guildmanagerapp.models

import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.util.ArrayList


data class CharacterModel(

    var id: String? = "",
    var name: String = "",
    var race: String? = "",
    var classType: String? = "",
    var itemsCollected: ArrayList<Item>? = ArrayList(),
    var dateCollected: ArrayList<LocalDate>? = ArrayList(),
)