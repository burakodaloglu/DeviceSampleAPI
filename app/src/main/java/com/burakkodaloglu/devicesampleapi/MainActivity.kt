package com.burakkodaloglu.devicesampleapi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fill the Adapter with the available activity names/classes
        val listMainActivities = findViewById<ListView>(R.id.listMainActivities)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, ACT_NAMES
        )
        listMainActivities.adapter = adapter
        listMainActivities.onItemClickListener = OpenActivityListener()
    }

    /**
     * Open the activity of corresponding position.
     * pos is the index in ACT_CLASSES
     */
    inner class OpenActivityListener : AdapterView.OnItemClickListener {

        override fun onItemClick(
            arg0: AdapterView<*>, arg1: View, pos: Int,
            arg3: Long
        ) {
            val intent = Intent(this@MainActivity, ACT_CLASSES[pos])
            startActivity(intent)
        }

    }

    companion object {
        private val ACT_NAMES = arrayOf(
            "Battery",
            "Location - LocationManager",
            "NFC - NfcManager",
            "Notifications - LedManager",
            "Touch - TouchManager",
            "Sleep and Wakeup - PowerManager",
            "Informations - SYSTEM",
            "Reset device"
        )

        private val ACT_CLASSES = arrayOf<Class<*>>(
            BatteryActivity::class.java,
            LocationActivity::class.java,
            NfcActivity::class.java,
            NotificationActivity::class.java,
            TouchActivity::class.java,
            SleepActivity::class.java,
            InfoActivity::class.java,
            ResetActivity::class.java
        )
    }
}