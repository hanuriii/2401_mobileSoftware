package ddwu.mobile.week11.foodrecyclerviewtest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.mobile.week11.foodrecyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //데이터 준비
        val foods = FoodDao().foods

        //어댑터 생성
        val adapter = FoodAdapter(foods)

        //레이아웃 매니저 생성 및 설정
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        //RecyclerView 에 레이아웃 매니저 및 Adapter 설정
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        val listener = object : FoodAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(this@MainActivity, "${foods[position]}", Toast.LENGTH_SHORT).show()
                //클릭하면 삭제하는 기능 추가
//                foods.removeAt(position)
//                adapter.notifyDataSetChanged()
            }
        }
        //커스텀 리스너 사용
        adapter.setOnItemClickListener(listener)

        //실습) 롱클릭하면 삭제
        val longClickListener = object: FoodAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int): Boolean {
                //dialog 생성
                val builder = AlertDialog.Builder(view.context)
                builder.setTitle("삭제 확인")
                builder.setMessage("삭제하시겠습니까?")

                //확인 버튼 클릭 시
                builder.setPositiveButton("확인") { dialog, id ->
                    foods.removeAt(position)
                    adapter.notifyDataSetChanged()
                }

                builder.show()

                return true
            }
        }

        adapter.setOnItemLongClickListener(longClickListener)

        //추가 기능 부분 -> 음식 입력 후 버튼을 누르면 목록이 추가됨
        binding.btnAdd.setOnClickListener {
            val foodName = binding.editText.text.toString()
            if (foodName.isNotEmpty()) {
                val newFood = FoodDto(R.drawable.food04, foodName, 10)
                foods.add(newFood)
                adapter.notifyDataSetChanged()
                binding.editText.text.clear()
            }
        }
    }

}