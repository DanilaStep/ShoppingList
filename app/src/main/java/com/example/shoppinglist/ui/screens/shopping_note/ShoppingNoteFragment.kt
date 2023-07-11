package com.example.shoppinglist.ui.screens.shopping_note

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.data.db.entities.ShoppingNoteItem
import com.example.shoppinglist.databinding.FragmentShoppingNoteBinding
import com.example.shoppinglist.ui.adapters.ShoppingNoteAdapter
import com.example.shoppinglist.ui.screens.new_note.NewNoteFragment
import com.example.shoppinglist.ui.screens.shopping_new_note.ShoppingNewNoteFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ShoppingNoteFragment : Fragment(), ShoppingNoteAdapter.ShoppingClickListener {
    private var _binding: FragmentShoppingNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoppingNoteViewModel by viewModels()
    private val adapter = ShoppingNoteAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvShoppingNote.adapter = adapter
        binding.rvShoppingNote.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
                .apply {
                    ResourcesCompat.getDrawable(resources, R.drawable.divider,null)
                        ?.let { setDrawable(it) }
                }
        )

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.notesStateFlow.collect {
                adapter.submitList(it)
            }
        }
        binding.floatCreateShoppingNewNote.setOnClickListener {
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container_view, ShoppingNewNoteFragment.newInstance(null))
//                .addToBackStack(null)
//                .commit()
            val createNewListDialogFragment = CreateNewListDialogFragment()
            createNewListDialogFragment.show(childFragmentManager, null)
        }
        binding.svSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.sendQuery("%$query%")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.sendQuery("%$newText%")
                }
                return false
            }

        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onShoppingClickDelete(id: Int) {
        viewModel.deleteShoppingNote(id)
    }

    override fun onShoppingClickNote(shoppingNoteItem: ShoppingNoteItem) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view,
                ShoppingNewNoteFragment.newInstance(shoppingNoteItem))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = ShoppingNoteFragment()
    }

}