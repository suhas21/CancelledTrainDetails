package com.example.suhas.cancelledtrains

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.icu.util.Calendar
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.suhas.cancelledtrains.CancelledTrainDetails.CancelledTrainsPOJO
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun getInfo(v: View)
    {
        val r = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.railwayapi.com/").build()
        val a3 = r.create(CanTrainINFF::class.java)

        val call: Call<CancelledTrainsPOJO> = a3.loadTotalData(et1.text.toString())
        call.enqueue(object : retrofit2.Callback<CancelledTrainsPOJO> {
            override fun onFailure(call: Call<CancelledTrainsPOJO>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed ", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<CancelledTrainsPOJO>, response: Response<CancelledTrainsPOJO>) {

                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                val list = mutableListOf<String>()
                val tname = response.body()
                list.add("Total Cancelled Train Details are")
                list.add(" ")
                val t = tname!!.trains
                for (x in t!!)
                {
                    list.add("Train Name:"+x.name)
                    list.add("Train Number:"+x.number)
                    list.add("Source Station Name:"+x.source.name)
                    list.add("Source Station Code: "+x.source.code)
                    list.add("Destination Station Name:"+x.dest.name)
                    list.add("Destination Station Code: "+x.dest.code)
                    list.add(" ")
                }
                val myadapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_single_choice, list)
                cantrainview.adapter = myadapter
            }
        })
    }
}
