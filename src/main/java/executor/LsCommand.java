package executor;

import exception.NotFoundException;
import model.AbstractModel;
import model.TransferModel;
import system.InMemorySystem;
import util.ConsoleHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kirill on 05.05.17.
 */
class LsCommand implements Command {
    @Override
    public void execute(String value, InMemorySystem system) throws NotFoundException {
        List<TransferModel> toList = new ArrayList<>();
        for (Map.Entry<String, AbstractModel> pair : system.getCurrentModel().getNext().entrySet()) {
            toList.add(new TransferModel(pair.getKey(), pair.getValue().isDirectory(), pair.getValue().getSize()));
        }
        ConsoleHelper.printContent(toList);
    }
}