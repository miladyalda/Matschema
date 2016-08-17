package model;

/**
 * Created by my on 2015-02-20.
 */
public class FoodModel {

    private String name;
    private int id;
    private double energyKj;
    private double energyKcal;
    private double protein;
    private double fat;
    private double carbohydrates;

        public FoodModel(){


    }
    public FoodModel(String name,
             int id,
             double energyKj,
             double energyKcal,
             double protein,
             double fat,
             double carbohydrates){


        this.name=name;
        this.id=id;
        this.energyKj=energyKj;
        this.energyKcal=energyKcal;
        this.protein=protein;
        this.fat=fat;
        this.carbohydrates=carbohydrates;



    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEnergyKj() {
        return energyKj;
    }

    public void setEnergyKj(double energyKj) {
        this.energyKj = energyKj;
    }

    public double getEnergyKcal() {
        return energyKcal;
    }

    public void setEnergyKcal(double energyKcal) {
        this.energyKcal = energyKcal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String toString(){

        return"Name: "+name+
                "Id: "+id+
                "EnergyKj: "+energyKj+
                "EnergyKcal: "+energyKcal+
                "Protein: "+protein+
                "Fat: "+fat+
                "Carbohydrates: "+carbohydrates;


    }
}
