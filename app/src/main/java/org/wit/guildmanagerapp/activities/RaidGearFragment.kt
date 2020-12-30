package org.wit.guildmanagerapp.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_raid_gear.*
import org.wit.guildmanagerapp.R

class RaidGearFragment: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_raid_gear, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button_add_raid.setOnClickListener{
            ItemOrRaidSelectionFragment().show(childFragmentManager, "")
        }
    }


}