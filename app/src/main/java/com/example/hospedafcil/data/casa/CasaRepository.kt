package com.example.hospedafcil.data.casa

import kotlinx.coroutines.flow.Flow

interface CasaRepository {
    fun getAllCasasStream(): Flow<List<Casa>>

    fun getCasaStream(id: Int): Flow<Casa?>

    suspend fun insertCasa(casa: Casa)

    suspend fun deleteCasa(casa: Casa)

    suspend fun updateCasa(casa: Casa)

}