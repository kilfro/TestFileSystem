package executor;

import exception.NotFoundException;
import model.Directory;
import org.junit.After;
import org.junit.Test;
import system.InMemorySystem;

import static org.junit.Assert.*;

/**
 * Created by kirill on 05.05.17.
 */
public class CdCommandTest {
    private InMemorySystem system = new InMemorySystem();
    private CdCommand cd = new CdCommand();
    private MkDirCommand mkDir = new MkDirCommand();
    private MkFileCommand mkFile = new MkFileCommand();

    @After
    public void init() {
        system = new InMemorySystem();
        cd = new CdCommand();
    }

    @Test
    public void cd() throws Exception {
        mkDir.execute("test", system);
        cd.execute("test", system);
    }

    @Test(expected = NotFoundException.class)
    public void cdNotFound() throws Exception {
        cd.execute("not found", system);
    }

    @Test(expected = NotFoundException.class)
    public void cdToFile() throws Exception {
        mkFile.execute("new file", system);
        cd.execute("new file", system);
    }
}