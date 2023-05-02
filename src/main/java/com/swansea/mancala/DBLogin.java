package com.swansea.mancala;

public enum DBLogin {
    URL("jdbc:mysql://localhost:3306/User"),
    USERNAME("root"),
    PASSWORD("pass");

    public final String label;

    DBLogin(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
