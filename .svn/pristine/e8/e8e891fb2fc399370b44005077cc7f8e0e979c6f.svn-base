/**
 *
 */
package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Required;
import play.data.validation.Unique;

/**
 *
 * @author Sissoko && Elay
 * @date 1 mai 2013 02:39:04
 */
@Entity
@Table(name = "T_ROLES")
public class Role extends Standard {

    /**
     * represents an attribute role to a user for example administrator,
     * moderator, president etc.
     */
    @Required(message = "Role code is required")
    @Column(name = "CODE", nullable = false, unique = true)
    @Unique(message = "This role code exists")
    public String code;
    /**
     * Describes a role.
     */
    @Column(name = "DESCRIPTION")
    public String description;
    /**
     * Represents all rights associate with this role.
     */
    @JoinTable(name = "AS_ROLES_RIGHTS", joinColumns = {
        @JoinColumn(name = "ROLE_ID", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "RIGHT_ID", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.PERSIST)
    public List<Right> rights;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    public List<RoleRight> asRoleRights;

    /**
     * constructs a role.
     */
    public Role() {
        super();
        rights = new ArrayList<Right>();
        asRoleRights = new ArrayList<RoleRight>();
    }

    /**
     * @param code
     */
    public Role(String code) {
        super();
        this.code = code;
        rights = new ArrayList<Right>();
        asRoleRights = new ArrayList<RoleRight>();
    }

    /**
     * returns the Role with the code $code if it exists or create new Role and
     * return it.
     *
     * @param code
     * @return
     */
    public static Role findOrCreate(String code) {
        if (code == null) {
            return null;
        }
        Role role = findByCode(code);
        if (role == null) {
            role = new Role(code);
            role.save();
        }
        return role;
    }

    /**
     * gives a new rule to the role. Check if he already has this rule we do
     * anything.
     *
     * @param code
     */
    @Deprecated
    public void attributeRule(String code, String restrict) {
        if (code == null) {
            return;
        }
        boolean restricted = Visibility.RESTRICTED.equals(restrict);
        Right right = Right.findByCode(code);
        if (right == null) {
            right = new Right(code);
        }

        if (rights.contains(right)) {
            if (restricted
                    && !right.visibility.code.equals(Visibility.RESTRICTED)) {
                right.visibility = Visibility.visibility(Visibility.RESTRICTED);
                right.save();
            } else if (!restricted
                    && right.visibility.equals(Visibility.RESTRICTED)) {
                right.visibility = Visibility
                        .visibility(Visibility.NOT_RESTRICTED);
                right.save();
            }
            return;
        }

        if (!restricted) {
            right.visibility = Visibility.visibility(Visibility.NOT_RESTRICTED);
        } else {
            right.visibility = Visibility.visibility(Visibility.RESTRICTED);
        }
        right.save();
        rights.add(right);
        save();
    }

    /**
     *
     * @param rightCode
     */
    @Deprecated
    public void attributeRight(String rightCode) {
        if (rightCode == null) {
            return;
        }
        Right right = Right.findByCode(rightCode);
        if (right == null) {
            return;
        }
        rights.add(right);
        save();
    }

    /**
     * removes a role from the role.
     *
     * @param code
     */
    public void removeRight(String code) {
        if (code == null) {
            return;
        }
        Right right = new Right(code);
        rights.remove(right);
        save();
    }

    /**
     * Attribute a rule to an existent role. if restricted is true that means
     * the rule is applicable only to the specific dendrite.
     *
     * @param roleCode
     * @param ruleCode
     * @param restricted
     */
    public static void attributeRuleToRole(String roleCode, String ruleCode,
            String restricted) {
        Role role = Role.findByCode(roleCode);
        if (role == null) {
            return;
        }
        role.attributeRule(ruleCode, restricted);
    }

    /**
     * Remove a rule from an existent role.
     *
     * @param roleCode
     * @param ruleCode
     */
    public static void removeRuleFromRole(String roleCode, String ruleCode) {
        Role role = Role.findByCode(roleCode);
        if (role == null) {
            return;
        }
        role.removeRight(ruleCode);
    }

    /**
     *
     * @param right
     * @return
     */
    public boolean contains(Right right) {
        return rights.contains(right);
    }

    /**
     * retrieve the role by his code.
     *
     * @param code
     * @return
     */
    public static Role findByCode(String code) {
        Role role = find("byCode", code).first();
        return role;
    }

    /**
     * set the description of the specific role with code $code
     *
     * @param code
     * @param description
     */
    public static void setDescription(String code, String description) {
        Role role = findByCode(code);
        if (role != null) {
            role.description = description;
            role.save();
        }
    }

    /**
     * Find all roles ordered by order
     *
     * @param order
     * @return
     */
    public static List<Role> findAll(String order) {
        List<Role> roles = Role.find(
                "select distinct u from Role u order by " + order).fetch();
        return roles;
    }

    /**
     *
     * @return
     */
    public List<Right> complementRight() {
        List<Right> rights = Right.findAll("code asc");
        rights.removeAll(this.rights);
        return rights;
    }

    /**
     *
     * @return
     */
    public boolean isAffected() {
        List<User> users;
        users = User.usersAsRole(this);
        return users != null && !users.isEmpty();
    }

    /**
     *
     * @param right
     * @return
     */
    public boolean hasRight(Right right) {
        return rights.contains(right);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Role other = (Role) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }
}
