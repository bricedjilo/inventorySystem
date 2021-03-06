/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.ObservableList;
import validation.ItemRemovalException;
import static javafx.collections.FXCollections.observableArrayList;

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
    private static Product lastProductAdded;
    private static int indexToBeUpdated;
    
    
    public Inventory() {
        
    }
    
    public static void addProduct(Product product) {
        Inventory.products.add(product);
    }
    
    public static boolean removeProduct(int index) throws ItemRemovalException {
        Product product = Inventory.products.get(index);
        if(product.getAssociatedParts().size() > 0) {
            throw new ItemRemovalException(
                "Product contains parts. Parts should be removed before product removal.");
        }
        return Inventory.products.remove(products.get(index));
    }
    
    public static Product lookupProduct(int productID) {
        Product result = null;
        for(Product product : products) {
            if(product.getProductID() == productID) {
                result = product;
                break;
            }
        }
        return result;
    }
    
    public static void updateProduct(int productID) {
        int size = Inventory.products.size();
        int i = 0;
        for(; i < size; i++) {
            if(Inventory.products.get(i).getProductID()== productID) {
                Inventory.products.set(i, Inventory.lastProductAdded);
                break;
            }
        }
        if(size == i) {
            Inventory.products.add(lastProductAdded);
        }
    }
    
    public static void addPart(Part part) {
        Inventory.allParts.add(part);
    }
    
    public static boolean deletePart(Part part) {
        if(part != null) {
            return Inventory.allParts.remove(part);
        }
        return false;
    }
    
    public Part lookupPart(int partID) {
        Part result = null;
        for(Part part : allParts) {
            if(part.getPartID()== partID) {
                result = part;
                break;
            }
        }
        return result;
    }
    
    public static void updatePart(int partID) {
        int i = 0;
        int size = Inventory.allParts.size();
        for(; i < size; i++) {
            if(Inventory.allParts.get(i).getPartID() == partID) {
                Inventory.allParts.set(i, Inventory.lastPartUpdated);
                break;
            }
        }
        if(size == i) {
            Inventory.allParts.add(lastPartUpdated);
        }
    }
    
    public static ObservableList getProducts() {
        return Inventory.products;
    }

    public static ObservableList getAllParts() {
        return Inventory.allParts;
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

    public static Product getLastProductAdded() {
        return lastProductAdded;
    }

    public static void setLastProductAdded(Product lastProductAdded) {
        Inventory.lastProductAdded = lastProductAdded;
    }
}
