/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Pat
 */
public class Inventory {
    
    private static ObservableList<Product> products = observableArrayList();
    private static ObservableList<Part> allParts = observableArrayList();
    private static OutsourcedPart previousOutsourcedPart;
    private static InhousePart previousInhousePart;
    private static Part lastPartUpdated;
    private static int indexToBeUpdated;
    private static int previousOutsourcedPartIndex;
    private static int previousInhousePartIndex;
    
    
    public Inventory() {
        
    }
    
    public static void addProduct(Product product) {
        Inventory.products.add(product);
    }
    
    public static boolean removeProduct(int index) {
        if(index < products.size()) {
            return Inventory.products.remove(products.get(index));
        }
        return false;
    }
    
    public static Product lookupProduct(int productID) {
        Product result = null;
        for(Product product : products) {
            if(product.getProductID() == productID) {
                result = product;
            }
        }
        return result;
    }
    
    public void updateProduct(int productID) {
        
    }
    
    public static void addPart(Part part) {
        Inventory.allParts.add(part);
    }
    
    public static boolean deletePart(Part part) {
        return Inventory.allParts.remove(part);
    }
    
    public Part lookupPart(int partID) {
        ObservableList<Part> parts = Inventory.allParts;
        return null;
    }
    
    public static void updatePart(int index) {
        Inventory.allParts.set(index, Inventory.lastPartUpdated);
    }
    
    public static ObservableList getProducts() {
        return Inventory.products;
    }

    public static ObservableList getAllParts() {
        return Inventory.allParts;
    }

    public static int getPreviousOutsourcedPartIndex() {
        return previousOutsourcedPartIndex;
    }

    public static void setPreviousOutsourcedPartIndex(int previousOutsourcedPartIndex) {
        Inventory.previousOutsourcedPartIndex = previousOutsourcedPartIndex;
    }

    public static int getPreviousInhousePartIndex() {
        return previousInhousePartIndex;
    }

    public static void setPreviousInhousePartIndex(int previousInhousePartIndex) {
        Inventory.previousInhousePartIndex = previousInhousePartIndex;
    }
    
    public static OutsourcedPart getPreviousOutsourcedPart() {
        return previousOutsourcedPart;
    }

    public static void setPreviousOutsourcedPart(OutsourcedPart previousOutsourcedPart) {
        Inventory.previousOutsourcedPart = previousOutsourcedPart;
    }

    public static Part getLastPartUpdated() {
        return lastPartUpdated;
    }

    public static void setLastPartUpdated(Part lastPartUpdated) {
        Inventory.lastPartUpdated = lastPartUpdated;
    }

    public static int getIndexToBeUpdated() {
        return indexToBeUpdated;
    }

    public static void setIndexToBeUpdated(int indexToBeUpdated) {
        Inventory.indexToBeUpdated = indexToBeUpdated;
    }
    
    public static InhousePart getPreviousInhousePart() {
        return previousInhousePart;
    }

    public static void setPreviousInhousePart(InhousePart previousInhousePart) {
        Inventory.previousInhousePart = previousInhousePart;
    }
    
    
}
