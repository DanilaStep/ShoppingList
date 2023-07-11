package com.example.shoppinglist.ui.screens.shopping_new_note

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.databinding.FragmentShoppingNewNoteBinding
import com.example.shoppinglist.utils.TimeManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingNewNoteFragment : Fragment() {
    private var _binding: FragmentShoppingNewNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoppingNewNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoppingNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val serializable =
            arguments?.getSerializable(KEY_FOR_FILL_SHOP_NOTE_ITEM) as ShoppingNoteItem?

        serializable?.let { fillShopScreen(it) }

        binding.topShopAppBar.setNavigationOnClickListener{

            serializable?.let {
                viewModel.updateShoppingNote(
                    it.copy(
                        title = binding.etShopTitle.text.toString(),
                        time = TimeManager.getCurrentTime()
                    )
                )
            } ?: run {
                if (binding.etShopTitle.text.isNotBlank()) {
                    viewModel.insertShoppingNote(
                        ShoppingNoteItem(
                            id = null,
                            title = binding.etShopTitle.text.toString(),
                            time = TimeManager.getCurrentTime()
                        )
                    )
                }
            }
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
    private fun fillShopScreen(shoppingNoteItem: ShoppingNoteItem){
        binding.etShopTitle.setText(shoppingNoteItem.title)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val KEY_FOR_FILL_SHOP_NOTE_ITEM = "key_for_shop_note_item"

        fun newInstance(shoppingNoteItem: ShoppingNoteItem?) = ShoppingNewNoteFragment().apply {
            arguments = bundleOf(KEY_FOR_FILL_SHOP_NOTE_ITEM to shoppingNoteItem)
        }
    }
}