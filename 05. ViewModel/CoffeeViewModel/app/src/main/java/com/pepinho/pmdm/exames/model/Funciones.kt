package com.pepinho.pmdm.exames.model

import android.graphics.BitmapFactory
import android.widget.ImageView
import com.pepinho.pmdm.exames.R

fun ImageView.setImageFromBytes(imageBytes: ByteArray?) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
//        Log.d("Webp", "Admitido")
//    }
    if (imageBytes != null) {
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        this.setImageBitmap(bitmap)
    } else {
        // Manejar el caso donde no hay imagen
        this.setImageResource(R.drawable.coffee)
    }
}