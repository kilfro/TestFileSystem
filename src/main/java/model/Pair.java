package model;

/**
 * Created by kirill on 25.04.17.
 */
public class Pair {
    private String operation;
    private String names;

    public Pair(String operation, String names) {
        this.operation = operation;
        this.names = names;
    }

    public String getOperation() {
        return operation;
    }

    public String getNames() {
        return names;
    }

    @Override
    public String toString() {
        return operation + ": " + names;
    }
}