package system;

import exception.AlreadyExistsException;
import exception.NotFoundException;
import model.Directory;
import model.File;
import model.TransferModel;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kirill on 23.04.17.
 */
public class InMemorySystemTest {
    private InMemorySystem system;

    @Before
    public void initTestData() {
        system = new InMemorySystem();
        system.getRoot().getNext().put("dir1", new Directory(system.getRoot()));
        system.getRoot().getNext().get("dir1").getNext().put("dir10", new Directory(system.getRoot()));
        system.getRoot().getNext().put("dir2", new Directory(system.getRoot()));
        system.getRoot().getNext().put("file1", new File(system.getRoot()));
    }

    @Test
    public void mkdir() throws Exception {
        assertTrue(system.mkdir("dir3"));
    }

    @Test
    public void mkdirAbsolutePath() throws Exception {
        assertTrue(system.mkdir("dir1/dir10/dir100"));
    }

    @Test
    public void mkdirAbsolutePathCreateNewDir() throws Exception {
        assertTrue(system.mkdir("dir1/dir20/dir100"));
    }

    @Test(expected = AlreadyExistsException.class)
    public void mkdirExists() throws Exception {
        system.mkdir("dir1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void mkdirIllegalName() throws Exception {
        system.mkdir("..");
    }

    @Test
    public void mkfile() throws Exception {
        assertTrue(system.mkdir("file2"));
    }

    @Test
    public void mkfileAbsolutePath() throws Exception {
        assertTrue(system.mkdir("dir1/dir10/file1"));
    }

    @Test
    public void mkfileAbsolutePathCreateNewDir() throws Exception {
        assertTrue(system.mkdir("dir1/dir20/file1"));
    }

    @Test(expected = AlreadyExistsException.class)
    public void mkfileExist() throws Exception {
        system.mkfile("file1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void mkfileIllegalName() throws Exception {
        system.mkdir("/");
    }

    @Test
    public void cd() throws Exception {
        assertTrue(system.cd("dir1"));
    }

    @Test
    public void cdNextDir() throws Exception {
        assertTrue(system.cd("dir1/dir10"));
    }

    @Test(expected = NotFoundException.class)
    public void cdNotFound() throws Exception {
        system.cd("nan");
    }

    @Test(expected = NotFoundException.class)
    public void cdToFile() throws Exception {
        system.cd("file1");
    }

    @Test
    public void pwd() throws Exception {
        system.cd("dir1/dir10");
        assertEquals(system.pwd(), "/dir1/dir10");
    }

    @Test
    public void pwdBackToRoot() throws Exception {
        system.cd("dir1/dir10");
        system.cd("/");
        assertEquals(system.pwd(), "");
    }

    @Test
    public void ls() throws Exception {
        List<TransferModel> actual = system.ls();
        assertEquals(actual.get(0), new TransferModel("dir1", true));
        assertEquals(actual.get(1), new TransferModel("dir2", true));
        assertEquals(actual.get(2), new TransferModel("file1", false));
    }
}