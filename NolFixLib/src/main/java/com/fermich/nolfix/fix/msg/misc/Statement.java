package com.fermich.nolfix.fix.msg.misc;

import com.fermich.nolfix.fix.msg.FixmlMessage;
import com.fermich.nolfix.fix.msg.common.Instrument;
import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * BOS message, not included in FIX
 * <Statement Acct="00-55-006638" type="M" ike="N"> <Fund name="Cash" value="100.00"/>
 * <Position Acc110="20" Isin="PL0112888821"/> <Position Acc110="1000" Isin="PL0FTR87632"/>
 * </Statement>
 */
@XStreamAlias("Statement")
public class Statement extends FixmlMessage {

    @XStreamAlias("Acct")
    @XStreamAsAttribute
    private String acct;    //numer konta

    @XStreamAlias("type")
    @XStreamAsAttribute
    private char acctType;

    @XStreamAlias("ike")
    @XStreamAsAttribute
    private char ike;

    @XStreamImplicit(itemFieldName = "Fund")
    private List<Fund> funds;

    @XStreamImplicit(itemFieldName = "Position")
    private List<Position> positions;

    @Override
    public String getMsgName() {
        return "Statement";
    }

    public enum AccountType { //typ rachunku
        CASH('M'),
        DERIVATIVE('P');

        private char acctType;

        private AccountType(char marketType) {
            this.acctType = marketType;
        }

        public static AccountType getAcctType(char value) throws FixmlElementNotFound {
            for (AccountType item : values()) {
                if (item.getAcctType() == value) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("Account Type not found for value: " + value);
        }

        public char getAcctType() {
            return acctType;
        }
    }

    @XStreamAlias("Position")
    public static class Position {

        @XStreamAlias("Acc110")
        @XStreamAsAttribute
        private String acc110;        // ilosc posiadanych aktywow

        @XStreamAlias("Acc120")
        @XStreamAsAttribute
        private String acc120;        // ilosc zablokowanych aktywow

        @XStreamImplicit(itemFieldName = "Instrmt")
        private List<Instrument> instrmts; //instrument

        public String getAcc110() {
            return acc110;
        }

        public Position setAcc110(String acc110) {
            this.acc110 = acc110;
            return this;
        }

        public String getAcc120() {
            return acc120;
        }

        public Position setAcc120(String acc120) {
            this.acc120 = acc120;
            return this;
        }

        public List<Instrument> getInstrmts() {
            return instrmts;
        }

        public Position setInstrmts(List<Instrument> instrmts) {
            this.instrmts = instrmts;
            return this;
        }

        @Override
        public String toString() {
            return String.format("Position{acc110='%s', acc120='%s', instrmts=%s}", acc110, acc120, instrmts);
        }
    }

    @XStreamAlias("Fund")
    public static class Fund {

        @XStreamAlias("name")
        @XStreamAsAttribute
        private String name;    //wartosci z FundName

        @XStreamAlias("value")
        @XStreamAsAttribute
        private String value;

        public String getName() {
            return name;
        }

        public Fund setName(String name) {
            this.name = name;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Fund setValue(String value) {
            this.value = value;
            return this;
        }

        @Override
        public String toString() {
            return String.format("Fund{name='%s', value='%s'}", name, value);
        }
    }

    public enum FundName {
        Cash, //Gotowka konto zwykle
        Recivables, //Naleznosci niezablokowane
        CashRecivables, //Gotowka + naleznosci niezablokowane
        CashBlocked, //Gotowka blokowana pod zlecenia
        RecivablesBlocked, //Naleznosci blokowane pod zlecenia
        Loans, //Kredyty - aktualne zaangazowane
        Liabilities, //Zobowiazania laczne (P+T) wobec DM BOS
        LiabilitiesP, //Zobowiazania wobec DM BOS typu P - zabezpieczone
        LiabilitiesT, //Zobowiazania wobec DM BOS typu T - niezabezpieczone
        MaxBuy, //Maksymalne kupno
        MaxOtpBuy, //Maksymalne kupno na OTP
        MaxOtpPBuy, //Maksymalne kupno na OTP typu P
        MaxOtpTBuy, //Maksymalne kupno na OTP typu T
        LiabilitiesLimitMax, //Kwota o jaka mozna zwiekszyc limit naleznosci
        LiabilitiesPLiimitMax, //Kwota o jaka mozna zwiekszyc limit naleznosci typu P
        LiabilitiesTLiimitMax, //Kwota o jaka mozna zwiekszyc limit naleznosci typu T
        Deposit, //Depozyt razem
        BlockedDeposit, //Depozyt zablokowany
        SecSafeties, //Zabezpiecznie PW ogolem
        SecSafetiesUsed, //Wykorzystane zabezpieczenie PW
        FreeDeposit, //Depozyt do dyspozycji
        DepositSurplus, //Nadwyzka ponad minimalny depozyt
        DepositDeficit, //Kwota do uzupelnienia
        SecValueSum, //Wartosc waloru
        PortfolioValue; //Wartosc portfela

        public static FundName getFundName(String name) throws FixmlElementNotFound {
            for (FundName item : values()) {
                if (item.name().equals(name)) {
                    return item;
                }
            }
            throw new FixmlElementNotFound("Fund Name not found for value: " + name);
        }
    }

    public String getAcct() {
        return acct;
    }

    public Statement setAcct(String acct) {
        this.acct = acct;
        return this;
    }

    public char getAcctType() {
        return acctType;
    }

    public void setAcctType(char acctType) {
        this.acctType = acctType;
    }

    public char getIke() {
        return ike;
    }

    public void setIke(char ike) {
        this.ike = ike;
    }

    public List<Fund> getFunds() {
        return funds;
    }

    public Statement setFunds(List<Fund> funds) {
        this.funds = funds;
        return this;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Statement setPositions(List<Position> positions) {
        this.positions = positions;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Statement{acct='%s', acctType=%s, ike=%s, funds=%s, positions=%s}", acct, acctType, ike, funds, positions);
    }
}
