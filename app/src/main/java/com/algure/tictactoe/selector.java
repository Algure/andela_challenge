package com.algure.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class selector extends AppCompatActivity {
    private String turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        turn="x";
        b1= (ImageButton) findViewById(R.id.x);
        b2= (ImageButton) findViewById(R.id.o);
        bt= (ImageButton) findViewById(R.id.andro);
        bt1= (ImageButton) findViewById(R.id.pple);
        bt.setBackgroundResource(R.drawable.andros);
        type="cpu";
        number=1;
        tv= (TextView) findViewById(R.id.tv1);
    }
    public String type;
    public int number;

    ImageButton bt,bt1,b1,b2;
    public void turnpicker1(View view){
        turn="x";
        b1.setBackgroundResource(R.drawable.x);
        b2.setBackgroundResource(R.drawable.blacko);
    }
    public void turnpicker2(View view){
        turn="o";
        b1.setBackgroundResource(R.drawable.blackx);
        b2.setBackgroundResource(R.drawable.o);
    }

    public void pickandro(View view){
        type="cpu";

        bt.setBackgroundResource(R.drawable.andros);
        bt1.setBackgroundResource(R.drawable.player);
        Toast.makeText(this,getResources().getString(R.string.cpuselect),Toast.LENGTH_SHORT).show();
    }
    public void pickppl(View view){
        type="player";
        bt.setBackgroundResource(R.drawable.andro);
        bt1.setBackgroundResource(R.drawable.pps);
        Toast.makeText(this,getResources().getString(R.string.playerselect),Toast.LENGTH_SHORT).show();
    }
    TextView tv;
    public void addnum(View v){
        int k= Integer.parseInt(tv.getText().toString());
        if(k>=0&&k<15) {
            k++;
            number=k;
        }else{
            Toast.makeText(this,getResources().getString(R.string.numnota),Toast.LENGTH_SHORT).show();
        }
        tv.setText(String.valueOf(k));

    }
    public void subnum(View v){
        int k= Integer.parseInt(tv.getText().toString());
        if(k>=0&&k<15) {
            k--;
        }else{
            Toast.makeText(this,getResources().getString(R.string.numnota),Toast.LENGTH_SHORT).show();
        }
        tv.setText(String.valueOf(k));

    }
    public void done (View view){
        if (type==null){
            Toast.makeText(this,getResources().getString(R.string.typenots),Toast.LENGTH_SHORT).show();
        }else if(number<=0||number>15){

            Toast.makeText(this,getResources().getString(R.string.numnota),Toast.LENGTH_SHORT).show();
        }else{
            Intent i=new Intent(this,MainActivity.class);
            i.putExtra("mode",type);
            i.putExtra("num",number);
            i.putExtra("turn",turn);
            startActivity(i);
        }
    }
}
