package com.example.tainv.myapplication;

/**
 * Created by tainv on 9/19/2017.
 */

public class restaurent {
    private String type;
    private String name;
    private String discount;
    private String address;
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getDiscount(){
        return discount;
    }
    public void setDiscount(String discount){
        this.discount=discount;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String toString(){
        return name+" "+address+" "+type+" "+discount;
    }

}

