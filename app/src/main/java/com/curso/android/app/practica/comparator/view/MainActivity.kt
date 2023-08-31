package com.curso.android.app.practica.comparator.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.curso.android.app.practica.comparator.databinding.ActivityMainBinding
//import com.curso.android.app.practica.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.comparator.observe(this) {
            binding.equality.text = "${it.equality}"
        }
        binding.compareButton.setOnClickListener {
            val inputString1 = binding.string1.text.toString()
            val inputString2 = binding.string2.text.toString()
            mainViewModel.compareStrings(inputString1, inputString2)
        }
    }
}
