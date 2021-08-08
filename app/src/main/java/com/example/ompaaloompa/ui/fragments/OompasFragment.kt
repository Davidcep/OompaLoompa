package com.example.ompaaloompa.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ompaaloompa.R
import com.example.ompaaloompa.data.models.Oompa
import com.example.ompaaloompa.utils.OompaAdapter
import com.example.ompaaloompa.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OompasFragment : Fragment() {

    companion object {
        fun newInstance() = OompasFragment()
    }

    private lateinit var viewModel: OompasViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextSearch: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_oompas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(OompasViewModel::class.java)
        recyclerView = view.findViewById(R.id.rv_main_oompas)
        editTextSearch = view.findViewById(R.id.et_main_search)
        bindViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.custom_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                if(editTextSearch.visibility == View.VISIBLE) {
                    editTextSearch.visibility = View.GONE
                } else {
                    editTextSearch.visibility = View.VISIBLE
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun bindViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = OompaAdapter(mutableListOf())
        bindData()
    }

    private fun bindData() {
        viewModel.oompas.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    val adapter = recyclerView.adapter as OompaAdapter
                    adapter.oompas.clear()
                    adapter.oompas = it.data as MutableList<Oompa>
                    adapter.notifyDataSetChanged()
                    //progress_bar.visibility = View.GONE
                    //if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                //Resource.Status.LOADING ->
                    //progress_bar.visibility = View.VISIBLE
            }
        })
    }
}