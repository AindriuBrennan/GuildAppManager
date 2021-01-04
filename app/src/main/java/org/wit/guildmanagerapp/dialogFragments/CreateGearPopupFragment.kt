package org.wit.guildmanagerapp.dialogFragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_create_loot_popup.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.guildmanagerapp.R
import org.wit.guildmanagerapp.viewModels.ItemViewModel
import org.wit.guildmanagerapp.models.ItemModel

class CreateGearPopupFragment: DialogFragment(), AnkoLogger {

    private lateinit var  viewModel: ItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        return inflater.inflate(R.layout.fragment_create_loot_popup, container, false
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if(it == null) {
                getString(R.string.item_added)
            } else {
                getString(R.string.item_failure, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })



        submit_name.setOnClickListener {
            val name: String = editTextGearName.text.toString()
            info("button clicked")
            if(TextUtils.isEmpty(name)) {
                input_layout_name.error = getString(R.string.field_required_error)
                return@setOnClickListener
            }

            val item = ItemModel()
            item.name = name
            viewModel.addItem(item)

        }
    }
}