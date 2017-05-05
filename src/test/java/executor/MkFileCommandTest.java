package executor;

import exception.AlreadyExistsException;
import exception.NotFoundException;
import org.junit.After;
import org.junit.Test;
import system.InMemorySystem;

/**
 * Created by kirill on 05.05.17.
 */
public class MkFileCommandTest {
    private InMemorySystem system = new InMemorySystem();
    private MkFileCommand mkFile = new MkFileCommand();

    @After
    public void init() {
        system = new InMemorySystem();
    }

    @Test
    public void createNewFile() throws NotFoundException, AlreadyExistsException {
        mkFile.execute("new", system);
    }

    @Test(expected = AlreadyExistsException.class)
    public void createDuplicate() throws NotFoundException, AlreadyExistsException {
        mkFile.execute("test", system);
        mkFile.execute("test", system);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createIncorrectName() throws NotFoundException, AlreadyExistsException {
        mkFile.execute(".", system);
    }
}