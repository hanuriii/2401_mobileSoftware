package ddwu.mobile.week04.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myText : TextView = findViewById(R.id.myText)
        myText.text = "Mobile!!!" //텍스트뷰는 수정가능
        myText.setTextColor(Color.GREEN)
    }

    fun onClick(view : View) {
        val myEdit : EditText = findViewById(R.id.myEdit) //입력한 값 //텍스트뷰 아님
        val myText : TextView = findViewById(R.id.myText) //텍스트뷰에 저장

        myText.text = myEdit.text //입력한 값이 버튼을 누르면 텍스트뷰에 저장되도록 실행

        //myEdit.text = "copied" //에러 //바꿀수없음
        myEdit.setText("copied") //edit text는 (텍스트뷰가 아니라) 값을 바꾸려면 setter 사용해서 바꿔야함

        Toast.makeText(this, "버튼을 클릭함", Toast.LENGTH_SHORT).show()
    }
}