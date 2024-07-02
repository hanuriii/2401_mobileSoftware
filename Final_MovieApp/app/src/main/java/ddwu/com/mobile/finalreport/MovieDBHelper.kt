package ddwu.com.mobile.finalreport

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

class MovieDBHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, 3) {

    val TAG = "MovieDBHelper"

    companion object {
        const val DB_NAME = "movie_db"
        const val TABLE_NAME = "movie_table"
        const val COL_IMAGE = "mvImage"
        const val COL_TYPE = "mvType"
        const val COL_TITLE = "mvTitle"
        const val COL_DIRECTOR = "mvDirector"
        const val COL_SCORE = "mvScore"
        const val COL_DATE = "mvDate"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE ${TABLE_NAME} ( ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${COL_IMAGE} INTEGER, ${COL_TYPE} TEXT, ${COL_TITLE} TEXT, ${COL_DIRECTOR} TEXT, ${COL_SCORE} TEXT, ${COL_DATE} TEXT )"
        Log.d(TAG, CREATE_TABLE)
        db?.execSQL(CREATE_TABLE)

        //영화 예시
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.drawable.img1}, '마블히어로', '스파이더맨', '샘 레이미','8.2', '2024 - 01 - 02')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.drawable.img2}, '마블히어로', '아이언맨', '존 파브로','7.9', '2024 - 03 - 04')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.drawable.img3}, '마블히어로', '호크아이', '버트&버티','6.5', '2024 - 05 - 06')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.drawable.img4}, '마블히어로', '토르', '타이카 와이티티','6.5', '2024 - 07 - 08')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.drawable.img5}, '마블히어로', '캡틴 아메리카', '앨버트 피언','7.0', '2024 - 09 - 10')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ${R.drawable.img6}, '마블히어로', '헐크', '이안','6.1', '2024 - 11 - 12')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }
}