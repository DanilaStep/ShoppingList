package com.example.shoppinglist.ui.screens.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.NoteItem
import com.example.shoppinglist.ui.adapters.NoteAdapter
import com.example.shoppinglist.databinding.FragmentNoteBinding
import com.example.shoppinglist.domain.models.NoteModel
import com.example.shoppinglist.ui.screens.new_note.NewNoteFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteFragment : Fragment(), NoteAdapter.ClickListener {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModels()

    private val adapter = NoteAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNote.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.notesStateFlow.collect {
                adapter.submitList(it)
            }
        }

        binding.rvNote2.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.notesStateFlow.collect {
                adapter.submitList(it)
            }
        }

        binding.btnCreateNewNote.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, NewNoteFragment.newInstance(null))
                .addToBackStack(null)
                .commit()
        }

//        binding.btnCreateNewNote.setOnLongClickListener{
////            Toast.makeText(requireContext(), "click", Toast.LENGTH_LONG).show()
//            val myDialog = MyAlertDialog(requireContext())
//        val dialog = MaterialAlertDialogBuilder(requireContext())
//            .setPositiveButton("подтвердить") { _, _ ->
//                Toast.makeText(requireContext(), "podtverdit'", Toast.LENGTH_SHORT).show()
//            }
//            .setNegativeButton("cancel"){ _, _ ->
//                Toast.makeText(requireContext(), "cancel'", Toast.LENGTH_SHORT).show()
//            }
//            .create()
//            dialog.show()
//        true
//        }

        binding.svSearches.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

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
                return true
            }

        })

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickDelete(id: Int) {
        viewModel.deleteNote(id)
    }

    override fun onClickNote(noteModel: NoteModel) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, NewNoteFragment.newInstance(noteModel))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = NoteFragment()
    }
}
