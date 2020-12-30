package org.wit.guildmanagerapp.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.wit.guildmanagerapp.models.ItemModel
import org.wit.guildmanagerapp.models.NODE_ITEMS
import java.lang.Exception

class ItemViewModel: ViewModel() {
    private val dbItemModels = FirebaseDatabase.getInstance().getReference(NODE_ITEMS)


    private val _items = MutableLiveData<List<ItemModel>>()
    val itemModel: LiveData<List<ItemModel>>
    get() = _items

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result



    /*
    add item to firebase db, with unique id key
     */

    fun addItem(item: ItemModel) {
        item.id = dbItemModels.push().key
        item.id?.let {
            dbItemModels.child(it).setValue(item).addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
        }
    }

    fun getItems() {
        dbItemModels.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    val items = mutableListOf<ItemModel>()
                    for(itemSnapshot in snapshot.children){
                        val item = itemSnapshot.getValue(ItemModel::class.java)
                        item?.id = itemSnapshot.key
                        item?.let{items.add(it)}
                    }
                    _items.value = items
                }

            }
            override fun onCancelled(error:DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }






}