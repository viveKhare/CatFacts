package com.sipl.catfacts.domain.user_cases

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sipl.catfacts.data.remote.dto.Data
import com.sipl.catfacts.domain.respository.CatFactsRepository
import javax.inject.Inject

class CatFactsSource @Inject constructor(private val factsRepository: CatFactsRepository) :
    PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key ?: 1
        val catFacts = factsRepository.getCatFacts(page = "$page").data
        return try {
            LoadResult.Page(
                data = catFacts, prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}