package com.example.ompaaloompa.data.remote

import com.example.ompaaloompa.data.models.Oompa
import javax.inject.Inject

class OompaDataSource @Inject constructor(
    private val oompaService: OompaService
): BaseDataSource() {

    suspend fun getOompas(page: Int) = getResult { oompaService.getOompas(page) }

}