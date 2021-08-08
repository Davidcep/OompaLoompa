package com.example.ompaaloompa.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ompaaloompa.data.models.Oompa
import com.example.ompaaloompa.data.repository.OompaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OompaInfoViewModel @Inject constructor(
    private val repository: OompaRepository
) : ViewModel() {

    fun searchOompaById(id: Int): LiveData<Oompa> {
        return repository.getOompaById(id)
    }
}