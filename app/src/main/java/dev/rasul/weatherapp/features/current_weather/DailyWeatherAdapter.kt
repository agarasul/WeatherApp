package dev.rasul.weatherapp.features.current_weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.rasul.weatherapp.Constants
import dev.rasul.weatherapp.R
import dev.rasul.weatherapp.data.db.entity.WeatherEntity
import dev.rasul.weatherapp.databinding.DailyWeatherItemBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class DailyWeatherAdapter :
    RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherHolder>() {

    private val sdf = SimpleDateFormat("EEE dd", Locale.getDefault())

    private var selected: WeatherEntity.DailyItem? = null

    private var selectedView: ConstraintLayout? = null

    var dailyItems: List<WeatherEntity.DailyItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            selected = value.firstOrNull()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.daily_weather_item, parent, false)
        return DailyWeatherHolder(view)
    }

    override fun onBindViewHolder(holder: DailyWeatherHolder, position: Int) {
        val daily = dailyItems[holder.adapterPosition]
        Picasso.get()
            .load(Constants.baseUrlForIcons + "${daily.weather?.icon}@4x.png")
            .into(holder.binding.imgWeather)

        holder.binding.txtTemperature.text =
            "${daily.temperature?.day?.roundToInt()}°/${daily.temperature?.night?.roundToInt()}°"


        holder.binding.txtDayOfWeek.text = sdf.format(Date(daily.dt!!.toLong() * 1000))
    }

    override fun getItemCount() = dailyItems.size

    inner class DailyWeatherHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = DailyWeatherItemBinding.bind(itemView)
    }
}