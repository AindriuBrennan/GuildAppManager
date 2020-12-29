package org.wit.guildmanagerapp.activities

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_create_player.*

import org.jetbrains.anko.AnkoLogger

import org.wit.guildmanagerapp.R



class CreatePlayerFragment: Fragment(), AnkoLogger {

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
        return inflater.inflate(R.layout.fragment_create_player, container, false)
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


        button_add_character.setOnClickListener{
            CreatePlayerPopupFragment().show(childFragmentManager, "")
        }


    }

}

