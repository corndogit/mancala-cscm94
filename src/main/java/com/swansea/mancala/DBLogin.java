package com.swansea.mancala;

/**
 * An enum for storing database login information.
 * @author Swapnil Sarmah
 */
public enum DBLogin {
    URL("jdbc:mysql://localhost:3306/Mancala"),
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
