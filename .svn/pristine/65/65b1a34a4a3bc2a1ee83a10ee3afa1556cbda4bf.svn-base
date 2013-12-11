/**
 *
 */
package models;

import controllers.Application;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Table;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import models.articles.Announce;
import models.articles.Article;
import models.events.Event;

import org.imgscalr.Scalr;

import play.libs.Codec;
import services.AWSHelper;

/**
 * @author Elay && Sissoko
 * @date 16 mars 2013 05:22:34
 */
@Entity
@Table(name = "T_PHOTOS")
public class Photo extends Standard {

    /**
     *
     */
    @Column(name = "TITLE")
    public String title;
    /**
     *
     */
    @Column(name = "URL")
    public String url;
    @Column(name = "DESCRIPTION")
    public String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Event event;
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Announce announce;
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Article article;
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User user;

    /**
     * @param file
     */
    public Photo() {
        super();
    }

    /**
     * This function upload a picture with his real size.
     *
     * @param photo
     * @return
     */
    public Photo saveFile(File photo) {
        try {
            if (photo == null) {
                try {
                    throw new IOException("La photo ne doit pas être null");
                } catch (IOException ex) {
                    Logger.getLogger(Photo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            title = photo.getName();
            BufferedImage img = null;
            File logoToUpload = null;
            logoToUpload = File.createTempFile(title, "." + "jpg");
            img = ImageIO.read(photo);
            int originalWidth = img.getWidth();
            int originalHeight = img.getHeight();
            int width = originalWidth;
            int height = originalHeight;
            int desired_wh_ratio = width / height;
            int wh_ratio = originalWidth / originalHeight;
            if (desired_wh_ratio > wh_ratio) {
                BufferedImage resized = Scalr.resize(img, Scalr.Mode.FIT_TO_WIDTH,
                        width);
                // int trim_y = ((originalHeight - height) / 2) /
                // originalHeight;
                if (resized.getHeight() < height) {
                    ImageIO.write(
                            Scalr.resize(resized, Scalr.Mode.FIT_TO_HEIGHT, height),
                            "jpg", logoToUpload);
                } else {
                    ImageIO.write(Scalr.crop(resized, width, height), "jpg",
                            logoToUpload);
                }
            } else {
                BufferedImage resized = Scalr.resize(img, Scalr.Mode.FIT_TO_HEIGHT,
                        height);
                // int trim_x = ((originalWidth- width) / 2) / originalWidth;
                if (resized.getWidth() < width) {
                    ImageIO.write(
                            Scalr.resize(resized, Scalr.Mode.FIT_TO_WIDTH, width),
                            "jpg", logoToUpload);
                } else {
                    ImageIO.write(Scalr.crop(resized, width, height), "jpg",
                            logoToUpload);
                }
            }
            title = Codec.UUID() + title;
            AWSHelper.saveFile(title, logoToUpload);
            url = AWSHelper.getUrl(title);
            save();
            logoToUpload.delete();
            return this;
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * This function upload a picture with size(width, height).
     *
     * @param photo
     * @param title
     * @param width
     * @param height
     * @return
     */
    public Photo saveFile(File photo, String title, int width, int height) {
        try {
            if (photo == null) {
                try {
                    throw new IOException("La photo ne doit pas être null");
                } catch (IOException ex) {
                    Logger.getLogger(Photo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.title = title;
            BufferedImage img = null;
            File logoToUpload = null;
            logoToUpload = File.createTempFile(this.title, "." + "jpg");
            img = ImageIO.read(photo);
            int originalWidth = img.getWidth();
            int originalHeight = img.getHeight();
            int desired_wh_ratio = width / height;
            int wh_ratio = originalWidth / originalHeight;
            if (desired_wh_ratio > wh_ratio) {
                BufferedImage resized = Scalr.resize(img, Scalr.Mode.FIT_TO_WIDTH,
                        width);
                // int trim_y = ((originalHeight - height) / 2) /
                // originalHeight;
                if (resized.getHeight() < height) {
                    ImageIO.write(
                            Scalr.resize(resized, Scalr.Mode.FIT_TO_HEIGHT, height),
                            "jpg", logoToUpload);
                } else {
                    ImageIO.write(Scalr.crop(resized, width, height), "jpg",
                            logoToUpload);
                }
            } else {
                BufferedImage resized = Scalr.resize(img, Scalr.Mode.FIT_TO_HEIGHT,
                        height);
                // int trim_x = ((originalWidth- width) / 2) / originalWidth;
                if (resized.getWidth() < width) {
                    ImageIO.write(
                            Scalr.resize(resized, Scalr.Mode.FIT_TO_WIDTH, width),
                            "jpg", logoToUpload);
                } else {
                    ImageIO.write(Scalr.crop(resized, width, height), "jpg",
                            logoToUpload);
                }
            }
            this.title = Codec.UUID() + this.title;
            AWSHelper.saveFile(title, logoToUpload);
            url = AWSHelper.getUrl(this.title);
            save();
            logoToUpload.delete();
            return this;
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     *
     * @param photo
     * @param title
     * @return
     */
    public static boolean saveProfileFile(File photo, String title, String type) {
        try {
            if (photo == null) {
                try {
                    throw new IOException("La photo ne doit pas être null");
                } catch (IOException ex) {
                    Logger.getLogger(Photo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String profileTitle = title;
            BufferedImage img = null;
            File logoToUpload = null;
            logoToUpload = File.createTempFile(type + "_" + profileTitle, "." + "jpg");
            img = ImageIO.read(photo);
            int width = Application.PHOTO_WIDTH_PROFILE;
            int height = Application.PHOTO_HEIGHT_PROFILE;
            if ("small".equals(type)) {
                width = Application.PHOTO_WIDTH_SMALL;
                height = Application.PHOTO_HEIGHT_SMALL;
            }
            int originalWidth = img.getWidth();
            int originalHeight = img.getHeight();
            int desired_wh_ratio = width / height;
            int wh_ratio = originalWidth / originalHeight;
            if (desired_wh_ratio > wh_ratio) {
                BufferedImage resized = Scalr.resize(img, Scalr.Mode.FIT_TO_WIDTH,
                        width);
                // int trim_y = ((originalHeight - height) / 2) /
                // originalHeight;
                if (resized.getHeight() < height) {
                    ImageIO.write(
                            Scalr.resize(resized, Scalr.Mode.FIT_TO_HEIGHT, height),
                            "jpg", logoToUpload);
                } else {
                    ImageIO.write(Scalr.crop(resized, width, height), "jpg",
                            logoToUpload);
                }
            } else {
                BufferedImage resized = Scalr.resize(img, Scalr.Mode.FIT_TO_HEIGHT,
                        height);
                // int trim_x = ((originalWidth- width) / 2) / originalWidth;
                if (resized.getWidth() < width) {
                    ImageIO.write(
                            Scalr.resize(resized, Scalr.Mode.FIT_TO_WIDTH, width),
                            "jpg", logoToUpload);
                } else {
                    ImageIO.write(Scalr.crop(resized, width, height), "jpg",
                            logoToUpload);
                }
            }
            AWSHelper.saveFile(type + "_" + profileTitle, logoToUpload);
            logoToUpload.delete();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public Event event() {
        Event event = Event.find("select distinct e from Event e join e.photos as p where p = ?", this).first();
        return event;
    }

    /**
     *
     * @param t
     * @return
     */
    public static List<Photo> findAll(String t) {
        return Photo.all().fetch();
    }

    /**
     *
     * @return
     */
    public String profileUrl() {
        return AWSHelper.getUrl("profile_" + title);
    }

    /**
     *
     * @return
     */
    public String smallUrl() {
        return AWSHelper.getUrl("small_" + title);
    }

    /*
     * (non-Javadoc)
     * 
     * @see play.db.jpa.GenericModel#delete()
     */
    @Override
    public Photo delete() {
        if (title != null) {
            AWSHelper.deleteFile(title);
            AWSHelper.deleteFile("profile_" + title);
            AWSHelper.deleteFile("small_" + title);
        }
        return super.delete();
    }
}