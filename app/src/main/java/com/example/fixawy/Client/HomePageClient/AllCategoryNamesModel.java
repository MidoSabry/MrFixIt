package com.example.fixawy.Client.HomePageClient;

import com.example.fixawy.Pojos.AllCategory;
import com.example.fixawy.R;

import java.util.ArrayList;
import java.util.List;

public class AllCategoryNamesModel {
    List<AllCategory> categories = new ArrayList<>();


    AllCategory electricity = new AllCategory(1, R.string.electricity+"");
    AllCategory plumber = new AllCategory(2, R.string.plumber+"");
    AllCategory carpenter = new AllCategory(3, R.string.carpenter+"");
    AllCategory painter = new AllCategory(4, R.string.painter+"");
    AllCategory tilesHandyman = new AllCategory(5, R.string.tilesHandyman+"");
    AllCategory mason = new AllCategory(6, R.string.mason+"");
    AllCategory smith = new AllCategory(7, R.string.smith+"");
    AllCategory parquet = new AllCategory(8, R.string.parquet+"");
    AllCategory gypsum = new AllCategory(9, R.string.gypsum+"");
    AllCategory marble = new AllCategory(10, R.string.marble+"");
    AllCategory alumetal = new AllCategory(11, R.string.alumetal+"");
    AllCategory glasses = new AllCategory(12, R.string.glasses+"");
    AllCategory woodPainter = new AllCategory(13, R.string.woodPainter+"");
    AllCategory curtains = new AllCategory(14, R.string.curtains+"");
    AllCategory satellite = new AllCategory(15, R.string.satellite+"");
    AllCategory Appliances = new AllCategory(16, R.string.Appliances+"");


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
