package com.sipl.catfacts.presentation.catsfact

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sipl.catfacts.data.remote.dto.Data
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

var lastIndex: Int=0

@Composable
fun CatListScreen(
    navController: NavHostController,
    viewModel: CatFactsListViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
// Remember a CoroutineScope to be able to launch
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.state.value
    val data= viewModel.getCatsFacts().collectAsLazyPagingItems()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEFEFEF))
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {

            items(data){item->
                FactItem(
                    coin = item,
                    onItemClick = {

                    }
                )

            }
        }
//        if (state.error.isNotBlank()) {
//            Text(
//                text = state.error,
//                color = MaterialTheme.colors.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .align(Alignment.Center)
//            )
//        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
       // loadMoreData(listState, viewModel, state)
    }

}

fun loadMoreData(
    listState: LazyListState,
    viewModel: CatFactsListViewModel,
    state: CatFactListState
) {
    if (listState.isScrolledToTheEnd(state)) {

        val index = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index!!
        if (index>lastIndex) {
            lastIndex = index
           // currentPage=currentPage+1
            println("In the end")
            viewModel.getCatsFacts()
        }

    }
}

private fun LazyListState.isScrolledToTheEnd(state: CatFactListState): Boolean {
    layoutInfo.visibleItemsInfo.lastIndex + firstVisibleItemIndex
//    println("layoutInfo.visibleItemsInfo.lastIndex ${layoutInfo.visibleItemsInfo.lastIndex}")
//    println("layoutInfo.visibleItemsInfo.size ${layoutInfo.visibleItemsInfo.size}")
//    println("layoutInfo.totalItemsCount ${layoutInfo.totalItemsCount}")
//    println("layoutInfo.viewportEndOffset ${layoutInfo.viewportEndOffset}")
//    println("layoutInfo.visibleItemsInfo.lastOrNull ${layoutInfo.visibleItemsInfo.lastOrNull()?.index}")

    val index = layoutInfo.visibleItemsInfo.lastOrNull()?.index

    return (index == layoutInfo.totalItemsCount - 1)&&index!=state.lastIndex
}


@Composable
fun FactItem(coin: Data?, onItemClick: () -> Unit) {

    Card(
        elevation = 15.dp,
        backgroundColor = Color.White,
        modifier = Modifier.absolutePadding(10.dp, 10.dp, 10.dp, 10.dp)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(
                    text = "${coin!!.fact}",
                    style = MaterialTheme.typography.h6,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight(100)

                )


            }
        }
    }
}
