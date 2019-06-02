package com.example.spaceapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info.*
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.content_info.*


class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            intent = Intent(this,TimeActivity::class.java)
            startActivity(intent)
        }

        val series = LineGraphSeries<DataPoint>(
            arrayOf<DataPoint>(
                DataPoint(0.0, 0.0),
                DataPoint(1.18, 0.19),
                DataPoint(1.48, 0.32),
                DataPoint(1.77, 0.4),
                DataPoint(2.07, 0.6),
                DataPoint(2.96, 0.67),
                DataPoint(5.93, 0.7),
                DataPoint(8.89, 0.74)
            )
        )
        graph.addSeries(series)
        graph.viewport.isScalable = true
        graph.viewport.isScrollable = true
        graph.viewport.setScalableY(true)
        graph.viewport.setScrollableY(true)
    }
}
