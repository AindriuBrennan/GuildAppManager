package org.wit.guildmanagerapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_create_player.*

import org.jetbrains.anko.AnkoLogger

import org.wit.guildmanagerapp.R
import org.wit.guildmanagerapp.listeners.CharacterRecyclerViewListener
import org.wit.guildmanagerapp.viewModels.CharacterViewModel
import org.wit.guildmanagerapp.dialogFragments.CreatePlayerPopupFragment
import org.wit.guildmanagerapp.dialogFragments.EditPlayerPopupFragment
import org.wit.guildmanagerapp.adapters.CharacterModelAdapter
import org.wit.guildmanagerapp.models.CharacterModel


 class CreatePlayerFragment : Fragment(), CharacterRecyclerViewListener, AnkoLogger {

    private lateinit var viewModel: CharacterViewModel
    private val adapter = CharacterModelAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        return inflater.inflate(R.layout.fragment_create_player, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view_characters.adapter = adapter

        //define click listener of the adapter
        adapter.listener = this

        viewModel.getCharacters()
        viewModel.getDBUpdates()



        viewModel.liveCharacterModel.observe(viewLifecycleOwner, Observer {
            adapter.addCharacter(it)
        })

        viewModel.characterModel.observe(viewLifecycleOwner, Observer {
            adapter.setCharacters(it)
        })

        button_add_character.setOnClickListener {
            CreatePlayerPopupFragment().show(childFragmentManager, "")
        }


    }
     //implement button from the interface
     override fun onRecyclerViewButtonClick(view: View, character: CharacterModel) {
         when (view.id) {
             R.id.edit_character -> {
                 EditPlayerPopupFragment(character).show(childFragmentManager, "")
             }
             R.id.delete_character -> {
                viewModel.deleteCharacter(character)
             }
         }
     }

 }

