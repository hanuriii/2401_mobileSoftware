package ddwu.mobile.week07.dialogtest

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.mobile.week07.dialogtest.databinding.ActivityMainBinding
import ddwu.mobile.week07.dialogtest.databinding.DialogInterfaceBinding

class MainActivity : AppCompatActivity() {
    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //라디오 버튼 변수
    var selectedIdx = 0 //변경 가능하게 var 사용
    //체크박스 변수
    var selectedItems = booleanArrayOf(false, false, false) //항목 선택여부를 저장하는 배열 멤버변수 선언 //체크박스 빈상태로 하려면 false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

//        val onOkClick = object: DialogInterface.OnClickListener {
//            override fun onClick(p0: DialogInterface?, whichButton: Int) {
//                Toast.makeText(this@MainActivity, "확인!", Toast.LENGTH_SHORT).show()
//            }
//        }

        mainBinding.button.setOnClickListener {
            val dialogBinding = DialogInterfaceBinding.inflate(layoutInflater)
            val builder : AlertDialog.Builder = AlertDialog.Builder(this).apply {
                setTitle("대화상자 제목")
                //dialog_interface 화면
                setView(dialogBinding.root)
                //setMessage("대화상자 메시지")
//                setItems(R.array.foods) {
//                    dialogInterface: DialogInterface?, idx : Int ->
//                    val food = resources.getStringArray(R.array.foods) //xml에서 바로 정보 가져올때 resources.getStringArray 사용
//                    Toast.makeText(this@MainActivity, "선택: ${ food[idx] }", Toast.LENGTH_SHORT).show()
//                }

                //라디오 버튼
//                setSingleChoiceItems(R.array.foods, selectedIdx) {
//                    dialogInterface : DialogInterface?, idx : Int ->
//                        selectedIdx = idx
//                }
//                setPositiveButton("확인") { p0: DialogInterface?, whichButton: Int ->
//                    val foods = resources.getStringArray(R.array.foods)
//                    Toast.makeText(this@MainActivity, "${ foods[selectedIdx] }", Toast.LENGTH_SHORT).show()
//                }

                //체크박스
//                setMultiChoiceItems(R.array.foods, selectedItems) {
//                    p0 : DialogInterface?, idx : Int, isSelected : Boolean ->
//                        selectedItems[idx] = isSelected
//                }
//                setPositiveButton("확인") { p0: DialogInterface?, whichButton: Int ->
//                    Log.d("MainActivity", "$ { selectedItems.toList() }")
//                    val foods = resources.getStringArray(R.array.foods)
//                    Toast.makeText(this@MainActivity, "${ selectedItems.toList() }", Toast.LENGTH_SHORT).show()
//                }
                setIcon(R.mipmap.ic_launcher_round)
                setNeutralButton("취소", null)
                //람다함수 //매개변수 -> 코드
                setNegativeButton("대기", { p0: DialogInterface?, whichButton: Int
                    -> Toast.makeText(this@MainActivity, "확인!", Toast.LENGTH_SHORT).show() })
                setCancelable(false)
            }
            //val dialog: Dialog = builder.create()
            //dialog.show()
            builder.show()
            Toast.makeText(this,"계속 수행!", Toast.LENGTH_SHORT).show()
        }
    }
}