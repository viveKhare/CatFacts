package com.sipl.catfacts.presentation.catsfact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sipl.catfacts.data.remote.dto.Data
import com.sipl.catfacts.domain.user_cases.CatFactsSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CatFactsListViewModel @Inject constructor(private val useCases: CatFactsSource) :
    ViewModel() {
    private var _state = mutableStateOf(CatFactListState())
    val state: State<CatFactListState> = _state

    init {
         getCatsFacts()
//        getCatsFacts().onEach { result ->
//            _state.value = CatFactListState(coins = result)
//        }.launchIn(viewModelScope)

    }

    fun getCatsFacts(): Flow<PagingData<Data>> {
        return Pager(PagingConfig(pageSize = 10)) {
            useCases
        }.flow
    }
//    fun getCatsFacts() {
//        useCases(page = state.value.page.toString()).onEach { result ->
//            when (result) {
//                is Resource.Success<*> -> {
//                    _state.value = CatFactListState(coins = result.data ?: emptyList())
//                }
//                is Resource.Error<*> -> {
//                    _state.value =
//                        CatFactListState(error = result.message ?: "Unexpected error occurred")
//                }
//                is Resource.Loading<*> -> {
//                    _state.value = CatFactListState(isLoading = true)
//                }
//
//            }
//        }.launchIn(viewModelScope)
//    }
}