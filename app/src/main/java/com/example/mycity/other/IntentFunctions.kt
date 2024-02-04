package com.example.mycity.other

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast

fun openMapWithAddress(context: Context, address: String) {
    val uri = Uri.parse("geo:0,0?q=Nizhny Novgorod, $address")
    val intent = Intent(Intent.ACTION_VIEW, uri)

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "There is no suitable app on your phone", Toast.LENGTH_SHORT).show()
    }

}

fun makePhoneCall(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phoneNumber")
    context.startActivity(intent)
}
