package ddwu.mobile.week12.activitytest

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ddwu.mobile.week12.activitytest.databinding.ActivityMainBinding
import ddwu.mobile.week12.activitytest.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    val subBinding by lazy {
        ActivitySubBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(subBinding.root)
    }
}