package com.irempamukcu.timer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.irempamukcu.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.seconds().observe(this, Observer {
            binding.numberTxt.text = it.toString()
        })

        viewModel.isFinished().observe(this,Observer{
            Toast.makeText(this,"Geri sayım tamamlandı.",Toast.LENGTH_LONG).show()
        })

        binding.start.setOnClickListener {
            if(binding.numberInput.text!!.isEmpty() || binding.numberInput.text == null){
                Toast.makeText(this,"Lütfen bir sayı giriniz",Toast.LENGTH_LONG).show()
            }else{
                viewModel.timerValue.value = binding.numberInput.text.toString().toLong()*1000
                viewModel.startTimer()
            }

        }

        binding.reset.setOnClickListener {
            binding.numberTxt.text = "0"
            viewModel.stopTimer()
        }
    }


}