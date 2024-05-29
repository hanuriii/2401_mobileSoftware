package ddwu.mobile.week12.activitytest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.mobile.week12.activitytest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val TAG = "MainActivity"
    val DETAIL_ACTIVITY_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //버튼 누를 때 동작하는거
        binding.button.setOnClickListener {
            val dto = FoodDto(R.mipmap.ic_launcher, "치킨", 10)

            val intent = Intent(this, DetailActivity::class.java)

            startActivityForResult(intent, DETAIL_ACTIVITY_CODE)

//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra("food", dto)
//            startActivity(intent)

//            명시적 방법
//            val intent = Intent (this, DetailActivity::class.java)
//
//            intent.putExtra("subject", "Mobile Software")
//            startActivity(intent)

//            묵시적 방법
//            val intent = Intent (Intent.ACTION_DIAL, Uri.parse("tel: 012-3456-7890")) //전화 불러오기
//            val intent = Intent (Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))   //홈페이지 불러오기
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == DETAIL_ACTIVITY_CODE) {
            when (requestCode) {
                RESULT_OK -> {
                    val result = data?.getStringExtra("result_data")
                    Log.d(TAG, result!!)
//                    Toast.makeText(this, "Result: $result", Toast.LENGTH_SHORT).show()
                }

                RESULT_CANCELED -> {
                    Log.d(TAG, "Canceled")
                }
            }
        }
    }
}