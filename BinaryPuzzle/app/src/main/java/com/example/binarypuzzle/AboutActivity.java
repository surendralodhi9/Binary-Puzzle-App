package com.example.binarypuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {


    public static String AboutInfo="<h3>Home</h3>" +
                                    "<p>No puzzle selected</p>"+
                                    "<br>" +
                                    "<h3>Select</h3>"+
                                    "<p>Select a board size of puzzle</p>"+
                                    "<br>" +
                                    "<h3>Restart</h3>"+
                                    "<p>Generate a new Binary Puzzle, from scratch of selected board size</p>"+
                                    "<br>" +
                                    "<h3>Check</h3>"+
                                    "<p>Check the puzzle after filling</p>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        TextView about_View=(TextView)findViewById(R.id.aboutView);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            about_View.setText(Html.fromHtml(AboutInfo, Html.FROM_HTML_MODE_COMPACT));
        } else {
            about_View.setText(Html.fromHtml(AboutInfo));
        }
    }
}
