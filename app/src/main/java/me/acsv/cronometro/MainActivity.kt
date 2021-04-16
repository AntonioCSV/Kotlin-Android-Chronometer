package me.acsv.cronometro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import me.acsv.cronometro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var running: Boolean = false
    private var pausedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iniciar.setOnClickListener {
            iniciarCronometro()
        }

        binding.pausar.setOnClickListener {
            pausarCronometro()
        }

        binding.zerar.setOnClickListener {
            zerarCronometro()
        }
    }

    private fun iniciarCronometro() {
        if(!running) {
            binding.cronometro.base = if(pausedTime > 0) pausedTime else SystemClock.elapsedRealtime()
            binding.cronometro.start()
            running = true
        }
    }

    private fun pausarCronometro() {
        if(running) {
            binding.cronometro.stop()
            pausedTime = SystemClock.elapsedRealtime()
            running = false
        }
    }

    private fun zerarCronometro() {
        binding.cronometro.base = SystemClock.elapsedRealtime()
        pausedTime = 0
    }
}