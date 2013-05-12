package com.fermich.nolfix.fix.msg.filter;

import com.fermich.nolfix.fix.msg.exception.FixmlElementNotFound;

public enum EntryType {

    BID('0'), //Oferta kupna
    OFFER('1'), //Oferta sprzedazy
    LAST_TRADE('2'), //Ostatnia transakcja
    TRADE_VOLUME('B'), //Wolumen obrotu
    OPEN_INTEREST('C'), //LOP
    INDEX_VOLUME('3'), //Wartosc indeksu
    OPENNING_PRICE('4'), //Kurs otwarcia
    CLOSING_PRICE('5'), //Kurs zamkniecia
    TRADING_SESSION_HIGH_PRICE('7'), //Kurs maksymalny
    TRADING_SESSION_LOW_PRICE('8'), //Kurs minimalny
    REFERENCE_PRICE('r'); //Kurs odniesienia

    private EntryType(char value) {
        this.value = value;
    }

    public static EntryType getQuotesEntryType(char value) throws FixmlElementNotFound {
        for (EntryType item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        throw new FixmlElementNotFound("Quotes Entry Type not found for value: " + value);
    }

    private char value;

    public char getValue() {
        return value;
    }
}
