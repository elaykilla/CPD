/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.cvs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import models.Standard;
import play.data.validation.Match;
import play.data.validation.Required;

/**
 *
 * @author Sissoko
 */
@Entity
@Table(name = "T_ADRESS")
public class Adresse extends Standard {

    /**
     *
     */
    @Required(message = "Adresse est réquise")
    @Column(name = "ADRESS")
    public String adress;
    /**
     *
     */
    @Required(message = "Code postal est réquis")
    @Column(name = "ZIP_CODE")
    public String zipCode;
    /**
     *
     */
    @Required(message = "Ville est réquise")
    @Column(name = "VILLE")
    public String ville;
    /**
     *
     */
    @Required(message = "Pays est réquis")
    @Column(name = "PAYS")
    public String pays;
    /**
     *
     */
    @Match(value = "\\+?[0-9]{10,15}$", message = "Numéro non valid")
    @Column(name = "PHONE")
    public String phone;

    public Adresse() {
        super();
    }
}
