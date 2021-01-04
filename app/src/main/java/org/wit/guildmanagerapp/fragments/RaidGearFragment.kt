package org.wit.guildmanagerapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_raid_gear.*
import org.wit.guildmanagerapp.R
import org.wit.guildmanagerapp.adapters.ItemModelAdapter
import org.wit.guildmanagerapp.dialogFragments.ItemOrRaidSelectionFragment
import org.wit.guildmanagerapp.listeners.ItemRecyclerViewListener
import org.wit.guildmanagerapp.models.ItemModel
import org.wit.guildmanagerapp.viewModels.ItemViewModel

class RaidGearFragment : Fragment(), ItemRecyclerViewListener {

    private lateinit var viewModel: ItemViewModel
    private val adapter = ItemModelAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        return inflater.inflate(R.layout.fragment_raid_gear, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view_items.adapter = adapter

        adapter.listener = this

        viewModel.getItems()
        viewModel.getDBUpdates()

        viewModel.liveItemModel.observe(viewLifecycleOwner, Observer {
            adapter.addItem(it)
        })

        viewModel.itemModel.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })

        button_add_raid.setOnClickListener {
            ItemOrRaidSelectionFragment().show(childFragmentManager, "")
        }
    }

    override fun onRecyclerViewButtonClick(view: View, item: ItemModel) {
        when(view.id) {
            R.id.delete_item -> {
                viewModel.deleteItem(item)
            }
        }
    }


}