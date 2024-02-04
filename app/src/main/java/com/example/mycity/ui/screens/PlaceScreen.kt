package com.example.mycity.ui.screens

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.data.Categories
import com.example.mycity.data.Place
import com.example.mycity.ui.theme.MyCityTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceListItem(
    place: Place,
    onItemClick: (Place) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = { onItemClick(place) }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painterResource(id = place.image),
                contentDescription = stringResource(place.title),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = place.title),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}


@Composable
fun PlaceDetail(
    selectedPlace: Place,
    onBackPressed: () -> Unit,
    onPhoneClick: () -> Unit,
    onAddressClick: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
){
    BackHandler {
        onBackPressed()
        println("BackHandler called: navigating back")
    }

    val layoutDirection = LocalLayoutDirection.current

    Box{
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ){
            Image(
                painterResource(id = selectedPlace.image),
                contentDescription = stringResource(selectedPlace.title),
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Row(
                modifier = Modifier.padding(start = 16.dp)
            ){
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "phone",
                )
                Text(
                    text = stringResource(id = selectedPlace.phone),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            onPhoneClick()
                        }
                )
            }
            Row(
                modifier = Modifier.padding(start = 16.dp)
            ){
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "address"
                )
                Text(
                    text = stringResource(id = selectedPlace.address),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            onAddressClick()
                        }
                )
            }
            Row(
                modifier = Modifier.padding(start = 16.dp)
            ){
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "rating"
                )
                Text(
                    text = (selectedPlace.rating).toString(),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Row(
                modifier = Modifier.padding(start = 16.dp)
            ){
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "schedule"
                )
                Text(
                    text = stringResource(id = selectedPlace.schedule),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Row(
                modifier = Modifier.padding(start = 16.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.baseline_money_24),
                    contentDescription = "bill",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(id = selectedPlace.averageBill),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PlaceDetailPreview(){
    MyCityTheme {
        PlaceDetail(
            selectedPlace = Categories.currentPlace,
            onBackPressed = { },
            contentPadding = PaddingValues(8.dp),
            onAddressClick = { },
            onPhoneClick = { }
        )
    }
}

//@Preview
//@Composable
//fun CategoryDetailPreview(){
//    MyCityTheme {
//        CategoryList(
//            categories = Categories.categories,
//            onClick = {},
//            contentPadding = PaddingValues(8.dp)
//        )
//    }
//}