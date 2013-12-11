/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.events;

/**
 *
 * @author Sissoko
 */
public enum InvitationStateEnum {

    GOING(1, "YES", "Invitation acceptée"),
    NOT_GOING(2, "NO", "Invitation refusée"),
    WAITING(3, "MAYBE", "Invitation en attende de reponse");
    public Integer code;
    public String libLong;
    public String libCourt;

    private InvitationStateEnum(Integer code, String libCourt, String libLong) {
        this.code = code;
        this.libCourt = libCourt;
        this.libLong = libLong;
    }

    /**
     *
     * @return
     */
    public static InvitationStateEnum[] invitationStates() {
        return InvitationStateEnum.values();
    }

    /**
     * 
     * @param code
     * @return 
     */
    public static InvitationStateEnum findByCode(Integer code) {
        switch (code) {
            case 1:
                return GOING;
            case 2:
                return NOT_GOING;
            case 3:
                return WAITING;
        }
        return null;
    }
}
