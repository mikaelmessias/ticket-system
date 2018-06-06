package org.ticket.model;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import javax.persistence.*;

@Entity
@Table(name="TB_USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private long id;

    @Column(name="USER_NAME")
    private String name;
    @Column(name="USER_NICK")
    private String nick;
    @Column(name="USER_PASS")
    private String password;

    public enum UserType {
        manager,
        client
    };

    @Column(name="USER_TYPE")
    private UserType userType;

    public User(String name, String nick, String password) {
        this.name = name;
        this.nick = nick;

        HashFunction sha256 = Hashing.sha256();
        this.password = sha256.newHasher()
                .putString(password, Charsets.UTF_8)
                .putString(name, Charsets.UTF_8)
                .hash().toString();
        this.userType = UserType.client;
    }

    public User(String name, String nick, String password, UserType ut) {
        this.name = name;
        this.nick = nick;

        HashFunction sha256 = Hashing.sha256();
        this.password = sha256.newHasher()
                .putString(password, Charsets.UTF_8)
                .putString(name, Charsets.UTF_8)
                .hash().toString();
        this.userType = ut;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public UserType getUserType() { return userType; }

    public String getPassword() { return password; }
}
