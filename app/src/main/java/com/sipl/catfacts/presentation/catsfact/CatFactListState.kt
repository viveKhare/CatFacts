package com.sipl.catfacts.presentation.catsfact

import androidx.paging.PagingData
import com.sipl.catfacts.data.remote.dto.Data

class CatFactListState constructor(
    val isLoading: Boolean = false,
    var coins:PagingData<Data>? =null,
    val error: String = "",
    var page: Int = 1,
    val limit: String = "10",
    var lastIndex: Int = 0
)