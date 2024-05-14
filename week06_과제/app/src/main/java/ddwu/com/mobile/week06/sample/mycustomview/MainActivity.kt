package ddwu.com.mobile.week06.sample.mycustomview

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.week06.sample.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        val myClick = object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val posX = event!!.x //항상 null이 아니라는 뜻
                val posY = event!!.y
                mainBinding.myCustomView.posX = posX
                mainBinding.myCustomView.posY = posY
                mainBinding.myCustomView.invalidate() //invalidate 호출함

                return false
            }
        }

        val myLongClick = View.OnLongClickListener {
            mainBinding.myCustomView.paintColor = Color.YELLOW
            mainBinding.myCustomView.invalidate()
            true
        }

        mainBinding.myCustomView.setOnTouchListener(myClick)
        mainBinding.myCustomView.setOnLongClickListener(myLongClick)
    }
}