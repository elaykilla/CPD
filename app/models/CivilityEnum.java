/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Sissoko
 */
public enum CivilityEnum {

    MONSIEUR(1, "M.", "Monsieur"),
    MADAME(2, "Mme", "Madame"),
    MADEMOISELLE(3, "Mlle", "Mademoiselle");
    public Integer code;
    public String libLong;
    public String libCourt;

    private CivilityEnum(Integer code, String libCourt, String libLong) {
        this.code = code;
        this.libCourt = libCourt;
        this.libLong = libLong;
    }

    /**
     *
     * @return
     */
    public static CivilityEnum[] civilities() {
        return CivilityEnum.values();
    }

    /**
     * 
     * @param code
     * @return 
     */
    public static CivilityEnum findByCode(Integer code) {
        switch (code) {
            case 1:
                return MONSIEUR;
            case 2:
                return MADAME;
            case 3:
                return MADEMOISELLE;
        }
        return null;
    }
}
