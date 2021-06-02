package com.example.newappkotlin_1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newappkotlin_1.R
import com.example.newappkotlin_1.db.Currency

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val arrayList: ArrayList<Currency> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun setItems(list: List<Currency>) {
        if (arrayList.isNotEmpty()) {
            arrayList.clear()
        }
        this.arrayList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val result: TextView = view.findViewById(R.id.tv_result_history)
        private val date: TextView = view.findViewById(R.id.tv_date_history)

        fun bind(currency: Currency) {
            result.text = currency.result
            date.text = currency.dateFormat
        }
    }
}