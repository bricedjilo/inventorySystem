/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_bricedjilo;

import model.InhousePart;
import model.Inventory;
import model.OutsourcedPart;

/**
 *
 * @author Pat
 */
public class InventoryService {
    
    private static int partID = 0;
    private static int productID = 0;
    
    public void createDefaultParts() {
        Inventory.addPart(new InhousePart(423389, getNextPartID(), "Transistor", 5.0, 100, 5, 500));
        Inventory.setPreviousInhousePart(new InhousePart(223389, getNextPartID(), "Capacitor", 10.0, 150, 15, 450));
        Inventory.addPart(Inventory.getPreviousInhousePart());
        Inventory.setPreviousInhousePartIndex(Inventory.getAllParts().size()-1);
        Inventory.addPart(new OutsourcedPart("Songo Tech" , getNextPartID(), "Resistance", 6.0, 380, 15, 56));
        Inventory.setPreviousOutsourcedPart(new OutsourcedPart("Songo Tech", getNextPartID(), "Nor Gate", 2.0, 40, 3, 200));
        Inventory.addPart(Inventory.getPreviousOutsourcedPart());
        Inventory.setPreviousOutsourcedPartIndex(Inventory.getAllParts().size()-1);
    }

    public static int getNextPartID() {
        return ++partID;
    }
    
    public static int getCurrentPartID() {
        return partID;
    }

    public static int getNextProductID() {
        return ++productID;
    }
    
    public static int getCurrentProductID() {
        return productID;
    }
}
