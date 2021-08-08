package com.example.ompaaloompa.ui.fragments

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
        return inflater.inflate(R.layout.fragment_oompa_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            val id = requireArguments().getInt("id");
        }
    }
}