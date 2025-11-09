package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var07MainActivity : AppCompatActivity() {
    var saveSum: Int = 0
    var saveProd: Int = 0
    var intentFilter = IntentFilter()
    private var messageReceiver = messageReceiver()
    lateinit var service: Intent

    private inner class messageReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var text1 = findViewById<EditText>(R.id.text1)
            var text2 = findViewById<EditText>(R.id.text2)
            var text3 = findViewById<EditText>(R.id.text3)
            var text4 = findViewById<EditText>(R.id.text4)

            text1.setText(intent!!.getIntExtra("R1", 0).toString())
            text2.setText(intent!!.getIntExtra("R2", 0).toString())
            text3.setText(intent!!.getIntExtra("R3", 0).toString())
            text4.setText(intent!!.getIntExtra("R4", 0).toString())
        }
    }
    val activityResultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data?.getExtras()!!.containsKey("RESULT_SUM")) {
                val res = result.data!!.getIntExtra("RESULT_SUM", 0)
                Toast.makeText(this, "The activity returned with result OK, sum is " + res, Toast.LENGTH_LONG).show()
                saveSum = res
            }

            if (result.data?.getExtras()!!.containsKey("RESULT_PROD")) {
                val res = result.data!!.getIntExtra("RESULT_PROD", 0)
                Toast.makeText(this, "The activity returned with result OK, prod is " + res, Toast.LENGTH_LONG).show()
                saveProd = res
            }

        }
        else if (result.resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "The activity returned with result CANCELED", Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var07)

        intentFilter.addAction("SEND_MESSAGE")
        service = Intent(this, PracticalTest01Var07Service::class.java)
        this.startService(service)

        var text1 = findViewById<EditText>(R.id.text1)
        var text2 = findViewById<EditText>(R.id.text2)
        var text3 = findViewById<EditText>(R.id.text3)
        var text4 = findViewById<EditText>(R.id.text4)

        var setButton = findViewById<Button>(R.id.set)
        setButton.setOnClickListener {
            if (!text1.text.isNullOrEmpty() && !text2.text.isNullOrEmpty() && !text3.text.isNullOrEmpty() && !text4.text.isNullOrEmpty()) {
                var intent = Intent(this, PracticalTest01Var07SecondaryActivity::class.java)

                intent.putExtra("TEXT1", Integer.parseInt(text1.text.toString()))
                intent.putExtra("TEXT2", Integer.parseInt(text2.text.toString()))
                intent.putExtra("TEXT3", Integer.parseInt(text3.text.toString()))
                intent.putExtra("TEXT4", Integer.parseInt(text4.text.toString()))

                activityResultsLauncher.launch(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SAVE_SUM", saveSum)
        outState.putInt("SAVE_PROD", saveProd)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState.containsKey("SAVE_SUM"))
            saveSum = savedInstanceState.getInt("SAVE_SUM")

        if (savedInstanceState.containsKey("SAVE_PROD"))
            saveProd = savedInstanceState.getInt("SAVE_PROD")

        Toast.makeText(this, "sum: " + saveSum + ", " +  "prod: " + saveProd, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        stopService(service)
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(messageReceiver, intentFilter, Context.RECEIVER_EXPORTED)
    }

    override fun onPause() {
        unregisterReceiver(messageReceiver)
        super.onPause()
    }
}