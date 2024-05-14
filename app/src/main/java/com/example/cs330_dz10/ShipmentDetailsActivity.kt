package com.example.cs330_dz10

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class ShipmentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = intent.getStringExtra("shipment")
        val shipment = Gson().fromJson(json, Shipment::class.java)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        setContentView(layout)

        val receiverNameTextView = TextView(this)
        receiverNameTextView.text = "Receiver Name: ${shipment.receiverName}"
        layout.addView(receiverNameTextView)

        val receiverEmailTextView = TextView(this)
        receiverEmailTextView.text = "Receiver Email: ${shipment.receiverEmail}"
        layout.addView(receiverEmailTextView)

        val addressTextView = TextView(this)
        addressTextView.text = "Address: ${shipment.countryTo}, ${shipment.cityTo}, ${shipment.streetTo}"
        layout.addView(addressTextView)

        val weightTextView = TextView(this)
        weightTextView.text = "Weight: ${shipment.weight} kg"
        layout.addView(weightTextView)

        val fragileImageView = ImageView(this)
        if (shipment.fragile) {
            fragileImageView.setImageResource(R.drawable.img1)
        } else {
            fragileImageView.setImageResource(R.drawable.img2)
        }
        layout.addView(fragileImageView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}

