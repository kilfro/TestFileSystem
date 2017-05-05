package executor;

import exception.NotFoundException;
import model.AbstractModel;
import system.InMemorySystem;

/**
 * Created by kirill on 05.05.17.
 */
class CdCommand implements Command {
    @Override
    public void execute(String value, InMemorySystem system) throws NotFoundException {
        if (value == null) {
            throw new NotFoundException("Путь не найден!\n");
        } else if ("/".equals(value)) {
            system.setCurrentModel(InMemorySystem.getROOT());
            system.setPwd(new StringBuilder(""));
        } else {
            AbstractModel bufModel = system.getCurrentModel();
            StringBuilder bufPwd = system.getPwd();
            system.setCurrentModel(InMemorySystem.getROOT());
            system.setPwd(new StringBuilder(""));
            for (String name : value.split("/")) {
                system.setCurrentModel(system.getCurrentModel().getNext().get(name));
                if (system.getCurrentModel() == null || system.getCurrentModel().isFile()) {
                    system.setCurrentModel(bufModel);
                    system.setPwd(bufPwd);
                    throw new NotFoundException("Путь не найден!\n");
                }
                system.getPwd().append("/").append(name);
            }
        }
    }
}