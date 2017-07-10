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
    
    public void createDefaultParts() {
        Inventory.addPart(new InhousePart(423389, 1, "Transistor", 5.0, 100, 5, 500));
        Inventory.setPreviousInhousePart(new InhousePart(223389, 2, "Capacitor", 10.0, 150, 15, 450));
        Inventory.addPart(Inventory.getPreviousInhousePart());
        Inventory.setPreviousInhousePartIndex(Inventory.getAllParts().size()-1);
        Inventory.addPart(new OutsourcedPart("Songo Tech" , 3, "Resistance", 6.0, 380, 15, 56));
        Inventory.setPreviousOutsourcedPart(new OutsourcedPart("Songo Tech", 4, "Nor Gate", 2.0, 40, 3, 200));
        Inventory.addPart(Inventory.getPreviousOutsourcedPart());
        Inventory.setPreviousOutsourcedPartIndex(Inventory.getAllParts().size()-1);
    }
    
}
