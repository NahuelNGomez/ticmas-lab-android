package com.curso.android.app.practica.comparator.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.android.app.practica.comparator.model.Comparator
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val comparator: LiveData<Comparator> get() = _comparator
    private var _comparator = MutableLiveData<Comparator>(
        Comparator( "Pulse 'comparar' para verificar\\n si las cadenas son iguales")
    )

    fun compareStrings(string1: String, string2: String) {
        val equality = (string1 == string2)
        updateComparator(equality)
    }

    private fun updateComparator(equality: Boolean) {
        viewModelScope.launch {
            if(equality) {
                _comparator.value = Comparator("Las cadenas son iguales")
            } else {
                _comparator.value = Comparator("Las cadenas son diferentes")
            }
        }
    }
}
