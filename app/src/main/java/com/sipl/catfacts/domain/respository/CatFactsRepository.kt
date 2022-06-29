package com.sipl.catfacts.domain.respository

import com.sipl.catfacts.data.remote.dto.CatFacts
import com.sipl.catfacts.data.remote.dto.Data

interface CatFactsRepository {
    suspend fun getCatFacts(page:String="1",limit:String="10"): CatFacts
}