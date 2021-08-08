package com.example.ompaaloompa.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ompaaloompa.data.models.Oompa

@Dao
interface OompaDao {

    @Query("SELECT * FROM oompas")
    fun getAllOompas() : LiveData<List<Oompa>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Oompa>)

    @Query("SELECT * FROM oompas WHERE first_name LIKE :name")
    fun getOompaByName(name: String): LiveData<List<Oompa>>

    @Query("SELECT * FROM oompas WHERE id = :id")
    fun getOompaById(id: Int): LiveData<Oompa>
}