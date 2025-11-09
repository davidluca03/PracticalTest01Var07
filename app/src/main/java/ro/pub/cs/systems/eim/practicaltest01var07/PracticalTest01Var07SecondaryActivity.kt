package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondary_activity)

        val n1 = intent.getIntExtra("TEXT1", 0)
        val n2 = intent.getIntExtra("TEXT2", 0)
        val n3 = intent.getIntExtra("TEXT3", 0)
        val n4 = intent.getIntExtra("TEXT4", 0)

        val sum = n1 + n2 + n3 + n4
        val prod = n1 * n2 * n3 * n4

        val text1 = findViewById<TextView>(R.id.text1)
        val text2 = findViewById<TextView>(R.id.text2)
        val text3 = findViewById<TextView>(R.id.text3)
        val text4 = findViewById<TextView>(R.id.text4)

        text1.setText(n1.toString())
        text2.setText(n2.toString())
        text3.setText(n3.toString())
        text4.setText(n4.toString())



        val sumButton = findViewById<Button>(R.id.sum)
        val prodButton = findViewById<Button>(R.id.prod)

        sumButton.setOnClickListener {
            var finalResult = Intent()
            finalResult.putExtra("RESULT_SUM", sum)
            setResult(Activity.RESULT_OK, finalResult)
            finish()
        }

        prodButton.setOnClickListener {
            var finalResult = Intent()
            finalResult.putExtra("RESULT_PROD", prod)
            setResult(Activity.RESULT_OK, finalResult)
            finish()
        }
    }
}