package com.example.workout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workout.databinding.ItemExerciseStatusBinding

import com.example.workout.databinding.ItemHistoryRowBinding


class HistoryAdapter(val items : ArrayList<String>):
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(binding: ItemHistoryRowBinding):RecyclerView.ViewHolder(binding.root) {
        val llMain = binding.llMain
        val tvPosition = binding.tvPosition
        val tvItem = binding.tvItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return HistoryAdapter.ViewHolder(
            ItemHistoryRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date:String = items.get(position)
        holder.tvPosition.text = (position+1).toString()
        holder.tvItem.text = date
        if(position % 2 == 0){
            holder.llMain.setBackgroundColor(Color.parseColor("#D3D3D3"))
        }else{
            holder.llMain.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
}