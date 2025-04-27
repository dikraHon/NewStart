package com.example.newstart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.databinding.StudentLayoutBinding

class RecyclerViewAdapter(private val items: MutableList<Person>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {


    class ItemViewHolder(val binding: StudentLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Person, position: Int, onClick: (Int) -> Unit) {

            binding.nameTextView.text = item.name

            binding.ratingTextView.setOnClickListener {
                onClick.invoke(position)
            }

            binding.ratingTextView.text = item.rating.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = StudentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        holder.onBind(items[position], position) { position ->
            items[position].rating += 0.01
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = items.size
}