package ddwu.mobile.week06.eventtest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import ddwu.mobile.week06.eventtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root) //root = 가장 바깥 레이아웃

        mainBinding.myButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "익명 리스너 인터페이스 구현", Toast.LENGTH_SHORT).show()

            Log.d(TAG, "익명 리스너 로그 출력")
        }
    }

//    mainBinding.mainLayout.setOnLongClickListener {
//            Toast.makeText(this@MainActivity, "롱클릭!", Toast.LENGTH_SHORT).show()
//            true //롱클릭은 반환타입이 있어야함
//        }

     /*메인에서 구현*/
//        mainBinding.myButton.setOnClickListener(this) //연결 역할 //this = Activity임
//    }
//
//    override fun onClick(v: View?) {
//        Toast.makeText(this, "Activity가 리스너 인터페이스 구현", Toast.LENGTH_SHORT).show()
//    }


    /*리스너 인터페이스 클래스 구현*/
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(mainBinding.root) //root = 가장 바깥 레이아웃
//
//        val myClick = MyClick(this)
//
//        mainBinding.myButton.setOnClickListener(myClick)
//    }
//
//    class MyClick(val context: Context) : View.OnClickListener {
//        override fun onClick(v: View?) {
//            Toast.makeText(context, "리스너 인터페이스 구현 클래스", Toast.LENGTH_SHORT).show()
//        }
//    }

}