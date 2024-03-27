/* The below code creates a simple, any operation type calculator. This calculator also holds the answers to the user's last 4 expressions, and these answers can be called using a button.

____________________________________________________________________________________________________________________________________________________________________________________________________________

3rd Party libraries used-
exp4j- an expression library used to evaluate mathematical expressions (e.g. 1+1) that are considered bu Java to be in string form. Instead of parsing the given expression until I find an operator,
splitting the expression on the operator to make two numbers, and doing something to these two numbers based on what operator is found(which would only allow one operation in each expression without
more code), I can give the entire expression to an Expression variable in string form and have it evaluated.

Link - https://www.objecthunter.net/exp4j/

____________________________________________________________________________________________________________________________________________________________________________________________________________

Made by Ethan Himmell on March 27, 2024.

 */

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

    //Initialize ArrayList that will hold answer history
    ArrayList<Double> answers = new ArrayList<Double>();
    private AppBarConfiguration appBarConfiguration;

    //Initialize Text Display
    private TextView text;

    //Initialize button whose listener will calculate the string in textInput upon press
    private Button equals;

    //Initialize the button that retrieves the last calculation's answer for quick access
    private Button ans;

    //Initialize the button that brings up display of answer history
    private Button ansHistory;

    //Initialize Input Space for calculations
    private TextInputEditText textInput;

    //Initialize the group of Radio Buttons that Display answer history
    private RadioGroup answerOrder;

    //Initialize each RadioButton
    private RadioButton ans1;
    private RadioButton ans2;
    private RadioButton ans3;
    private RadioButton ans4;

    //Initialize "dummy" RadioButton that is used to represent selected button when a RadioButton is pressed
    private RadioButton selectedAnswer;

    //Initialize Double for the current answer to be stored in
    private Double total;

    //Initialize "Storage" string for the current text of an element to be stored and added to
    private String current;

    //Create string to store the calculation from textInput to be calculated when equals is pressed
    private String userInput;

    //Create an expression, a type from the exp4j library, which holds a string that can be evaluated mathematically
    private Expression expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set view to main layout
        setContentView(R.layout.activity_main2);

        //Map all buttons and displays by their ids in the main layout, and set the RadioButton group to be invisible once it is mapped
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

        //Create a RadioButton Array to be Parsed with a list of answers, setting the current item from the array to the current answer in answers list
        RadioButton[] radioButtonList = new RadioButton[]{ans1, ans2, ans3, ans4};

        //Create on click listener for ansHistory Button
        ansHistory.setOnClickListener(new View.OnClickListener() {


            //Define on-click behavior
            @Override
            public void onClick(View v) {

                //Make the answer history radio buttons visible
                answerOrder.setVisibility(View.VISIBLE);

                //Parse through the list of answers
                for (int i = 0; i < answers.size(); i++) {

                    //Set the current button in the RadioButton list's text to be the current answer in string form
                    radioButtonList[i].setText(String.valueOf(answers.get(i)));
                }
            }
        });

        //Create on click listener for the RadioButton group
        answerOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            //Set on-click behavior
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //Set the "dummy" RadioButton to be the button that was selected by the user
                selectedAnswer = (RadioButton)findViewById(checkedId);

                //Get the expression the user is currently inputting and turn it into a string
                current = textInput.getText().toString();

                //Add the text from the dummy RadioButton in string form to the user's current expression
                current += selectedAnswer.getText().toString();

                //Set the text to the updated string created above
                textInput.setText(current);

                //Make the radio buttons disappear again
                answerOrder.setVisibility(View.INVISIBLE);

            }
        });

        //Create on click listener for the button made to insert the last answer
        ans.setOnClickListener(new View.OnClickListener() {

            //Define on-click behavior
            @Override
            public void onClick(View v) {

                /*Similar to the process when a radio button is clicked
                Get the expression the user is currently typing in text input */
                current = textInput.getText().toString();

                //If the answer currently being displayed wasn't a result of a faulty expression, do the following:
                if(!(text.getText().toString().equals("Error"))) {
                    //Add the answer that is currently being displayed in text
                    current += text.getText().toString();

                    //Set the text to the updated string created above
                    textInput.setText(current);
                }
            }
        });

        /*The below code uses the exp4j 3rd party party, which, as mentioned above, evaluates expressions in string form.
         Create on click listener for the equals sign button*/
        equals.setOnClickListener(new View.OnClickListener() {

            //Define on-click behavior
            @Override
            public void onClick(View v) {
                /*Try the following, and if it errors out go to catch. In this case, the given expression may
                not be something that the ExpressionBuilder can evaluate, and if this is the case, it will throw an error.
                 */
                try {
                    //Retrieve the user's expression from the textInput element
                    userInput = textInput.getText().toString();
                    //Create an expression for the user's currently string-form expression
                    expression = new ExpressionBuilder(userInput).build();
                    //Evaluate the user's expression, and store it in the total variable
                    total = expression.evaluate();

                    //Set the answer display's text to the answer
                    text.setText(String.valueOf(total));
                    //Add the latest answer ot the list of answers
                    answers.add(total);

                    //While the size of the answers list is over 4, do the following:
                    while (answers.size() > 4) {
                        //Take out the first element of answers, aka the oldest answer that is currently being stored
                        answers.remove(0);
                    }

                //If something in the try didn't work, do this:
                } catch(Exception ex) {
                    //Set the answer display's text to error to indicate there was a problem with evaluation
                    text.setText("Error");
                }
            }


        });

    }

}