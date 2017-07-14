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
public class ItemRemovalException extends RuntimeException {
    public ItemRemovalException(String message) {
        super(message);
    }
}
