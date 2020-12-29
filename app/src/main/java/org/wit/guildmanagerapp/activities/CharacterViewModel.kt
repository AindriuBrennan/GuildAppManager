package org.wit.guildmanagerapp.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import org.wit.guildmanagerapp.models.CharacterModel
import org.wit.guildmanagerapp.models.NODE_CHARACTERS
import java.lang.Exception

class CharacterViewModel : ViewModel() {

    private val dbCharacterModels = FirebaseDatabase.getInstance().getReference(NODE_CHARACTERS)


    //add data from db to this list, this list then is used inside the fragment
    //to display the data
    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characterModel: LiveData<List<CharacterModel>>
    get() = _characters



    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result

    /*
        Add character to DB, save a character under the characters node in the DB with a
        unique key id.

     */
    fun addCharacter(character: CharacterModel) {

        character.id = dbCharacterModels.push().key
        character.id?.let {
            dbCharacterModels.child(it).setValue(character).addOnCompleteListener {
                if(it.isSuccessful){
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
        }

    }
    /*
     Fetch data from the db, make sure the data is not null, add it to a list and assign
     it to the mutable list which gets displayed in the fragment
     */
    fun getCharacters() {
        dbCharacterModels.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    val characters = mutableListOf<CharacterModel>()
                    for(characterSnapshot in snapshot.children) {
                        val character = characterSnapshot.getValue(CharacterModel::class.java)
                        character?.id = characterSnapshot.key
                        character?.let{characters.add(it)}
                    }
                    _characters.value = characters
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}