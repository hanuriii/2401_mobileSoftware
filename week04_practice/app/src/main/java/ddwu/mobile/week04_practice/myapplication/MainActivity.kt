package ddwu.mobile.week04_practice.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick1(view : View) {
        val myName : EditText = findViewById(R.id.etName)
        val myPhone : EditText = findViewById(R.id.etPhone)

        val name: String = myName.text.toString()
        val phone: String = myPhone.text.toString()

        val message = "안녕하세요, 저는 $name 입니다. 전화번호는 $phone 입니다."

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun onClick2(view : View) {
        finish()
    }
}