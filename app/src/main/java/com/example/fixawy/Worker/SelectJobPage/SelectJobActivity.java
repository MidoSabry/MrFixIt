package com.example.fixawy.Worker.SelectJobPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fixawy.R;
import com.example.fixawy.Share.RegisterPage.RegisterActivity;

public class SelectJobActivity extends AppCompatActivity {
    Button btnElectrican,btnPlumber,btnCarpenter,btnPainter,btnTiles,btnMason,btnSmith,btnParquet,btnGyp,btnGlass,btnAlumetal,btnWood,btnCurtains,btnSatellite,btnAppliances,btnMarble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_job);

        btnElectrican = findViewById(R.id.electricity_btn);
        btnPlumber = findViewById(R.id.pulmber_btn);
        btnCarpenter = findViewById(R.id.carpenter_btn);
        btnPainter = findViewById(R.id.painter_btn);
        btnTiles = findViewById(R.id.tilesHandyman_btn);
        btnMason = findViewById(R.id.mason_btn);
        btnSmith = findViewById(R.id.smith_btn);
        btnParquet = findViewById(R.id.parquet_btn);
        btnGyp = findViewById(R.id.gypsum_btn);
        btnMarble = findViewById(R.id.marble_btn);
        btnGlass = findViewById(R.id.glasses_btn);
        btnAlumetal = findViewById(R.id.alumetal_btn);
        btnWood = findViewById(R.id.woodPainter_btn);
        btnCurtains = findViewById(R.id.curtains_btn);
        btnSatellite = findViewById(R.id.satellite_btn);
        btnAppliances = findViewById(R.id.Appliances_btn);

        btnElectrican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Electricity"));
            }
        });

        btnPlumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Plumber"));
            }
        });

        btnCarpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Carpenter"));
            }
        });

        btnPainter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Painter"));
            }
        });

        btnTiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Tiles Handyman"));
            }
        });

        btnMason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Mason"));
            }
        });

        btnSmith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Smith"));
            }
        });

        btnParquet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Parquet"));
            }
        });

        btnGyp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Gypsum"));
            }
        });

        btnMarble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Marble"));
            }
        });

        btnAlumetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Alumetal"));
            }
        });

        btnGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Glass"));
            }
        });

        btnWood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Wood Painter"));
            }
        });

        btnCurtains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Curtais"));
            }
        });

        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Satellite"));
            }
        });

        btnAppliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("jopTitle", "Appliances Maintenance"));
            }
        });

    }
}