package com.example.ompaaloompa.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ompaaloompa.R
import com.example.ompaaloompa.databinding.FragmentOompasBinding
import com.example.ompaaloompa.utils.OompaAdapter
import com.example.ompaaloompa.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OompasFragment : Fragment() {

    private val viewModel: OompasViewModel by viewModels()
    private lateinit var binding: FragmentOompasBinding

    private val adapter = OompaAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentOompasBinding.inflate(inflater, container, false)
        binding.rvMainOompas.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        bindViews()
    }

    private fun bindViews() {
        bindAdapters()
        bindEditText()
    }

    private fun bindAdapters(){
        bindData()
    }

    private fun bindEditText() {
        binding.etMainSearch.doAfterTextChanged { it ->
            if(it.toString() == ""){
                bindData()
            } else {
                searchByName(it.toString())
            }
        }
    }

    private fun bindData() {
        viewModel.oompas.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    adapter.submitList(it.data)
                }
                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    //Con tiempo podría haber hecho una ProgressBar
                }
            }
        })
    }

    private fun searchByName(name: String) {
        viewModel.searchOompa(name).observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.custom_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                if(binding.etMainSearch.visibility == View.VISIBLE) binding.etMainSearch.visibility = View.GONE
                else binding.etMainSearch.visibility = View.VISIBLE
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
