package executor;

import exception.InterruptOperationException;
import exception.NotFoundException;
import system.InMemorySystem;
import util.ConsoleHelper;

/**
 * Created by kirill on 05.05.17.
 */
class ExitCommand implements Command {
    @Override
    public void execute(String value, InMemorySystem system) throws NotFoundException, InterruptOperationException {
        ConsoleHelper.printMessage("Действительно хотите выйти? (yes / no): ");
        String answer = ConsoleHelper.readString();
        if (answer != null && "yes".equals(answer.toLowerCase())) {
            throw new InterruptOperationException("До свидания!\n");
        }
    }
}