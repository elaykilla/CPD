/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import services.Sortable;

/**
 *
 * @author Sissoko
 */
public interface Convers extends Sortable{
    List<Message> getMessages();
    
    Dendrite getDendrite();
    
    User getUser1();
        
    User getUser2();
}
