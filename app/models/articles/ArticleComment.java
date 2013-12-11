/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.articles;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import models.Searchable;
import models.User;
import models.events.Event;
import play.db.jpa.GenericModel;
import services.Sortable;

/**
 *
 * @author Sissoko
 */
@Entity
@Table(name = "AS_ARTICLES_COMMENTS")
@IdClass(ArticleCommentId.class)
public class ArticleComment  extends GenericModel implements Searchable{

    @Id
    public long article_id;
    @Id
    public long comment_id;
    
    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    public Date created;
    /**
     *
     */
    @Column(name = "MODIFIED")
    public Date modified;
    /**
     *
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User modifier; 
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "article_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Article article;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Comment comment;

    public ArticleComment() {
        created = new Date();
    }

    public ArticleComment(long article_id, long comment_id) {
        this();
        this.article_id = article_id;
        this.comment_id = comment_id;
    }

    public int compareTo(Sortable o) {
        ArticleComment c = (ArticleComment)o;
        if(comment == null)
            return 1;
        if(c == null)
            return -1;
        return comment.compareTo(c.comment);
    }

    public String myType() {
        return "comment";
    }
    
}
