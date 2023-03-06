package it.corso.mygym.model.enums;

public enum SubscriptionType {

    OPEN("OPEN"),
    WEIGHTROOM("SALA_PESI");


    private final String type;
    SubscriptionType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }

}
