package com.arash.braingame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView question;
    TextView time;
    TextView counter;
    Display display;
    Point size= new Point();
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btnStart;
    TextView result;
    RelativeLayout game;
    int[] location= new  int[4];
    int cc=0 , qc=0;
    int a,b,d=0;
    int correctLocation=0 ;
    long timerTime = 30100;
    CountDownTimer aaa;
    int progress = 1;



    public void onStartUpCode (){

        btn1=(Button)findViewById(R.id.btn1);
        btn1.setBackgroundColor(Color.parseColor("#6C6969"));
        //in XML COLOR =================================> android:backgroundTint="#6C6969"
        btn2=(Button)findViewById(R.id.btn2);
        btn2.setBackgroundColor(Color.parseColor("#6C6969"));
        btn3=(Button)findViewById(R.id.btn3);
        btn3.setBackgroundColor(Color.parseColor("#6C6969"));
        btn4=(Button)findViewById(R.id.btn4);
        btn4.setBackgroundColor(Color.parseColor("#6C6969"));
        btnStart=(Button)findViewById(R.id.btnStart);
        game=(RelativeLayout)findViewById(R.id.relativeLayout2);
        result=(TextView)findViewById(R.id.txtResult);
        result.bringToFront();
        result.setVisibility(View.INVISIBLE);
        question = (TextView)findViewById(R.id.questiionTextView);
        time = (TextView)findViewById(R.id.timeTextView);
        counter = (TextView)findViewById(R.id.counterTextView);
        counter.setText(Integer.toString(cc)+"/"+Integer.toString(qc));
        display= getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int width = size.x;
        question.setWidth(3*(width)/7);
        time.setWidth(2*(width)/7);
        counter.setWidth(2*(width)/7);
        startGameCalculate();




    }

    public void countdownTimer(){


        aaa = new CountDownTimer(timerTime, 1000) {



            @Override
            public void onTick(long l) {

                l=timerTime;
                time.setText(Integer.toString((int)l/1000)+"s");
                timerTime-=1000;
                //time.setText("HI");



            }

            @Override
            public void onFinish() {

                time.setText("0s");
                result.setVisibility(View.VISIBLE);
                result.setText(Integer.toString(cc)+"\n Correct Answers \n\n Tap To Restart");


            }
        }.start();





        //Log.i("CountDownTimer"," done");
    }

    public int calcul(){

        int first,second, ans=1000;

        Random rand = new Random();
        int select = rand.nextInt(progress)+1;
        //Log.i("random number==",Integer.toString(select));
        if(select==1){
            first=rand.nextInt(50)+1;
            second=rand.nextInt(50)+1;
            ans=add(first,second);
            question.setText(Integer.toString(first)+ " + " + Integer.toString(second));
        }
        if(select==2){
            first=rand.nextInt(50)+1;
            second=rand.nextInt(50)+1;
            ans=sub(first,second);
            question.setText(Integer.toString(first)+ " - " + Integer.toString(second));
        }
        if(select==3){
            first=rand.nextInt(9)+1;
            second=rand.nextInt(9)+1;
            ans=mul(first,second);
            question.setText(Integer.toString(first)+ " * " + Integer.toString(second));
        }
        return ans;
    }

    public int add(int first,int second){

        int ans = first+second;
        return ans;

    }
    public int sub(int first,int second){

        int ans = first-second;

        return ans;

    }
    public int mul(int first,int second){

        int ans = first*second;

        return ans;

    }

    public void startGameCalculate(){

        int ans,posetiveans=0;

        Random rand = new Random();
        while (correctLocation==d) {
            correctLocation = rand.nextInt(4);
        }
        d=correctLocation;

        //a=rand.nextInt(50)+1;
        //b=rand.nextInt(50)+1;
        ans=calcul();
        //Log.i("khodeANS",Integer.toString(ans));
        //posetiveans=ans;
       /* if(ans<0){

            posetiveans=ans*-1;
        } */
        //question.setText(Integer.toString(a)+ " + " + Integer.toString(b));

        for (int i=0;i<4;i++){

            if(i==correctLocation){

                //location[i]=a+b;
                location[i]=ans;

            }else {
                //int c=rand.nextInt(posetiveans)+1;
                int c=rand.nextInt(100)+1;

                while (c==ans){
                    //c=rand.nextInt(posetiveans)+1;
                    c=rand.nextInt(100)+1;
                }

                location[i]=c;

                for(int j=0;j<4;j++){
                    if(j!=correctLocation) {
                        if (location[j] == location[i]) {
                            location[j] = rand.nextInt(100) + 1;
                        }

                    }
                }

                if(ans<0) {
                    location[i] = location[i] * -1;
                }
            }

        }
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 3; j++) {
                if (j != correctLocation) {
                    if (location[j] == location[j + 1]) {
                        //Log.i("Before J", Integer.toString(location[j]));
                        location[j] = location[j] - 10;

                        //Log.i("this I value ="+ Integer.toString(i),"this J value ="+ Integer.toString(j));
                    }
                    if(location[j]==ans){

                        location[j] = location[j] - 10;

                    }

                }

            }

        }
        for (int j = 0; j < 4; j++) {
            if (j != correctLocation) {
                if (j < 3) {
                    if (location[j] == location[j + 1]) {
                        //Log.i("Before J", Integer.toString(location[j]));
                        location[j] = location[j] - 10;

                        //Log.i("this J OUT value =", "this J value =" + Integer.toString(j));
                    }
                }else{

                    if (location[j] == location[j - 1]) {
                        //Log.i("Before J", Integer.toString(location[j]));
                        location[j] = location[j] - 10;

                        //Log.i("this J OUT value =", "this J value =" + Integer.toString(j));
                    }

                }
                if(location[j]==ans){

                    location[j] = location[j] - 10;

                }

            }

        }


        btn1.setText(Integer.toString(location[0]));
        btn2.setText(Integer.toString(location[1]));
        btn3.setText(Integer.toString(location[2]));
        btn4.setText(Integer.toString(location[3]));


        /*for(int j=0;j<3;j++){
                if (location[j] == location[j+1]) {
                    Log.i("THERE IS SOME EQUALSSSSSSS","HAPPPPPEEEEENNNNNDDDDDD");
                    Log.i("LOCATION 0 =",Integer.toString( location[0]));
                    Log.i("LOCATION 1 =",Integer.toString( location[1]));
                    Log.i("LOCATION 2 =",Integer.toString( location[2]));
                    Log.i("LOCATION 3 =",Integer.toString( location[3]));
                    Log.i("Correct LOCATION =",Integer.toString(correctLocation));
                    Log.i("Correct ANSWEAR =",Integer.toString(ans));
                }


        }*/

        //Log.i("this period"," done");

    }



    public void function (final View view){

        qc++;
        int views=Integer.parseInt(view.getTag().toString());

        if (views==correctLocation){

            view.setBackgroundColor(Color.GREEN);
            new CountDownTimer(250,250) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    view.setBackgroundColor(Color.parseColor("#6C6969"));
                }
            }.start();
            cc++;
            timerTime+=6000/progress;
            aaa.cancel();
            countdownTimer();
            if(cc==10){
                progress++;
            }
            if(cc==20){
                progress++;
            }
            /*if(cc==30){
                progress++;
            }*/

        }else {

            view.setBackgroundColor(Color.RED);
            new CountDownTimer(250,250) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    view.setBackgroundColor(Color.parseColor("#6C6969"));
                }
            }.start();
            timerTime-=1000;
            aaa.cancel();
            countdownTimer();
        }

        counter.setText(Integer.toString(cc)+"/"+Integer.toString(qc));
        startGameCalculate();


        //Log.i("this function"," done");

    }



    public void startFunction(final View view){


        btnStart.animate().scaleX(0.01f).scaleY(0.01f).setDuration(1000);

        new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                game.setVisibility(View.VISIBLE);
                btnStart.setVisibility(view.INVISIBLE);
                countdownTimer();
            }
        }.start();



    }

    public void restartFunction(View view){

        timerTime=30100;
        startGameCalculate();
        countdownTimer();
        result.setVisibility(View.INVISIBLE);
        counter.setText("0/0");
        cc=0;
        qc=0;
        progress=1;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        onStartUpCode();

    }
}






/*
if(Integer.parseInt(btn1.getText().toString())==a+b) {
        btn1.setBackgroundColor(Color.GREEN);
        new CountDownTimer(250,250) {
@Override
public void onTick(long l) {

        }

@Override
public void onFinish() {
        btn1.setBackgroundColor(Color.parseColor("#6C6969"));
        }
        }.start();
        }
        if(Integer.parseInt(btn2.getText().toString())==a+b) {
        btn2.setBackgroundColor(Color.GREEN);
        new CountDownTimer(250,250) {
@Override
public void onTick(long l) {

        }

@Override
public void onFinish() {
        btn2.setBackgroundColor(Color.parseColor("#6C6969"));
        }
        }.start();
        }
        if(Integer.parseInt(btn3.getText().toString())==a+b) {
        btn3.setBackgroundColor(Color.GREEN);
        new CountDownTimer(250,250) {
@Override
public void onTick(long l) {

        }

@Override
public void onFinish() {
        btn3.setBackgroundColor(Color.parseColor("#6C6969"));
        }
        }.start();
        }
        if(Integer.parseInt(btn4.getText().toString())==a+b) {
        btn4.setBackgroundColor(Color.GREEN);
        new CountDownTimer(250,250) {
@Override
public void onTick(long l) {

        }

@Override
public void onFinish() {
        btn4.setBackgroundColor(Color.parseColor("#6C6969"));
        }
        }.start();
        }
*/