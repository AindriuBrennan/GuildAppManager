package org.wit.guildmanagerapp.dialogFragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_create_player.*

import kotlinx.android.synthetic.main.fragment_create_player.view.*
import kotlinx.android.synthetic.main.fragment_create_player_popup.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.guildmanagerapp.R
import org.wit.guildmanagerapp.viewModels.CharacterViewModel
import org.wit.guildmanagerapp.models.CharacterModel



class EditPlayerPopupFragment(
    private val character: CharacterModel
): DialogFragment(), AnkoLogger {

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
        return inflater.inflate(R.layout.fragment_edit_player_popup, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextPlayerName.setText(character.name)
        editTextRace.setText(character.race)


        //listener for submission button
        submit.setOnClickListener {
            val name: String = editTextPlayerName.text.toString().trim()
            val race: String = editTextRace.text.toString().trim()
            info("button clicked")
            if (TextUtils.isEmpty(name)) {
                input_layout_name.error = getString(R.string.field_required_error)
                return@setOnClickListener
            }
            character.name = name
            character.race = race
            viewModel.editCharacter(character)
        }

    }

}