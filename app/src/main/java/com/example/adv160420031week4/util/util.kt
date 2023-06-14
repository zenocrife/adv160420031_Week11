package com.example.adv160420031week4.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.example.adv160420031week4.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


@BindingAdapter("android:imageUrl","android:progressBar")
fun loadPhotoUrl(view: ImageView,url: String,progressBar: ProgressBar){
    view.loadImage(url,progressBar)
}

fun ImageView.loadImage(url: String?, progressBar:ProgressBar){
  Picasso.get().load(url).resize(400,400).centerCrop().error(R.drawable.ic_baseline_error_24)
      .into(this,object : Callback{
      override fun onError(e: Exception?) {
          TODO("Not yet implemented")
      }
      override fun onSuccess() {
          progressBar.visibility = View.GONE
      }
  }
  )
}