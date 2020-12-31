package org.wit.guildmanagerapp.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentContainer
import com.google.android.material.datepicker.DateSelector
import kotlinx.android.synthetic.main.fragment_create_raid_popup.*
import org.jetbrains.anko.AnkoLogger
import org.wit.guildmanagerapp.R
import java.time.LocalDate

class CreateRaidPopupFragment: DialogFragment(), AnkoLogger {

    private val adapter = CharacterModelAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_raid_popup, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        create_item_record.setOnClickListener {
            val date: CalendarView? = calendarView
        }



    }


}