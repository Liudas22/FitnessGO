package com.first.run

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class RunFrame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_frame)

        //val Timer = findViewById<Button>(R.id.RunFramePause)


        /*val startButton = findViewById<Button>(R.id.Start_Button)
        startButton.setOnClickListener{
            val intent = Intent(this, RunFrame::class.java)
            startActivity(intent)
        }*/

        val pace = findViewById<TextView>(R.id.RunFramePace)

        pace.text = "00:00"

        val distance = findViewById<TextView>(R.id.RunFrameDistance)

        distance.text = "0 km"

        val chronometer = findViewById<Chronometer>(R.id.c_meter)
        chronometer.base = SystemClock.elapsedRealtime()
        val button = findViewById<Button>(R.id.btn)

        button?.setOnClickListener(object : View.OnClickListener {

            var isWorking = false

            override fun onClick(v: View) {
                if (!isWorking) {
                    chronometer.start()
                    isWorking = true
                } else {
                    chronometer.stop()
                    isWorking = false
                }

                button.setText(if (!isWorking) R.string.start else R.string.stop)
                Toast.makeText(this@RunFrame, getString(if (isWorking) R.string.working else R.string.stopped), Toast.LENGTH_SHORT).show()
            }

        })

        val button2 = findViewById<Button>(R.id.RunFrameStop)

        button2?.setOnClickListener(object : View.OnClickListener {

            var isWorking = true

            override fun onClick(v: View?) {
                if(isWorking){
                    chronometer.stop()
                }

                button.setText(R.string.start)
                Toast.makeText(this@RunFrame, getString(R.string.stopped), Toast.LENGTH_SHORT).show()
            }
        })

    }

}