package lat.pam.droidcafe

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {

    private lateinit var noteEditText: EditText
    private lateinit var citySpinner: Spinner
    private var selectedCity: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        noteEditText = findViewById(R.id.note)

        citySpinner = findViewById(R.id.city_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.city_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            citySpinner.adapter = adapter
        }

        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCity = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    fun submitOrder(view: View) {
        val note = noteEditText.text.toString()
        // Lakukan proses sesuai kebutuhan
        Toast.makeText(this, "Note: $note, City: $selectedCity", Toast.LENGTH_LONG).show()
    }

    fun onRadioButtonClicked(view: View) {
        // Is the button now checked?
        val checked = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.sameday -> if (checked) // Same day service
                displayToast(getString(R.string.same_day_messenger_service))


            R.id.nextday -> if (checked) // Next day delivery
                displayToast(getString(R.string.next_day_ground_delivery))


            R.id.pickup -> if (checked) // Pick up
                displayToast(getString(R.string.pick_up))


            else -> {}
        }
    }

    private fun displayToast(message: String) {
        Toast.makeText(
            applicationContext, message,
            Toast.LENGTH_SHORT
        ).show()
    }

}