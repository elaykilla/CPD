/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.feedback;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import models.Standard;
import models.User;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import services.CompareFunction;
import services.Sortable;

/**
 *
 * @author Sissoko
 */
@Table(name = "T_FEEDBACKS")
@Entity
public class Feedback extends Standard implements Sortable {

    @Column(name = "POSTER")
    public String poster;
    @Column(name = "EMAIL")
    public String email;
    @ManyToOne
    public User author;
    @ManyToOne
    public User responsable;
    @Required(message = "Le contenu du message est obligatoire")
    @MaxSize(20000)
    @Lob
    public String content;
    @Column(name = "SUBJECT")
    public String subject;
    @Column(name = "PROGRESS")
    public Integer progress;
    @Column(name = "PRIORITY")
    public Integer priority;
    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL)
    public List<Evolution> evolutios;

    public Feedback() {
        super();
        priority = 0;
        progress = 0;
        evolutios = new ArrayList<Evolution>();
    }
    

    public int compareTo(Sortable o) {
        Feedback other = (Feedback) o;
        if (other.progress != 100 && progress != 100) {
            int comp = priority.compareTo(other.priority);
            if (comp != 0) {
                return -comp;
            }
            comp = progress.compareTo(other.progress);
            if (comp != 0) {
                return comp;
            }
        } else if(progress == 100){
            int  comp = progress.compareTo(other.progress);
            if (comp != 0) {
                return 1;
            }
            comp = priority.compareTo(other.priority);
            if (comp != 0) {
                return -comp;
            }
        } else {
            int  comp = progress.compareTo(other.progress);
            if (comp != 0) {
                return -11;
            }
            comp = priority.compareTo(other.priority);
            if (comp != 0) {
                return -comp;
            }
        }
        return -created.compareTo(other.created);
    }

    @Override
    public String toString() {
        return "progress: "+progress+"\n"
                + "priority: " + priority +"\n"
                + "poster: "+poster+"\n"
                + "author: "+author+"\n"
                + "email: "+email;
    }
    
    
}
