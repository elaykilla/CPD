/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.articles.Announce;
import models.articles.Article;

/**
 *
 * @author Sissoko
 */
public class SearchField {

    public String type = "user";
    //User
    public String firstName = "";
    public String lastName = "";
    public Integer promo_from = 0;
    public Integer promo_to;
    //Article
    public String title = "";
    public String content = "";
    //Both
    public String request[];

    public Class getSearchClass() {
        if ("article".equalsIgnoreCase(type)) {
            return Article.class;
        }
        if ("announce".equalsIgnoreCase(type)) {
            return Announce.class;
        }
        if ("user".equalsIgnoreCase(type)) {
            return User.class;
        }
        return this.getClass();
    }

    public String createRequest() {
        if (request == null) {
            return null;
        }
        String req = "";
        if ("article".equalsIgnoreCase(type)) {
            req = "select distinct s from Article s where " + "s.title like '%" + request[0] + "%' or s.content like '%" + request[0] + "%' or s.author.firstName like '%" + request[0] + "%' or s.author.lastName like '%" + request[0] + "%'";
            for (int i = 1; i < request.length; i++) {
                req += " or s.title like '%" + request[i] + "%' or s.content like '%" + request[i] + "%' or s.author.firstName like '%" + request[i] + "%' or s.author.lastName like '%" + request[i] + "%'";
            }
        }
        if ("announce".equalsIgnoreCase(type)) {
            req = "select distinct s from Announce s where " + "s.title like '%" + request[0] + "%' or s.content like '%" + request[0] + "%' or s.author.firstName like '%" + request[0] + "%' or s.author.lastName like '%" + request[0] + "%'";
            for (int i = 1; i < request.length; i++) {
                req += " or s.title like '%" + request[i] + "%' or s.content like '%" + request[i] + "%' or s.author.firstName like '%" + request[i] + "%' or s.author.lastName like '%" + request[i] + "%'";
            }
        }
        if ("event".equalsIgnoreCase(type)) {
            req = "select distinct s from Event s where " + "s.title like '%" + request[0] + "%' or s.content like '%" + request[0] + "%' or s.author.firstName like '%" + request[0] + "%' or s.author.lastName like '%" + request[0] + "%'";
            for (int i = 1; i < request.length; i++) {
                req += " or s.title like '%" + request[i] + "%' or s.content like '%" + request[i] + "%' or s.author.firstName like '%" + request[i] + "%' or s.author.lastName like '%" + request[i] + "%'";
            }
        }
        if ("user".equalsIgnoreCase(type)) {
            req = "select distinct s from User s where " + "s.firstName like '%"
                    + request[0] + "%' or s.lastName like '%" + request[0] + "%' or s.promotion like '%" + request[0] + "%'";
            for (int i = 1; i < request.length; i++) {
                req += " or s.firstName like '%"
                        + request[i] + "%' or s.lastName like '%" + request[i] + "%' or s.promotion like '%" + request[i] + "%'";
            }
        }
        return req;
    }
}
