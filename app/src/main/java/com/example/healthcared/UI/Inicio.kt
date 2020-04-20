package com.example.healthcared.UI

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.healthcared.Modelo.MyXAxisFormatter
import com.example.healthcared.R
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.ramijemli.percentagechartview.callback.ProgressTextFormatter
import kotlinx.android.synthetic.main.activity_inicio.*
import java.text.DecimalFormat
import java.util.*
import kotlin.math.roundToInt

class Inicio : AppCompatActivity(), SensorEventListener {
    var running = false
    var sensorManager: SensorManager? = null



    //TODO() Actualizar con datos de usuario
    var targetSteps = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        setContentView(R.layout.activity_inicio)

        val barDataSet = getOtherDays()
        val data = BarData(barDataSet)
        barChart.data = data
        barDataSet.color = resources.getColor(R.color.colorAccent)
        barChart.setScaleEnabled(false)
        barChart.setPinchZoom(false)
        barChart.setDrawBarShadow(false)
        barChart.setDrawGridBackground(false)

        val xAxis: XAxis = barChart.getXAxis()
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = MyXAxisFormatter()

        barChart.getAxisRight().setEnabled(false)
        barChart.animateY(2500)

        /**
         * Ring graph inside text format to steps instead of percentage
         */
        graph.setTextFormatter(ProgressTextFormatter setTextFormatter@{ progress: Float ->
            var steps = (targetSteps / progress).roundToInt()
            "$steps steps"
        })
    }

    fun getOtherDays(): BarDataSet{
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 200f))
        entries.add(BarEntry(2f, 600f))
        entries.add(BarEntry(4f, 988f))
        entries.add(BarEntry(6f, 9084f))
        entries.add(BarEntry(8f, 15000f))
        entries.add(BarEntry(10f, 759f))

        barChart.getDescription().setEnabled(false)

        val barDataSet = BarDataSet(entries, "Steps")
        return barDataSet
    }

    override fun onResume() {
        super.onResume()
        running = true
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepsSensor == null) {
            Toast.makeText(this, "No step counter sensor found!", Toast.LENGTH_LONG)
                .show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
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
    }

    fun pause(view: View) {
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
     * Podometer functions
     *
     */
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("not implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running) {
            if (event != null) {
                graph.setProgress(event.values[0]/targetSteps, true)
                testText.text = (event.values[0]/targetSteps).toString()
            }
        }
    }


}



