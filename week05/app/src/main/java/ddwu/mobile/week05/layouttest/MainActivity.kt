package ddwu.mobile.week05.layouttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import ddwu.mobile.week05.layouttest.databinding.ActivityMainBinding
import ddwu.mobile.week05.layouttest.databinding.LinearLayoutBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.linear_layout)

//        val mainBinding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(mainBinding.root)

        val linearBinding : LinearLayoutBinding = LinearLayoutBinding.inflate(layoutInflater)
        setContentView(linearBinding.root)

        linearBinding.button2.setOnclickListener {
            //버튼별로 만들어서 쓸수있음
        }

//        val button : Button = findViewById(R.id.button)
//
//        button.setOnClickListener {
//            val layout : LinearLayout = findViewById(R.id.linear_layout)
//            layout.orientation = LinearLayout.VERTICAL
//        }
    }
}