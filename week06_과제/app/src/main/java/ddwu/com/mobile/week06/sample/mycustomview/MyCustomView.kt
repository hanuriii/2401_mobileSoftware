package ddwu.com.mobile.week06.sample.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

const val TAG="MyCustomView"

class MyCustomView : View {
    var posX : Float
    var posY : Float = 200f
    var paintColor : Int

    init {
        posX = 200f
        paintColor = Color.RED
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        posX = event!!.x //항상 null이 아니라는 뜻
//        posY = event!!.y
//        invalidate() //onDraw 직접 호출 못해서 invalidate 호출함
//
//        return true
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.LTGRAY)
        val paint = Paint()
        paint.setColor(paintColor)
        canvas?.drawCircle(posX, posY, 100f, paint)
        Log.d (TAG, "($posX, $posY)")
    }
}