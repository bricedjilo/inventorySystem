/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.util.regex.PatternSyntaxException;

/**
 *
 * @author Pat
 */
public class Validate {
    
    public static boolean isPositiveInt(String input, String fieldName, String fieldID) throws PatternSyntaxException {
        if(input.length()<Integer.MAX_VALUE && input.matches("^[0-9]*[1-9][0-9]*$")) {
            return true;
        } else {
            throw new PatternSyntaxException(
                ("\'" + fieldName + "\' must be greater than zero and less than " + 
                    Integer.MAX_VALUE + " - " + fieldID), input, -1);
        }
    }
    
    public static boolean isPositiveDouble(String input, String fieldName, String fieldID) throws PatternSyntaxException {
        if(input.length()<=13 && input.matches("\\d+(\\.\\d{1,2})?")) {
            return true;
        } else {
            throw new PatternSyntaxException(
                ("\'" + fieldName + "\' must be a decimal number with at most two decimal places \n" + 
                    "The max value is 9,999,999,999,999 - " + fieldID), input, -1);
        }
    }
    
    public static boolean isAlphaNumeric(String input, String fieldName, String fieldID) throws PatternSyntaxException {
        if(input.length()>=3 && input.matches("^[a-zA-Z]+[a-zA-Z0-9_\\-\\s]*$")) {
            return true;
        } else {
            throw new PatternSyntaxException(
                ("Enter the \'" + fieldName + "\' using alphanumeric characters and _ only. \n" + 
                    fieldName + " must start with a letter and should contain at least 3 characters - " + fieldID), input, -1);
        }
    }
    
    public static boolean isNotGreaterThan(String val1, String fieldName1, String fieldID1, 
            String val2, String fieldName2, String fieldID2) {
        if(Integer.parseInt(val1) > Integer.parseInt(val2)) {
           throw new InputConstraintException(
                fieldName2 + " must be greater than " + fieldName1 + 
                    " - " + fieldID1 + " - " + fieldID2);
        }
        return true;
    }
    
    public static boolean isNotLessThan(String val1, String fieldName1, String fieldID1, 
            String val2, String fieldName2, String fieldID2) {
        if(Integer.parseInt(val1) < Integer.parseInt(val2)) {
           throw new InputConstraintException(
                fieldName2 + " must be less than " + fieldName1 + 
                    " - " + fieldID1 + " - " + fieldID2);
        }
        return true;
    }
    
//    public static boolean checkInputConstraint(
//            String min, String max, String inv,
//            ) {
//        int minVal = Integer.parseInt(min);
//        int maxVal = Integer.parseInt(max);
//        int invVal = Integer.parseInt(inv);
//        
//        if(invVal > maxVal) {
//           throw new InputConstraintException("Inventory must be less than Max value.");
//        } 
//        if(invVal < minVal) {
//            throw new InputConstraintException("Inventory must be greater than Min value.");
//        }
//        if(minVal > maxVal) {
//            throw new InputConstraintException("Min value must be less than Max value.");
//        }
//        return true;
//    }
    
}
