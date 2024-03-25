package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Button;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMain2Binding;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    private TextView text;

    private Button button;

    private TextInputEditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        textInput = (TextInputEditText)findViewById(R.id.name);

        button = (Button)findViewById(R.id.button2);

        text = (TextView)findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userInput = textInput.getText().toString();

                int addIndex = userInput.indexOf("+");
                int subIndex = userInput.indexOf("-");

                int operatorIndex = Math.max(addIndex, subIndex);

                if(operatorIndex != -1) {
                    String firstPart = userInput.substring(0, operatorIndex);
                    String secondPart = userInput.substring(operatorIndex + 1, userInput.length());

                    int firstNumber;
                    int secondNumber;
                    try {
                        firstNumber = Integer.parseInt(firstPart);
                        secondNumber = Integer.parseInt(secondPart);

                        int total;
                        if ((addIndex != -1) && (subIndex == -1)) {
                             total = firstNumber + secondNumber;
                            text.setText(String.valueOf(total));
                        } else if((subIndex != 1) && (addIndex == -1)) {
                            total = firstNumber - secondNumber;
                            text.setText(String.valueOf(total));
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