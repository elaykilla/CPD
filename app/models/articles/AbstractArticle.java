/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.articles;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import models.Standard;
import models.User;
import play.data.validation.MaxSize;
import play.data.validation.Required;

/**
 *
 * @author Sissoko
 */
@MappedSuperclass
public class AbstractArticle extends Standard {

    /**
     * The author of this announce.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User author;
    /**
     *
     */
    @Required(message = "Title is required please give a title")
    @Column(name = "TITLE", nullable = false)
    @MaxSize(value = 256)
    public String title;
    /**
     *
     */
    @Required(message = "Content is required please give a content")
    @Lob
    @Column(name = "CONTENT")
    public String content;
    
    /**
     * 
     */
    @Column(name = "VIEWED")
    public Integer viewed;

    public AbstractArticle() {
        super();
    }

    public String title() {
        if (title == null) {
            return null;
        }
        String t;
        t = title.replaceAll(" - ", "-");
        t = t.replaceAll("[ ]+", "-").toLowerCase();
        t = t.replaceAll("'", "");
        return t;
    }
    
    /**
     * 
     * @return 
     */
    public int incermentViewed() {
        if(viewed == null) {
            viewed = 0;
        }
        viewed++;
        save();
        return viewed;
    }
}
