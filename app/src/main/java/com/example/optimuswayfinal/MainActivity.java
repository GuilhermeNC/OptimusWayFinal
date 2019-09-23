package com.example.optimuswayfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText se;
    private EditText it;
    private EditText xv;
    private EditText arv;

    private final GeoPoint concas = new GeoPoint(-27.2328968,-52.0276853);
    private final GeoPoint seara = new GeoPoint(-27.153100, -52.310387);
    private final GeoPoint itah = new GeoPoint(-27.276689, -52.339888);
    private final GeoPoint xavantina = new GeoPoint(-27.070874, -52.344416);
    private final GeoPoint arvore = new GeoPoint(-27.075903, -52.455530);
    private final GeoPoint chape = new GeoPoint(-27.102082, -52.620079);

    private ArrayList<GeoPoint> rota = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        se = findViewById(R.id.ponto1edt);
        it = findViewById(R.id.ponto2edt);
        xv= findViewById(R.id.ponto3edt);
        arv = findViewById(R.id.ponto4edt);


    }
    public void telaMapa(View view) {
        rota.clear();

        rota.add(concas);

        String pp1 = se.getText().toString();
        String pp2 = it.getText().toString();
        String pp3 = xv.getText().toString();
        String pp4 = arv.getText().toString();

        if (pp1.length() > 0 &&  Integer.parseInt(pp1)>0) {
            rota.add(seara);
        }
        if (pp2.length() > 0 && Integer.parseInt(pp2)>0){
            rota.add(itah);
        }
        if (pp3.length() > 0 && Integer.parseInt(pp3)>0){
            rota.add(xavantina);
        }
        if (pp4.length() > 0 && Integer.parseInt(pp4)>0){
            rota.add(arvore);
            Log.i(this.getLocalClassName(), "Adicionando arvoredo!");
        }
        rota.add(chape);


        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(MapActivity.WAYPOINTS_EXTRA, rota);
        startActivity(intent);
    }
}
