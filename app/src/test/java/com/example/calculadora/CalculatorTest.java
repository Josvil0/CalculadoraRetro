package com.example.calculadora;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Prueba unitaria para verificar la suma de dos operandos.
 */

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd2perands(){
        Calculator calc = new Calculator();
        double total = calc.evaluarExpresion("5+3");

        assertEquals("X + Y operations not working correctly",8.0,total,0.001);
    }

    @Test
    public void testAdd1Operand() {
        Calculator calc = new Calculator();
        double total = calc.evaluarExpresion("4+3+1");

        assertEquals("+X operations not working correctly",8.0,total,0.001);
    }

    @Test
    public void testMult2Operands(){
        Calculator calc = new Calculator();
        double total = calc.evaluarExpresion("4x2");

        assertEquals("4*X operations not working correctly",8.0,total,0.001);
    }

    @Test
    public void testAddOperands() {
        Calculator calc = new Calculator();
        assertEquals(8.0, calc.evaluarExpresion("5+3"), 0.001);
        assertEquals(0.0, calc.evaluarExpresion("0+0"), 0.001);
        assertEquals(10.0, calc.evaluarExpresion("7+3"), 0.001);
        assertEquals(-4.0, calc.evaluarExpresion("-2+-2"), 0.001);
    }

    @Test
    public void testMultiplyOperands() {
        Calculator calc = new Calculator();
        assertEquals(6.0, calc.evaluarExpresion("2x3"), 0.001);
        assertEquals(16.0, calc.evaluarExpresion("1x2x8"), 0.001);
        assertEquals(7.0, calc.evaluarExpresion("2x2+3"), 0.001);
        assertEquals(7.0, calc.evaluarExpresion("3+2x2"), 0.001);
        assertEquals(11.0, calc.evaluarExpresion("3+2x2+4"), 0.001);
    }

    @Test
    public void testMixedOperations() {
        Calculator calc = new Calculator();
        assertEquals(1.0, calc.evaluarExpresion("1 +2-2"), 0.001);
        assertEquals(0.0, calc.evaluarExpresion("2-2+2-2"), 0.001);
        assertEquals(12.0, calc.evaluarExpresion("2x3+6"), 0.001);
        assertEquals(12.0, calc.evaluarExpresion("2+3x2+4"), 0.001);
        assertEquals(9.0, calc.evaluarExpresion("3x3"), 0.001);
    }

    @Test
    public void testNegativeOperands() {
        Calculator calc = new Calculator();
        assertEquals(-1.0, calc.evaluarExpresion("-2+1"), 0.001);
        assertEquals(-7.0, calc.evaluarExpresion("3-10"), 0.001);
        assertEquals(6.0, calc.evaluarExpresion("-2x-3"), 0.001); // El resultado de -2 * -3 es 6
    }
}
