/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.events;

/**
 *
 * @author Sissoko
 */
public enum InvitationPaymentStateEnum {

    PAID(1, "payé", "Payée"),
    NOT_PAID(2, "pas payé", "Non payée");
    public Integer code;
    public String libLong;
    public String libCourt;

    private InvitationPaymentStateEnum(Integer code, String libCourt, String libLong) {
        this.code = code;
        this.libCourt = libCourt;
        this.libLong = libLong;
    }

    /**
     *
     * @return
     */
    public static InvitationPaymentStateEnum[] paymentStates() {
        return InvitationPaymentStateEnum.values();
    }

    /**
     *
     * @param code
     * @return
     */
    public static InvitationPaymentStateEnum findByCode(Integer code) {
        switch (code) {
            case 1:
                return PAID;
            case 2:
                return NOT_PAID;
        }
        return null;
    }
}
