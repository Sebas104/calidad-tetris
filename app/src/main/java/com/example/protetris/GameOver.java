package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;
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
    int Score;
    String Name;

    public Elements(int S, String St){
        Score=S;
        Name=St;
    }
}


public class GameOver extends AppCompatActivity {
    private Button Button1;
    private Button Button2;
    private TextView Txv1;
    private TextView Txv2;
    private TextView Txv3;
    private TextView Txv4;
    private TextView Txv5;
    private EditText Edtx;
    private String loadSt;
    private int loadInt;
    private int Score;
    private boolean Sent= false;
    private boolean Reset = true;
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
    PriorityQueue<Elements> Pqueue= new PriorityQueue<Elements>(6, new Comparator<Elements>() {
        @Override
        public int compare(Elements o1, Elements o2) {
            return o2.Score-o1.Score;
        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Score = getIntent().getIntExtra("Score", Score);
        Txv1 = findViewById(R.id.textView2);
        Txv2 = findViewById(R.id.textView3);
        Txv3 = findViewById(R.id.textView4);
        Txv4 = findViewById(R.id.textView5);
        Txv5 = findViewById(R.id.textView6);
        Edtx = findViewById(R.id.Edit1);
        Button1 = findViewById(R.id.button2);
        Button2 = findViewById(R.id.button3);
        InitScores();
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Txv1.setText(Edtx.getText().toString());
                save(Txv1.getText().toString(), Score, FIRST, SECOND);
                Intent intent = new Intent(v.getContext(), ProTetris.class);
                startActivity(intent);
                finish();
                update(Txv1,loadSt(FIRST),loadInt(SECOND));*/
                SortScores();
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Reset) {
                    Reset = false;
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
        Pqueue.add(new Elements(loadInt(SECOND),loadSt(FIRST)));
        Pqueue.add(new Elements(loadInt(SECOND2),loadSt(FIRST2)));
        Pqueue.add(new Elements(loadInt(SECOND3),loadSt(FIRST3)));
        Pqueue.add(new Elements(loadInt(SECOND4),loadSt(FIRST4)));
        Pqueue.add(new Elements(loadInt(SECOND5),loadSt(FIRST5)));

        loadScores();
    }

    private void SortScores() {
        if (!Sent) {
            Sent = true;
            Pqueue.add(new Elements(Score, Edtx.getText().toString()));
            Elements aux = new Elements(0, "...");

            for (int n = 0; n < 5; n++) {
                aux = Pqueue.poll();
                save(aux.Name, aux.Score, FIRSTAUX + n, SECONDAUX + n);
            }

            update(Txv1, loadSt(FIRST), loadInt(SECOND));
            update(Txv2, loadSt(FIRST2), loadInt(SECOND2));
            update(Txv3, loadSt(FIRST3), loadInt(SECOND3));
            update(Txv4, loadSt(FIRST4), loadInt(SECOND4));
            update(Txv5, loadSt(FIRST5), loadInt(SECOND5));
        }
        else {
            Toast.makeText(getBaseContext(),"Can't send", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadScores(){
        update(Txv1,loadSt(FIRST),loadInt(SECOND));
        update(Txv2,loadSt(FIRST2),loadInt(SECOND2));
        update(Txv3,loadSt(FIRST3),loadInt(SECOND3));
        update(Txv4,loadSt(FIRST4),loadInt(SECOND4));
        update(Txv5,loadSt(FIRST5),loadInt(SECOND5));

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
        Txv1.setText(d1+": "+Integer.toString(d2)+" puntos");
    }
}



