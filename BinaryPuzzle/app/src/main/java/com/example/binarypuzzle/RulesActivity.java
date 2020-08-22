package com.example.binarypuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    public static String Rules="1. Each box should contain either a zero or a one.\n" + "\n" +
            "2. More than two equal numbers immediately next to or below each are not allowed.\n" +
            "\n" + "3. Each row and each column should contain an equal number of zeros and ones.\n" +
            "\n" + "4. Each row is unique and each column is unique. Thus, any row cannot be exactly equal to another row, and any column cannot be exactly equal to another column.\n" + "\n" +"";
            //"Each binary puzzle has a unique solution. It is always possible to make a next step by reasoning. In other words, the solution can always be found without guessing.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        TextView rules_View=(TextView)findViewById(R.id.rulesView);

            rules_View.setText(Rules);
    }
}
