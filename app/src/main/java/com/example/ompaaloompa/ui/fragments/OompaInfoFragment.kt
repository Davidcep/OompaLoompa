package com.example.ompaaloompa.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.ompaaloompa.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OompaInfoFragment : Fragment() {

    companion object {
        fun newInstance() = OompaInfoFragment()
    }

    private lateinit var viewModel: OompaInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_oompa_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(OompaInfoViewModel::class.java)
        val idOompa = arguments?.let{ requireArguments().getInt("id") }
    }
}