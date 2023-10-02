package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public ShipmentSize getNext() {
        if (this == getBiggest())
            return getBiggest();
        return values()[(ordinal() + 1) % values().length];
    }

    public ShipmentSize getBiggest() {
        return values()[values().length - 1];
    }
}


