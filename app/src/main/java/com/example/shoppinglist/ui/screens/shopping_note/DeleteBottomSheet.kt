package com.example.shoppinglist.ui.screens.shopping_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.shoppinglist.databinding.BottomSheetNewShoppingListBinding
import com.example.shoppinglist.databinding.FragmentShoppingNoteBinding
import com.example.shoppinglist.ui.adapters.ShoppingNoteAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeleteBottomSheet() : BottomSheetDialogFragment() {
    private var _binding: BottomSheetNewShoppingListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetNewShoppingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btDelete.setOnClickListener {

        }
    }
}