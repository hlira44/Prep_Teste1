package com.example.prep_teste1

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    // Definindo a constante PARAM_ANO
    companion object {
        const val PARAM_ANO = "param_ano"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Aplicando os insets corretamente ao layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calcular(view: View) {
        // Pegando os valores das EditTexts e convertendo para Int
        val inputAno = findViewById<EditText>(R.id.ano).text.toString().toIntOrNull()
        val inputMes = findViewById<EditText>(R.id.mes).text.toString().toIntOrNull()
        val inputDia = findViewById<EditText>(R.id.dia).text.toString().toIntOrNull()

        // Validando os valores de entrada
        if (inputAno == null || inputMes == null || inputDia == null) {
            Toast.makeText(this, "Por favor, insira todos os campos corretamente.", Toast.LENGTH_SHORT).show()
            return
        }

        // Verificando se o ano está no intervalo permitido
        if (inputAno < 1900 || inputAno > 2024) {
            Toast.makeText(this, R.string.msg1, Toast.LENGTH_SHORT).show()
            //findViewById<Button>(R.id.botao).text = getText(R.string.botao_erro)
        } else {
            // Pegando a data atual
            val c = Calendar.getInstance()

            // Configurando a data de nascimento inserida
            val cInput = Calendar.getInstance()
            cInput.set(inputAno, inputMes - 1, inputDia) // inputMes - 1 porque o mês em Calendar é baseado em 0

            // Calculando a idade
            val idade = if (c.before(cInput)) {
                c.get(Calendar.YEAR) - inputAno - 1
            } else {
                c.get(Calendar.YEAR) - inputAno
            }

            // Atualizando a UI
            //findViewById<Button>(R.id.botao).text = getText(R.string.botao_ok)
            findViewById<TextView>(R.id.text).text = idade.toString()
        }
    }

    fun segunda(view: View) {
        // Pegando o valor do ano e enviando para a SecondActivity
        val ano = findViewById<EditText>(R.id.ano).text.toString().toIntOrNull()
        if (ano != null) {
            val intent = Intent(applicationContext, SecondActivity::class.java)
            val putExtra = intent.putExtra(PARAM_ANO, ano)
            startActivity(putExtra)
        } else {
            Toast.makeText(this, "Por favor, insira um ano válido.", Toast.LENGTH_SHORT).show()
        }
    }
}
