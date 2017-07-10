/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pat
 */
public abstract class Part {
    
    protected final IntegerProperty partID;
    protected final StringProperty partName;
    protected final DoubleProperty price;
    protected final IntegerProperty inStock;
    protected final IntegerProperty min;
    protected final IntegerProperty max;

    /**
     * @param partID is non-null integer 
     * @param partName
     * @param price
     * @param inStock
     * @param min
     * @param max 
     */
    public Part(int partID, String partName, double price, int inStock, int min, int max) {
        this.partID = new SimpleIntegerProperty(partID);
        this.partName = new SimpleStringProperty(partName);
        this.price = new SimpleDoubleProperty(price);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    public int getPartID() {
        return partID.get();
    }
    
    public void setPartID(int partID) {
        this.partID.set(partID);
    }
    
    public IntegerProperty partIdProperty() {
        return partID;
    }

    public String getPartName() {
        return partName.get();
    }

    public void setPartName(String name) {
        this.partName.set(name);
    }
    
    public StringProperty partNameProperty() {
        return partName;
    }
    
    public double getPrice() {
        return price.get();
    }
    
    public void setPrice(double price) {
        this.price.set(price);
    }
    
    public DoubleProperty priceProperty() {
        return price;
    }

    public int getInStock() {
        return inStock.get();
    }
    
    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public IntegerProperty instockProperty() {
        return inStock;
    }
    
    public int getMin() {
        return min.get();
    }
    
    public void setMin(int min) {
        this.min.set(min);
    }

    public IntegerProperty minProperty() {
        return min;
    }
    
    public int getMax() {
        return max.get();
    }

    public void setMax(int max) {
        this.max.set(max);
    }
    
    public IntegerProperty maxProperty() {
        return max;
    }
    
}
