package ddwu.mobile.week10.recyclerviewtest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.mobile.week10.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val dao = SubjectDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dataList = dao.dataList

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL    //세로방향으로 스크롤됨   //HORIZONTAL로 하면 가로 방향으로 스크롤됨
        binding.recyclerView.layoutManager = layoutManager

        val adapter = MyAdapter(this, R.layout.list_item, dataList) //adapter 결합
        binding.recyclerView.adapter = adapter

        //과목 추가
        binding.button.setOnClickListener {
//            val subject = binding.etSubject.text.toString()
//            dataList.add(subject)
            //위의 2줄을 한번에 쓰기
            dataList.add(binding.etSubject.text.toString())
            adapter.notifyDataSetChanged()
        }
    }
}