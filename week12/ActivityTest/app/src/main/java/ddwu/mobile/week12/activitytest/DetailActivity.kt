package ddwu.mobile.week12.activitytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.mobile.week12.activitytest.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    val detailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    val TAG = "DetailActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(detailBinding.root)

        detailBinding.btnResult.setOnClickListener {
            val resultIntent = Intent() //완전히 비어있는 인텐트 만들기
            resultIntent.putExtra("result_data", "돌려받은 결과")

            setResult(RESULT_OK, resultIntent)  //정상 응답 반환
            finish()
        }

//        putExtra 불러오기
//        val food = intent.getSerializableExtra("food") as FoodDto  //typecasting 하면 FoodDto 객체가 됨
//        Log.d(TAG, food.toString())   //? null이 있으면 예외처리함
    }
}