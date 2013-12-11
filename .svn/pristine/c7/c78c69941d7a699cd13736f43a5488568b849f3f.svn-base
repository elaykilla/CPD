/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.elections;

import controllers.Application;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import java.util.List;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import models.Dendrite;
import models.User;
import models.articles.Announce;
import models.articles.Comment;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.db.jpa.JPABase;

/**
 * Cette classe représente la table d'association entre un utilisateur et un
 * poste. Elle contient l'id de l'utilisateur et le poste. Et un champ
 * description que l'utilisateur remplira après son élection au poste.
 *
 * @author Sissoko
 */
@Entity
@Table(name = "AS_MANDATES")
@IdClass(MandateId.class)
public class Mandate extends GenericModel {

    @Id
    @Column(name = "USER_ID")
    public Long user_id;
//    @Id
//    @Column(name = "POSTE_ID")
    //public Long poste_id;
    @Id
    @Column(name = "DENDRITE_ID")
    public Long dendrite_id;
    @Id
    @Column(name = "ELECTION_ID")
    public Long election_id;
    @Column(name = "DESCRIPTION")
    @MaxSize(value = 1024, message = "La description ne doit pas dépasser 1024 caractères.")
    public String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", updatable = false, insertable = false, referencedColumnName = "id")
    public User user;
    @Required(message = "Le poste est obligatoire")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "POSTE_ID", updatable = true, insertable = true, referencedColumnName = "id")
    public Poste poste;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dendrite_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Dendrite dendrite;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "election_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Election election;
    
    @Required(message = "La date de début est requise, veuillez choisir une date")
    @Column(name = "BEGIN_MANDATE_DATE")
    public Date beginMandate;
    @Column(name = "END_MANDATE_DATE")
    public Date endMandate;
    @Column(name="INTERIM")
    public Boolean interim;
    
    /**
     *
     */
    @Column(name = "CREATED")
    public Date created;
    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED")
    public Date modified;
    /**
     *
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User modifier;

    public Mandate() {
        super();
        this.created = new Date();
        this.modified = new Date();
        this.interim = false;
    }

    /**
     *
     * @param user_id
     * @param poste_id
     * @param dendrite_id
     * @param election_id
     */
    public Mandate(Long user_id, /*Long poste_id,*/ Long dendrite_id, Long election_id) {
        this();
        this.user_id = user_id;
        //this.poste_id = poste_id;
        this.dendrite_id = dendrite_id;
        this.election_id = election_id;
    }
    
    public boolean isOver(){
        return new Date().after(endMandate);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.user_id != null ? this.user_id.hashCode() : 0);
//        hash = 59 * hash + (this.poste_id != null ? this.poste_id.hashCode() : 0);
        hash = 59 * hash + (this.dendrite_id != null ? this.dendrite_id.hashCode() : 0);
        hash = 59 * hash + (this.election_id != null ? this.election_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mandate other = (Mandate) obj;
        if (this.user_id != other.user_id && (this.user_id == null || !this.user_id.equals(other.user_id))) {
            return false;
        }
//        if (this.poste_id != other.poste_id && (this.poste_id == null || !this.poste_id.equals(other.poste_id))) {
//            return false;
//        }
        if (this.dendrite_id != other.dendrite_id && (this.dendrite_id == null || !this.dendrite_id.equals(other.dendrite_id))) {
            return false;
        }
        if (this.election_id != other.election_id && (this.election_id == null || !this.election_id.equals(other.election_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mandate[user["+user_id+"] poste["+poste.id+"] dendrite["+dendrite_id+"] election["+election_id+"]]"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T extends JPABase> T save() {
        modified = new Date();
        modifier = Application.connected();
        return super.save(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static List<Mandate> dendriteMandates(long dendriteId){
        Dendrite d = Dendrite.findById(dendriteId);
        
        List<Mandate> dendriteMandates = Mandate.find("byDendrite_id",dendriteId).fetch();
        return dendriteMandates;   
    }
    
    public static List<Mandate> dendriteCurrentMandates(long dendriteId){
        //endrite d = Dendrite.findById(dendriteId);
        Date d = new Date();
        List<Mandate> dendriteCurrentMandates = Mandate.find("select m from Mandate m where m.dendrite_id=? and beginMandate<? and endMandate>?",dendriteId,d,d).fetch();
        return dendriteCurrentMandates;   
    }
}
