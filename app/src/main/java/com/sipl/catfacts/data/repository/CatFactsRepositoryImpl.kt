package com.sipl.catfacts.data.repository

import com.sipl.catfacts.data.remote.CatFactApi
import com.sipl.catfacts.data.remote.dto.CatFacts
import com.sipl.catfacts.data.remote.dto.Data
import com.sipl.catfacts.domain.respository.CatFactsRepository
import javax.inject.Inject

class CatFactsRepositoryImpl @Inject constructor(private val catFactApi: CatFactApi) : CatFactsRepository {

    override suspend fun getCatFacts(page: String, limit: String): CatFacts {
        return catFactApi.getCatFacts(page,limit)
    }

}