package com.example.in_app_review

import android.app.Activity
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.tasks.Task

class ReviewManager(
    private val activity: Activity,
    private val reviewManager: ReviewManager,
    private val callback: CallBack
) {

    fun initRequestTask() {
        val requestReviewTask = reviewManager.requestReviewFlow()
        requestReviewTask.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                // Request succeeded and a ReviewInfo instance was received
                val reviewInfo: ReviewInfo = request.result
                requestReview(activity, reviewInfo)
                callback.success(reviewInfo)
            } else {
                // Request failed
                request.exception?.let { callback.failure(it) }
            }
        }
    }

    private fun requestReview(activity: Activity, reviewInfo: ReviewInfo) {
        val launchReviewTask: Task<*> = reviewManager.launchReviewFlow(activity, reviewInfo)
        launchReviewTask.addOnCompleteListener {
            callback.complete()
        }
    }
}

interface CallBack {
    fun failure(e: Exception)
    fun success(info: ReviewInfo)
    fun complete()
}