package executor;

import exception.AlreadyExistsException;
import org.junit.After;
import org.junit.Test;
import system.InMemorySystem;

/**
 * Created by kirill on 05.05.17.
 */
public class MkDirCommandTest {
    private InMemorySystem system = new InMemorySystem();
    private MkDirCommand mkDir = new MkDirCommand();

    @After
    public void init() {
        system = new InMemorySystem();
    }

    @Test
    public void createNewDirectory() throws Exception {
        mkDir.execute("folder", system);
    }

    @Test(expected = AlreadyExistsException.class)
    public void createDuplicate() throws Exception {
        mkDir.execute("test", system);
        mkDir.execute("test", system);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createIncorrectName() throws Exception {
        mkDir.execute("/", system);
    }
}