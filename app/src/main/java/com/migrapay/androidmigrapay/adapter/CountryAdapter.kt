package com.migrapay.androidmigrapay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.migrapay.androidmigrapay.R
import com.migrapay.androidmigrapay.model.Country

class CountryAdapter(private var countries: List<Country>, private val onCountrySelected: (Country) -> Unit) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CountryViewHolder,
        position: Int,
    ) {
        val country = countries[position]
        holder.bind(country)
        holder.itemView.findViewById<LinearLayout>(R.id.item_layout).setOnClickListener {
            onCountrySelected(country)
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    fun updateList(newCountries: List<Country>) {
        countries = newCountries
        notifyDataSetChanged()
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val flagTextView: TextView = itemView.findViewById(R.id.flagTextView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        fun bind(country: Country) {
            flagTextView.text = country.flag
            nameTextView.text = country.name
        }
    }
}
