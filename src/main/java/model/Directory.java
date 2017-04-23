package model;

import java.util.TreeMap;

/**
 * Created by kirill on 21.04.17.
 */
public class Directory extends AbstractModel {

    public Directory(AbstractModel previous) {
        super(previous, true);
        setNext(new TreeMap<String, AbstractModel>());
        setSize(0);
    }
}