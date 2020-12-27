package org.wit.guildmanagerapp.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Logger
import org.wit.guildmanagerapp.models.CharacterModel
import org.wit.guildmanagerapp.models.NODE_CHARACTERS
import java.lang.Exception

class CharacterViewModel : ViewModel() {

    private val dbCharacterModels = FirebaseDatabase.getInstance().getReference(NODE_CHARACTERS)

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result

    /*
        Add character to DB, save a character under the characters node in the DB with a
        unique key id.

     */
    fun addCharacter(character: CharacterModel) {

        character.id = dbCharacterModels.push().key
        dbCharacterModels.child(character.id!!).setValue(character).addOnCompleteListener {
            if(it.isSuccessful){
                _result.value = null
            } else {
                _result.value = it.exception
            }
        }
    }
}