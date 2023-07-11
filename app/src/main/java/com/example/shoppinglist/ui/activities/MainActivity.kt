package com.example.shoppinglist.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.replace
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.ui.screens.notes.NoteFragment
import com.example.shoppinglist.ui.screens.shopping_note.ShoppingNoteFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, NoteFragment.newInstance())
            .commit()

        binding.bnvBottomNav.setOnItemSelectedListener {
        when(it.itemId){
            R.id.notesMenuItem -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, NoteFragment.newInstance())
                .commit()
            R.id.shoppingListMenuItem -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, ShoppingNoteFragment.newInstance())
                .commit()
        }
            true
        }
    }
}
