package ddwu.com.mobile.foodexam

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ddwu.com.mobile.foodexam.databinding.ActivityAddFoodBinding

class AddFoodActivity : AppCompatActivity() {
    val TAG = "AddFoodActivity"

    val binding by lazy {
        ActivityAddFoodBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val subject = intent.getSerializableExtra("foodName")

        binding.btnSave.setOnClickListener {
            val foodName = binding.etNewFood.text.toString()
            val countryName = binding.etCountry.text.toString()

            if (foodName.isNotEmpty()) {
                val newFood = FoodDto(foodName, countryName)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("newFood", newFood)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        binding.btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}