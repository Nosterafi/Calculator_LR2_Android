package com.example.calculator;

import androidx.lifecycle.ViewModel;

/**
 * ViewModel для калькулятора.
 */
public class CalculatorModel extends ViewModel {

    /**
     * Выполняет арифметическую операцию над двумя числами.
     *
     * @param a    Первое число в виде строки
     * @param b    Второе число в виде строки
     * @param sign Знак операции (+, -, *, /)
     * @return Результат вычисления
     * @throws NumberFormatException    Если строка не является корректным числом
     * @throws IllegalArgumentException Если операция не поддерживается или деление на ноль
     */
    public float calculate(String a, String b, String sign) {
        // Парсинг первого числа
        float aNum;
        try {
            aNum = Float.parseFloat(a);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ошибка при вводе числа A");
        }

        // Парсинг второго числа
        float bNum;
        try {
            bNum = Float.parseFloat(b);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ошибка при вводе числа B");
        }

        // Выполнение арифметической операции в зависимости от знака
        switch (sign) {
            case "+":
                return aNum + bNum;
            case "-":
                return aNum - bNum;
            case "*":
                return aNum * bNum;
            case "/":
                return tryDivide(aNum, bNum);
            default:
                // Если знак операции не поддерживается
                throw new IllegalArgumentException("Ошибка. Выбранная операция не поддерживается");
        }
    }

    /**
     * Выполняет деление с проверкой деления на ноль.
     *
     * @param a Делимое
     * @param b Делитель
     * @return Результат деления
     * @throws IllegalArgumentException Если делитель равен нулю
     */
    private float tryDivide(float a, float b) {
        if (b == 0) {
            throw new IllegalArgumentException("Деление на ноль");
        }
        return a / b;
    }
}
