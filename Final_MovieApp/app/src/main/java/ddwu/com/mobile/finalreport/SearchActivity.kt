package ddwu.com.mobile.finalreport

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.finalreport.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    val searchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    lateinit var myAdapter : MyAdapter
    val mvData = ArrayList<SubjectDto>()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(searchBinding.root)

        myAdapter = MyAdapter(mvData)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        searchBinding.recyclerView.layoutManager = layoutManager
        searchBinding.recyclerView.adapter = myAdapter

        searchBinding.btnSearch.setOnClickListener {
            val searchTitle = searchBinding.etSearch.text.toString()

            mvData.clear()
            mvData.addAll(getAllMovies(searchTitle))
            myAdapter.notifyDataSetChanged()

            if (mvData.isEmpty()) {
                Toast.makeText(this, "$searchTitle 의 영화는 없습니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "$searchTitle 의 영화는 존재합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        searchBinding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("Range")
    fun  getAllMovies(searchTitle : String) : ArrayList<SubjectDto> {
        val helper = MovieDBHelper(this)
        val db = helper.readableDatabase
        val whereClause = "${MovieDBHelper.COL_TITLE}=?"
        val whereArgs = arrayOf("$searchTitle")
        val cursor = db.query(MovieDBHelper.TABLE_NAME, null, whereClause, whereArgs, null, null, null)
        val mvData = arrayListOf<SubjectDto>()

        with(cursor) {
            while(moveToNext()) {
                val id = getLong( getColumnIndex(BaseColumns._ID) )
                val mvImage = getInt( getColumnIndex(MovieDBHelper.COL_IMAGE) )
                val mvType = getString( getColumnIndex(MovieDBHelper.COL_TYPE) )
                val mvTitle = getString( getColumnIndex(MovieDBHelper.COL_TITLE) )
                val mvDirector = getString( getColumnIndex(MovieDBHelper.COL_DIRECTOR) )
                val mvScore = getString( getColumnIndex(MovieDBHelper.COL_SCORE) )
                val mvDate = getString( getColumnIndex(MovieDBHelper.COL_DATE) )
                val dto = SubjectDto(id, mvImage, mvType, mvTitle, mvDirector, mvScore, mvDate)
                mvData.add(dto)
            }
        }
        cursor.close()
        helper.close()
        return mvData
    }
}