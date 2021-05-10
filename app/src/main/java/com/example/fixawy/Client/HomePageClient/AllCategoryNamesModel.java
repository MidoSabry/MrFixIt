package com.example.fixawy.Client.HomePageClient;

import com.example.fixawy.Pojos.AllCategory;

import java.util.ArrayList;
import java.util.List;

public class AllCategoryNamesModel {
    List<AllCategory> categories = new ArrayList<>();


    AllCategory electricity = new AllCategory(1,"@string/electricity");
    AllCategory plumber = new AllCategory(2,"@string/plumber");
    AllCategory carpenter = new AllCategory(3,"@string/carpenter");
    AllCategory painter = new AllCategory(4,"@string/painter");
    AllCategory tilesHandyman = new AllCategory(5,"@string/tilesHandyman");
    AllCategory mason = new AllCategory(6,"@string/mason");
    AllCategory smith = new AllCategory(7,"@string/smith");
    AllCategory parquet = new AllCategory(8,"@string/parquet");
    AllCategory gypsum = new AllCategory(9,"@string/gypsum");
    AllCategory marble = new AllCategory(10,"@string/marble");
    AllCategory alumetal = new AllCategory(11,"@string/alumetal");
    AllCategory glasses = new AllCategory(12,"@string/glasses");
    AllCategory woodPainter = new AllCategory(13,"@string/woodPainter");
    AllCategory curtains = new AllCategory(14,"@string/curtains");
    AllCategory satellite = new AllCategory(15,"@string/satellite");
    AllCategory Appliances = new AllCategory(16,"@string/Appliances");


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
