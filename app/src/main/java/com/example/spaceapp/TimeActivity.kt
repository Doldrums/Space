package com.example.spaceapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_time.*
import android.view.MotionEvent
import kotlinx.android.synthetic.main.bottom_sheet.*


class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        var dX  = 100.0F
        var dY = 400.0F


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
                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    linerallayout.animate().scaleX(1F).scaleY(1F).setDuration(300).start()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })



        img_planet.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
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

    }
}
