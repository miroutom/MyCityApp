package com.example.mycity.ui

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.mycity.data.Categories
import com.example.mycity.data.Categories.currentPlace
import com.example.mycity.data.Category
import com.example.mycity.data.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(
        CityUiState(
            categories = Categories.categories,
            currentCategory = Categories.categories.getOrElse(0) {
                Categories.currentCategory
            },
            currentPlace = Categories.currentCategory.places.getOrElse(0) {
                Categories.currentPlace
            }
        )
    )

    val uiState : StateFlow<CityUiState> = _uiState

    fun updateCurrentPlace(selectedPlace: Place){
        _uiState.update {
            it.copy(currentPlace = selectedPlace)
        }
    }

    fun updateCurrentCategory(selectedCategory: Category){
        _uiState.update {
            it.copy(currentCategory = selectedCategory)
        }
    }

    fun navigateToListPage(){
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }

    fun navigateToCategoryPage() {
        _uiState.update {
            it.copy(isShowingCategoryPage = true, isShowingListPage = false)
        }
        println("Navigating to Category Page: isShowingListPage = false, isShowingCategoryPage = true")
    }


    fun navigateToPlacePage(){
        _uiState.update {
            it.copy(
                isShowingCategoryPage = false,
                isShowingListPage = false)
        }
    }
}

data class CityUiState(
    val categories : List<Category> = emptyList(),
    val currentCategory: Category = Categories.currentCategory,
    val currentPlace: Place = Categories.currentPlace,
    val isShowingListPage: Boolean = true,
    val isShowingCategoryPage: Boolean = true
)