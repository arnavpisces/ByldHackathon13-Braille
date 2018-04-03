package com.example.ak.braille;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Locale;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    TextToSpeech t1;
    static int[] braille=new int[6];
    static int[] current=new int[6];
    static String global="";
    static String notepad="";
    Map<String,String> map=new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map.put("0","a");
        map.put("01","b");
        map.put("03","c");
        map.put("034","d");
        map.put("04","e");
        map.put("013","f");
        map.put("0134","g");
        map.put("014","h");
        map.put("13","i");
        map.put("134","j");
        map.put("02","k");
        map.put("012","l");
        map.put("023","m");
        map.put("0234","n");
        map.put("024","o");
        map.put("0123","p");
        map.put("01234","q");
        map.put("0124","r");
        map.put("123","s");
        map.put("1234","t");
        map.put("025","u");
        map.put("0125","v");
        map.put("1345","w");
        map.put("0235","x");
        map.put("02345","y");
        map.put("0245","z");
        Button button3=(Button)findViewById(R.id.button3);
        Button button=(Button)findViewById(R.id.button);
        Button button2=(Button)findViewById(R.id.button2);
        Button button4=(Button)findViewById(R.id.button4);
        Button button5=(Button)findViewById(R.id.button5);
        Button button6=(Button)findViewById(R.id.button6);
        Button spacebutton=(Button)findViewById(R.id.spacebutton);

        button3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Vibrator vb=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//                vb.vibrate(5);
                TextView darker=(TextView) findViewById(R.id.textView2);
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        darker.getBackground().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);

                        current[3]=1;
                        braille[3]=1;
                        String curr="";
                        for(int i=0;i<current.length;i++){
                            if(current[i]==1){
                                curr+=String.valueOf(i);
                            }
                        }
                        String toShow=map.get(curr);
                        if(toShow!=null) {
                            global+=String.valueOf(toShow);
                            TextView constchange = (TextView) findViewById(R.id.currstring);
                            constchange.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
                            constchange.setTextColor(Color.BLACK);
                            constchange.setText(toShow);

//                            RotateAnimation rotate=(RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotateanimation);
//                            constchange.setAnimation(rotate);
                        }

                        break;

                    case MotionEvent.ACTION_UP:
                        darker.getBackground().setColorFilter(Color.parseColor("#EDE2FF"), PorterDuff.Mode.SRC_IN);

                        braille[3]=0;
                        char flag='y';
                        for(int x=0;x<6;x++){
                            if(braille[x]==1)flag='n';
                        }
                        if(flag=='y'){ //something is down, all fingers are not up
                            curr="";
                            for(int i=0;i<current.length;i++){
                                if(current[i]==1){
                                    curr+=String.valueOf(i);
                                }
                            }
                            toShow=map.get("curr");
                            Log.d(toShow,"toshow");
                            if (toShow!=null)
                            global+=String.valueOf(toShow);
                        }
                        current[3]=0;

                        break;
                }


                return true;
            }
        });

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Vibrator vb=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//                vb.vibrate(5);
                TextView darker=(TextView) findViewById(R.id.textView6);
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        darker.getBackground().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);
                        current[5]=1;
                        braille[5]=1;
                        String curr="";
                        for(int i=0;i<current.length;i++){
                            if(current[i]==1){
                                curr+=String.valueOf(i);
                            }
                        }
                        String toShow=map.get(curr);
                        if(toShow!=null) {
                            global+=String.valueOf(toShow);
                            TextView constchange = (TextView) findViewById(R.id.currstring);
                            constchange.setTextColor(Color.BLACK);
                            constchange.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
                            constchange.setText(toShow);
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        darker.getBackground().setColorFilter(Color.parseColor("#EDE2FF"), PorterDuff.Mode.SRC_IN);

                        braille[5]=0;
                        char flag='y';
                        for(int x=0;x<6;x++){
                            if(braille[x]==1)flag='n';
                        }
                        if(flag=='y'){ //something is down, all fingers are not up
                            curr="";
                            for(int i=0;i<current.length;i++){
                                if(current[i]==1){
                                    curr+=String.valueOf(i);
                                }
                            }

                            toShow=map.get("curr");
                            Log.d(toShow,"toshow");
                            if(toShow!=null)
                            global+=String.valueOf(toShow);
                        }
                        current[5]=0;

                        break;
                }


                return true;
            }
        });

        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Vibrator vb=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//                vb.vibrate(5);
                TextView darker=(TextView) findViewById(R.id.textView5);
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        darker.getBackground().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);
                        current[2]=1;
                        braille[2]=1;
                        String curr="";
                        for(int i=0;i<current.length;i++){
                            if(current[i]==1){
                                curr+=String.valueOf(i);
                            }
                        }
                        String toShow=map.get(curr);
                        if(toShow!=null) {
                            global+=String.valueOf(toShow);
                            TextView constchange = (TextView) findViewById(R.id.currstring);
                            constchange.setTextColor(Color.BLACK);
                            constchange.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
                            constchange.setText(toShow);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        darker.getBackground().setColorFilter(Color.parseColor("#EDE2FF"), PorterDuff.Mode.SRC_IN);

                        braille[2]=0;
                        char flag='y';
                        for(int x=0;x<6;x++){
                            if(braille[x]==1)flag='n';
                        }
                        if(flag=='y'){ //something is down, all fingers are not up
                            curr="";
                            for(int i=0;i<current.length;i++){
                                if(current[i]==1){
                                    curr+=String.valueOf(i);
                                }
                            }

                            toShow=map.get("curr");
                            Log.d(toShow,"toshow");
                            if(toShow!=null)
                            global+=String.valueOf(toShow);
                        }
                        current[2]=0;

                        break;
                }


                return true;
            }
        });

        button4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Vibrator vb=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//                vb.vibrate(5);
                TextView darker=(TextView) findViewById(R.id.textView);
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        darker.getBackground().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);

                        current[4]=1;
                        braille[4]=1;
                        String curr="";
                        for(int i=0;i<current.length;i++){
                            if(current[i]==1){
                                curr+=String.valueOf(i);
                            }
                        }
                        String toShow=map.get(curr);
                        if(toShow!=null) {
                            global+=String.valueOf(toShow);
                            TextView constchange = (TextView) findViewById(R.id.currstring);
                            constchange.setTextColor(Color.BLACK);
                            constchange.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
                            constchange.setText(toShow);
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        darker.getBackground().setColorFilter(Color.parseColor("#EDE2FF"), PorterDuff.Mode.SRC_IN);

                        braille[4]=0;
                        char flag='y';
                        for(int x=0;x<6;x++){
                            if(braille[x]==1)flag='n';
                        }
                        if(flag=='y'){ //something is down, all fingers are not up
                            curr="";
                            for(int i=0;i<current.length;i++){
                                if(current[i]==1){
                                    curr+=String.valueOf(i);
                                }
                            }

                            toShow=map.get("curr");
                            Log.d(toShow,"toshow");
                            if(toShow!=null)
                            global+=String.valueOf(toShow);
                        }
                        current[4]=0;

                        break;
                }


                return true;
            }
        });

        button5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Vibrator vb=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//                vb.vibrate(5);
                TextView darker=(TextView) findViewById(R.id.textView4);
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        darker.getBackground().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);

                        current[0]=1;
                        braille[0]=1;
                        String curr="";
                        for(int i=0;i<current.length;i++){
                            if(current[i]==1){
                                curr+=String.valueOf(i);
                            }
                        }
                        String toShow=map.get(curr);
                        if(toShow!=null) {
                            global+=String.valueOf(toShow);
                            TextView constchange = (TextView) findViewById(R.id.currstring);
                            constchange.setTextColor(Color.BLACK);
                            constchange.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
                            constchange.setText(toShow);
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        darker.getBackground().setColorFilter(Color.parseColor("#EDE2FF"), PorterDuff.Mode.SRC_IN);

                        braille[0]=0;
                        char flag='y';
                        for(int x=0;x<6;x++){
                            if(braille[x]==1)flag='n';
                        }
                        if(flag=='y'){ //something is down, all fingers are not up
                            curr="";
                            for(int i=0;i<current.length;i++){
                                if(current[i]==1){
                                    curr+=String.valueOf(i);
                                }
                            }

                            toShow=map.get("curr");
                            Log.d(toShow,"toshow");
                            if(toShow!=null)
                            global+=String.valueOf(toShow);
                        }
                        current[0]=0;

                        break;
                }


                return true;
            }
        });

        button6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Vibrator vb=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//                vb.vibrate(5);
                TextView darker=(TextView) findViewById(R.id.textView3);
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        darker.getBackground().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);

                        current[1]=1;
                        braille[1]=1;
                        String curr="";
                        for(int i=0;i<current.length;i++){
                            if(current[i]==1){
                                curr+=String.valueOf(i);
                            }
                        }
                        String toShow=map.get(curr);
                        if(toShow!=null) {
                            global+=String.valueOf(toShow);
                            TextView constchange = (TextView) findViewById(R.id.currstring);
                            constchange.setTextColor(Color.BLACK);
                            constchange.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
                            constchange.setText(toShow);
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        darker.getBackground().setColorFilter(Color.parseColor("#EDE2FF"), PorterDuff.Mode.SRC_IN);

                        braille[1]=0;
                        char flag='y';
                        for(int x=0;x<6;x++){
                            if(braille[x]==1)flag='n';
                        }
                        if(flag=='y'){ //something is down, all fingers are not up
                            curr="";
                            for(int i=0;i<current.length;i++){
                                if(current[i]==1){
                                    curr+=String.valueOf(i);
                                }
                            }

                            toShow=map.get("curr");
                            Log.d(toShow,"toshow");
                            if(toShow!=null)
                            global+=String.valueOf(toShow);

                        }
                        current[1]=0;

                        break;
                }


                return true;
            }
        });


        spacebutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Vibrator vb=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//                vb.vibrate(5);
                TextView darker=(TextView) findViewById(R.id.textView3);
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
//                        darker.getBackground().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);
//
//                        current[1]=1;
//                        braille[1]=1;
//                        String curr="";
//                        for(int i=0;i<current.length;i++){
//                            if(current[i]==1){
//                                curr+=String.valueOf(i);
//                            }
//                        }
//                        String toShow=map.get(curr);
//                        if(toShow!=null) {
//                            TextView constchange = (TextView) findViewById(R.id.currstring);
//                            constchange.setTextColor(Color.BLACK);
//                            constchange.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
//                            constchange.setText(toShow);
//                        }

                        break;
                    case MotionEvent.ACTION_UP:

                        Log.d(global,"HELLO");
                        TextView constchange = (TextView) findViewById(R.id.currstring);
                        constchange.setTextColor(Color.BLACK);
                        constchange.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
                        constchange.setText(global);

                        t1=new TextToSpeech(MainActivity.this,new TextToSpeech.OnInitListener(){
                            @Override
                            public void onInit(int status) {
                                if(status == TextToSpeech.SUCCESS) {
                                    t1.setLanguage(Locale.UK);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Feature Not Supported On Your Device",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        String toSpeak = constchange.getText().toString();
                        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ttsGreater21(global);
                        } else {
                            ttsUnder20(global);
                        }
                        notepad+=global;
                        generateNoteOnSD("brailleText.txt",notepad);
                        global="";
                        break;
                }


                return true;
            }
        });


    }

    public void buttonOnClick(View v){
        Button button=(Button)v;
        ((Button)v).setText("clicked");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TextView darker=(TextView) findViewById(R.id.textView);
        darker.setBackgroundColor(Color.BLACK);
        return true;
    }

    @SuppressWarnings("deprecation")
    public void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        t1.speak(global, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void ttsGreater21(String text) {
        String utteranceId=this.hashCode() + "";
        t1.speak(global, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

    public void generateNoteOnSD( String sFileName, String sBody) {
        try {
            File root = new File("storage/emulated/0/", "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
//            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
