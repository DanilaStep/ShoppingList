package com.example.shoppinglist.ui.screens.new_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.shoppinglist.databinding.FragmentNewNoteBinding
import com.example.shoppinglist.domain.models.NoteModel
import com.example.shoppinglist.utils.TimeManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewNoteFragment : Fragment() {

//    @Inject
//    lateinit var mfactory: NewNoteViewModel.Factory
    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = requireNotNull(_binding)

//    @Provides
//    fun getNoteItem():NoteItem = noteItem
//    private val viewModel: NewNoteViewModel by viewModels()

    private val viewModel: NewNoteViewModel by viewModels ()
//        mfactory.build(noteItem=arguments?.getSerializable(KEY_FOR_FILL_NOTE_ITEM) as NoteItem)
//    LambdaFactory(this){ stateHandle ->
//        mfactory.build(noteItem,stateHandle)
//        NewNoteViewModel.provideFactory(mfactory, this, arguments, noteItem)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val noteModel = arguments?.getSerializable(KEY_FOR_FILL_NOTE_ITEM) as NoteModel?

        noteModel?.let { fillScreen(it) }

        binding.topAppBar.setNavigationOnClickListener {

           noteModel?.let {
                viewModel.updateNote(
                    it.copy(
                        title = binding.etTitle1.text.toString(),
                        content = binding.etContent.text.toString(),
                        time = TimeManager.getCurrentTime()
                    )
                )
            } ?: run{
                    viewModel.notBlanc(
                            title = binding.etTitle1.text.toString(),
                            content = binding.etContent.text.toString()
                        )
            }
            requireActivity().supportFragmentManager.popBackStack()
        }

    }

    private fun fillScreen(noteModel: NoteModel) {
        binding.etTitle1.setText(noteModel.title)
        binding.etContent.setText(noteModel.content)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_FOR_FILL_NOTE_ITEM = "key_for_note_item"

        fun newInstance(noteModel: NoteModel?) = NewNoteFragment().apply {
            arguments = Bundle().apply {
                putSerializable(KEY_FOR_FILL_NOTE_ITEM, noteModel)
            }
        }
    }
}