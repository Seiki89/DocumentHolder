package com.example.documentholder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.documentholder.card.CardFragment
import com.example.documentholder.document.DocumentFragment
import com.example.documentholder.note.NoteFragment
import com.example.documentholder.pass.PassFragment
import com.example.documentholder.databinding.ActivityWorkBinding
import com.example.documentholder.settings.SettingsFragment

class WorkActivity : AppCompatActivity() {
    lateinit var bind: ActivityWorkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityWorkBinding.inflate(layoutInflater)
        setContentView(bind.root)

        openFrag(DocumentFragment.newInstance(),R.id.docFragmentHolder)


        bind.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Document -> {openFrag(DocumentFragment.newInstance(),R.id.docFragmentHolder)}
                R.id.Card -> {openFrag(CardFragment.newInstance(),R.id.docFragmentHolder)}
                R.id.Password -> {openFrag(PassFragment.newInstance(),R.id.docFragmentHolder)}
                R.id.Note -> {openFrag(NoteFragment.newInstance(),R.id.docFragmentHolder)}
                R.id.Settings -> {openFrag(SettingsFragment.newInstance(),R.id.docFragmentHolder)}
            }
            true

        }
    }

    private fun openFrag(f: Fragment, idHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .commit()
    }
}
