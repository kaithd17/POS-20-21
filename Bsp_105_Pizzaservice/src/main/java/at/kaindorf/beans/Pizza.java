/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

/**
 *
 * @author kainz
 */
public class Pizza {
    private String name;
    private double price;
    private String ingredients;
    private String image;
    private int order;

    public Pizza() {
    }

    
    public Pizza(String name, double price, String ingredients, String image, int order) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.image = image;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Pizza{" + "name=" + name + ", price=" + price + ", ingredients=" + ingredients + ", image=" + image + ", order=" + order + '}';
    }
    
    public static Pizza getPizza(String line){
        //Name;Preis;Zutaten;image
        String name = line.split(";")[0];
        double price = Double.parseDouble(line.split(";")[1]);
        String ingredients = line.split(";")[2];
        String image = line.split(";")[3];
        int order = 0;
        return new Pizza(name,price,ingredients,image,order);
    }
}
