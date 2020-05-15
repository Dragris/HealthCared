package com.example.healthcared.UI

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcared.Controlador
import com.example.healthcared.Modelo.Utils.StepDetector
import com.example.healthcared.Modelo.Utils.StepListener
import com.example.healthcared.UI.Inicio
import com.example.healthcared.R
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class Settings : AppCompatActivity(), SensorEventListener, StepListener {
    private var simpleStepDetector: StepDetector? = null
    private var sensorManager: SensorManager? = null
    private var steps: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        /**
         * Pedometer block
         */
        // Get an instance of the SensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        simpleStepDetector = StepDetector()
        simpleStepDetector!!.registerListener(this)
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onBackPressed() {
        goToInicio()
        finish()
    }
    fun goBack(view: View){
        goToInicio()
        finish()
    }

    fun goToInicio(){
        var intent = Intent(this, Inicio::class.java)
        intent.putExtra("steps", steps)
        startActivity(intent)
    }

    override fun finish(){
        sensorManager?.unregisterListener(this)
        super.finish()
    }

    fun goSupport(view: View){
        var lista = arrayOf("support@healthcared.com")
        var intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, lista)
        intent.type = "plain/text"
        startActivity(Intent.createChooser(intent, "Choose an application"))
    }
    fun goFaq(view: View){
        var link = "https://ubarcelona-my.sharepoint.com/:w:/g/personal/dlopezad7_alumnes_ub_edu/EUKfLg0-0KBNjT8hssqPusQBX9W2YXyn2BieUyq52TulKQ?e=Efzkxu"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)

    }
    fun signOut(view: View){
        Controlador.guardarDatos()
        FirebaseAuth.getInstance().signOut()
        var login = Intent(this, LogIn::class.java)
        startActivity(login)
        finish()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //Do nothing
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector!!.updateAccelerometer(event.timestamp, event.values[0], event.values[1], event.values[2])
        }
    }

    override fun step(timeNs: Long) {
        steps++
    }
}
