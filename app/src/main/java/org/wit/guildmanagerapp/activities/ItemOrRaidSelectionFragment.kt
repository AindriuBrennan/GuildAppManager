package org.wit.guildmanagerapp.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_item_or_raid.*
import org.wit.guildmanagerapp.R

class ItemOrRaidSelectionFragment: DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_or_raid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        select_item.setOnClickListener {
            CreateGearPopupFragment().show(childFragmentManager, "")
        }

        select_raid.setOnClickListener {
            CreateRaidPopupFragment().show(childFragmentManager, "")
        }
    }
}