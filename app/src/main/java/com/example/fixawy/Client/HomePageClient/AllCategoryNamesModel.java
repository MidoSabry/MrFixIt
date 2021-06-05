package com.example.fixawy.Client.HomePageClient;

import android.content.Context;
import android.content.res.Resources;

import com.example.fixawy.Pojos.AllCategory;
import com.example.fixawy.R;

import java.util.ArrayList;
import java.util.List;

public class AllCategoryNamesModel {
    List<AllCategory> categories = new ArrayList<>();
    private Context context;



    // AllCategory electricity = new AllCategory(1, R.string.electricity+"");
    AllCategory electricity = new AllCategory(1, "Electricity");

    AllCategory plumber = new AllCategory(2,"Plumber");
    AllCategory carpenter = new AllCategory(3,"Carpenter");
    AllCategory painter = new AllCategory(4,"Painter");
    AllCategory tilesHandyman = new AllCategory(5,"tilesHandyman");
    AllCategory mason = new AllCategory(6,"Mason");
    AllCategory smith = new AllCategory(7,"Smith");
    AllCategory parquet = new AllCategory(8,"Parquet");
    AllCategory gypsum = new AllCategory(9,"Gypsum");
    AllCategory marble = new AllCategory(10,"Marble");
    AllCategory alumetal = new AllCategory(11,"Alumetal");
    AllCategory glasses = new AllCategory(12,"Glasses");
    AllCategory woodPainter = new AllCategory(13,"WoodPainter");
    AllCategory curtains = new AllCategory(14,"Curtains");
    AllCategory satellite = new AllCategory(15,"Satellite");
    AllCategory Appliances = new AllCategory(16,"Appliances Maintenances");


    public List<AllCategory>getAllCategories(){

        categories.add(electricity);
        categories.add(plumber);
        categories.add(carpenter);
        categories.add(painter);
        categories.add(tilesHandyman);
        categories.add(mason);
        categories.add(smith);
        categories.add(parquet);
        categories.add(gypsum);
        categories.add(marble);
        categories.add(alumetal);
        categories.add(glasses);
        categories.add(woodPainter);
        categories.add(curtains);
        categories.add(satellite);
        categories.add(Appliances);

        return categories;
    }








}
