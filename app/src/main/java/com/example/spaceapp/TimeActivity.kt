package com.example.spaceapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import android.widget.LinearLayout
import android.support.annotation.NonNull
import android.support.v4.view.ViewCompat.animate
import android.R.attr.scaleX
import android.R.attr.scaleY
import android.R.attr.start
import kotlinx.android.synthetic.main.activity_time.*


class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

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

    }
}
