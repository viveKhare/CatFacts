package com.sipl.catfacts.presentation

sealed class Screens(val route: String) {
    object CatListScreen: Screens("cat_list_screen")

}
