package model;

import java.util.Map;

/**
 * Created by kirill on 21.04.17.
 */
public abstract class AbstractModel {
    private AbstractModel previous;
    private boolean isDirectory;
    private int size;
    private Map<String, AbstractModel> next;
    private Object data;

    AbstractModel(AbstractModel previous, boolean isDirectory) {
        this.previous = previous;
        this.isDirectory = isDirectory;
    }

    public AbstractModel getPrevious() {
        return previous;
    }

    public Map<String, AbstractModel> getNext() {
        return next;
    }

    public void setNext(Map<String, AbstractModel> next) {
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public boolean isFile() {
        return !isDirectory;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}