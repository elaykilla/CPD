package models.cvs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import models.Standard;
import play.data.validation.Required;

/**
 *
 * @author Cheick Mahady Sissoko && Abdoulaye Maiga
 * @date 26 mai 2013 19:58:19
 */
@Entity
@Table(name = "T_EXPERIENCES")
public class Experience extends Standard {

    @Required(message = "Company is required")
    @Column(name = "COMPANY")
    public String company;
    @Required(message = "Sector is required")
    @Column(name = "SECTOR")
    public String sector;
    @Required(message = "Poste is required")
    @Column(name = "POSTE")
    public String poste;
    @Column(name = "MISSION")
    public String mission;
    @Required(message = "Beginning month is required")
    @Column(name = "BEGIN_MONTH")
    public String beginMonth;
    @Required(message = "Beginning year is required")
    @Column(name = "BEGIN_YEAR")
    public Integer beginYear;
    @Column(name = "END_MONTH")
    public String endMonth;
    @Column(name = "END_YEAR")
    public Integer endYear;
    @Column(name = "ON_GOING")
    public Boolean onGoing;
    @ManyToOne
    public Cv cv;

    public Experience() {
        super();
    }
}
