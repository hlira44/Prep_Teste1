package com.example.prep_teste1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        // Pegando o valor enviado pela MainActivity
        val paramAno = intent.getIntExtra(MainActivity.PARAM_ANO, -1)
        // Exibindo o valor em um Toast
        Toast.makeText(this, "Ano recebido: $paramAno", Toast.LENGTH_LONG).show()
    }
}
