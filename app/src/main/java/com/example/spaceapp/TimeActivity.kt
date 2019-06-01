package com.example.spaceapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_time.*
import android.view.MotionEvent
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_time.*
import java.util.*


class TimeActivity : AppCompatActivity() {

    val r = 1 * 0.2966 * Math.pow(10.0,12.0)
    val G = 6.67408 *Math.pow(10.0,-11.0)
    val M = 1.9 * Math.pow(10.0,38.0)
    val c = 299792458

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        var dX  = 100.0F
        var dY = 600.0F

        val currentTime = Calendar.getInstance().time
        txt_date.text= currentTime.toString()


        val actionBar = supportActionBar
        actionBar!!.hide()

        val llBottomSheet = findViewById<LinearLayout>(R.id.bottom_sheet)

        val bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet)

        bottomSheetBehavior.peekHeight = 450
        bottomSheetBehavior.isHideable = false

        // настройка колбэков при изменениях
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

                // этот код скрывает кнопку сразу же
                // и отображает после того как нижний экран полностью свернется
                if (BottomSheetBehavior.STATE_DRAGGING == newState) {
                    linerallayout.animate().scaleX(0F).scaleY(0F).setDuration(300).start()
                    txt_desc.visibility = View.VISIBLE
                    ln_info.visibility =View.VISIBLE
                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    linerallayout.animate().scaleX(1F).scaleY(1F).setDuration(300).start()
                    txt_desc.visibility = View.INVISIBLE
                    ln_info.visibility =View.INVISIBLE
                    img_planet.x = 250.0F
                    img_planet.y = 600.0F
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })



        img_planet.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {


                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                    Log.e("KEK_TAG",view.y.toString())
                    if(view.y>=750) {
                        txt_time.text = "0.9"
                        img_accent1.visibility=View.VISIBLE
                        img_accent2.visibility=View.INVISIBLE
                        img_accent3.visibility=View.INVISIBLE
                        img_accent4.visibility=View.INVISIBLE
                    }else if(view.y<750&&view.y>=650){
                        txt_time.text = "0.873279"
                        img_accent2.visibility=View.VISIBLE
                        img_accent3.visibility=View.INVISIBLE
                        img_accent4.visibility=View.INVISIBLE
                        img_accent1.visibility=View.INVISIBLE
                    }else if(view.y<650&&view.y>=550){
                        txt_time.text = "0.82673"
                        img_accent3.visibility=View.VISIBLE
                        img_accent1.visibility=View.INVISIBLE
                        img_accent2.visibility=View.INVISIBLE
                        img_accent4.visibility=View.INVISIBLE
                    }else if(view.y<550&&view.y>=450){
                        txt_time.text = "0.22464"
                        img_accent4.visibility=View.VISIBLE
                        img_accent1.visibility=View.INVISIBLE
                        img_accent2.visibility=View.INVISIBLE
                        img_accent3.visibility=View.INVISIBLE
                    }else {
                        txt_time.text = "Normal"
                        img_accent1.visibility=View.INVISIBLE
                        img_accent2.visibility=View.INVISIBLE
                        img_accent3.visibility=View.INVISIBLE
                        img_accent4.visibility=View.INVISIBLE
                    }
                }

                MotionEvent.ACTION_MOVE -> view.animate()
                    .x(event.rawX + dX)
                    .y(event.rawY + dY)
                    .setDuration(0)
                    .start()
                MotionEvent.ACTION_UP -> {
                }
                else -> return@OnTouchListener false


            }
            true
        })

        fab_info.setOnClickListener {
            intent = Intent(this,InfoActivity::class.java)
            startActivity(intent)
        }

    }
}
