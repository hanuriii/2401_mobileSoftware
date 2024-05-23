package ddwu.mobile.week09.menutest

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.mobile.week09.menutest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var selected : Int = 3  //체크했는지 기억하는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        registerForContextMenu(binding.tvText)  //롱클릭하면 context menu를 띄울 수 있음
    }

    override fun onCreateContextMenu(   //롱클릭하면 호출되는 함수
        menu: ContextMenu?, //메뉴 만드는 작업 //지금은 비어있는 메뉴
        v: View?,   //해당하는 뷰 확인용
        menuInfo: ContextMenu.ContextMenuInfo?  //메뉴를 선택했는지
    ) {
        menuInflater.inflate(R.menu.menu_main, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {   //메뉴 선택했을때 반응
        Toast.makeText(this, "Context!!!", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when(selected) {
            3 -> menu?.findItem(R.id.subitem03)?.setChecked(true)
            4 -> menu?.findItem(R.id.subitem04)?.setChecked(true)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.subitem01 -> Toast.makeText(this, "SubItem01", Toast.LENGTH_SHORT).show()
            R.id.subitem02 -> Toast.makeText(this, "SubItem02", Toast.LENGTH_SHORT).show()
            R.id.subitem03 -> {
                Toast.makeText(this, "SubItem03", Toast.LENGTH_SHORT).show()
                selected = 3    //체크했는지 확인
            }
            R.id.subitem04 -> {
                Toast.makeText(this, "SubItem04", Toast.LENGTH_SHORT).show()
                selected = 4    //체크했는지 확인
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun onMenuClick(item: MenuItem) {

    }
}