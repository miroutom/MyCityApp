package com.example.mycity.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.R
import com.example.mycity.data.Category
import com.example.mycity.data.Place
import com.example.mycity.other.makePhoneCall
import com.example.mycity.other.openMapWithAddress
import com.example.mycity.ui.CityViewModel

@Composable
fun CityApp(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    val viewModel: CityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var menuClicked by remember { mutableStateOf(false) }
//    val contentType = if (uiState.isShowingListPage){
//        CityContentType.Categories
//    } else if (uiState.isShowingCategoryPage){
//        CityContentType.Places
//    } else {
//        CityContentType.PlaceDetail
//    }

    Scaffold(
        topBar = {
            CityAppBar(
                category = uiState.currentCategory,
                place = uiState.currentPlace,
                onBackButtonClick = { if (uiState.isShowingCategoryPage) viewModel.navigateToListPage()
                else viewModel.navigateToCategoryPage()
                },
                isShowingListPage = uiState.isShowingListPage,
                isShowingCategoryPage = uiState.isShowingCategoryPage,
                onMenuClick = { menuClicked = !menuClicked}
            )
        }
    ) { innerPadding ->
        if (uiState.isShowingListPage){
            MainScreen(
                modifier = modifier
                    .padding(58.dp),
                contentPadding = contentPadding
            )
            if (menuClicked) {
                Menu(
                    categories = uiState.categories,
                    onClick = {
                        viewModel.updateCurrentCategory(it)
                        viewModel.navigateToCategoryPage()
                    },
                    contentPadding = innerPadding,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        } else if (uiState.isShowingCategoryPage){
            CategoryDetail(
                category = uiState.currentCategory,
                onClick = { viewModel.updateCurrentPlace(it)
                    viewModel.navigateToPlacePage()
                },
                onBackPressed = onBackPressed,
                contentPadding = innerPadding,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        } else {
            PlaceDetail(
                selectedPlace = uiState.currentPlace,
                onBackPressed = {
                    viewModel.navigateToPlacePage() },
                onAddressClick = {
                    openMapWithAddress(
                        context,
                        context.getString(uiState.currentPlace.address)
                    )
                },
                onPhoneClick = {
                    makePhoneCall(
                        context,
                        context.getString(uiState.currentPlace.phone)
                    )
                },
                contentPadding = innerPadding)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppBar(
    category: Category,
    place: Place,
    onBackButtonClick: () -> Unit,
    onMenuClick: () -> Unit,
    isShowingListPage: Boolean,
    isShowingCategoryPage: Boolean,
    modifier: Modifier = Modifier,
){
    val isShowingDetailPage = !isShowingListPage && !isShowingCategoryPage

    TopAppBar(
        title = {
            Text(
                text =
                if (isShowingListPage){
                    stringResource(id = R.string.categories_list)
                } else if (isShowingCategoryPage){
                    stringResource(id = category.title)
                } else {
                    stringResource(id = place.title)
                },
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = if (!isShowingListPage){
            {
                IconButton(onClick = onBackButtonClick){
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        } else {
            {
                IconButton(onClick = onMenuClick){
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu"
                    )
                }
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    )
}
