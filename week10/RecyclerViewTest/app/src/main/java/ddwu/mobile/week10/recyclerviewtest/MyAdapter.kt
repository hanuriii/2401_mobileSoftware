package ddwu.mobile.week10.recyclerviewtest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context, val layout: Int, val list: ArrayList<String>)
    : RecyclerView.Adapter< MyAdapter.MyViewHolder >() {

    override fun getItemCount(): Int {  //아이템 몇개 만들건지 전달
        return list.size    // 리스트의 개수 반환
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {   //화면 모양 만들어서 뷰홀더에 반환
        val view = LayoutInflater.from(context).inflate(layout, parent, false)
        Log.d("MyViewHolder", "onCreateViewHolder")
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {    //항목 뷰의 내용과 원본 데이터 연결
        holder.tvText.text = list[position]
        Log.d("MyViewHolder", "onBindViewHolder")
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {  //inner 안쓰면 MyAdapter 랑 완전 다른 클래스임
        val tvText : TextView = view.findViewById(R.id.tvText)

        init {
            view.setOnClickListener {
                Toast.makeText(view.context, "항목 $adapterPosition View 터치!", Toast.LENGTH_SHORT).show()
            }

            //longClick 하면 과목 삭제
            view.setOnLongClickListener {
                list.removeAt(adapterPosition)
                this@MyAdapter.notifyDataSetChanged()
                true
            }

            tvText.setOnClickListener {
                Toast.makeText(view.context, "Textview Click!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}