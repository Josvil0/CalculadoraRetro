package com.example.calculadora;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que realiza operaciones matemáticas.
 *
 * @author Jose Alonso Villanova
 * @version 1.0
 * @since 2024-11-05
 */
public class Calculator {

    /**
     * Evalúa una expresión matemática dada como String.
     *
     * @param expresion La expresión a evaluar, que debe ser una cadena de texto
     *      *                  que contenga números y operadores. Se permite el uso de
     *      *                  números negativos.
     * @return El resultado de la evaluación como double.
     *
     * @throws IllegalArgumentException Si la expresión es incompleta o contiene
     *      *                                  operadores sin números asociados.
     *
     *      @throws ArithmeticException Si se intenta dividir por cero.
     */
    public double evaluarExpresion(String expresion) {
        expresion = expresion.replaceAll("\\s+", ""); // Eliminar espacios en blanco

        List<Double> numeros = new ArrayList<>();
        List<Character> operadores = new ArrayList<>();
        StringBuilder numeroTemporal = new StringBuilder();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            // Manejar números negativos (deben estar al principio o después de un operador)
            if (Character.isDigit(c) || (c == '-' && (i == 0 || isOperador(expresion.charAt(i - 1))))) {
                numeroTemporal.append(c); // Agregar al número temporal
            } else {
                // Si hay un número temporal, lo agregamos a la lista de números
                if (numeroTemporal.length() > 0) {
                    numeros.add(Double.parseDouble(numeroTemporal.toString()));
                    numeroTemporal.setLength(0); // Limpiar el número temporal
                }

                // Agregar el operador a la lista
                if (isOperador(c)) {
                    operadores.add(c);
                }
            }
        }

        // Agregar el último número
        if (numeroTemporal.length() > 0) {
            numeros.add(Double.parseDouble(numeroTemporal.toString()));
        }

        // Depuración: imprimir números y operadores
        System.out.println("Números: " + numeros);
        System.out.println("Operadores: " + operadores);

        // Verificar si hay un operador sin número a la derecha
        if (operadores.size() > numeros.size() - 1) {
            throw new IllegalArgumentException("Expresión incompleta: hay operadores sin números asociados.");
        }

        // Manejo de multiplicación y división primero
        for (int i = 0; i < operadores.size(); i++) {
            char operador = operadores.get(i);
            if (operador == 'x' || operador == '/') {
                if (i < numeros.size() - 1) { // Verifica si hay un número a la derecha
                    double left = numeros.get(i);
                    double right = numeros.get(i + 1);
                    double resultado;

                    if (operador == 'x') {
                        resultado = left * right;
                    } else {
                        if (right != 0) {
                            resultado = left / right;
                        } else {
                            throw new ArithmeticException("División por cero");
                        }
                    }

                    // Actualizar listas después de la operación
                    numeros.set(i, resultado);
                    numeros.remove(i + 1);
                    operadores.remove(i);
                    i--; // Volver a evaluar en el mismo índice
                } else {
                    throw new IllegalArgumentException("Operador '" + operador + "' no tiene un número a la derecha");
                }
            }
        }

        // Manejo de suma y resta
        double total = numeros.get(0); // Comienza con el primer número
        for (int i = 0; i < operadores.size(); i++) {
            if (i + 1 < numeros.size()) { // Verifica si hay un número a la derecha
                double right = numeros.get(i + 1);
                char operador = operadores.get(i);

                if (operador == '+') {
                    total += right;
                } else if (operador == '-') {
                    total -= right;
                }
            } else {
                throw new IllegalArgumentException("Operador '" + operadores.get(i) + "' no tiene un número a la derecha");
            }
        }

        return total;
    }
    /**
     * Verifica si un carácter es un operador matemático válido.
     *
     * @param c El carácter a verificar.
     * @return true si el carácter es un operador ('+', '-', 'x', '/'), false de
     *         lo contrario.
     */

    private boolean isOperador(char c) {
        return c == '+' || c == '-' || c == 'x' || c == '/';
    }


}
