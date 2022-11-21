package edu.itstep.hw20221114;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import edu.itstep.hw20221114.models.MyCalculator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> numbersAndSigns = new ArrayList<>();

    private final int maxLengthStringOnScreen = 10;
    private final String signs = "√^*✕/÷+-";
    private final String END_OF_CALCULATIONS = "###end###";

    private TextView tvText;
    private Button btnClearAll;
    private Button btnClearLast;
    private Button btnDegree;
    private Button btnDivision;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnMultiplication;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btnMinus;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btnPlus;
    private Button btnSqrt;
    private Button btn0;
    private Button btnPoint;
    private Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ініціалізація змінних класу - елементів (кнопок..) калькулятора
        tvText = findViewById(R.id.tvText);
        btnClearAll = findViewById(R.id.btnClearAll);
        btnClearLast = findViewById(R.id.btnClearLast);
        btnDegree = findViewById(R.id.btnDegree);
        btnSqrt = findViewById(R.id.btnSqrt);
        btnDivision = findViewById(R.id.btnDivision);
        btnMultiplication = findViewById(R.id.btnMultiplication);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnResult = findViewById(R.id.btnResult);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnPoint = findViewById(R.id.btnPoint);

        // підключення події onClick до кнопок
        btnClearAll.setOnClickListener(this);
        btnClearLast.setOnClickListener(this);
        btnDegree.setOnClickListener(this);
        btnSqrt.setOnClickListener(this);
        btnDivision.setOnClickListener(this);
        btnMultiplication.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnResult.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPoint.setOnClickListener(this);

        // перше число у колекції чисел/знаків - ініціалізація колекції
        numbersAndSigns.add("0");
    }

    @Override
    public void onClick(View view) {
        int btn_id = view.getId();

        // у разі наявності системної позначки про закінчення обчислень (виведення результату)
        // колекцію буде очищено -
        // використовується для забезпечення можливості вводити нове число після отримання
        // результату обчислень,
        // інакше число (результат) буде дописуватись
        if (numbersAndSigns.size() > 1 && numbersAndSigns.get(1).equals(END_OF_CALCULATIONS)) {
            clearOll();
        }

        switch (btn_id) {
            case R.id.btnClearAll: {
                tvText.setText("0");
                clearOll();
                break;
            }
            case R.id.btnClearLast: {
                dellLastSymbol();
                break;
            }
            case R.id.btnDegree: {
                addSymbol("^");
                break;
            }
            case R.id.btnSqrt: {
                addSymbol("√");
                break;
            }
            case R.id.btnDivision: {
                addSymbol("÷");
                break;
            }
            case R.id.btnMultiplication: {
                addSymbol("✕");
                break;
            }
            case R.id.btnMinus: {
                addSymbol("-");
                break;
            }
            case R.id.btnPlus: {
                addSymbol("+");
                break;
            }
            case R.id.btnResult: {
                calculations();
                break;
            }
            case R.id.btn0: {
                addSymbol("0");
                break;
            }
            case R.id.btn1: {
                addSymbol("1");
                break;
            }
            case R.id.btn2: {
                addSymbol("2");
                break;
            }
            case R.id.btn3: {
                addSymbol("3");
                break;
            }
            case R.id.btn4: {
                addSymbol("4");
                break;
            }
            case R.id.btn5: {
                addSymbol("5");
                break;
            }
            case R.id.btn6: {
                addSymbol("6");
                break;
            }
            case R.id.btn7: {
                addSymbol("7");
                break;
            }
            case R.id.btn8: {
                addSymbol("8");
                break;
            }
            case R.id.btn9: {
                addSymbol("9");
                break;
            }
            case R.id.btnPoint: {
                addSymbol(".");
                break;
            }
        }
        showExpression();
    }

    // очищення колекції
    private void clearOll() {
        numbersAndSigns.clear();
        numbersAndSigns.add("0");
    }

    // додавання символа - реакція на натискання кнопок
    private void addSymbol(String text) {
        int masSize = numbersAndSigns.size();
        String lastElement = numbersAndSigns.get(masSize - 1);
        if (text.equals(".")) { // якщо введено "."
            if (!signs.contains(lastElement) && !lastElement.contains(".")) {
                String newNumber = lastElement + text;
                numbersAndSigns.set(masSize - 1, newNumber);
            }
        } else if (signs.contains(text)) { // якщо введено знак матем.дії
            if (text.equals("√")) {
                if (signs.contains(lastElement) && !lastElement.equals("√")) {
                    numbersAndSigns.add(text);
                } else if ((masSize == 1 && lastElement.equals("0"))) {
                    numbersAndSigns.set(0, text);
                }
            } else if (!signs.contains(lastElement)) {
                fixLastPoint();
                numbersAndSigns.add(text);
            }
        } else {
            if (signs.contains(lastElement)) {
                numbersAndSigns.add(text);
            } else {
                String newNumber = lastElement + text;
                numbersAndSigns.set(masSize - 1, checkFirstZero(newNumber));
            }
        }
    }

    // виправлення вводу числа у форматі "2." - додається в кінці 0 - виправляє на формат "2.0"
    private void fixLastPoint() {
        String lastElement = numbersAndSigns.get(numbersAndSigns.size() - 1);
        int lastElementLength = lastElement.length();
        if (lastElement.charAt(lastElementLength - 1) == ".".charAt(0)) {
            addSymbol("0");
        }
    }

    // усуває введення нулів на початку цілої частини числа
    private String checkFirstZero(String text) {
        StringBuilder newText = new StringBuilder(text);
        if (newText.charAt(0) == '0' && newText.charAt(1) != ".".charAt(0)) {
            newText.deleteCharAt(0);
        }
        return newText.toString();
    }

    // виведення вмісту колекції
    private void showExpression() {
        StringBuilder newText = new StringBuilder("");
        for (String numberOrSign : numbersAndSigns) {
            if (!numberOrSign.equals(END_OF_CALCULATIONS)) { // ігнорування системного значення - про закінчення обчислень
                newText.append(numberOrSign);
            }
        }

        // виведення вмісту колекції в залежності від того, здійснюється введення виразу або
        // виводиться результат обчислення виразу
        if (numbersAndSigns.size() > 1 && numbersAndSigns.get(1).equals(END_OF_CALCULATIONS)) { // виведення результату обчислення
            tvText.setText(numberRounding(newText.toString()));
        } else { // виведення при здійсненні вводу виразу
            if (newText.length() > maxLengthStringOnScreen) {
                newText = new StringBuilder("..." + newText.substring(newText.length() - maxLengthStringOnScreen - 1));
            }
            tvText.setText(newText);
        }
    }

    // забезпечення роботи кнопки видалення останнього символу "backspace"
    private void dellLastSymbol() {
        int masSize = numbersAndSigns.size();
        String lastElement = numbersAndSigns.get(masSize - 1);
        if (masSize == 1 && lastElement.length() == 1) {
            clearOll();
        } else if (lastElement.length() == 1) {
            numbersAndSigns.remove(masSize - 1);
        } else if (lastElement.length() > 0) {
            StringBuilder newText = new StringBuilder(lastElement).deleteCharAt(lastElement.length() - 1);
            numbersAndSigns.set(masSize - 1, newText.toString());
        }
    }

    // розбирання колекції=математичного виразу на групи "число-знак-число" або у разі кв.кореня "знак-число"
    private void calculations() {
        removeLastSign();
        int pozSign = -1;
        StringBuilder leftNumber = new StringBuilder("");
        StringBuilder rightNumber = new StringBuilder("");
        StringBuilder sign = new StringBuilder("");
        for (int i = 0; i < signs.length(); i++) {
            sign = new StringBuilder(signs.substring(i, i + 1));
            while (numbersAndSigns.contains(sign.toString())) { // перевірка наявності і-го знака матем.дії
                pozSign = numbersAndSigns.indexOf(sign.toString()); // позиція знака матем.дії
                if (sign.toString().equals("√")) {
                    rightNumber = new StringBuilder("0"); // збереження елемента зліва від знака матем.дії
                    leftNumber = new StringBuilder(numbersAndSigns.get(pozSign + 1)); // збереження елемента зправа від знака матем.дії
                } else {
                    // формування параметрів - число-дія-число
                    leftNumber = new StringBuilder(numbersAndSigns.get(pozSign - 1)); // збереження елемента зліва від знака матем.дії
                    rightNumber = new StringBuilder(numbersAndSigns.get(pozSign + 1)); // збереження елемента зправа від знака матем.дії
                    numbersAndSigns.set(pozSign - 1, ""); // обнулення в колекції елемента зліва від знака матем.дії
                }
                numbersAndSigns.set(pozSign + 1, ""); // обнулення в колекції елемента зправа від знака матем.дії

                // виклик калькулятора
                double result = new MyCalculator(sign.toString()).calculations(Double.parseDouble(leftNumber.toString()), Double.parseDouble(rightNumber.toString()));
                numbersAndSigns.set(pozSign, "" + result);

                // видалення пустих елементів з колекції
                removeEmptyElement();
            }
        }
        // додавання в колекцію позначки про закінчення обчислень
        if (numbersAndSigns.size() == 1) {
            numbersAndSigns.add(END_OF_CALCULATIONS);
        }
    }

    // видалення останнього елемента масиву, якщо він є знаком (математична дія)
    private void removeLastSign() {
        while (signs.contains(numbersAndSigns.get(numbersAndSigns.size() - 1))) {
            numbersAndSigns.remove(numbersAndSigns.size() - 1);
        }
    }

    // видалення пустого елемента з колекції
    private void removeEmptyElement() {
        while (numbersAndSigns.contains("")) {
            numbersAndSigns.remove("");
        }
    }

    // округлення результату обчислень для зменшення довжини рядка на екрані калькулятора
    private String numberRounding(String numberInString) {
        if (numberInString.length() > maxLengthStringOnScreen && numberInString.contains("E")) {
            BigDecimal bd = new BigDecimal(numberInString.substring(0, numberInString.indexOf("E")));
            bd = bd.setScale(5, RoundingMode.UP);
            return bd.toString() + numberInString.substring(numberInString.indexOf("E"));
        }
        return numberInString;
    }

}