package com.example.protetris;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;

        import android.Manifest;
        import android.content.Context;
        import android.content.ContextWrapper;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageSwitcher;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.ViewSwitcher;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.Comparator;
        import java.util.PriorityQueue;

class Elements {
    int score;
    String name;
    int img;

    public Elements(int s, String st, int path){
        score = s;
        name = st;
        img = path;
    }
}

public class GameOver extends AppCompatActivity {
    static final String appDirectoryName = "XYZ";
    static final File imageRoot = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appDirectoryName);
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
    private ImageSwitcher sw1;
    private ImageSwitcher sw2;
    private ImageSwitcher sw3;
    private ImageSwitcher sw4;
    private ImageSwitcher sw5;
    private int points;
    private int color;
    private boolean sent = false;
    private boolean reset = true;
    public static final String SHARED_PREFS = "Shprefs";
    public static final String FIRSTAUX = "Nombre";
    public static final String SECONDAUX = "Puntuacion";
    public static final String THIRDAUX = "Img";
    public static final String FIRST = "Nombre0";
    public static final String SECOND = "Puntuacion0";
    public static final String THIRD = "Img0";
    public static final String FIRST2 = "Nombre1";
    public static final String SECOND2 = "Puntuacion1";
    public static final String THIRD2 = "Img1";
    public static final String FIRST3 = "Nombre2";
    public static final String SECOND3 = "Puntuacion2";
    public static final String THIRD3 = "Img2";
    public static final String FIRST4 = "Nombre3";
    public static final String SECOND4 = "Puntuacion3";
    public static final String THIRD4 = "Img3";
    public static final String FIRST5 = "Nombre4";
    public static final String SECOND5 = "Puntuacion4";
    public static final String THIRD5 = "Img4";

    PriorityQueue<Elements> pqueue = new PriorityQueue<Elements>(6, new Comparator<Elements>() {
        @Override
        public int compare(Elements o1, Elements o2) {
            return o2.score -o1.score;
        }
    });

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

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortScores();
                TakePhoto();
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

        sw1 = findViewById(R.id.Switcher);
        sw1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgv = new ImageView(getApplicationContext());
                imgv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imgv;
            }
        });

        sw2 = findViewById(R.id.Switcher1);
        sw2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgv = new ImageView(getApplicationContext());
                imgv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imgv;
            }
        });

        sw3 = findViewById(R.id.Switcher2);
        sw3.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgv = new ImageView(getApplicationContext());
                imgv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imgv;
            }
        });

        sw4 = findViewById(R.id.Switcher3);
        sw4.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgv = new ImageView(getApplicationContext());
                imgv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imgv;
            }
        });

        sw5 = findViewById(R.id.Switcher4);
        sw5.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgv = new ImageView(getApplicationContext());
                imgv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imgv;
            }
        });

        InitScores();

    }

    private void save(String data, int data2, int data3, String key1, String key2, String key3) {
        SharedPreferences Sh = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor Edit = Sh.edit();

        Edit.putString(key1, data);

        if (points >= 60) {
            Edit.putInt(key3, data3);
        } else {
            Edit.putInt(key3, R.drawable.img);
        }

        Edit.putInt(key2, data2);
        Edit.apply();
    }

    private void resetScores() {
        save("...", 0, R.drawable.img, FIRST, SECOND, THIRD);
        save("...", 0, R.drawable.img, FIRST2, SECOND2, THIRD2);
        save("...", 0, R.drawable.img, FIRST3, SECOND3, THIRD3);
        save("...", 0, R.drawable.img, FIRST4, SECOND4, THIRD4);
        save("...", 0, R.drawable.img, FIRST5, SECOND5, THIRD5);

        loadScores();
        Toast.makeText(getBaseContext(),"Changes saved", Toast.LENGTH_SHORT).show();

        update(txv1, loadSt(FIRST), loadInt(SECOND), loadImg(THIRD), sw1);
        update(txv2, loadSt(FIRST2), loadInt(SECOND2), loadImg(THIRD2), sw2);
        update(txv3, loadSt(FIRST3), loadInt(SECOND3), loadImg(THIRD3), sw3);
        update(txv4, loadSt(FIRST4), loadInt(SECOND4), loadImg(THIRD4), sw4);
        update(txv5, loadSt(FIRST5), loadInt(SECOND5), loadImg(THIRD5), sw5);
    }

    private void InitScores() {
        pqueue.add(new Elements(loadInt(SECOND),loadSt(FIRST),loadImg(THIRD)));
        pqueue.add(new Elements(loadInt(SECOND2),loadSt(FIRST2),loadImg(THIRD2)));
        pqueue.add(new Elements(loadInt(SECOND3),loadSt(FIRST3),loadImg(THIRD3)));
        pqueue.add(new Elements(loadInt(SECOND4),loadSt(FIRST4),loadImg(THIRD4)));
        pqueue.add(new Elements(loadInt(SECOND5),loadSt(FIRST5),loadImg(THIRD5)));

        loadScores();
    }

    private void SortScores() {
        if (!sent) {
            sent = true;
            pqueue.add(new Elements(points, edtx.getText().toString(), R.drawable.no_user));
            Elements aux;

            for (int n = 0; n < 5; n++) {
                aux = pqueue.poll();
                save(aux.name, aux.score, aux.img, FIRSTAUX + n, SECONDAUX + n, THIRDAUX + n);
            }

            update(txv1, loadSt(FIRST), loadInt(SECOND), loadImg(THIRD), sw1);
            update(txv2, loadSt(FIRST2), loadInt(SECOND2), loadImg(THIRD2), sw2);
            update(txv3, loadSt(FIRST3), loadInt(SECOND3), loadImg(THIRD3), sw3);
            update(txv4, loadSt(FIRST4), loadInt(SECOND4), loadImg(THIRD4), sw4);
            update(txv5, loadSt(FIRST5), loadInt(SECOND5), loadImg(THIRD5), sw5);
        }
        else {
            Toast.makeText(getBaseContext(),"Can't send", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadScores(){
        update(txv1,loadSt(FIRST),loadInt(SECOND),loadImg(THIRD),sw1);
        update(txv2,loadSt(FIRST2),loadInt(SECOND2),loadImg(THIRD2),sw2);
        update(txv3,loadSt(FIRST3),loadInt(SECOND3),loadImg(THIRD3),sw3);
        update(txv4,loadSt(FIRST4),loadInt(SECOND4),loadImg(THIRD4),sw4);
        update(txv5,loadSt(FIRST5),loadInt(SECOND5),loadImg(THIRD5),sw5);

    }

    private String  loadSt(String key) {
        SharedPreferences Sh = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return Sh.getString(key, "...");
    }

    private int loadImg(String key) {
        SharedPreferences sh = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sh.getInt(key, R.drawable.img);
    }

    private int loadInt(String key) {
        SharedPreferences Sh = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return Sh.getInt(key, 0);
    }

    private void update (TextView Txv1, String d1, int d2, int img, ImageSwitcher sw) {
        Txv1.setText(d1+": "+ d2 +" puntos");
        sw.setImageResource(img);
    }

    private void TakePhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions( new String[] {Manifest.permission.CAMERA}, 1);
            }
            else
            {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            Bundle extras = data.getExtras();
            Bitmap imgBmap = (Bitmap) extras.get("data");
            addImageToGallery(imgBmap, "auxph");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loadImageFromStorage(String path)
    {
        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            Drawable drawable =new BitmapDrawable(getResources(), b);
            sw1.setImageDrawable(drawable);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /*private String saveToInternalStorage(Bitmap bitmapImage, String name){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath=new File(directory,name+".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }*/


    public void addImageToGallery(Bitmap file, String name) {
        MediaStore.Images.Media.insertImage(getContentResolver(), file, name, "...");
    }

    private void saveToInternalStorage(Bitmap imageToSave, String fileName) {

        imageRoot.mkdirs();
        File file = new File(imageRoot, fileName+".jpg");
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {

        if (points < 250) {
            super.onBackPressed();
        } else {
            btnSend.setVisibility(View.INVISIBLE);
            btnReset.setVisibility(View.INVISIBLE);
            findViewById(R.id.newGame).setVisibility(View.VISIBLE);
        }

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