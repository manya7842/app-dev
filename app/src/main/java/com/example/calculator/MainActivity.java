package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution, result;
    MaterialButton buttonAC, buttonOpenBracket, buttonCloseBracket, buttonDot, buttonC;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonDivide, buttonMultiply, buttonAdd, buttonSubtract, buttonEquals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
            });
        result=findViewById(R.id.result);
        solution=findViewById(R.id.solution);
        assignId(buttonAC, R.id.button_AC);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonAdd, R.id.button_add);
        assignId(buttonSubtract, R.id.button_subtract);
        assignId(buttonC, R.id.button_C);
        assignId(buttonDot, R.id.button_decimal);
        assignId(buttonEquals, R.id.button_equal);
        assignId(buttonOpenBracket, R.id.button_open_bracket);
        assignId(buttonCloseBracket, R.id.button_close_bracket);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);

    }
    void assignId(MaterialButton btn, int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        MaterialButton button= (MaterialButton) v;
        String buttonText= button.getText().toString();
        String dataCalc= solution.getText().toString();

        if(buttonText.equals("AC"))
        {
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            solution.setText(result.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            dataCalc= dataCalc.substring(0, dataCalc.length()-1);
        }
        else {
            dataCalc = dataCalc + buttonText;
        }
        solution.setText(dataCalc);
        String finalRes= getResult(dataCalc);
        if(finalRes.equals("Error")!=true) {
            result.setText(finalRes);
        }
    }
    String getResult(String data)
    {
        try
        {
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable= context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0"))
                finalResult = finalResult.replace(".0", "");
            return finalResult;
        }
        catch(Exception e)
        {
            return "Error";
        }

    }
}