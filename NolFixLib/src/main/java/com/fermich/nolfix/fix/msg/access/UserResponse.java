package com.fermich.nolfix.fix.msg.access;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Response to the UserReq message
 * <UserRsp UserReqID="22" Username=“Comarch" UserStat ="1"/>
 */
@XStreamAlias("UserRsp")
public class UserResponse extends FixmlMessage {

    @XStreamAlias("UserReqID")
    @XStreamAsAttribute
    private String userReqId;    // ID requestu

    @XStreamAlias("Username")
    @XStreamAsAttribute
    private String username;        // ID usera

    @XStreamAlias("UserStat")
    @XStreamAsAttribute
    private Integer userStat;        // values from UserStatus

    @XStreamAlias("UserStatText")
    @XStreamAsAttribute
    private String userStatText;            // moze przyjmowac wartosci z UserStatText przy komunikatach async ze stanem polaczenia

    @XStreamAlias("MktDepth")
    @XStreamAsAttribute
    private Integer mktDepth;        // ilosc ofert; values from MarketDepth

    @Override
    public String getMsgName() {
        return "UserRsp";
    }

    public enum UserStatText {
        NOL3_CLOSED(1), //Zamkniecie aplikacji NOL3
        NOL3_OFFLINE(2), //NOL3 jest offline
        NOL3_ONLINE(3), //NOL3 jest online
        NOL3_DISABLED(4); //Aplikacja NOL3 nie jest uruchomiona

        private UserStatText(int val) {
            this.value = val;
        }

        public static UserStatText getUserStatText(int value) throws FixmlElementNotFound {
            for (UserStatText item : values()) {
                if (item.getValue() == value) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("User Stat Text not found for value: " + value);
        }

        public int getValue() {
            return value;
        }

        private final int value;
    }

    // UserReq completes with:
    public enum UserStatus {
        LOGGED_IN(1), //Zalogowanie uzytkownika
        LOGGED_OUT(2), //Wylogowanie uzytkownika
        USER_NOT_EXISTS(3), //Uzytkownik nie istnieje
        WRONG_PASSWD(4), //Niepoprawne haslo
        PASSWD_CHANGED(5), //Haslo zmienione
        OTHER(6), //Inne
        STOCK_LOGOUT(7), //Wymuszenie wylogowania przez Gielde
        SESSION_CLOSED(8); //Sesja zamknieta

        private UserStatus(int statVal) {
            this.value = statVal;
        }

        public static UserStatus getUserStatus(int value) throws FixmlElementNotFound {
            for (UserStatus item : values()) {
                if (item.getValue() == value) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("User Status not found for value: " + value);
        }

        public int getValue() {
            return value;
        }

        private int value;
    }

    public String getUserReqId() {
        return userReqId;
    }

    public UserResponse setUserReqId(String userReqId) {
        this.userReqId = userReqId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getUserStat() {
        return userStat;
    }

    public UserResponse setUserStat(Integer userStat) {
        this.userStat = userStat;
        return this;
    }

    public String getUserStatText() {
        return userStatText;
    }

    public UserResponse setUserStatText(String userStatText) {
        this.userStatText = userStatText;
        return this;
    }

    public Integer getMktDepth() {
        return mktDepth;
    }

    public UserResponse setMktDepth(Integer mktDepth) {
        this.mktDepth = mktDepth;
        return this;
    }

    @Override
    public String toString() {
        return String.format("UserResponse{userReqId='%s', username='%s', userStat=%d, userStatText='%s', mktDepth=%d}",
                userReqId, username, userStat, userStatText, mktDepth);
    }
}
