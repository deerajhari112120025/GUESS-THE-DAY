package com.example.guesstheday;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;
import java.util.Random;
public class MainActivity extends AppCompatActivity {

    TextView date;
    Button generate;
    Button check;
    Button start;
    String finalday = "";
    String values[];
    String days[]= {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    RadioButton SelectedButton;
    RadioGroup radio_optn;
    String ans;
    TextView score;
    int scr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = (TextView) findViewById(R.id.date);
        generate = (Button) findViewById(R.id.generate);
        check = (Button) findViewById(R.id.checkbtn);
        score=(TextView)findViewById(R.id.score);
        check.setVisibility(View.INVISIBLE);
        generate.setVisibility(View.INVISIBLE);
        start = (Button) findViewById(R.id.strtbtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                date.setText(generateRandomDate());
            }
        });



    }

    public String generateRandomDate() {
        check.setVisibility(View.VISIBLE);
        generate.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);

        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(0, 2100);
        gc.set(GregorianCalendar.YEAR, year);
        int dayofYear = randBetween(1, gc.getLeastMaximum(GregorianCalendar.DAY_OF_YEAR));
        gc.set(GregorianCalendar.DAY_OF_YEAR, dayofYear);
        finalday = gc.get(GregorianCalendar.DAY_OF_MONTH) + "-" + (gc.get(GregorianCalendar.MONTH) + 1) + "-" + gc.get(GregorianCalendar.YEAR);
        values = finalday.split("-");
        calculate(Integer.parseInt(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2]));
        randomoptions();

        return gc.get(GregorianCalendar.DAY_OF_MONTH) + "-" + (gc.get(GregorianCalendar.MONTH) + 1) + "-" + gc.get(GregorianCalendar.YEAR);


    }

    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));


    }


    public int checkLeap(int y) {
        if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0))
            return 1;
        else
            return 0;
    }


    public void calculate(int dd, int mm, int yyyy) {

        // Checking Leap year. If true then 1 else 0.
        int flag_for_leap = checkLeap(yyyy);


        String day[] = {"Sunday", "Monday", "Tuesday",
                "Wednesday", "Thursday", "Friday",
                "Saturday"};
        int m_no[] = {0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5};

        //Generalised check to find any Year Value
        int j;
        if ((yyyy / 100) % 2 == 0) {
            if ((yyyy / 100) % 4 == 0)
                j = 6;
            else
                j = 2;
        } else {
            if (((yyyy / 100) - 1) % 4 == 0)
                j = 4;
            else
                j = 0;
        }


        int total = (yyyy % 100) + ((yyyy % 100) / 4) + dd
                + m_no[mm - 1] + j;
        if (flag_for_leap == 1) {
            if ((total % 7) > 0)
                ans = day[(total % 7) - 1];
               // Toast.makeText(MainActivity.this,day[(total % 7) - 1] , Toast.LENGTH_LONG).show();
            else
                ans= day[6];
               // Toast.makeText(MainActivity.this, day[6], Toast.LENGTH_LONG).show();
        }
        else
            ans= day[(total % 7)];
           // Toast.makeText(MainActivity.this, day[(total % 7)], Toast.LENGTH_LONG).show();

    }

    public void randomoptions(){
        String fday1,fday2,fday3,fday4;
        RadioButton radioButton1 ,radioButton2,radioButton3,radioButton4;
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton1=findViewById(R.id.radioButton1);
        radioButton2=findViewById(R.id.radioButton2);
        radioButton4=findViewById(R.id.radioButton4);
        RadioButton[] arr = {radioButton1,radioButton2,radioButton3,radioButton4};
        Random myrandom= new Random();
        Random rand = new Random();

        int randomNum = rand.nextInt((3) + 1);
        // Log.i("random",String.valueOf(randomNum));


        do {
            fday1 = days[myrandom.nextInt(days.length)];

        }while(fday1.equals(ans));


        radioButton1.setText(fday1);


        do { fday2=days[myrandom.nextInt(days.length)];

        }while(fday2.equals(fday1) || fday2.equals(ans));



        radioButton2.setText(fday2);

        do {
            fday3 = days[myrandom.nextInt(days.length)];

        }while(fday3.equals(fday1) || fday3.equals(fday2) || fday3.equals(ans));

        radioButton3.setText(fday3);

        do{
            fday4=days[myrandom.nextInt(days.length)];
        }while(fday4.equals(fday1)||fday4.equals(fday2)||fday4.equals(fday3)||fday4.equals(ans));



        radioButton4.setText(fday4);
        arr[randomNum].setText(ans);







    }


    public void check_btn(View view) {
        radio_optn=(RadioGroup)findViewById(R.id.RadGrp);
        int selectedId =radio_optn.getCheckedRadioButtonId();
        SelectedButton =(RadioButton)findViewById(selectedId);
        if(SelectedButton.getText().equals(ans)){
            Toast.makeText(MainActivity.this, "YAYY CORRECT!!! CLICK FOR NEXT QUESTION", Toast.LENGTH_LONG).show();
            scr+=1;
        }
        else
        {Toast.makeText(MainActivity.this, "OOPS WRONG.. GAME OVER!! " +
                "" +
                "YOUR SCORE: "+scr+" CLICK GENERATE TO PLAY AGAIN", Toast.LENGTH_LONG).show();
        scr=0;}
        score.setText("SCORE:"+scr);

    }

    public void generate_btn(View view) {

        date.setText(generateRandomDate());
    }

}