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
    public int hashCode() {
        return operation.length() * names.length();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Pair other = (Pair) obj;
        return operation.equals(other.getOperation()) && names.equals(other.getNames());
    }
}