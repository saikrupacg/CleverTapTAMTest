package com.clevertap.tamtest

import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.clevertap.android.sdk.CleverTapAPI
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    lateinit var btnClickme:Button
    var cleverTapDefaultInstance: CleverTapAPI? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()

    }

    fun initialize(){
        btnClickme = findViewById(R.id.btnClickme)

        cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)

        btnClickme.setOnClickListener( View.OnClickListener {
            updateProfileData()
            updateEventInformation()
        })

        CleverTapAPI.createNotificationChannel(
            applicationContext, "saitest", "SaiTamTest", "this is an assessment test",
            NotificationManager.IMPORTANCE_MAX, true,
        )


    }

    private fun updateEventInformation() {
        val prodViewedAction = mapOf(
            "Product ID" to "1",
            "Product Image" to "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg",
            "Product Name " to "CleverTap"
            )
        cleverTapDefaultInstance?.pushEvent("Product Viewed", prodViewedAction)
    }

    private fun updateProfileData() {

        val profileUpdate = HashMap<String, Any>()
        profileUpdate["Name"] = "Bala Saikrupa"
        profileUpdate["Email"] = "puram.saikrupa@gmail.com"
        cleverTapDefaultInstance?.pushProfile(profileUpdate)
    }

}