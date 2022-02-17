package com.example.inappreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.in_app_review.CallBack
import com.example.in_app_review.ReviewManager
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory

class MainActivity : AppCompatActivity(),CallBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reviewManager: com.google.android.play.core.review.ReviewManager = ReviewManagerFactory.create(this)
        ReviewManager(this, reviewManager = reviewManager,this).initRequestTask()
    }

    override fun failure(e: Exception) {
    Log.e("error",e.localizedMessage)
    }

    override fun success(info: ReviewInfo) {
    }

    override fun complete() {
        Log.e("complete","complete")

    }

}