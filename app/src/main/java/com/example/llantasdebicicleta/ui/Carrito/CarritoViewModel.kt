package com.example.llantasdebicicleta.ui.Carrito

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarritoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //value = "This is carrito Fragment"
    }
    val text: LiveData<String> = _text
}