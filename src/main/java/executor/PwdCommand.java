package executor;

import system.InMemorySystem;

import static util.ConsoleHelper.printMessage;

/**
 * Created by kirill on 05.05.17.
 */
class PwdCommand implements Command {
    @Override
    public void execute(String value, InMemorySystem system) {
        printMessage(system.getPwd().toString());
    }
}