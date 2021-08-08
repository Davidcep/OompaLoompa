package com.example.ompaaloompa.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.ompaaloompa.data.local.OompaDao
import com.example.ompaaloompa.data.models.Oompa
import com.example.ompaaloompa.data.remote.OompaDataSource
import com.example.ompaaloompa.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class OompaRepository @Inject constructor(
    private val oompaDataSource: OompaDataSource,
    private val oompaDao: OompaDao
) {

    fun getOompas(page: Int) = performGetOperation(
        databaseQuery = { oompaDao.getAllCharacters() },
        networkCall = { oompaDataSource.getOompas(page) },
        saveCallResult = { it -> oompaDao.insertAll(it.results) }
    )

    fun getOompaByName(name: String): LiveData<List<Oompa>> {
        return oompaDao.getOompaByName(name);
    }

    private fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                                   networkCall: suspend () -> Resource<A>,
                                   saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val source = databaseQuery.invoke().map { Resource.success(it) }
            emitSource(source)

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)

            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
                emitSource(source)
            }
        }
}