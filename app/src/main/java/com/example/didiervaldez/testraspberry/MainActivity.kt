package com.example.didiervaldez.testraspberry

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    private var isOn: Boolean = false
    private var boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD)
    private var normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main)

        initializeComponents()
        enableButtons()
        setButtonListeners()
    }

    private fun initializeComponents() {
        button_encendido.setBackgroundColor(Color.rgb(17, 173, 64))
        button_encendido.text = "Encender"
        acelerador.max = 10
        acelerador.progress = 5
        label_valor_aceleracion.text = "Potencia: ${acelerador.progress.toString()}"
    }

    private fun formatButtonDown(button: Button) {
        button.backgroundColor = Color.BLUE
        button.typeface = boldTypeface
        checkIfEmergency()
    }

    private fun formatButtonUp(button: Button) {
        button.backgroundColor = Color.DKGRAY
        button.typeface = normalTypeface
        checkIfEmergency()
    }

    private fun checkIfEmergency() {
        if (emergencia.isChecked) {
            Background_get().execute("emergencia=1")
        } else {
            Background_get().execute("emergencia=0")
        }
    }

    private fun enableButtons() {
        if (isOn) {
            button_avanzar.isEnabled = true
            button_avanzar.backgroundColor = Color.DKGRAY
            button_derecho.isEnabled = true
            button_derecho.backgroundColor = Color.DKGRAY
            button_izquierdo.isEnabled = true
            button_izquierdo.backgroundColor = Color.DKGRAY
            button_reversa.isEnabled = true
            button_reversa.backgroundColor = Color.DKGRAY
            acelerador.isEnabled = true
            emergencia.isEnabled = true
            label_valor_aceleracion.visibility = View.VISIBLE
        } else {
            button_avanzar.isEnabled = false
            button_avanzar.backgroundColor = Color.BLACK
            button_derecho.isEnabled = false
            button_derecho.backgroundColor = Color.BLACK
            button_izquierdo.isEnabled = false
            button_izquierdo.backgroundColor = Color.BLACK
            button_reversa.isEnabled = false
            button_reversa.backgroundColor = Color.BLACK
            acelerador.isEnabled = false
            acelerador.progress = 5
            emergencia.isEnabled = false
            emergencia.isChecked = false
            label_valor_aceleracion.visibility = View.INVISIBLE
        }
    }

    private fun setButtonListeners() {

        acelerador.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                label_valor_aceleracion.text = "Potencia: ${acelerador.progress.toString()}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        button_encendido.setOnClickListener {
            if (isOn) {
                isOn = false
                button_encendido.setBackgroundColor(Color.rgb(17, 173, 64))
                button_encendido.text = "Encender"
                toast("El carro se apagó")
            } else if (!isOn) {
                isOn = true
                button_encendido.setBackgroundColor(Color.RED)
                button_encendido.text = "Apagar"
                toast("El carro se encendió")
            }
            enableButtons()
        }

        button_avanzar.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                Background_get().execute("avanzar=${acelerador.progress}")
                formatButtonDown(button_avanzar)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                Background_get().execute("avanzar=0")
                formatButtonUp(button_avanzar)
            }
            false
        }

        button_izquierdo.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                Background_get().execute("izquierda=1")
                formatButtonDown(button_izquierdo)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                Background_get().execute("izquierda=0")
                formatButtonUp(button_izquierdo)
            }
            false
        }

        button_derecho.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                Background_get().execute("derecha=1")
                formatButtonDown(button_derecho)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                Background_get().execute("derecha=0")
                formatButtonUp(button_derecho)
            }
            false
        }

        button_reversa.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                Background_get().execute("reversa=1")
                formatButtonDown(button_reversa)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                Background_get().execute("reversa=0")
                formatButtonUp(button_reversa)
            }
            false
        }

    }

}



