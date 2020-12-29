package org.wit.guildmanagerapp.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_create_player.*

import kotlinx.android.synthetic.main.fragment_create_player.view.*
import kotlinx.android.synthetic.main.fragment_create_player_popup.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.guildmanagerapp.R
import org.wit.guildmanagerapp.models.CharacterModel



class CreatePlayerPopupFragment: DialogFragment(), AnkoLogger {

    private lateinit var viewModel: CharacterViewModel
//    private lateinit var submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        return inflater.inflate(R.layout.fragment_create_player_popup, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        //        display message on succesful character creation, display dynamic error message on failure
        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if (it == null) {
                getString(R.string.character_added)
            } else {
                getString(R.string.character_failure, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })

        //listener for submission button
        submit.setOnClickListener {
            val name: String = editTextPlayerName.text.toString().trim()
            val race: String = editTextRace.text.toString().trim()
            val classType: String = editTextClass.text.toString().trim()
            info("button clicked")
            if (TextUtils.isEmpty(name)) {
                input_layout_name.error = getString(R.string.field_required_error)
                return@setOnClickListener
            }
            val character = CharacterModel()
            character.name = name
            character.race = race
            character.classType = classType
            viewModel.addCharacter(character)
        }

    }

}