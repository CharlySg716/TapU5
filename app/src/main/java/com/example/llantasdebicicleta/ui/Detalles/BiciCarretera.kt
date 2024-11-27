package com.example.llantasdebicicleta.ui.Detalles

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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
import com.example.llantasdebicicleta.R
import com.example.llantasdebicicleta.databinding.BiciCarreteraBinding

class BiciCarretera : AppCompatActivity() {

    private lateinit var binding: BiciCarreteraBinding
    private var quantity: Int = 1 // Variable para almacenar la cantidad
    private var pricePerUnit: Double = 0.0 // Precio por unidad
    private lateinit var priceText: TextView // TextView para mostrar el precio total

    // Lista de precios según el tamaño de la llanta
    private val prices = mapOf(
        "26 in" to 1270.0,
        "27 in" to 1550.0,
        "28 in" to 1710.0,
        "29 in" to 1740.0
    ) // Precios para cada tamaño de llanta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BiciCarreteraBinding.inflate(layoutInflater)
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
        val spinnerTamanoLlanta: Spinner = findViewById(R.id.spinner_tamano_llanta1)
        priceText = findViewById(R.id.precio_text) // Asegúrate de tener un TextView para mostrar el precio

        // Crear un ArrayAdapter usando el layout personalizado para cada elemento
        val adapter = ArrayAdapter(this,
            R.layout.spinner_item, resources.getStringArray(R.array.tamanos_llantas_array1))

        // Configurar el fondo del dropdown
        adapter.setDropDownViewResource(R.layout.spinner_item) // Usa el layout de fondo personalizado

        // Asignar el adapter al spinner
        spinnerTamanoLlanta.adapter = adapter

        // Configuración de los botones de incremento y decremento
        val quantityText: TextView = findViewById(R.id.quantity_text)
        val buttonDecrease: Button = findViewById(R.id.button_decrease)
        val buttonIncrease: Button = findViewById(R.id.button_increase)

        // Establecer el listener para el Spinner
        spinnerTamanoLlanta.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Obtener el tamaño de la llanta seleccionado
                val selectedSize = parent.getItemAtPosition(position) as String

                // Actualizar el precio según el tamaño seleccionado
                updatePrice(selectedSize)
                updateTotalPrice()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada
            }
        })

        buttonDecrease.setOnClickListener {
            if (quantity > 1) {
                quantity--
                quantityText.text = "  $quantity  "
                updateTotalPrice()
            }
        }

        buttonIncrease.setOnClickListener {
            quantity++
            quantityText.text = "  $quantity  "
            updateTotalPrice()
        }
    }

    private fun updatePrice(selectedSize: String) {
        // Asignar el precio según el tamaño seleccionado
        pricePerUnit = prices[selectedSize]!!
    }

    private fun updateTotalPrice() {
        val totalPrice = quantity * pricePerUnit
        priceText.text = "Precio Total: $totalPrice" // Actualiza el TextView con el precio total
    }
}