package com.example.newspick.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.newspick.util.ImageUtil
import kotlinx.coroutines.delay
import java.lang.Exception

class ImageDownloadWorker(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {
    val imageUrl=inputData.getString("imageUrl")
    override fun doWork(): Result {
        try {
            val savedUri = imageUrl?.let { ImageUtil().getUriFromUrl(applicationContext,it) }
            return Result.success(workDataOf("IMAGE_URI" to savedUri.toString()))
        }catch (e:Exception){
            return Result.failure(workDataOf("FAILURE" to e.localizedMessage))
        }
    }
}