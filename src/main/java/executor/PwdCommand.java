package executor;

import system.InMemorySystem;
import util.ConsoleHelper;

/**
 * Created by kirill on 05.05.17.
 */
class PwdCommand implements Command {
    @Override
    public void execute(String value, InMemorySystem system) {
        ConsoleHelper.printMessage(system.getPwd().toString());
    }
}