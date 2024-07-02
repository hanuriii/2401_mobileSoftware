package ddwu.com.mobile.finalreport

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.finalreport.databinding.ActivityUpdateBinding
import java.util.Calendar

class UpdateActivity : AppCompatActivity() {

    val updateBinding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }

    lateinit var helper : MovieDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(updateBinding.root)

        helper = MovieDBHelper(this)

        val calendar = Calendar.getInstance()
        val year : Int = calendar.get(Calendar.YEAR)
        val month : Int = calendar.get(Calendar.MONTH) + 1
        val day : Int = calendar.get(Calendar.DAY_OF_MONTH)

        val dto = intent.getSerializableExtra("dto") as SubjectDto
        updateBinding.upImage.setImageResource(dto.mvImage)
        updateBinding.upType.setText(dto.mvType.toString())
        updateBinding.upTitle.setText(dto.mvTitle.toString())
        updateBinding.upDirector.setText(dto.mvDirector.toString())
        updateBinding.upScore.setText(dto.mvScore.toString())
        updateBinding.upDate.setText(dto.mvDate.toString())

        updateBinding.upbtnDate.setOnClickListener {
            DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
                    updateBinding.upDate.text = "$year - ${month + 1} - $day"
                }
            }, year, month, day).show()
        }

        updateBinding.btnAdd.setOnClickListener {
            val mvType = updateBinding.upType.text.toString()
            val mvTitle = updateBinding.upTitle.text.toString()
            val mvDirector = updateBinding.upDirector.text.toString()
            val mvScore = updateBinding.upScore.text.toString()
            val mvDate = updateBinding.upDate.text.toString()

            // 필수 항목 확인
            if (mvType.isEmpty() || mvTitle.isEmpty() || mvDirector.isEmpty() || mvScore.isEmpty()) {
                Toast.makeText(this, "필수항목을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //항목이 모두 입력된 상태
            dto.mvType = mvType
            dto.mvTitle = mvTitle
            dto.mvDirector = mvDirector
            dto.mvScore = mvScore
            dto.mvDate = mvDate

            if ( updateMovie(dto) > 0 ) {
                setResult(RESULT_OK)
                Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show()
            }
            else {
                setResult(RESULT_CANCELED)
            }
            finish()
        }

        updateBinding.btnCancel.setOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun updateMovie(dto: SubjectDto) : Int {
        val helper = MovieDBHelper(this)
        val db = helper.writableDatabase
        val updateValue = ContentValues()

        updateValue.put(MovieDBHelper.COL_IMAGE, dto.mvImage)
        updateValue.put(MovieDBHelper.COL_TYPE, dto.mvType)
        updateValue.put(MovieDBHelper.COL_TITLE, dto.mvTitle)
        updateValue.put(MovieDBHelper.COL_DIRECTOR, dto.mvDirector)
        updateValue.put(MovieDBHelper.COL_SCORE, dto.mvScore)
        updateValue.put(MovieDBHelper.COL_DATE, dto.mvDate)

        val whereClause = "${BaseColumns._ID}=?"
        val whereArgs = arrayOf(dto.id.toString())

        val result = db.update(MovieDBHelper.TABLE_NAME, updateValue, whereClause, whereArgs)

        helper.close()
        return result
    }
}