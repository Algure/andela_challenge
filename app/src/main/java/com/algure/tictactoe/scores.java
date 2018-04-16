package com.algure.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class scores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Bundle r=getIntent().getExtras();
        String x,o,won = "tie";
        x= String.valueOf(r.getInt("xwins"));
        o= String.valueOf(r.getInt("owins"));
        String turn=r.getString("turn");
        String mode=r.getString("mode");
        TextView xt,ot,w,winner;
        winner= (TextView) findViewById(R.id.winner);

        xt= (TextView) findViewById(R.id.x);
        ot= (TextView) findViewById(R.id.o);
        w= (TextView) findViewById(R.id.winlt);
        xt.setText(x);
        ot.setText(o);
        int xv,ov;
        xv=Integer.parseInt(x);
        ov=Integer.parseInt(o);

        if(xv>ov){
            w.setBackgroundResource(R.drawable.x);
            won="x";
        }else if(ov>xv){
            w.setBackgroundResource(R.drawable.o);
            won="o";
        }else{
            w.setText("TIE");
        }
        if (won.matches(turn)&&!mode.matches("cpu")){
            winner.setText(winner.getText().toString()+": Player1");
        }else if(!won.matches(turn)&&!mode.matches("cpu")){
            winner.setText(winner.getText().toString()+": Player2");
        }else if(won.matches(turn)&&mode.matches("cpu")){
            winner.setText(winner.getText().toString()+": Player1");
        }else if(!won.matches(turn)&&mode.matches("cpu")){
            winner.setText(winner.getText().toString()+": CPU");
        }
    }

}
