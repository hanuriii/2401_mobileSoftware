package ddwu.mobile.week11.foodrecyclerviewtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ddwu.mobile.week11.foodrecyclerviewtest.databinding.ListItemBinding

class FoodAdapter (val foods : ArrayList<FoodDto>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

//    override fun getItemCount(): Int {
//        return foods.size
//    }
    override fun getItemCount(): Int = foods.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//        return FoodViewHolder(itemView)
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
//        holder.photo.setImageResource( foods[position].photo )
//        holder.food.text = foods[position].food
//        holder.count.text = foods[position].count.toString()    //count는 int형이라서 string형으로 변환
        holder.itemBinding.ivPhoto.setImageResource( foods[position].photo )
        holder.itemBinding.tvFood.text = foods[position].food
        holder.itemBinding.tvCount.text = foods[position].count.toString()
    }

    inner class FoodViewHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
//        val photo = view.findViewById<ImageView>(R.id.ivPhoto)
//        val food = view.findViewById<TextView>(R.id.tvFood)
//        val count = view.findViewById<TextView>(R.id.tvCount)
        val TAG = "FoodViewHolder"

        init {
            itemBinding.root.setOnClickListener {
//                Log.d(TAG, "${foods[adapterPosition]}")
                listener.onItemClick(it, adapterPosition)   //click하면 전달받은 리스너가 수행됨
            }

            //롱클릭
            itemBinding.root.setOnLongClickListener {
                //롱클릭 리스너 멤버함수 호출
                longClickListener.onItemLongClick(it, adapterPosition)
            }
        }
    }

    //Listener를 지정하는 인터페이스 선언
    interface OnItemClickListener {
        fun onItemClick(view : View, position: Int) : Unit
    }

    //전달받은 Listener를 보관할 멤버변수
    lateinit var listener : OnItemClickListener

    //외부에서 구현한 listener를 전달받아 멤버변수에 저장
    fun setOnItemClickListener (listener: OnItemClickListener) {
        this.listener = listener
    }

    //롱클릭 -> 인터페이스 선언
    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int) : Boolean
    }

    //롱클릭 -> listener 멤버변수
    lateinit var longClickListener: OnItemLongClickListener

    //롱클릭 -> set 형태
    fun setOnItemLongClickListener (listener: OnItemLongClickListener) {
        this.longClickListener = listener
    }
}