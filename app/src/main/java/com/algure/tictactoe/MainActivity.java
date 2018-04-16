package com.algure.tictactoe;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements owins.OnFragmentInteractionListener,xwins.OnFragmentInteractionListener{

    ImageButton a,b,c,d,e,f,g,h,i;

    private String winner;
    public String ex;
    public String oh;
    public String turn;
    public String safeturn;
    public boolean apressed,bpressed,cpressed,dpressed,epressed,fpressed,gpressed,hpressed,ipressed;
    public String mode;
    public int numofgames;
    public int xwining,owining;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle playdata=getIntent().getExtras();
        numofgames=playdata.getInt("num");
        cpuplayed=0;
        mode=playdata.getString("mode");
        fromcpu=false;
        oh="";
        ex="";
        xwining=0;
        owining=0;
        turn=playdata.getString("turn");
        o= (TextView) findViewById(R.id.o);
        x= (TextView) findViewById(R.id.x);
        safeturn=turn;
        if(turn.matches("x")){
            x.setTextColor(Color.YELLOW);
        }else {

            o.setTextColor(Color.YELLOW);
        }
        a= (ImageButton) findViewById(R.id.a);
        b= (ImageButton) findViewById(R.id.b);
        c= (ImageButton) findViewById(R.id.c);
        d= (ImageButton) findViewById(R.id.d);
        e= (ImageButton) findViewById(R.id.e);
        f= (ImageButton) findViewById(R.id.f);
        g= (ImageButton) findViewById(R.id.g);
        h= (ImageButton) findViewById(R.id.h);
        i= (ImageButton) findViewById(R.id.i);
        apressed=false;
        bpressed=false;
        cpressed=false;
        dpressed=false;
        epressed=false;
        fpressed=false;
        gpressed=false;
        hpressed=false;
        ipressed=false;
    }
    TextView x,o;
    public boolean won;
    public void pressed(View view){
        if(turn.matches("x")){
            turn="o";
            o.setTextColor(Color.YELLOW);
            x.setTextColor(Color.GRAY);
        }else{
            turn="x";
            x.setTextColor(Color.YELLOW);
            o.setTextColor(Color.GRAY);
        }
        if((oh.contains("a")&&oh.contains("b")&&oh.contains("c"))||
                (oh.contains("d")&&oh.contains("e")&&oh.contains("f"))||
                (oh.contains("g")&&oh.contains("h")&&oh.contains("i"))||
                (oh.contains("a")&&oh.contains("d")&&oh.contains("g"))||
                (oh.contains("b")&&oh.contains("e")&&oh.contains("h"))||
                (oh.contains("c")&&oh.contains("f")&&oh.contains("i"))||
                (oh.contains("a")&&oh.contains("e")&&oh.contains("i"))||
                (oh.contains("c")&&oh.contains("e")&&oh.contains("g"))){
            winner="o";
            owining++;
            owins o=new owins();
            android.support.v4.app.FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.add(R.id.lla,o,"o");
            ft.setCustomAnimations(R.anim.slidein,R.anim.slideout);
            ft.commit();
            won=true;

        }else if((ex.contains("a")&&ex.contains("b")&&ex.contains("c"))||
                (ex.contains("d")&&ex.contains("e")&&ex.contains("f"))||
                (ex.contains("g")&&ex.contains("h")&&ex.contains("i"))||
                (ex.contains("a")&&ex.contains("d")&&ex.contains("g"))||
                (ex.contains("b")&&ex.contains("e")&&ex.contains("h"))||
                (ex.contains("c")&&ex.contains("f")&&ex.contains("i"))||
                (ex.contains("a")&&ex.contains("e")&&ex.contains("i"))||
                (ex.contains("c")&&ex.contains("e")&&ex.contains("g"))){
            winner="x";
            xwins o=new xwins();
            xwining++;
            android.support.v4.app.FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.add(R.id.lla,o,"x");
            ft.setCustomAnimations(R.anim.slidein,R.anim.slideout);
            ft.commit();
            won=true;
        }else if(apressed&&bpressed&&cpressed&&dpressed&&epressed&&fpressed&&gpressed&&hpressed
                &&ipressed){
            checkreset(getCurrentFocus());
            won=true;
        }
        if (won){
            TextView x=(TextView) findViewById(R.id.x);
            TextView o=(TextView) findViewById(R.id.o);
            x.setText("X :"+String.valueOf(xwining));
            o.setText("O :"+String.valueOf(owining));
            checkreset(getCurrentFocus());
        }
    }
    public int gamesplayed;
    private void checkreset(View currentFocus) {
        gamesplayed++;
        if (gamesplayed<numofgames){
            reset(currentFocus);
        }else{
            showscores(currentFocus);
        }
    }

    private void showscores(View currentFocus) {
        Intent i=new Intent(this,scores.class);
        i.putExtra("xwins",xwining);
        i.putExtra("owins",owining);
        i.putExtra("mode",mode);
        i.putExtra("turn",safeturn);
        startActivity(i);
    }

    public void comot(View view){
        android.support.v4.app.FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.remove(getSupportFragmentManager().findFragmentByTag("x"));
        ft.commit();
    }
    public void remove(View view){
        android.support.v4.app.FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.remove(getSupportFragmentManager().findFragmentByTag("o"));
        ft.commit();
    }
    public void reset(View view){

        cpuplayed=0;
        fromcpu=true;
        oh="";
        ex="";
        won=false;
        turn=safeturn;
        o= (TextView) findViewById(R.id.o);
        x= (TextView) findViewById(R.id.x);
        if(turn.matches("x")){
            x.setTextColor(Color.YELLOW);
            o.setTextColor(Color.GRAY);
        }else {
            o.setTextColor(Color.YELLOW);
            x.setTextColor(Color.GRAY);
        }
        a.setImageDrawable(null);
        b.setImageDrawable(null);
        c.setImageDrawable(null);
        d.setImageDrawable(null);
        e.setImageDrawable(null);
        f.setImageDrawable(null);
        g.setImageDrawable(null);
        h.setImageDrawable(null);
        i.setImageDrawable(null);
        apressed=false;
        bpressed=false;
        cpressed=false;
        dpressed=false;
        epressed=false;
        fpressed=false;
        gpressed=false;
        hpressed=false;
        ipressed=false;
    }
    public void resett(View view){

        cpuplayed=0;
        fromcpu=false;
        oh="";
        ex="";
        won=false;
        turn=safeturn;
        o= (TextView) findViewById(R.id.o);
        x= (TextView) findViewById(R.id.x);
        if(turn.matches("x")){
            x.setTextColor(Color.YELLOW);
            o.setTextColor(Color.GRAY);
        }else {
            o.setTextColor(Color.YELLOW);
            x.setTextColor(Color.GRAY);
        }
        a.setImageDrawable(null);
        b.setImageDrawable(null);
        c.setImageDrawable(null);
        d.setImageDrawable(null);
        e.setImageDrawable(null);
        f.setImageDrawable(null);
        g.setImageDrawable(null);
        h.setImageDrawable(null);
        i.setImageDrawable(null);
        apressed=false;
        bpressed=false;
        cpressed=false;
        dpressed=false;
        epressed=false;
        fpressed=false;
        gpressed=false;
        hpressed=false;
        ipressed=false;
    }
    public void aset(View view) {
        try {
            if (!apressed&&!won) {
                a.setImageDrawable(turn.matches("x") ? getResources().getDrawable(R.drawable.x) : getResources().getDrawable(R.drawable.o));
                if (turn.matches("x")) {
                    ex += "a";
                } else {
                    oh += "a";
                }
                apressed = true;
                pressed(view);
                if (mode.matches("cpu")&&!fromcpu) {
                    cpuplay(getCurrentFocus());
                }
                else if(mode.matches("cpu")&&fromcpu) {
                    fromcpu = false;
                }
            }
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }
    public void bset(View view){
        try{
            if(!bpressed&&!won) {
                b.setImageDrawable(turn.matches("x") ? getResources().getDrawable(R.drawable.x) : getResources().getDrawable(R.drawable.o));
                if (turn.matches("x")) {
                    ex += "b";
                } else {
                    oh += "b";
                }
                bpressed = true;
                pressed(view);
                if (mode.matches("cpu")&&!fromcpu) {
                    cpuplay(getCurrentFocus());
                }
                else if(mode.matches("cpu")&&fromcpu) {
                    fromcpu = false;
                }
            }}catch(Exception e){
            Log.i("error",e.toString());
        }
    }
    public void cset(View view){
        if(!cpressed&&!won) {
            c.setImageDrawable(turn.matches("x") ? getResources().getDrawable(R.drawable.x) : getResources().getDrawable(R.drawable.o));
            if (turn.matches("x")) {
                ex += "c";
            } else {
                oh += "c";
            }
            cpressed = true;
            pressed(view);

            if (mode.matches("cpu")&&!fromcpu) {
                cpuplay(getCurrentFocus());
            }
            else if(mode.matches("cpu")&&fromcpu) {
                fromcpu = false;
            }
        }
    }
    public void dset(View view){
        if(!dpressed&&!won) {
            d.setImageDrawable(turn.matches("x") ? getResources().getDrawable(R.drawable.x) : getResources().getDrawable(R.drawable.o));
            if (turn.matches("x")) {
                ex += "d";
            } else {
                oh += "d";
            }
            dpressed = true;
            pressed(view);

            if (mode.matches("cpu")&&!fromcpu) {
                cpuplay(getCurrentFocus());
            }
            else if(mode.matches("cpu")&&fromcpu) {
                fromcpu = false;
            }
        }
    }
    public void eset(View view){
        if(!epressed&&!won) {
            e.setImageDrawable(turn.matches("x") ? getResources().getDrawable(R.drawable.x) : getResources().getDrawable(R.drawable.o));
            if (turn.matches("x")) {
                ex += "e";
            } else {
                oh += "e";
            }
            epressed = true;
            pressed(view);
            if (mode.matches("cpu")&&!fromcpu) {
                cpuplay(getCurrentFocus());
            }
            else if(mode.matches("cpu")&&fromcpu) {
                fromcpu = false;
            }
        }
    }
    public void fset(View view){
        if(!fpressed&&!won){
            f.setImageDrawable(turn.matches("x")?getResources().getDrawable(R.drawable.x):getResources().getDrawable(R.drawable.o));
            if (turn.matches("x")){
                ex+="f";
            }else{
                oh+="f";
            }

            fpressed=true;
            pressed(view);
            if (mode.matches("cpu")&&!fromcpu) {
                cpuplay(getCurrentFocus());
            }
            else if(mode.matches("cpu")&&fromcpu) {
                fromcpu = false;
            }}
    }
    public void gset(View view){
        if(!gpressed&&!won){
            g.setImageDrawable(turn.matches("x")?getResources().getDrawable(R.drawable.x):getResources().getDrawable(R.drawable.o));

            if (turn.matches("x")){
                ex+="g";
            }else{
                oh+="g";
            }
            gpressed=true;
            pressed(view);
            if (mode.matches("cpu")&&!fromcpu) {
                cpuplay(getCurrentFocus());
            }
            else if(mode.matches("cpu")&&fromcpu) {
                fromcpu = false;
            }}
    }
    public void hset(View view){
        if(!hpressed&&!won){
            h.setImageDrawable(turn.matches("x")?getResources().getDrawable(R.drawable.x):getResources().getDrawable(R.drawable.o));
            if (turn.matches("x")){
                ex+="h";
            }else{
                oh+="h";
            }

            hpressed=true;
            pressed(view);
            if (mode.matches("cpu")&&!fromcpu) {
                cpuplay(getCurrentFocus());
            }
            else if(mode.matches("cpu")&&fromcpu) {
                fromcpu = false;
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1=new AlertDialog.Builder(this);
        builder1.setMessage("Current game details will be deleted");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(MainActivity.this,selector.class));
                        finish();
                    }
                }
        );
        builder1.setNegativeButton(getResources().getString(R.string.no),new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int id){
                dialog.cancel();
            }
        });
        AlertDialog alert=builder1.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void iset(View view){
        if(!ipressed&&!won){
            i.setImageDrawable(turn.matches("x")?getResources().getDrawable(R.drawable.x):getResources().getDrawable(R.drawable.o));
            if (turn.matches("x")){
                ex+="i";
            }else{
                oh+="i";
            }
            ipressed=true;
            pressed(view);
            if (mode.matches("cpu")&&(!fromcpu)) {
                cpuplay(getCurrentFocus());
            }
            else if(mode.matches("cpu")&&fromcpu) {
                fromcpu = false;
            }
        }
    }
    public int cpuplayed;
    public boolean fromcpu;
    public void cpuplay(View view){
        double i=0;
        boolean played;
        played=false;
        cpuplayed+=1;
        int trier=0;
        while (!played&&(trier<9))  {
            i=Math.floor(10*Math.random());
            if(i==0) {
                trier++;
                if (!apressed) {

                    played = true;
                    fromcpu = true;
                    aset(getCurrentFocus());
                }
            }
            else if (i==1) {
                trier++;
                if (!bpressed) {
                    fromcpu = true;
                    played = true;
                    bset(getCurrentFocus());
                }
            }
            else if (i==2) {
                trier++;
                if (!cpressed) {
                    played = true;
                    fromcpu = true;
                    cset(getCurrentFocus());
                }
            }
            else if (i==3) {
                trier++;
                if (!dpressed) {
                    played = true;
                    fromcpu = true;
                    dset(getCurrentFocus());
                }
            }
            else if (i==4) {
                trier++;
                if (!epressed) {
                    played = true;

                    fromcpu = true;
                    eset(getCurrentFocus());
                }
            }
            else if (i==5) {
                trier++;
                if (!fpressed) {
                    played = true;
                    fromcpu = true;
                    fset(getCurrentFocus());
                }
            }
            else if (i==6) {
                trier++;
                if (!gpressed) {
                    played = true;
                    fromcpu = true;
                    gset(getCurrentFocus());
                }
            }
            else if (i==7) {
                trier++;
                if (!hpressed) {
                    played = true;

                    fromcpu = true;
                    hset(getCurrentFocus());
                }
            }
            else if (i==8) {
                trier++;
                if (!ipressed) {
                    played = true;
                    fromcpu = true;
                    iset(getCurrentFocus());


                }
            }
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
