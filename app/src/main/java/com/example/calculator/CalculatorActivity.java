package com.example.calculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

public class CalculatorActivity extends AppCompatActivity {
    private CalculatorModel viewModel;
    private EditText aEditText;
    private EditText bEditText;
    private EditText resultEditText;
    private TextView signView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calculator_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.start_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(CalculatorModel.class);
        setElements();
    }

    /**
     * Обработчик нажатия кнопок операций.
     * Устанавливает выбранный знак операции в TextView.
     * @param view Кнопка с знаком операции
     */
    public void setOperation(View view) {
        String newSign = ((Button) view).getText().toString();
        signView.setText(newSign);
    }

    /**
     * Обработчик кнопки очистки.
     * Сбрасывает значения полей ввода в "0".
     * @param view Кнопка очистки
     */
    public void clearNums(View view) {
        aEditText.setText("0");
        bEditText.setText("0");
    }

    /**
     * Обработчик кнопки вычисления.
     * Получает числа и знак операции, выполняет расчет и отображает результат.
     * @param view Кнопка "="
     */
    public void solve(View view) {
        var a = aEditText.getText().toString();
        var b = bEditText.getText().toString();
        var sign = signView.getText().toString();

        try {
            resultEditText.setText(String.valueOf(viewModel.calculate(a, b, sign)));
        } catch (Exception e) {
            showAlert("Ошибка", e.getMessage());
        }
    }

    public void closeActivity(View view){
        finish();
    }

    /**
     * Инициализирует ссылки на элементы UI.
     */
    private void setElements() {
        aEditText = findViewById(R.id.aEditText);
        bEditText = findViewById(R.id.bEditText);
        signView = findViewById(R.id.signView);
        resultEditText = findViewById(R.id.resultEditText);
    }

    /**
     * Отображает диалоговое окно с сообщением об ошибке.
     * @param title Заголовок диалога
     * @param message Текст сообщения
     */
    private void showAlert(CharSequence title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CalculatorActivity.this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("Выход", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}