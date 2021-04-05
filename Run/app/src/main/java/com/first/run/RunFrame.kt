package com.first.run

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.os.CountDownTimer
import android.os.health.TimerStat
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
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

        val chronometer = findViewById<Chronometer>(R.id.c_meter)

        val button = findViewById<Button>(R.id.btn)
        button?.setOnClickListener(object : View.OnClickListener {

            internal var isWorking = false

            override fun onClick(v: View) {
                if (!isWorking) {
                    chronometer.start()
                    isWorking = true
                } else {
                    chronometer.stop()
                    isWorking = false
                }

                button.setText(if (isWorking) R.string.start else R.string.stop)
                Toast.makeText(this@RunFrame, getString(if (isWorking) R.string.working else R.string.stopped), Toast.LENGTH_SHORT).show()
            }
        })



    }

}