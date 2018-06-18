package com.epam.igor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2044188054947951995L;

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToMany(mappedBy = "userRole")
    private Set<User> users = new HashSet<>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}
