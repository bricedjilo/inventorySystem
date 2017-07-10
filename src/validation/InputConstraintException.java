/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

/**
 *
 * @author Pat
 */
public class InputConstraintException extends RuntimeException {
    
    public InputConstraintException(String message) { //, String input2, String message) {
        super(message);
    }

}
