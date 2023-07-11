package com.example.shoppinglist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ItemListNoteBinding
import com.example.shoppinglist.domain.models.NoteModel

class NoteAdapter(private val listener: ClickListener) :
    ListAdapter<NoteModel,NoteAdapter.NoteViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_note, parent, false)
        return NoteViewHolder(view)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(currentList[position], listener)
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel) =
            oldItem == newItem
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemListNoteBinding.bind(view)


        fun bind(data: NoteModel, listener: ClickListener) {
            with(binding) {
                with(data) {
                    tvTitle.text = title
                    tvContent.text = content
                    tvTime.text = time.toString()
                    ibDelete.setOnClickListener {
                        listener.onClickDelete(id!!)
                    }
                    itemView.setOnClickListener{
                        listener.onClickNote(data)

                    }
                }
            }
        }
    }

    interface ClickListener {
        fun onClickDelete(id: Int)
        fun onClickNote(noteModel: NoteModel)
    }
}
