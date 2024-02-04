package com.example.mycity.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycity.data.Category
import com.example.mycity.data.Place

@Composable
fun CategoryDetail(
    category: Category,
    onClick: (Place) -> Unit,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
){
    BackHandler {
        onBackPressed()
    }

    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxSize(),
    ) {
        items(category.places, key = { place: Place -> place.title }) { place ->
            PlaceListItem(place = place, onItemClick = onClick)
        }
    }
}