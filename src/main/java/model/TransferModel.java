package model;

/**
 * Created by kirill on 23.04.17.
 */
public class TransferModel {
    private String name;
    private boolean isDirectory;
    private int size;

    public TransferModel(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
    }

    public TransferModel(String name, boolean isDirectory, int size) {
        this(name, isDirectory);
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int hashCode() {
        return name.length();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TransferModel other = (TransferModel) obj;
        return name.equals(other.getName()) && isDirectory == other.isDirectory();
    }
}
