package com.example.shoppinglist.ui.screens.shopping_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.databinding.BottomSheetNewShoppingListBinding
import com.example.shoppinglist.databinding.DialogNewListBinding
import com.example.shoppinglist.ui.screens.new_note.NewNoteViewModel
import com.example.shoppinglist.ui.screens.shopping_new_note.ShoppingNewNoteViewModel
import com.example.shoppinglist.utils.TimeManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewListDialogFragment : DialogFragment() {
    private var _binding: DialogNewListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateNewListDialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogNewListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialogWidth()
        binding.buttonCreateList.cvNewList.setOnClickListener {
            viewModel.insertShoppingNote(
                ShoppingNoteItem(
                    id = null,
                    title = binding.etListName.text.toString(),
                    time = TimeManager.getCurrentTime()
                )
            )
            dismiss()
        }
    }

    private fun setDialogWidth() {
        dialog?.window?.attributes?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            dialog?.window?.attributes = this
        }
    }
}
