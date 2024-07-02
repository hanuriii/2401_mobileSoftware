// 과제명: 영화 정보 관리 앱
// 분반: 01분반
// 학번: 20211557 성명: 김하늘
// 제출일: 2024년 6월 20일

package ddwu.com.mobile.finalreport

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.finalreport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val ADD = 1
    val UPDATE = 2

    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var helper : MovieDBHelper
    lateinit var myAdapter : MyAdapter
    val mvData = ArrayList<SubjectDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        helper = MovieDBHelper(this)
        mvData.clear()
        mvData.addAll(getAllMovies(helper))
        myAdapter = MyAdapter(mvData)
        myAdapter.notifyDataSetChanged()

        mainBinding.recyclerView.layoutManager = layoutManager
        mainBinding.recyclerView.adapter = myAdapter

        myAdapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(this@MainActivity, "수정페이지", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                intent.putExtra("dto", mvData[position])
                startActivityForResult(intent, UPDATE)
            }
        })

        myAdapter.setOnItemLongClickListener(object : MyAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int) {
                val builder = AlertDialog.Builder(view.context).apply {
                    setTitle("연락처 삭제")
                    setMessage("${mvData[position].mvTitle} 연락처를 삭제하시겠습니까?")
                    setNegativeButton("취소", null)
                    setPositiveButton("삭제") { dialog, id ->
                        Toast.makeText(this@MainActivity, "삭제 완료", Toast.LENGTH_SHORT).show()
                        // 데이터베이스에서 항목 삭제
                        deleteMovie(helper, mvData[position].id)
                        // mvData 리스트에서 항목 삭제
                        mvData.removeAt(position)
                        myAdapter.notifyItemRemoved(position)
                        myAdapter.notifyItemRangeChanged(position, mvData.size)
                    }
                }
                builder.show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.page1 -> {
                Toast.makeText(this, "영화 추가 페이지", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, AddActivity::class.java)
                startActivityForResult(intent, ADD)
            }
            R.id.page2 -> {
                Toast.makeText(this, "개발자 소개 페이지", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.page3 -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this).apply {
                    setTitle("앱 종료")
                    setMessage("앱을 종료하시겠습니까?")
                    setPositiveButton("종료", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            finish()
                        }
                    })
                    setNegativeButton("취소", null)
                }
                builder.show()
            }
            R.id.page4 -> {
                Toast.makeText(this, "영화 검색 페이지", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            ADD -> {
                if (resultCode == RESULT_OK) {
                    mvData.clear()
                    mvData.addAll(getAllMovies(helper))
                    myAdapter.notifyDataSetChanged()
                }
                else {
                    Toast.makeText(this@MainActivity, "취소", Toast.LENGTH_SHORT).show()
                }
            }
            UPDATE -> {
                if (resultCode == RESULT_OK) {
                    mvData.clear()
                    mvData.addAll(getAllMovies(helper))
                    myAdapter.notifyDataSetChanged()
                }
                else {
                    Toast.makeText(this@MainActivity, "취소", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun deleteMovie(helper: MovieDBHelper, id: Long) {
        val db = helper.writableDatabase
        val whereClause = "${BaseColumns._ID}=?"
        val whereArgs = arrayOf(id.toString())
        db.delete("${MovieDBHelper.TABLE_NAME}", whereClause, whereArgs)
        helper.close()
    }

    @SuppressLint("Range")
    fun  getAllMovies(helper: MovieDBHelper) : ArrayList<SubjectDto> {
        val db = helper.readableDatabase
        val cursor = db.query(MovieDBHelper.TABLE_NAME, null, null, null, null, null, null)
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