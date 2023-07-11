package com.example.shoppinglist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.databinding.ItemListNoteBinding
import com.example.shoppinglist.databinding.ItemShoppingListNoteBinding

class ShoppingNoteAdapter(private val listener: ShoppingClickListener) :
    ListAdapter<ShoppingNoteItem, ShoppingNoteAdapter.ShoppingNoteViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingNoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shopping_list_note, parent, false)
        return ShoppingNoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingNoteViewHolder, position: Int) {
        holder.bind(currentList[position], listener)
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<ShoppingNoteItem>() {
        override fun areItemsTheSame(oldItem: ShoppingNoteItem, newItem: ShoppingNoteItem) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: ShoppingNoteItem, newItem: ShoppingNoteItem) =
            oldItem == newItem
    }

    inner class ShoppingNoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemShoppingListNoteBinding.bind(view)


        fun bind(data: ShoppingNoteItem, listener: ShoppingClickListener) {
            with(binding) {
                with(data) {
                    tvShoppingText.text = title
                    tvShoppingTime.text = time
                    ibDelete.setOnClickListener{
                        listener.onShoppingClickDelete(id!!)
                    }
                    }
                    itemView.setOnClickListener {
                        listener.onShoppingClickNote(data)
                    }
                }
            }
        }


    interface ShoppingClickListener {
        fun onShoppingClickDelete(id: Int)
        fun onShoppingClickNote(shoppingNoteItem: ShoppingNoteItem)
    }
}


