package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.textfield.TextInputEditText;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<Double> answers = new ArrayList<Double>();
    private AppBarConfiguration appBarConfiguration;
    private TextView text;
    private Button equals;
    private Button ans;
    private Button ansHistory;
    private TextInputEditText textInput;
    private RadioGroup answerOrder;
    private RadioButton ans1;
    private RadioButton ans2;
    private RadioButton ans3;
    private RadioButton ans4;
    private RadioButton selectedAnswer;
    private Double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        textInput = findViewById(R.id.equation);

        equals = findViewById(R.id.equals);

        text = findViewById(R.id.textView);

        ans = findViewById(R.id.answerButton);

        ansHistory = findViewById(R.id.history);

        answerOrder = findViewById(R.id.ansGroup);

        answerOrder.setVisibility(View.INVISIBLE);

        ans1 = findViewById(R.id.answer1);

        ans2 = findViewById(R.id.answer2);

        ans3 = findViewById(R.id.answer3);

        ans4 = findViewById(R.id.answer4);

        RadioButton[] radioButtonList = new RadioButton[]{ans1, ans2, ans3, ans4};
        ansHistory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                answerOrder.setVisibility(View.VISIBLE);
                for (int i = 0; i < answers.size(); i++) {
                    radioButtonList[i].setText(String.valueOf(answers.get(i)));
                    text.setText("Test");
                }
            }
        });

        answerOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedAnswer = (RadioButton)findViewById(checkedId);
                String current = textInput.getText().toString();

                current += selectedAnswer.getText();

                textInput.setText(current);

                answerOrder.setVisibility(View.INVISIBLE);

            }
        });
        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current = textInput.getText().toString();
                String answer = text.getText().toString();

                current += answer;

                textInput.setText(current);
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String userInput = textInput.getText().toString();
                    Expression expression = new ExpressionBuilder(userInput).build();
                    total = expression.evaluate();

                    text.setText(String.valueOf(total));
                    answers.add(total);

                    while (answers.size() > 4) {
                        answers.remove(0);
                    }
                } catch(Exception ex) {
                    text.setText("Error");
                }
            }


        });

    }

}