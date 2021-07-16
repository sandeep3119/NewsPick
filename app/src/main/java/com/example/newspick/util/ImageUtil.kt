package com.example.newspick.util

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class ImageUtil {
    //Changing url to a bitmap and returning Uri
    fun getUriFromUrl(context: Context,imageUrl: String): Uri?{
        val imageUrl=URL("$imageUrl?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
        val bitmap=BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream())

        var savedUri:Uri?=null
        bitmap?.apply {
            savedUri=writeBitmapToFile(context,bitmap)
        }
        return savedUri
    }
    //storing a bitmap in storage and returning Uri
    fun writeBitmapToFile(context: Context,image: Bitmap):Uri{
        val timeStamp= SimpleDateFormat("ddMMyyyy_HHmm").format(Date( ))
        val imageName= "snap_$timeStamp.jpg"
        val wrapper=ContextWrapper(context)
        val directory=wrapper.getDir("savedImages",Context.MODE_PRIVATE)
        val imagePath=File(directory,imageName)
        var fos:FileOutputStream?=null
        try {
            fos= FileOutputStream(imagePath)
            image.compress(Bitmap.CompressFormat.JPEG,100,fos)
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            fos?.close()
        }
        return Uri.parse(imagePath.absolutePath)
        }
    }