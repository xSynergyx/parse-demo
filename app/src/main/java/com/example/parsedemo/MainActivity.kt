package com.example.parsedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class MainActivity : AppCompatActivity() {
    private val garage = mutableListOf<Car>()
    private lateinit var carsRv: RecyclerView
    private lateinit var carAdapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logoutButton = findViewById<Button>(R.id.logout_button)
        val addCarFab = findViewById<FloatingActionButton>(R.id.add_car_fab)

        // RecyclerView setup
        carsRv = findViewById(R.id.car_recyclerview)
        carAdapter = CarAdapter(this, garage)
        carsRv.adapter = carAdapter
        carsRv.layoutManager = LinearLayoutManager(this)

        // Get cars from Parse DB
        queryCars()

        // Parse logout functionality
        logoutButton.setOnClickListener {
            ParseUser.logOut()
            goToLoginActivity()
        }

        addCarFab.setOnClickListener {
            goToNewCarActivity()
        }
    }

    // Navigate to Login activity
    private fun goToLoginActivity() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish() // So that we can't come back to the log in page by hitting back
    }

    private fun goToNewCarActivity() {
        val intent = Intent(this@MainActivity, NewCarActivity::class.java)
        startActivity(intent)
    }

    private fun queryCars() {
        // Start creating a Parse query
        val query: ParseQuery<Car> = ParseQuery(Car::class.java)

        // Asking parse to also include the user that posted the Post (Since User is a pointer in the Post table)
        query.include(Car.KEY_USER)
        // Get only the first 10 posts from the server
        query.limit = 10
        // Get cars in reverse-chronological order that we created them
        query.addDescendingOrder("createdAt")
        // Only retrieving the cars of the currently authenticated user
        query.whereEqualTo("user", ParseUser.getCurrentUser())
        // Launch the query
        query.findInBackground(object : FindCallback<Car> {
            override fun done(carsResponse: MutableList<Car>?, e: ParseException?) {
                if (e != null) {
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if (carsResponse != null) {
                        for (singleCar in carsResponse) {
                            Log.i(
                                TAG,
                                "Car: " + singleCar.getModel() + ", username: " + singleCar.getUser()?.username
                            )
                        }
                        garage.addAll(carsResponse)
                        carAdapter.notifyDataSetChanged()
                        //swipeContainer.isRefreshing = false
                    }
                }
            }
        })
    }

    companion object {
        const val TAG = "MainActivity"
    }
}