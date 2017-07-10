/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Pat
 */
public class InhousePart extends Part {
    
    private final IntegerProperty machineID;

    public InhousePart(int machineID, int partID, String name, double price, int inStock, int min, int max) {
        super(partID, name, price, inStock, min, max);
        this.machineID = new SimpleIntegerProperty(machineID);
    }

    public int getMachineID() {
        return machineID.get();
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
    
    public IntegerProperty machineIdProperty() {
        return machineID;
    }

    @Override
    public String toString() {
        return "InhousePart{" + "machineID=" + machineID + '}';
    }
    
    
    
}
