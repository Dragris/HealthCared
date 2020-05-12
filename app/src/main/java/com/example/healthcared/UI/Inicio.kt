package com.example.healthcared.UI

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.healthcared.Controlador
import com.example.healthcared.Modelo.MyXAxisFormatter
import com.example.healthcared.Modelo.Utils.StepDetector
import com.example.healthcared.Modelo.Utils.StepListener
import com.example.healthcared.R
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.ramijemli.percentagechartview.callback.ProgressTextFormatter
import kotlinx.android.synthetic.main.activity_inicio.*
import java.lang.Exception
import java.util.*

class Inicio : AppCompatActivity(), SensorEventListener, StepListener {

    private var simpleStepDetector: StepDetector? = null
    private var sensorManager: SensorManager? = null
    private var targetSteps = 1000L
    lateinit var activityLabel: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLabel = this
        setContentView(R.layout.activity_inicio)
        if (Controlador.usuario.targetSteps != null) targetSteps = Controlador.usuario.targetSteps!!
        try{
            var steps = intent.getLongExtra("steps", 0)
            Controlador.usuario.numSteps += steps
        }catch (e :Exception){
            //Pass
        }
        var tmp_today: Int = Calendar.getInstance().get(Calendar.YEAR) * 365 + Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        var today : Long = tmp_today.toLong()
        var lastDay: Long = Controlador.usuario.lastDay
        var registroPasos: MutableList<Long> = Controlador.usuario.registroPasos
        var yesterdaySteps: Long = Controlador.usuario.numSteps
        var daysToShift = today - lastDay

        if (lastDay == 0L){
            lastDay = today
            daysToShift = 0
        }
        shift(daysToShift)
        Controlador.usuario.lastDay = today

        /**
         * Chart block
         */
        val barDataSet = getOtherDays()
        val data = BarData(barDataSet)
        barChart.data = data
        barDataSet.color = resources.getColor(R.color.colorAccent)
        barChart.setScaleEnabled(false)
        barChart.setPinchZoom(false)
        barChart.setDrawBarShadow(false)
        barChart.setDrawGridBackground(false)

        val xAxis: XAxis = barChart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = MyXAxisFormatter()

        val yAxis: YAxis = barChart.axisLeft
        yAxis.axisMinimum = 0f
        barChart.axisRight.isEnabled = false
        barChart.animateY(2500)

        /**
         * Ring graph inside text format to steps instead of percentage
         */

        graph.setTextFormatter(ProgressTextFormatter setTextFormatter@{ progress: Float ->
            ""
        })
        var percentage: Float = ((Controlador.usuario.numSteps.toFloat()/targetSteps.toFloat() * 100.0).toFloat())
        if (percentage >= 100f){
            graph.setProgress(100f, true)
        } else{
            graph.setProgress(percentage, true)
        }
        findViewById<TextView>(R.id.stepText).text = "${Controlador.usuario.numSteps} steps"

        /**
         * Pedometer block
         */
        // Get an instance of the SensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        simpleStepDetector = StepDetector()
        simpleStepDetector!!.registerListener(this)
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST)

    }

    fun shift(int: Long){
        if (int >= 6){//Si hay que desplazar más de 6 días la lista queda vacía
            Controlador.usuario.registroPasos = mutableListOf(0,0,0,0,0,0)
            Controlador.usuario.numSteps = 0
            return
        } else if(int == 0L){//Si hay que desplazar 0 días salimos de la función
            return
        }

        Controlador.usuario.registroPasos.add(0, Controlador.usuario.numSteps)
        //Adding to list
        for (i in 1..(int-1)){
            Controlador.usuario.registroPasos.add(0, 0)
        }
        var temp: MutableList<Long> = mutableListOf()

        for (i in 0..5){
            temp.add(Controlador.usuario.registroPasos[i])
        }
        Controlador.usuario.registroPasos = temp
        Controlador.usuario.numSteps = 0

    }

    fun getOtherDays(): BarDataSet{
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, Controlador.usuario.registroPasos[0].toFloat()))
        entries.add(BarEntry(2f, Controlador.usuario.registroPasos[1].toFloat()))
        entries.add(BarEntry(4f, Controlador.usuario.registroPasos[2].toFloat()))
        entries.add(BarEntry(6f, Controlador.usuario.registroPasos[3].toFloat()))
        entries.add(BarEntry(8f, Controlador.usuario.registroPasos[4].toFloat()))
        entries.add(BarEntry(10f, Controlador.usuario.registroPasos[5].toFloat()))

        barChart.description.isEnabled = false

        val barDataSet = BarDataSet(entries, "Steps")
        return barDataSet
    }

    override fun onResume() {
        super.onResume()
        //Little resume animation
        try {
            var percentage: Float =
                ((Controlador.usuario.numSteps.toFloat() / targetSteps.toFloat() * 100.0).toFloat())
            graph.setProgress(percentage * 0.3f, false)
            if (percentage >= 100f) {
                graph.setProgress(100f, false)
            } else {
                graph.setProgress(percentage, false)
            }
        } catch (e: Exception){
        }
    }

    override fun finish(){
        sensorManager?.unregisterListener(this)
        super.finish()
    }


    /**
     *
     * BUTTON SECTION
     *
     */

    /**
     *Función para que la flecha atrás no lleve al registro/log in
     */
    override fun onBackPressed() {}

    fun tracker(view: View) {
        trackerPermissionCheck()
    }

    fun workouts(view: View) {
        val intent = Intent(this, WorkoutHome::class.java)
        startActivity(intent)
    }

    fun profile(view: View) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

    fun diets(view: View) {
        val intent = Intent(this, DietHome::class.java)
        startActivity(intent)
    }

    fun settings(view: View) {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
        finish()
    }



    /**
     *
     * PERMISSION SECTION
     *
     */
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1138
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            //Snippet for Location permission
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //If permission granted for first time
                    val intent = Intent(this, Tracker::class.java)
                    startActivity(intent)
                } else {
                    //If not given permission, do nothing
                }
            }
        }
    }

    fun trackerPermissionCheck() {
        //Check location permissions
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        //If already given
        val intent = Intent(this, Tracker::class.java)
        startActivity(intent)
    }


    /**
     *
     * Pedometer functions
     *
     */
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector!!.updateAccelerometer(event.timestamp, event.values[0], event.values[1], event.values[2])
        }
    }

    override fun step(timeNs: Long) {
        Controlador.usuario.numSteps++
        Controlador.updateUserSteps(Controlador.usuario.numSteps)
        var percentage: Float = ((Controlador.usuario.numSteps.toFloat()/targetSteps.toFloat() * 100.0).toFloat())
        if (percentage >= 100f){
            graph.setProgress(100f, false)
        } else {
            graph.setProgress(percentage, false)
        }
        findViewById<TextView>(R.id.stepText).text = "${Controlador.usuario.numSteps} steps"
    }



}



