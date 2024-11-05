package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad principal de la aplicación calculadora.
 * Esta clase gestiona la interfaz de usuario y la interacción con el usuario.
 */
public class MainActivity extends AppCompatActivity {
    private TextView resultado; // Muestra el resultado de la operación
    private String operacion = ""; // La operación completa que se va a calcular
    private boolean error = false; // Controla si hubo un error previo
    private Calculator calculator; // Instancia de la clase Calculator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = findViewById(R.id.resultado);
        calculator = new Calculator();

        // Configurar botones
        configurarBotones();
    }

    /**
     * Configura los botones de la calculadora y sus respectivos listeners.
     */
    private void configurarBotones() {
        // Botones de números
        int[] numeros = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        for (int id : numeros) {
            Button boton = findViewById(id);
            boton.setOnClickListener(saberNumero);
        }

        // Botones de operadores
        Button botonSumar = findViewById(R.id.buttonPlus);
        Button botonRestar = findViewById(R.id.buttonRes);
        Button botonMultiplicar = findViewById(R.id.buttonMult);
        Button botonDividir = findViewById(R.id.buttonDiv);
        botonSumar.setOnClickListener(operadores);
        botonRestar.setOnClickListener(operadores);
        botonMultiplicar.setOnClickListener(operadores);
        botonDividir.setOnClickListener(operadores);

        // Botones de acción
        Button botonIgual = findViewById(R.id.buttonIgual);
        Button botonBorrar = findViewById(R.id.buttonC);
        botonIgual.setOnClickListener(igual);
        botonBorrar.setOnClickListener(borrar);
    }

    /**
     * Reinicia la calculadora, borrando el resultado y la operación actual.
     */
    private void reiniciarCalculadora() {
        resultado.setText("");
        operacion = "";
        error = false;
    }

    /**
     * Listener para manejar el ingreso de números.
     * Concatenará el número al final de la operación actual.
     */
    private final View.OnClickListener saberNumero = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (error) {
                reiniciarCalculadora();
            }
            Button boton = (Button) view;
            String numero = boton.getText().toString();

            // Concatenar el número al final de la operación
            operacion += numero;
            resultado.setText(operacion);
        }
    };

    /**
     * Listener para manejar el evento de borrar la operación actual.
     * Reinicia la calculadora.
     */
    private final View.OnClickListener borrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reiniciarCalculadora();
        }
    };

    /**
     * Listener para manejar el evento de calcular el resultado de la operación.
     * Evalúa la expresión y muestra el resultado o un mensaje de error.
     */
    private final View.OnClickListener igual = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                double resultadoOperacion = calculator.evaluarExpresion(operacion);
                resultado.setText(String.valueOf(resultadoOperacion));
                operacion = String.valueOf(resultadoOperacion); // Actualizar la operación con el resultado
            } catch (Exception e) {
                resultado.setText("Error");
                error = true;
            }
        }
    };

    /**
     * Listener para manejar la entrada de operadores.
     * Concatenará el operador al final de la operación actual.
     */
    private final View.OnClickListener operadores = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (error) {
                reiniciarCalculadora();
            }
            Button boton = (Button) view;
            String operador = boton.getText().toString();

            // Concatenar el operador al final de la operación
            operacion += operador;
            resultado.setText(operacion);
        }
    };
}
