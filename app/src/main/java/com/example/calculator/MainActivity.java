package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result_tv, solution_tv;
    MaterialButton button_c, button_openbracket, button_closedbracket;
    MaterialButton button_divide, button_multliply, button_add, button_subtract, button_equal;
    MaterialButton button_seven, button_eight, button_nine, button_four, button_five, button_six, button_one, button_two, button_three, button_zero;
    MaterialButton button_ac, button_dot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv = findViewById(R.id.result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        assignId(button_c, R.id.button_c);
        assignId(button_openbracket, R.id.button_openbracket);
        assignId(button_closedbracket, R.id.button_closedbracket);
        assignId(button_divide, R.id.button_divide);
        assignId(button_multliply, R.id.button_multliply);
        assignId(button_add, R.id.button_add);
        assignId(button_subtract, R.id.button_subtract);
        assignId(button_equal, R.id.button_equal);
        assignId(button_seven, R.id.button_seven);
        assignId(button_eight, R.id.button_eight);
        assignId(button_nine, R.id.button_nine);
        assignId(button_four, R.id.button_four);
        assignId(button_five, R.id.button_five);
        assignId(button_six, R.id.button_six);
        assignId(button_one, R.id.button_one);
        assignId(button_two, R.id.button_two);
        assignId(button_three, R.id.button_three);
        assignId(button_ac, R.id.button_ac);
        assignId(button_zero, R.id.button_zero);
        assignId(button_dot, R.id.button_dot);

    }


    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();

        if(buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        } else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solution_tv.setText(dataToCalculate);


        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Error")){
            result_tv.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch(Exception e){
            return "Error";
        }
    }
}