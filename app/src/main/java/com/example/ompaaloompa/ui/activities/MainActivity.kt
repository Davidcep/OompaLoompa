package com.example.ompaaloompa.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ompaaloompa.R
import com.example.ompaaloompa.ui.fragments.OompasFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, OompasFragment.newInstance())
                    .commitNow()
            setSupportActionBar(findViewById(R.id.custom_toolbar))
        }
    }
}