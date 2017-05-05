package executor;

import exception.AlreadyExistsException;
import exception.InterruptOperationException;
import exception.NotFoundException;
import system.InMemorySystem;

/**
 * Created by kirill on 05.05.17.
 */
public interface Command {
    void execute(String value, InMemorySystem system) throws NotFoundException, InterruptOperationException, AlreadyExistsException;
}
