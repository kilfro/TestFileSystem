package executor;

import exception.InterruptOperationException;
import exception.NotFoundException;
import system.InMemorySystem;

import static util.ConsoleHelper.printMessage;
import static util.ConsoleHelper.readString;

/**
 * Created by kirill on 05.05.17.
 */
class ExitCommand implements Command {
    @Override
    public void execute(String value, InMemorySystem system) throws NotFoundException, InterruptOperationException {
        printMessage("Действительно хотите выйти? (yes / no): ");
        if ("yes".equals(readString().toLowerCase())) {
            throw new InterruptOperationException("До свидания!\n");
        }
    }
}