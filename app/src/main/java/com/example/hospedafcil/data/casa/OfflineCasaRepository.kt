package com.example.hospedafcil.data.casa

import kotlinx.coroutines.flow.Flow

class OfflineCasaRepository (private val CasaDao: CasaDao): CasaRepository {
    override fun getAllCasasStream(): Flow<List<Casa>> = CasaDao.getAllCasas()
    override fun getCasaStream(id: Int): Flow<Casa?> = CasaDao.getCasa(id)

    override suspend fun insertCasa(casa: Casa) = CasaDao.insert(casa)

    override suspend fun deleteCasa(casa: Casa) = CasaDao.delete(casa)

    override suspend fun updateCasa(casa: Casa) = CasaDao.update(casa)

}