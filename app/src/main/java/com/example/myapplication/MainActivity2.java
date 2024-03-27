package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMain2Binding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

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

    Map<RadioButton, Double> buttonMap;

    ArrayList<Double> answers = new ArrayList<Double>();

    RadioButton[] radioButtonList = new RadioButton[]{ans1, ans2, ans3, ans4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textInput = (TextInputEditText)findViewById(R.id.name);

        equals = (Button)findViewById(R.id.button2);

        text = (TextView)findViewById(R.id.textView);

        ans = (Button)findViewById(R.id.button);

        ansHistory = (Button)findViewById(R.id.button3);

        answerOrder = (RadioGroup)findViewById(R.id.ansGroup);

        setContentView(R.layout.activity_main2);

        answerOrder.setVisibility(View.INVISIBLE);


        ansHistory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                answerOrder.setVisibility(View.VISIBLE);
                for(int i = 0; i < answers.size(); i++) {
                    radioButtonList[i].setText(String.valueOf(answers.get(i)));
                    buttonMap.put(radioButtonList[i], answers.get(i));
                }
            }
        });

        answerOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

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
        equals.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userInput = textInput.getText().toString();

                int addIndex = userInput.indexOf("+");
                int subIndex = userInput.indexOf("-");
                int multIndex = userInput.indexOf("*");
                int divIndex = userInput.indexOf("/");


                int sumIndex = Math.max(addIndex, subIndex);
                int prodIndex = Math.max(multIndex, divIndex);

                int operatorIndex = Math.max(prodIndex, sumIndex);

                if(operatorIndex != -1) {
                    String firstPart = userInput.substring(0, operatorIndex);
                    String secondPart = userInput.substring(operatorIndex + 1, userInput.length());

                    double firstNumber;
                    double secondNumber;
                    try {
                        firstNumber = Double.parseDouble(firstPart);
                        secondNumber = Double.parseDouble(secondPart);

                        double total = 0;
                        if ((prodIndex == -1) && (subIndex == -1)) {
                             total = firstNumber + secondNumber;
                        } else if((prodIndex == -1) && (addIndex == -1)) {
                            total = firstNumber - secondNumber;
                        } else if((sumIndex == -1) && (divIndex == -1)) {
                            total = firstNumber * secondNumber;
                        } else if((sumIndex == -1) && (multIndex == -1)) {
                            total = firstNumber / secondNumber;
                        }

                        text.setText(String.valueOf(total));
                        answers.add(total);

                        if(answers.size() > 4) {
                            answers.remove(answers.size());
                        }

                    }catch(Exception ex) {
                        text.setText("One or more of your expressions is not a number");
                    }
                } else {
                    text.setText("This is not an equation");
                }
            }


        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main2);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}