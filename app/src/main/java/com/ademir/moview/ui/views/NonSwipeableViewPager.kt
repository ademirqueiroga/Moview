package com.ademir.moview.views

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent


class NonSwipeableViewPager : androidx.viewpager.widget.ViewPager {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}


//    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
//        // Never allow swiping to switch between pages
//        return false
//    }

    public override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }

}
