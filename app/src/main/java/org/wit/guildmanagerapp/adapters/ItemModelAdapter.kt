package org.wit.guildmanagerapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_create_player.*
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import org.wit.guildmanagerapp.R
import org.wit.guildmanagerapp.listeners.ItemRecyclerViewListener
import org.wit.guildmanagerapp.models.ItemModel
import org.wit.guildmanagerapp.viewModels.ItemViewModel

class ItemModelAdapter : RecyclerView.Adapter<ItemModelAdapter.ItemViewModel>() {

    private var items = mutableListOf<ItemModel>()
    var listener: ItemRecyclerViewListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewModel(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
    )

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ItemViewModel, position: Int) {
        holder.view.text_view_name.text = items[position].name

        holder.view.delete_item.setOnClickListener {
            listener?.onRecyclerViewButtonClick(it, items[position])
        }
    }


    fun setItems(items: List<ItemModel>) {
        this.items = items as MutableList<ItemModel>
        notifyDataSetChanged()
    }


    fun addItem(item: ItemModel) {
        if (!items.contains(item)) {
            items.add(item)
            notifyDataSetChanged()
        } else {
            val index = items.indexOf(item)
            if (item.itemDeleted) {
                items.removeAt(index)
            } else {
                items[index] = item
            }
        }
        notifyDataSetChanged()
    }

    class ItemViewModel(val view: View) : RecyclerView.ViewHolder(view)
}