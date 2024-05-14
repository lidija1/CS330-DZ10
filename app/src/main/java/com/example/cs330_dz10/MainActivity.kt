package com.example.cs330_dz10

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cs330_dz10.ui.theme.CS330DZ10Theme
//import com.example.cs330_dz10.Shipment
//import com.example.cs330_dz10.ShipmentDetailsActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var shipments: List<Shipment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listView = ListView(this)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        listView.adapter = adapter
        setContentView(listView)

        val internetPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
        if (internetPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 1)
        } else {
            loadShipments()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            showShipmentDetails(position)
        }
    }

//    private fun loadShipments() {
//        val json = "file.json"
//        val gson = Gson()
//        val listType = object : TypeToken<List<Shipment>>() {}.type
//        shipments = gson.fromJson(json, listType)
//        adapter.clear()
//        for (shipment in shipments) {
//            adapter.add("${shipment.receiverName}, ${shipment.receiverEmail}")
//        }
//    }


    private fun loadShipments() {
        val json = "[\n" +
                "  {\n" +
                "    \"receiverName\": \"John Doe\",\n" +
                "    \"receiverEmail\": \"john.doe@example.com\",\n" +
                "    \"countryTo\": \"USA\",\n" +
                "    \"cityTo\": \"New York\",\n" +
                "    \"streetTo\": \"123 Main St\",\n" +
                "    \"weight\": 2.5,\n" +
                "    \"fragile\": false\n" +
                "  },\n" +
                "  {\n" +
                "    \"receiverName\": \"Jane Smith\",\n" +
                "    \"receiverEmail\": \"jane.smith@example.com\",\n" +
                "    \"countryTo\": \"Canada\",\n" +
                "    \"cityTo\": \"Toronto\",\n" +
                "    \"streetTo\": \"456 Elm St\",\n" +
                "    \"weight\": 1.8,\n" +
                "    \"fragile\": true\n" +
                "  }\n" +
                "]"
        val gson = Gson()
        val listType = object : TypeToken<List<Shipment>>() {}.type
        shipments = gson.fromJson(json, listType)
        adapter.clear()
        for (shipment in shipments) {
            adapter.add("${shipment.receiverName}, ${shipment.receiverEmail}")
        }
    }


    private fun showShipmentDetails(position: Int) {
        val intent = Intent(this, ShipmentDetailsActivity::class.java)
        intent.putExtra("shipment", Gson().toJson(shipments[position]))
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadShipments()
            } else {
                // Ako korisnik ne odobri pristup internetu, možemo prikazati poruku ili prekinuti proces
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun ShipmentListPreview() {
        CS330DZ10Theme {
            // Pozivamo funkciju koja prikazuje listu pošiljaka
            ShipmentList()
        }
    }

}
