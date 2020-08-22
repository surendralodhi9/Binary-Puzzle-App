package com.example.binarypuzzle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Puzzle8x8 extends AppCompatActivity {


    public static FragmentManager fragmentManager;
    private Button btnRestart,btnCheck,btnHome,btnAbout,btnRules;

    private TextView generateMessage;

    public static String []puzzlelist={"Select","6x6","8x8","10x10","12x12","14x14"};
    public static int size=0;
    public static String  [][]curgrid=new String[14][14];

    public Spinner spinner;
    public ArrayAdapter<String> adapter;
    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle8x8);

        setUpUIView();
        //Fragment code start from here
        fragmentManager=getSupportFragmentManager();


        if(findViewById(R.id.fragment_container)!=null)
        {

            if(savedInstanceState!=null)
            {
                return;
            }

            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

            HomeGridFragment homeGridFragment=new HomeGridFragment();

            fragmentTransaction.add(R.id.fragment_container,homeGridFragment,null);
            fragmentTransaction.commit();

        }

        fillBinaryPuzzleGrid();

        handler=new Handler(getApplicationContext().getMainLooper());
        spinner=(Spinner)findViewById(R.id.spinner1);

        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,puzzlelist);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(size!=0)
                    generateMessage.setText("Generating puzzle....");
                else
                    generateMessage.setText("");
                switch(position) {

                    case 0:
                    {
                       //size=0;
                    }
                    break;
                    case 1:
                     {
                         handler.post(new Runnable() {
                             @Override
                             public void run() {
                                 size=6;
                                 fillBinaryPuzzleGrid();
                                 Puzzle8x8.fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeGridFragment(),null).commit();

                             }
                         });
                     }
                    break;
                    case 2:
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                size=8;
                                fillBinaryPuzzleGrid();
                                Puzzle8x8.fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeGridFragment(),null).commit();

                            }
                        });
                    }
                    break;
                    case 3:
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                size=10;
                                fillBinaryPuzzleGrid();
                                Puzzle8x8.fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeGridFragment(),null).commit();

                            }
                        });
                    }
                    break;
                    case 4:
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                size=12;
                                fillBinaryPuzzleGrid();
                                Puzzle8x8.fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeGridFragment(),null).commit();

                            }
                        });
                    }
                    break;
                    case 5:
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                size=14;
                                fillBinaryPuzzleGrid();
                                Puzzle8x8.fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeGridFragment(),null).commit();

                            }
                        });

                    }
                    break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnHome.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                size=0;
                Intent intent=new Intent(Puzzle8x8.this,Puzzle8x8.class);

                startActivity(intent);

            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Puzzle8x8.this,AboutActivity.class);

                startActivity(intent);
            }
        });
        btnRules.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Puzzle8x8.this,RulesActivity.class);

                startActivity(intent);
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {


                if(size==0)
                {

                    Toast t= Toast.makeText(getApplicationContext(),"Please!! generate puzzle",Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    t.show();
                   return;
                }
                BinaryPuzzleGenerator binary=new BinaryPuzzleGenerator();

                int fl=0;
                char [][]temp=new char[size][size];
                for(int i=0;i<size;i++)
                {

                    for(int j=0;j<size;j++)
                    {
                        if(curgrid[i][j].charAt(0)==' ')
                        {
                            fl=1;
                            break;
                        }
                        temp[i][j]=curgrid[i][j].charAt(0);
                    }
                    if(fl==1)
                        break;
                }
                if(fl==1)
                {
                    Toast t= Toast.makeText(getApplicationContext(),"Puzzle not filled yet, Please fill the puzzle",Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                    t.show();
                    return;

                }
                if(binary.isCorrectSolution(size,temp)) {


                    Toast t=Toast.makeText(getApplicationContext(), "Congratulations!!! you solved the puzzle", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                    t.show();
                }
                else {
                    Toast t=Toast.makeText(getApplicationContext(), "Wrong answer.. Please try again", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                    t.show();
                }

            }
        });
        btnRestart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                fillBinaryPuzzleGrid();
                Puzzle8x8.fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeGridFragment(),null).commit();

            }
        });

    }
    private void fillBinaryPuzzleGrid()
    {
        int n=size;
        SudokuBoardView.size=size;

        String []basePuzzle={"    1    0   1",
                             "   0   0    1 ",
                             "   1    0     ",
                             "1     1   0  1",
                             "   1     0    ",
                             "   0       1 0",
                             "1     0      1",
                             "    0        0",
                             "  0    1     1",
                             "  1    0     0",
                             "       1     0",
                             "  1    0     1",
                             "             0",
                             "  1   1     0 "};
        char [][]s1=new char[n][n];
        switch(n)
        {
            case 6:
            case 8:
            {
                for(int i=0;i<n;i++)
                {
                    for(int j=0;j<n;j++)
                       s1[i][j]=' ';
                }
            }
            break;
            case 10:
            case 12:
            case 14:
            {

                for(int i=0;i<n;i++)
                {
                    for(int j=0;j<n;j++)
                        s1[i][j]=basePuzzle[i].charAt(j);
                }
            }
            break;

        }
         /* String []s={"      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      "};


      char [][]s1=new char[n][n];

        for(int i=0;i<n;i++)
        {

            for(int j=0;j<n;j++)
                s1[i][j]=s[i].charAt(j);
        }*/

        BinaryPuzzleGenerator binary=new BinaryPuzzleGenerator();
        ThreadLocalRandom threadRandom = ThreadLocalRandom.current();


        int des=(int)threadRandom.nextInt(1, 1001);
        char [][]puzzle=new char[n][n];

        BinaryPuzzleGenerator.Flag=0;


        binary.randomSudokuSelector(0,0,n,s1,des,puzzle);
        binary.generateSolvablePuzzle(n,puzzle);

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                curgrid[i][j]=String.valueOf(puzzle[i][j]);
        //fill Binary
        fillPuzzleOnButtonGrid();
        if(size!=0)
        generateMessage.setText("Puzzle generated in "+(n*n)/4+" moves.");
        //binary.printSudoku(n,curgrid);



    }
    public void fillPuzzleOnButtonGrid()
    {


    }
    public void setUpUIView()
    {

        btnHome=(Button)findViewById(R.id.btn_home);
        btnAbout=(Button)findViewById(R.id.btn_about);
        btnRules=(Button)findViewById(R.id.btn_rules);
        btnRestart=(Button)findViewById(R.id.btn_restart);
        btnCheck=(Button)findViewById(R.id.btn_check);
        generateMessage= (TextView)findViewById(R.id.generated_message);
        Intent intent = new Intent();
    }
}
