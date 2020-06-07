package com.example.healthcared.UI

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.*
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.healthcared.Controlador
import com.example.healthcared.Modelo.Utils.StepDetector
import com.example.healthcared.Modelo.Utils.StepListener
import com.example.healthcared.R
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker


class Tracker() : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    SensorEventListener, StepListener {
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var locationCallback: LocationCallback
    private var locationRequest: LocationRequest? = null
    private var locationUpdateState = false
    private var simpleStepDetector: StepDetector? = null
    private var sensorManager: SensorManager? = null
    var time: Long = 0

    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1138
        private const val BACKGROUND_LOCATION_CODE = 2950
    }

    private fun mapUpdate() {
        //Habilita la localización
        mMap.isMyLocationEnabled = true
        //Nos da la localización más reciente disponible
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            //Rara vez la localización puede ser null.
            if (location != null) {
                //Si hay localización le hacemos zoom en el mampa
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18f))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                lastLocation = p0.lastLocation
                //placeMarkerOnMap(LatLng(lastLocation.latitude, lastLocation.longitude))
            }
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setContentView(R.layout.activity_tracker)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val timer: Chronometer = findViewById<Chronometer>(R.id.chrono)
        timer.base = SystemClock.elapsedRealtime()

        /**
         * Pedometer block
         */
        // Get an instance of the SensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        simpleStepDetector = StepDetector()
        simpleStepDetector!!.registerListener(this)
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST)
        findViewById<TextView>(R.id.stepText).text = "You've done ${Controlador.usuario.numSteps} steps"
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        /* //Ponemos un marcador personalizado con nombre en Estocolmo.
        val stockholm = LatLng(59.3255792, 18.0670089)
        mMap.addMarker(MarkerOptions().position(stockholm).title("Stockholm"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stockholm, 12.0f))
        */

        //CONTROLES DE ZOOM
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)
        //CONTROLES DE ZOOM

        mapUpdate() //Comprobamos permisos y actualizamos mapa
        createLocationRequest()
    }
    //Hacemos que no puedas clickar en marcadores
    override fun onMarkerClick(p0: Marker?) = false

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE )
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest?.interval = 10000 //ms
        locationRequest?.fastestInterval = 5000 //ms
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest!!)


        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(this,
                        LOCATION_PERMISSION_REQUEST_CODE)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
    public override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }

    override fun finish() {
        sensorManager?.unregisterListener(this)
        super.finish()
    }

    /**
     * Revisar que tiene permiso para localización en cualquier momento (dibujar ruta)
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray){
        when (requestCode){
            BACKGROUND_LOCATION_CODE ->{
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    val intent = Intent(this, Tracker::class.java)
                    startActivity(intent)
                } else {
                    //No se concede el permiso, no se hace nada
                }
            }
        }
    }


    /**
     * Funciones de la interfaz
     */
    fun goBack(view: View) {
        finish()
    }

    fun settings(view: View) {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }


    /**
     * Funciones chrono
     */
    fun pauseTimer(view: View){
        val timer: Chronometer = findViewById<Chronometer>(R.id.chrono)
        val button = findViewById<Button>(R.id.pause)
        if (button.text == "Pause") {
            time = timer.base - SystemClock.elapsedRealtime()
            timer.stop()
            button.text = "Resume"
        } else {
            timer.base = time + SystemClock.elapsedRealtime()
            timer.start()
            button.text = "Pause"
        }
    }

    fun finishTimer(view: View){
        val timer: Chronometer = findViewById<Chronometer>(R.id.chrono)
        val button = findViewById<Button>(R.id.pause)
        timer.stop()
        timer.base = SystemClock.elapsedRealtime()
        button.text = "Start"
        time = 0
        //Finish routine
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
        findViewById<TextView>(R.id.stepText).text = "You've done ${Controlador.usuario.numSteps} steps"
    }

    fun workouts(view: View) {
        val intent = Intent(this, WorkoutHome::class.java)
        startActivity(intent)
        finish()
    }

    fun profile(view: View) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

    fun diets(view: View) {
        val intent = Intent(this, DietHome::class.java)
        startActivity(intent)
        finish()
    }

    fun home(view: View) {
        finish()
    }
}


