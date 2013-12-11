/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.articles;

import java.io.Serializable;

/**
 *
 * @author Sissoko
 */
public class ArticleCommentId implements Serializable{
    long article_id;
    long comment_id;

    public ArticleCommentId(long article_id, long comment_id) {
        this.article_id = article_id;
        this.comment_id = comment_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.article_id ^ (this.article_id >>> 32));
        hash = 79 * hash + (int) (this.comment_id ^ (this.comment_id >>> 32));
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
        final ArticleCommentId other = (ArticleCommentId) obj;
        if (this.article_id != other.article_id) {
            return false;
        }
        if (this.comment_id != other.comment_id) {
            return false;
        }
        return true;
    }
}
