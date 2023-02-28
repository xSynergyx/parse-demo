package com.example.parsedemo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class CarAdapter(private val context: Context, private val cars: List<Car>) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = cars[position]
        holder.bind(car)
    }

    override fun getItemCount() = cars.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val carImageView = itemView.findViewById<ImageView>(R.id.car_image_iv)
        private val makeTextView = itemView.findViewById<TextView>(R.id.make_tv)
        private val modelTextView = itemView.findViewById<TextView>(R.id.model_tv)
        private val horsepowerTextView = itemView.findViewById<TextView>(R.id.horsepower_tv)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(car: Car) {
            makeTextView.text = car.getMake()
            modelTextView.text = car.getModel()
            horsepowerTextView.text = car.getModel()

            // Load the car image
            Glide.with(context)
                .load(car.getImage()?.url)
                .into(carImageView)
        }

        override fun onClick(v: View?) {

            val car = cars[adapterPosition]
            /*
            // TODO: Navigate to Details screen and pass selected item
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ARTICLE_EXTRA, article)
            context.startActivity(intent)
             */
            Snackbar.make(v!!, "Sweet ${car.getModel()} dude!", Snackbar.LENGTH_SHORT).show()
        }
    }
}