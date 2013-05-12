package com.fermich.nolfix.fix.msg.access;

import com.fermich.nolfix.fix.msg.Fixml;
import com.fermich.nolfix.fix.msg.FixmlRequest;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Login request
 * <UserReq UserReqID="22" UserReqTyp="1" Username=“Comarch" Password ="Comarch"/>
 */
@XStreamAlias("UserReq")
public class UserRequest extends FixmlRequest {

    @XStreamAlias("UserReqID")
    @XStreamAsAttribute
    private String userReqId;    // ID komunikatu

    @XStreamAlias("UserReqTyp")
    @XStreamAsAttribute
    private Integer userReqTyp;    // typ zadania, values from UserRequestType

    @XStreamAlias("Username")
    @XStreamAsAttribute
    private String username;    // ID uzytkownika

    @XStreamAlias("Password")
    @XStreamAsAttribute
    private String password;    // haslo

    @XStreamAlias("NewPassword")
    @XStreamAsAttribute
    private String newPassword;    // nowe haslo

    @Override
    public Fixml pack() {
        return super.pack().addUserRequest(this);
    }

    @Override
    public String getMsgName() {
        return "UserReq";
    }

    public enum UserRequestType {
        LOGIN(1), //Logowanie
        LOGOUT(2), //Wylogowanie
        PASSWORD_CHANGE(3), //Zmiana hasla
        USER_STATUS(4); //Status uzytkownika

        private UserRequestType(int userReqTyp) {
            this.userReqTyp = userReqTyp;
        }

        public UserRequestType getUserRequestType(int value) throws FixmlElementNotFound {
            for (UserRequestType item : values()) {
                if (item.getUserReqTyp() == value) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("User Request Type not found for value: " + value);
        }

        public int getUserReqTyp() {
            return this.userReqTyp;
        }

        private int userReqTyp;
    }

    @Override
    public String toString() {
        return "UserRequest [userReqId=" + userReqId + ", userReqTyp="
                + userReqTyp + ", username=" + username + ", password="
                + password + ", newPassword=" + newPassword + "]";
    }

    public String getUserReqId() {
        return userReqId;
    }

    public UserRequest setUserReqId(String userReqId) {
        this.userReqId = userReqId;
        return this;
    }

    public Integer getUserReqTyp() {
        return userReqTyp;
    }

    public UserRequest setUserReqTyp(Integer userReqTyp) {
        this.userReqTyp = userReqTyp;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public UserRequest setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

}
