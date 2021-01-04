package org.wit.guildmanagerapp.dialogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_create_raid_popup.*
import org.jetbrains.anko.AnkoLogger
import org.wit.guildmanagerapp.R
import org.wit.guildmanagerapp.viewModels.CharacterViewModel
import org.wit.guildmanagerapp.viewModels.ItemViewModel
import org.wit.guildmanagerapp.adapters.CharacterModelAdapter

class CreateRaidPopupFragment : DialogFragment(), AnkoLogger {
    private lateinit var viewModel: CharacterViewModel
    private lateinit var viewModelItem: ItemViewModel
    private val adapter = CharacterModelAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        viewModelItem = ViewModelProvider(this).get(ItemViewModel::class.java)
        return inflater.inflate(R.layout.fragment_create_raid_popup, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getCharacters()
        viewModelItem.getItems()

        viewModel.characterModel.observe(this, Observer {
            val spinnerAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
            select_character.adapter = spinnerAdapter
        })

        viewModelItem.itemModel.observe(this, Observer {
            val itemSpinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
            select_item.adapter = itemSpinnerAdapter
        })





    }


}