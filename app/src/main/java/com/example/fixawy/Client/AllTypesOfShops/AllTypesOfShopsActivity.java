package com.example.fixawy.Client.AllTypesOfShops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.fixawy.Client.ShowProductsOfShopType.ShowProductsOfShopTypeActivity;
import com.example.fixawy.Pojos.ShopOwnerUser;
import com.example.fixawy.R;
import com.example.fixawy.Share.RegisterPage.RegisterActivity;
import com.example.fixawy.Worker.SelectJobPage.SelectJobActivity;

import java.util.ArrayList;

public class AllTypesOfShopsActivity extends AppCompatActivity {

    Button btnElectricity, btnPlumber, btnCarpenter, btnPainter, btnTiles, btnMason, btnSmith, btnParquet, btnGyp, btnGlass, btnAlumetal, btnWood, btnCurtains, btnSatellite, btnAppliances, btnMarble;
    String shopType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_types_of_shops);

        btnElectricity = findViewById(R.id.electricity);
        btnPlumber = findViewById(R.id.plumber);
        btnCarpenter = findViewById(R.id.carpenter);
        btnPainter = findViewById(R.id.painter);
        btnTiles = findViewById(R.id.tilesHandyman);
        btnMason = findViewById(R.id.mason);
        btnSmith = findViewById(R.id.smith);
        btnParquet = findViewById(R.id.parquet);
        btnGyp = findViewById(R.id.gypsum);
        btnGlass = findViewById(R.id.glasses);
        btnAlumetal = findViewById(R.id.alumetal);
        btnWood = findViewById(R.id.woodPainter);
        btnCurtains = findViewById(R.id.curtains);
        btnSatellite = findViewById(R.id.satellite);
        btnAppliances = findViewById(R.id.Appliances);
        btnMarble = findViewById(R.id.marble);

        btnElectricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Electricity");
                startActivity(intent);
                finish();
            }
        });

        btnPlumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Plumber");
                startActivity(intent);
                finish();
            }
        });

        btnCarpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Carpenter");
                startActivity(intent);
                finish();
            }
        });

        btnPainter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Painter");
                startActivity(intent);
                finish();
            }
        });

        btnTiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "TilesHandyMan");
                startActivity(intent);
                finish();
            }
        });

        btnMason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Mason");
                startActivity(intent);
                finish();
            }
        });

        btnSmith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Smith");
                startActivity(intent);
                finish();
            }
        });

        btnParquet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Parquet");
                startActivity(intent);
                finish();
            }
        });

        btnGyp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Gypsum");
                startActivity(intent);
                finish();
            }
        });

        btnMarble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Marble");
                startActivity(intent);
                finish();
            }
        });

        btnAlumetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Alumetal");
                startActivity(intent);
                finish();
            }
        });

        btnGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Glasses");
                startActivity(intent);
                finish();
            }
        });

        btnWood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "WoodPainter");
                startActivity(intent);
                finish();
            }
        });

        btnCurtains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Curtains");
                startActivity(intent);
                finish();
            }
        });

        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Satellite");
                startActivity(intent);
                finish();
            }
        });

        btnAppliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTypesOfShopsActivity.this, ShowProductsOfShopTypeActivity.class);
                intent.putExtra("shopType", "Appliances");
                startActivity(intent);
                finish();
            }
        });
    }
}
