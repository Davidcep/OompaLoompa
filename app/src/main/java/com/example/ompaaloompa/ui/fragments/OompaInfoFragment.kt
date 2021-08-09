package com.example.ompaaloompa.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ompaaloompa.R
import com.example.ompaaloompa.databinding.FragmentOompaInfoBinding
import com.example.ompaaloompa.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OompaInfoFragment : Fragment() {

    private lateinit var viewModel: OompaInfoViewModel
    private lateinit var binding: FragmentOompaInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOompaInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(OompaInfoViewModel::class.java)
        val id = arguments?.let{ requireArguments().getInt("id") }
        if(id != null) bindData(id)
    }

    private fun bindData(id: Int){
        viewModel.searchOompaById(id).observe(viewLifecycleOwner, Observer {
            with(binding){
                tvInfoName.text = it.first_name
                tvInfoLastName.text = it.last_name
                tvInfoProfession.text = it.profession
                tvInfoGender.text = it.gender
                executePendingBindings()
            }
        })
    }
}