package system;

import model.AbstractModel;
import model.Directory;

/**
 * Created by kirill on 22.04.17.
 */
public class InMemorySystem {
    private static final AbstractModel ROOT = new Directory(null);
    private AbstractModel currentModel = ROOT;
    private StringBuilder pwd = new StringBuilder();

    public static AbstractModel getROOT() {
        return ROOT;
    }

    public AbstractModel getCurrentModel() {
        return currentModel;
    }

    public void setCurrentModel(AbstractModel currentModel) {
        this.currentModel = currentModel;
    }

    public StringBuilder getPwd() {
        return pwd;
    }

    public void setPwd(StringBuilder pwd) {
        this.pwd = pwd;
    }
}