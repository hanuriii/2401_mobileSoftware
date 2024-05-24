package ddwu.com.mobile.week06.sample.mycustomview

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.week06.sample.mycustomview.databinding.ActivityMainBinding
import kotlinx.coroutines.selects.select

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

//    var selected : Int = 1
    var contextSelected : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        registerForContextMenu(binding.myCustomView)    //커스텀뷰 등록
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)    //메뉴 만든거 가져옴
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        when(selected) {
//            1 -> menu?.findItem(R.id.red)?.setChecked(true)
//            2 -> menu?.findItem(R.id.green)?.setChecked(true)
//            3 -> menu?.findItem(R.id.blue)?.setChecked(true)
//        }
//
//        return super.onPrepareOptionsMenu(menu)
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.big -> {
                binding.myCustomView.radius += 50f
                binding.myCustomView.invalidate()
            }
            R.id.small -> {
                binding.myCustomView.radius -= 50f
                binding.myCustomView.invalidate()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
//        when (v?.id) {    //id로 메뉴 구분하는법
//            R.id.myCustomView -> menuInflater.inflate(R.menu.menu_color, menu)
//        }
        menuInflater.inflate(R.menu.menu_color, menu)

        when(contextSelected) {
            1 -> menu?.findItem(R.id.red)?.setChecked(true)
            2 -> menu?.findItem(R.id.green)?.setChecked(true)
            3 -> menu?.findItem(R.id.blue)?.setChecked(true)
        }

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.red -> {
                binding.myCustomView.setColor(Color.RED)
                binding.myCustomView.invalidate()
                Toast.makeText(this, "RED", Toast.LENGTH_SHORT).show()
                contextSelected = 1
            }

            R.id.green -> {
                binding.myCustomView.setColor(Color.GREEN)
                binding.myCustomView.invalidate()
                Toast.makeText(this, "GREEN", Toast.LENGTH_SHORT).show()
                contextSelected = 2
            }

            R.id.blue -> {
                binding.myCustomView.setColor(Color.BLUE)
                binding.myCustomView.invalidate()
                Toast.makeText(this, "BLUE", Toast.LENGTH_SHORT).show()
                contextSelected = 3
            }
        }
        return super.onContextItemSelected(item)
    }
}