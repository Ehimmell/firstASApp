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

                if(userInput.equals("carter")) {
                    text.setText("smells");
                } else if(userInput.equals("ethan")) {
                    text.setText("rules");
                } else {
                    text.setText(userInput);
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