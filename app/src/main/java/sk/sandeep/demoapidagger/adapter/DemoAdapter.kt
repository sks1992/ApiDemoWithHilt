package sk.sandeep.demoapidagger.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sk.sandeep.demoapidagger.R
import sk.sandeep.demoapidagger.data.model.RecyclerData
import sk.sandeep.demoapidagger.databinding.DemoItemsBinding

class DemoAdapter : RecyclerView.Adapter<DemoAdapter.DemoViewHolder>() {

    private var listData: List<RecyclerData>? = null

    fun setListData(listData: List<RecyclerData>?) {
        this.listData = listData
    }

    class DemoViewHolder(private val binding: DemoItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: RecyclerData) {
            Glide.with(binding.thumbImage).load(data.owner?.avatar_url).into(binding.thumbImage)
            with(binding) {
                tvName.text = data.name
                tvDescription.text = data.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        return DemoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.demo_items,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(listData!![position])
    }

    override fun getItemCount(): Int {
        if(listData ==null)return 0
        return listData?.size!!
    }

}