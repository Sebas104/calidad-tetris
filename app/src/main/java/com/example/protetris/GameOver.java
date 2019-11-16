package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Comparator;
import java.util.PriorityQueue;

class Elements {
    int score;
    String name;

    public Elements(int s, String st){
        score = s;
        name = st;
    }
}


public class GameOver extends AppCompatActivity {
    private Button btnSend;
    private Button btnReset;
    private Button btnNewGame;
    private Button btnColor;
    private Button btnMenu;
    private Button btnExit;
    private TextView txv1;
    private TextView txv2;
    private TextView txv3;
    private TextView txv4;
    private TextView txv5;
    private EditText edtx;
    private int points;
    private int color;
    private boolean sent = false;
    private boolean reset = true;
    public static final String SHARED_PREFS = "Shprefs";
    public static final String FIRSTAUX = "Nombre";
    public static final String SECONDAUX = "Puntuacion";
    public static final String FIRST = "Nombre0";
    public static final String SECOND = "Puntuacion0";
    public static final String FIRST2 = "Nombre1";
    public static final String SECOND2 = "Puntuacion1";
    public static final String FIRST3 = "Nombre2";
    public static final String SECOND3 = "Puntuacion2";
    public static final String FIRST4 = "Nombre3";
    public static final String SECOND4 = "Puntuacion3";
    public static final String FIRST5 = "Nombre4";
    public static final String SECOND5 = "Puntuacion4";
    PriorityQueue<Elements> pqueue = new PriorityQueue<Elements>(6, new Comparator<Elements>() {
        @Override
        public int compare(Elements o1, Elements o2) {
            return o2.score -o1.score;
        }
    });

    public GameOver() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        points = getIntent().getIntExtra("score", points);
        color = getIntent().getIntExtra("COLORKEY",color);
        txv1 = findViewById(R.id.textView2);
        txv2 = findViewById(R.id.textView3);
        txv3 = findViewById(R.id.textView4);
        txv4 = findViewById(R.id.textView5);
        txv5 = findViewById(R.id.textView6);
        edtx = findViewById(R.id.Edit1);
        btnSend = findViewById(R.id.button2);
        btnReset = findViewById(R.id.button3);
        btnNewGame = findViewById(R.id.button4);
        btnColor = findViewById(R.id.button5);
        btnMenu = findViewById(R.id.button6);
        btnExit = findViewById(R.id.button7);
        InitScores();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortScores();
                edtx.setEnabled(false);
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reset) {
                    reset = false;
                    resetScores();
                } else {
                    Toast.makeText(getBaseContext(),"Highscore already reset", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void save(String data, int data2, String key1, String key2) {
        SharedPreferences Sh = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor Edit = Sh.edit();

        Edit.putString(key1, data);
        Edit.putInt(key2, data2);
        Edit.apply();
    }

    private void resetScores() {
        save("...", 0, FIRST, SECOND);
        save("...", 0, FIRST2, SECOND2);
        save("...", 0, FIRST3, SECOND3);
        save("...", 0, FIRST4, SECOND4);
        save("...", 0, FIRST5, SECOND5);
        loadScores();
        Toast.makeText(getBaseContext(),"Changes saved", Toast.LENGTH_SHORT).show();
    }

    private void InitScores() {
        pqueue.add(new Elements(loadInt(SECOND),loadSt(FIRST)));
        pqueue.add(new Elements(loadInt(SECOND2),loadSt(FIRST2)));
        pqueue.add(new Elements(loadInt(SECOND3),loadSt(FIRST3)));
        pqueue.add(new Elements(loadInt(SECOND4),loadSt(FIRST4)));
        pqueue.add(new Elements(loadInt(SECOND5),loadSt(FIRST5)));

        loadScores();
    }

    private void SortScores() {
        if (!sent) {
            sent = true;
            pqueue.add(new Elements(points, edtx.getText().toString()));
            Elements aux;

            for (int n = 0; n < 5; n++) {
                aux = pqueue.poll();
                save(aux.name, aux.score, FIRSTAUX + n, SECONDAUX + n);
            }

            update(txv1, loadSt(FIRST), loadInt(SECOND));
            update(txv2, loadSt(FIRST2), loadInt(SECOND2));
            update(txv3, loadSt(FIRST3), loadInt(SECOND3));
            update(txv4, loadSt(FIRST4), loadInt(SECOND4));
            update(txv5, loadSt(FIRST5), loadInt(SECOND5));
        }
        else {
            Toast.makeText(getBaseContext(),"Can't send", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadScores(){
        update(txv1,loadSt(FIRST),loadInt(SECOND));
        update(txv2,loadSt(FIRST2),loadInt(SECOND2));
        update(txv3,loadSt(FIRST3),loadInt(SECOND3));
        update(txv4,loadSt(FIRST4),loadInt(SECOND4));
        update(txv5,loadSt(FIRST5),loadInt(SECOND5));

    }

    private String  loadSt(String key) {
        SharedPreferences Sh = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return Sh.getString(key, "...");
    }

    private int loadInt(String key) {
        SharedPreferences Sh = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return Sh.getInt(key, 0);
    }

    private void update (TextView Txv1, String d1, int d2) {
        Txv1.setText(d1+": "+ d2 +" puntos");
    }

    @Override
    public void onBackPressed() {
        btnSend.setVisibility(View.INVISIBLE);
        btnReset.setVisibility(View.INVISIBLE);
        findViewById(R.id.newGame).setVisibility(View.VISIBLE);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this,ProTetris.class);
                intent.putExtra("COLORKEY",color);
                startActivity(intent);
                GameOver.this.finish();
            }
        });
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this,MainColor.class);
                startActivity(intent);
                GameOver.this.finish();
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOver.this.finish();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                GameOver.this.finish();
            }
        });
    }
}