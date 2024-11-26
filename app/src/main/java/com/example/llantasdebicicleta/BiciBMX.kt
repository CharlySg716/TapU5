package com.example.llantasdebicicleta

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.llantasdebicicleta.databinding.BiciBmxBinding

class BiciBMX : AppCompatActivity() {

    private lateinit var binding: BiciBmxBinding
    private var quantity: Int = 1 // Variable para almacenar la cantidad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BiciBmxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_catalogo, R.id.navigation_carrito
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Configuración del Spinner
        val spinnerTamanoLlanta: Spinner = findViewById(R.id.spinner_tamano_llanta)
        ArrayAdapter.createFromResource(
            this,
            R.array.tamanos_llantas_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTamanoLlanta.adapter = adapter
        }

        // Configuración de los botones de incremento y decremento
        val quantityText: TextView = findViewById(R.id.quantity_text)
        val buttonDecrease: Button = findViewById(R.id.button_decrease)
        val buttonIncrease: Button = findViewById(R.id.button_increase)

        buttonDecrease.setOnClickListener {
            if (quantity > 1) {
                quantity--
                quantityText.text = "  $quantity  "
            }
        }

        buttonIncrease.setOnClickListener {
            quantity++
            quantityText.text = "  $quantity  "
        }
    }
}