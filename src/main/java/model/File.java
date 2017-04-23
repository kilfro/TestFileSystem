package model;

/**
 * Created by kirill on 21.04.17.
 */
public class File extends AbstractModel{

    public File(AbstractModel previous) {
        super(previous, false);
        setData(new Object());
        setSize((int) (Math.random() * 100));
    }
}