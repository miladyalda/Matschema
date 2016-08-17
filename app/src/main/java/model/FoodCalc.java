package model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by my on 2015-02-25.
 */
public class FoodCalc {


    private String name;

    private double energyKj;
    private double energyKcal;
    private double protein;
    private double fat;
    private double carbohydrates;
    private FoodModel model;
    private ArrayList<String> stringList=new ArrayList<>();
    private ArrayList<Double> doubleList=new ArrayList<>();

    public FoodCalc(FoodModel model){

        this.model=model;


    }

    public FoodCalc( ){


    }



     public void addName(String Name){

         stringList.add(Name);
         for(int i=0; i<stringList.size(); i++) {


             Log.d("FoodCalc", stringList.get(i));

         }
     }

    public void addKj(double Name){





    }

    public void addKcal(double Name){





    }

    public void addProt(double Name){





    }

    public void addfat(double Name){





    }

    public void addcarbo(double Name){





    }

}
