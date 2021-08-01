package dev.rasul.weatherapp.features.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.rasul.weatherapp.R
import dev.rasul.weatherapp.data.db.entity.PlacesEntity
import dev.rasul.weatherapp.databinding.SearchPlaceItemBinding

class SearchPlaceAdapter(private val onClick: (PlacesEntity) -> Unit) :
    RecyclerView.Adapter<SearchPlaceAdapter.SearchPlaceHolder>() {

    var places: List<PlacesEntity> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPlaceHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_place_item, parent, false)
        return SearchPlaceHolder(view)
    }

    override fun onBindViewHolder(holder: SearchPlaceHolder, position: Int) {
        val place = places[holder.adapterPosition]
        holder.binding.mainView.setOnClickListener {
            onClick.invoke(place)
        }
        holder.binding.txtPlaceName.text = place.name
        holder.binding.txtCountryName.text = place.country
    }

    override fun getItemCount() = places.size

    inner class SearchPlaceHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = SearchPlaceItemBinding.bind(itemView)
    }
}