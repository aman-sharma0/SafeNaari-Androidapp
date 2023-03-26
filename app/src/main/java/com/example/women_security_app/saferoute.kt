package com.example.women_security_app

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import java.util.ArrayList
import java.util.HashMap

class saferoute : AppCompatActivity() {

    val multiValueMap: MutableMap<String, ArrayList<String>> = HashMap()

    lateinit var source: EditText
   lateinit var desti:EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saferoute)

        source = findViewById<EditText>(R.id.source)
         desti = findViewById<EditText>(R.id.desti)

        multiValueMap["SKNSITSLonavla"] = ArrayList()
        multiValueMap["SKNSITSLonavla"]!!.add("chhtrapatishivajimaharajchow-railwaystation")

        multiValueMap["PUNEBarshi"] = ArrayList()
        multiValueMap["PUNEBarshi"]!!.add("Indapur-Kurwadi")

        multiValueMap["LONAVLAMumbai"] = ArrayList()
        multiValueMap["LONAVLAMumbai"]!!.add("Kalyan")

        multiValueMap["JAMMUKashmir"] = ArrayList()
        multiValueMap["JAMMUKashmir"]!!.add("Banihal")


        multiValueMap["PUNELonavla"] = ArrayList()
        multiValueMap["PUNELonavla"]!!.add("Kamseth--ShivajiNagar")


        multiValueMap["PUNEAkluj"] = ArrayList()
        multiValueMap["PUNEAkluj"]!!.add("yavat -indapur")


        val btn= findViewById<Button>(R.id.btnsearch)

        btn.setOnClickListener {

            val a = source.text.toString()
            val b = desti.text.toString()
            val result = a.plus(b)
            listdata(result)
            val pref = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = pref.edit()
            editor.putString("name",result)
            editor.apply()
            editor.commit()
        }




    }

    private fun listdata(result: String) {

        val listview = findViewById<ListView>(R.id.listview)
        val size = multiValueMap[result]!!.size
        val name = arrayOfNulls<String>(size)

        for(i in 0 until size)
        {
            //Toast.makeText(applicationContext, multiValueMap[result]?.get(i),Toast.LENGTH_LONG).show()
            name.set(i, multiValueMap[result]?.get(i).toString())

        }

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
            name)

        listview.adapter = arrayAdapter
        listview.setOnItemClickListener { adapterView, view, i, l ->
//            val intent = Intent(applicationContext, citizen_all_month_qrcode::class.java)
//            startActivity(intent)
            val a = source.text.toString()
            val b = desti.text.toString()
            try {
                val uri = Uri.parse("https://www.google.co.in/maps/dir/"+a+"/"+ b)

                val intent = Intent(Intent.ACTION_VIEW,uri)
                intent.setPackage("com.google.android.apps.maps")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

            }catch (e: ActivityNotFoundException)
            {
                val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
                val intent = Intent(Intent.ACTION_VIEW,uri)
                intent.setPackage("com.google.android.apps.maps")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }

        }
    }
}