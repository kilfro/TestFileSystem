package executor;

import exception.AlreadyExistsException;
import exception.NotFoundException;
import model.AbstractModel;
import system.InMemorySystem;

/**
 * Created by kirill on 05.05.17.
 */
class MkFileCommand extends MakeAbstractCommand implements Command {
    @Override
    public void execute(String value, InMemorySystem system) throws NotFoundException, AlreadyExistsException {
        if (value == null || INCORRECT_NAMES.contains(value)) {
            throw new IllegalArgumentException("Некорректное имя файла!\n");
        }
        AbstractModel model = system.getCurrentModel();
        system.setCurrentModel(InMemorySystem.getROOT());
        String[] values = value.split("/");
        for (int i = 0; i < values.length; i++) {
            String name = values[i];
            if (i == values.length - 1) {
                makeModel(name, false, system);
            } else {
                try {
                    makeModel(name, true, system);
                } catch (AlreadyExistsException e) {

                }
                system.setCurrentModel(system.getCurrentModel().getNext().get(name));
            }
        }
        system.setCurrentModel(model);
    }
}