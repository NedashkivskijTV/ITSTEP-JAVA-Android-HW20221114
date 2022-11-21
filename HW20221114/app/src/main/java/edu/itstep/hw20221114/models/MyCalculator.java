package edu.itstep.hw20221114.models;

public class MyCalculator {
    private final String operator;

    public MyCalculator(String operator) {
        this.operator = operator;
    }

    public double calculations(double... a) {
        // перевірки аргументів не передбачені, оскільки при виникненні помилок
        // виводяться стнандартні повідомлення, що достатньо для реалізації
        // функціоналу навчального калькулятора
        switch (operator) {
            //case "√^*✕/÷+-":{
            case "√":
            {
                return Math.sqrt(a[0]);
            }
            case "^":
            {
                return Math.pow(a[0], a[1]);
            }
            case "✕":
            case "*":
            {
                return a[0] * a[1];
            }
            case "÷":
            case "/":
            {
                return a[0] / a[1];
            }
            case "+":
            {
                return a[0] + a[1];
            }
            case "-":
            {
                return a[0] - a[1];
            }
        }
        return 0;
    }
}
