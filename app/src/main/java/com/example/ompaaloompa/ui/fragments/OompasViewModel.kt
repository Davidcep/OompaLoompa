package com.example.ompaaloompa.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ompaaloompa.data.models.Oompa
import com.example.ompaaloompa.data.repository.OompaRepository
import com.example.ompaaloompa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OompasViewModel @Inject constructor(
    private val repository: OompaRepository
) : ViewModel() {

    companion object{
        //Por falta de tiempo no puedo jugar con el número de páginas en la app
        //(Podría montar algo como un ViewPager), así que recojo solo los datos de la primera
        const val PAGE_NUMBER = 1
    }

    val oompas: LiveData<Resource<List<Oompa>>> = repository.getOompas(PAGE_NUMBER)

    fun searchOompa(name: String): LiveData<List<Oompa>> {
        return repository.getOompaByName(name)
    }
}