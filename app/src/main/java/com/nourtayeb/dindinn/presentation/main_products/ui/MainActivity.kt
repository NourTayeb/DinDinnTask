package com.nourtayeb.dindinn.presentation.main_products.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nourtayeb.dindinn.R
import com.nourtayeb.dindinn.presentation.main_products.ui.fragments.MainFragment
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}