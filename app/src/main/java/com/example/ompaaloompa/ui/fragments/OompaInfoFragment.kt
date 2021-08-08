package com.example.ompaaloompa.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ompaaloompa.R

class OompaInfoFragment : Fragment() {

    companion object {
        fun newInstance() = OompaInfoFragment()
    }

    private lateinit var viewModel: OompaInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.oompa_info_fragment, container, false)
    }

}