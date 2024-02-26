package com.burakkodaloglu.devicesampleapi

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.datalogic.device.BootType
import com.datalogic.device.DeviceException
import com.datalogic.device.ErrorManager
import com.datalogic.device.power.PowerManager

class ResetActivity : AppCompatActivity() {

    private var listReset: ListView? = null

    private var pm: PowerManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        ErrorManager.enableExceptions(true)

        try {
            pm = PowerManager()
        } catch (e: DeviceException) {
            android.util.Log.e(javaClass.name, "While creating ResetActivity", e)
            return
        }

        // Set listArray and bootTypes
        setArray()

        val adapter = ArrayAdapter<Any>(this, android.R.layout.simple_list_item_1, listArray)
        listReset = findViewById<ListView>(R.id.listReset)
        listReset!!.adapter = adapter
        listReset!!.onItemClickListener = ResetAdapter()
    }

    /**
     * Set bootTypes to all BootType enum values, and listArray to the names of
     * each.
     */
    private fun setArray() {
        if (listArray[0] == "-1" || bootTypes == null) {
            bootTypes = BootType.values()
            val inter = bootTypes!!.size

            listArray = arrayOfNulls(inter)
            for (i in bootTypes!!.indices) {
                listArray[i] = bootTypes!![i].name
            }
        }
    }

    /**
     * Reset the terminal on an item click. pos is the BootType index in bootTypes
     */
    inner class ResetAdapter : AdapterView.OnItemClickListener {

        override fun onItemClick(
            arg0: AdapterView<*>, arg1: View, pos: Int,
            arg3: Long
        ) {
            try {
                pm!!.reboot(bootTypes!![pos])
            } catch (e: DeviceException) {
                android.util.Log.e(javaClass.name, "While onItemClick", e)
                return
            }

        }

    }

    companion object {
        private var listArray: Array<String?> = arrayOf("-1")
        private var bootTypes: Array<BootType>? = null
    }
}
