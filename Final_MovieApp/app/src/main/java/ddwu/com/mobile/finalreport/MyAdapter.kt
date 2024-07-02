package ddwu.com.mobile.finalreport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.finalreport.databinding.ListItemBinding

class MyAdapter(val mvData: ArrayList<SubjectDto>) : RecyclerView.Adapter<MyAdapter.MyViewHolder> () {

    override fun getItemCount(): Int = mvData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding, longClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = mvData[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            clickListener?.onItemClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            longClickListener?.onItemLongClick(it, position)
            true
        }
    }

    class MyViewHolder(val itemBinding: ListItemBinding, listener: OnItemLongClickListener?) : RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemView.setOnLongClickListener {
                listener?.onItemLongClick(it, adapterPosition)
                true
            }
        }

        fun bind(movie: SubjectDto) {
            itemBinding.mvImage.setImageResource(movie.mvImage)
            itemBinding.mvType.text = movie.mvType
            itemBinding.mvTitle.text = movie.mvTitle
            itemBinding.mvDirector.text = movie.mvDirector
            itemBinding.mvScore.text = movie.mvScore
            itemBinding.mvDate.text = movie.mvDate
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    var clickListener: OnItemClickListener? = null

    fun setOnItemClickListener( listener: OnItemClickListener? ) {
        this.clickListener = listener
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int)
    }

    var longClickListener: OnItemLongClickListener? = null

    fun setOnItemLongClickListener ( listener: OnItemLongClickListener? ) {
        this.longClickListener = listener
    }
}