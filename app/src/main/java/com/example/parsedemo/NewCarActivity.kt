package com.example.parsedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class NewCarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_car)

        val makeEt: EditText = findViewById(R.id.new_car_make_et)
        val modelEt: EditText = findViewById(R.id.new_car_model_et)
        val horsePowerEt: EditText = findViewById(R.id.new_car_horsepower_et)
        val addNewCarButton: Button = findViewById(R.id.add_new_car_button)

        addNewCarButton.setOnClickListener {
            val makeText = makeEt.text.toString()
            val modelText = modelEt.text.toString()
            val horsePowerText = horsePowerEt.text.toString()

            saveCar(makeText, modelText, horsePowerText)
            goToMainActivity()
        }
    }

    // Horsepower could be saved as integer or float. Depends on your application
    private fun saveCar(make: String, model: String, horsePower: String) {

        // Create car object and update it's attributes
        val car = Car()
        car.setMake(make)
        car.setModel(model)
        car.setHorsepower(horsePower)
        car.setUser(ParseUser.getCurrentUser()) // Get currently logged in user and set as owner of car

        // Save car to parse backend
        car.saveInBackground{ exception ->
            if (exception != null) {
                Log.d(TAG, "Error while adding car")
                exception.printStackTrace()
                Toast.makeText(this, "Failed to add car", Toast.LENGTH_SHORT).show()
            } else {
                Log.i(TAG, "Successfully added car to parse!")
            }
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this@NewCarActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val TAG = "NewCarActivity"
    }
}