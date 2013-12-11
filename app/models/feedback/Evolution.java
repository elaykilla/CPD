/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.feedback;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import models.Standard;
import play.data.validation.Required;

/**
 *
 * @author Sissoko
 */
@Table(name = "T_EVOLUTIONS")
@Entity
public class Evolution extends Standard {

    
    @ManyToOne
    public Feedback feedback;
    @Column(name = "DESCRIPTION")
    @Required(message = "Description est requise")
    public String description;
    @Column(name = "PROGRESS")
    public Integer progress;
    @Column(name = "PRIORITY")
    public Integer priority;

    public Evolution() {
        super();
    }
   

    @Override
    public String toString() {
        return description;
    }
}
