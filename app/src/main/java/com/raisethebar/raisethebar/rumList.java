package com.raisethebar.raisethebar;

/**
 * Created by Anna on 16-01-16.
 */
public class rumList {
    private String rumName;
    private String ingredients;
    private String price;

    private int quantity;


    public rumList(String rumName, String ingredients, String price, int quantity){
        super();
        this.rumName = rumName;
        this.ingredients = ingredients;
        this.price = price;

        this.quantity = quantity;

    }

    public String getRumName(){
        return rumName;
    }
    public String getIngredients(){
        return ingredients;
    }

    public String getPrice(){
        return price;
    }



    public int getQuantity(){
        return quantity;
    }

}
