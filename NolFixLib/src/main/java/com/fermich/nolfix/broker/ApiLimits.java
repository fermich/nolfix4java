package com.fermich.nolfix.broker;

public class ApiLimits {

    // maksymalna ilosc walorow w filtrze:
    public static final int MAX_SECURITIES_IN_FILTER = 100;

    // maksymalna ilosc zlecen na interwal czasowy (10000 milis)
    public static final int MAX_ORDERS_PER_INTERVAL = 8;

    // dzienny limit operacji (zlecenia kupna, sprzedazy, anulaty, modyfikacje) na jednym rachunku
    public static final int MAX_OPS_PER_DAY = 2000;

}
