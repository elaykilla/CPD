/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.cvs;

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
@Entity
@Table(name = "T_HOBBIES")
public class Hobbie extends Standard {

    @Required(message = "Title is required.")
    @Column(name = "TITLE")
    public String title;
    @Required(message = "Hobbie comment is required. Please give comments.")
    @Column(name = "COMMENT")
    public String comment;
    @ManyToOne
    public Cv cv;

    public Hobbie() {
        super();
    }
}
