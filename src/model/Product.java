/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Pat
 */
public class Product {
    
    private ObservableList<Part> associatedParts;// = observableArrayList();
    private final IntegerProperty productID;
    private final StringProperty name;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;

    public Product(ObservableList<Part> associatedParts, int productID, String name, int inStock, int min, int max) {
        this.associatedParts = associatedParts;
        this.productID = new SimpleIntegerProperty(productID);
        this.name = new SimpleStringProperty(name);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

//    public void setAssociatedParts(ArrayList<Part> associatedParts) {
//        this.associatedParts = associatedParts;
//    }

    public int getProductID() {
        return productID.get();
    }

    public void setProductID(int productID) {
        this.productID.set(productID);
    }
    
    public IntegerProperty productIdProperty() {
        return productID;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }
    
    public int getInStock() {
        return inStock.get();
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public IntegerProperty inStockProperty() {
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
    
    public IntegerProperty naxProperty() {
        return max;
    }
    
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    
    public boolean removeAssociatedPart(int partID) {
        return associatedParts.remove(lookupAssociatedPart(partID));
    }
    
    public Part lookupAssociatedPart(int partID) {
        return associatedParts.get(partID);
    }
    
}
