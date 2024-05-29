package ddwu.com.mobile.foodexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.foodexam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    val DETAIL_ACTIVITY_CODE = 100

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val foods = FoodDao().foods
    val adapter = FoodAdapter(foods)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        // btnAdd를 클릭하면 AddFoodActivity 실행
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddFoodActivity::class.java)
            startActivityForResult(intent, DETAIL_ACTIVITY_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == DETAIL_ACTIVITY_CODE) {
            when (resultCode) {
                RESULT_OK -> {
                    val newFood = data?.getSerializableExtra("newFood") as FoodDto
                    newFood?.let {
                        foods.add(it)
                        adapter.notifyDataSetChanged()
                    }
                    Toast.makeText(this, "Added: $newFood", Toast.LENGTH_SHORT).show()
                }

                RESULT_CANCELED -> {
                    Log.d(TAG, "Canceled")
                    Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}