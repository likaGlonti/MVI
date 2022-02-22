package composable.superheroes.mvi.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import composable.superheroes.mvi.databinding.DataItemLayoutBinding
import composable.superheroes.mvi.domain.DataEntity

class DataAdapter : ListAdapter<DataEntity, DataAdapter.ViewHolder>(DataDiffCallBack) {
    var getPosition: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
        holder.onClick = {
            getPosition?.invoke(position)
        }
    }

    inner class ViewHolder(private val binding: DataItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var onClick: (() -> Unit)? = null

        fun bindItem(item: DataEntity) {
            with(binding) {
                title.text = item.title
                description.text = item.description
                root.setOnClickListener {
                    onClick?.invoke()
                }
            }
        }
    }
}

object DataDiffCallBack : DiffUtil.ItemCallback<DataEntity>() {
    override fun areItemsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
        return oldItem == newItem
    }

}