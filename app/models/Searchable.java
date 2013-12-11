/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import services.Sortable;

/**
 *
 * @author Sissoko
 */
public interface Searchable extends Serializable, Sortable {
    String myType(); 
}
