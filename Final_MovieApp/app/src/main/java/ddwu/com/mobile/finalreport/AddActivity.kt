package ddwu.com.mobile.finalreport

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.finalreport.databinding.ActivityAddBinding
import java.util.Calendar
import kotlin.random.Random

class AddActivity : AppCompatActivity() {

    val TAG = "AddActivity"

    val addBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    //랜덤으로 이미지 지정(17-27)
    val imageList = listOf (
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5,
        R.drawable.img6
    )

    val randomIndex = Random.nextInt(imageList.size)
    val randomImage = imageList[randomIndex]

    lateinit var helper : MovieDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(addBinding.root)
        Log.d(TAG, "추가화면")
        addBinding.etImage.setImageResource(randomImage)

        helper = MovieDBHelper(this)    //MovieDBHelper 호출 -> DB 파일 생성

        //날짜 입력 위젯
        val calendar = Calendar.getInstance()
        val year : Int = calendar.get(Calendar.YEAR)
        val month : Int = calendar.get(Calendar.MONTH) + 1
        val day : Int = calendar.get(Calendar.DAY_OF_MONTH)

        addBinding.etDate.text = "$year - ${month + 1} - $day"

        addBinding.btnDate.setOnClickListener{
            DatePickerDialog(this@AddActivity, object: DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
                    addBinding.etDate.text = "$year - ${month + 1} - $day"
                }
            }, year, month, day).show()
        }

        //영화 추가
        addBinding.btnAdd.setOnClickListener {
            val mvType = addBinding.etType.text.toString()
            val mvTitle = addBinding.etTitle.text.toString()
            val mvDirector = addBinding.etDirector.text.toString()
            val mvScore = addBinding.etScore.text.toString()
            val mvDate = addBinding.etDate.text.toString()

            //필수항목 입력 안하면 안내 토스트
            if (mvType.isEmpty() || mvTitle.isEmpty() || mvDirector.isEmpty() || mvScore.isEmpty()) {
                Toast.makeText(this, "필수항목을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //새로운 항목 추가
            val newDto = SubjectDto(0, randomImage, mvType, mvTitle, mvDirector, mvScore, mvDate)

            val result = addMovie(newDto)
            if ( result > 0 ) {
                setResult(RESULT_OK)
            }
            else {
                setResult(RESULT_CANCELED)
            }
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        addBinding.btnCancel.setOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun addMovie(newDto : SubjectDto) : Long {
        val db = helper.writableDatabase
        val newRow = ContentValues()

        newRow.put(MovieDBHelper.COL_IMAGE, newDto.mvImage)
        newRow.put(MovieDBHelper.COL_TYPE, newDto.mvType)
        newRow.put(MovieDBHelper.COL_TITLE, newDto.mvTitle)
        newRow.put(MovieDBHelper.COL_DIRECTOR, newDto.mvDirector)
        newRow.put(MovieDBHelper.COL_SCORE, newDto.mvScore)
        newRow.put(MovieDBHelper.COL_DATE, newDto.mvDate)

        val result = db.insert(MovieDBHelper.TABLE_NAME, null, newRow)

        helper.close()

        return result
    }
}