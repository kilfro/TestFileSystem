package controller;

import model.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kirill on 26.04.17.
 */
public class CommandControllerTest {
    CommandController controller;

    @Before
    public void init() {
        controller = new CommandController();
    }

    @Test
    public void parseCommand() throws Exception {
        assertEquals(controller.parseCommand("cd test"), new Pair("cd", "test"));
        assertEquals(controller.parseCommand("cd /test"), new Pair("cd", "test"));
        assertEquals(controller.parseCommand("cd   test   "), new Pair("cd", "test"));
        assertEquals(controller.parseCommand("cd /"), new Pair("cd", "/"));
        assertEquals(controller.parseCommand("mkdir test/dir"), new Pair("mkdir", "test/dir"));
        assertEquals(controller.parseCommand("mkdir /test/dir"), new Pair("mkdir", "test/dir"));
    }
}