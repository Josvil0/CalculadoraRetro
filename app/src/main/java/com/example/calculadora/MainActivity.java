package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView resultado; // Muestra el resultado
    String operacion = ""; // La operación que se está realizando
    double valor1 = 0, valor2 = 0; // Los valores que se van a calcular
    String operador = ""; // Guarda el operador actual (+, -, *, /)
    boolean nuevoValor = true; // Controla si es un nuevo valor a ingresar
    boolean error = false; // Controla si hubo un error previo

    View.OnClickListener saberNumero = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (error) {
                // Si hubo un error previo, reiniciamos la calculadora
                reiniciarCalculadora();
            }
            Button boton = (Button) view;
            String numero = boton.getText().toString();

            if (nuevoValor) {
                resultado.setText(numero);
                nuevoValor = false;
            } else {
                resultado.append(numero);
            }

            operacion = resultado.getText().toString();
        }
    };

    View.OnClickListener borrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reiniciarCalculadora();
        }
    };

    View.OnClickListener igual = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                // Obtenemos el segundo valor
                valor2 = Double.parseDouble(resultado.getText().toString());
                double resultadoOperacion = 0;

                // Realizamos la operación basada en el operador seleccionado
                switch (operador) {
                    case "+":
                        resultadoOperacion = valor1 + valor2;
                        break;
                    case "-":
                        resultadoOperacion = valor1 - valor2;
                        break;
                    case "x":
                        resultadoOperacion = valor1 * valor2;
                        break;
                    case "/":
                        if (valor2 != 0) {
                            resultadoOperacion = valor1 / valor2;
                        } else {
                            throw new ArithmeticException("División por cero");
                        }
                        break;
                    default:
                        throw new IllegalStateException("Operador inválido");
                }

                // Mostramos el resultado
                resultado.setText(String.valueOf(resultadoOperacion));
                nuevoValor = true; // Preparar para un nuevo cálculo
            } catch (Exception e) {
                resultado.setText("Error"); // Error 
                error = true;
            }
        }
    };

    View.OnClickListener operadores = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (error) {
                reiniciarCalculadora();
            }
            Button boton = (Button) view;
            operador = boton.getText().toString(); // Guarda el operador presionado

            // Verificar si el campo no está vacío antes de asignar valor
            if (!resultado.getText().toString().isEmpty()) {
                valor1 = Double.parseDouble(resultado.getText().toString());
                nuevoValor = true; // Prepara para el siguiente valor
            }
        }
    };

    private void reiniciarCalculadora() {
        // Reinicia todos los valores de la calculadora
        resultado.setText("");
        operacion = "";
        valor1 = 0;
        valor2 = 0;
        operador = "";
        nuevoValor = true;
        error = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = findViewById(R.id.resultado);

        // Vinculamos los botones con la interfaz gráfica
        Button boton0 = findViewById(R.id.button0);
        Button boton1 = findViewById(R.id.button1);
        Button boton2 = findViewById(R.id.button2);
        Button boton3 = findViewById(R.id.button3);
        Button boton4 = findViewById(R.id.button4);
        Button boton5 = findViewById(R.id.button5);
        Button boton6 = findViewById(R.id.button6);
        Button boton7 = findViewById(R.id.button7);
        Button boton8 = findViewById(R.id.button8);
        Button boton9 = findViewById(R.id.button9);

        // Asignamos los listeners para cada botón
        boton0.setOnClickListener(saberNumero);
        boton1.setOnClickListener(saberNumero);
        boton2.setOnClickListener(saberNumero);
        boton3.setOnClickListener(saberNumero);
        boton4.setOnClickListener(saberNumero);
        boton5.setOnClickListener(saberNumero);
        boton6.setOnClickListener(saberNumero);
        boton7.setOnClickListener(saberNumero);
        boton8.setOnClickListener(saberNumero);
        boton9.setOnClickListener(saberNumero);

        // Vinculamos los botones de las operaciones y el botón C
        Button botonSumar = findViewById(R.id.buttonPlus);
        Button botonRestar = findViewById(R.id.buttonRes);
        Button botonMultiplicar = findViewById(R.id.buttonMult);
        Button botonDividir = findViewById(R.id.buttonDiv);
        Button botonIgual = findViewById(R.id.buttonIgual);
        Button botonBorrar = findViewById(R.id.buttonC);

        // Asignamos los listeners para las operaciones
        botonSumar.setOnClickListener(operadores);
        botonRestar.setOnClickListener(operadores);
        botonMultiplicar.setOnClickListener(operadores);
        botonDividir.setOnClickListener(operadores);

        // Listener para el botón igual
        botonIgual.setOnClickListener(igual);

        // Listener para el botón borrar
        botonBorrar.setOnClickListener(borrar);
    }
}


