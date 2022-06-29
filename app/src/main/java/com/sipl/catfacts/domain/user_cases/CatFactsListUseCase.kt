package com.sipl.catfacts.domain.user_cases

import com.sipl.catfacts.constant.Resource
import com.sipl.catfacts.data.remote.dto.Data
import com.sipl.catfacts.domain.respository.CatFactsRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CatFactsListUseCase @Inject constructor(private val catFactsRepository: CatFactsRepository) {
    operator fun invoke( page:String="1",limit:String="10") = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(catFactsRepository.getCatFacts(limit=limit, page = page).data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?:"Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Please check internet connectivity"))
        }
    }
}