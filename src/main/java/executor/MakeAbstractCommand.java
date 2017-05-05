package executor;

import exception.AlreadyExistsException;
import model.Directory;
import model.File;
import system.InMemorySystem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kirill on 05.05.17.
 */
abstract class MakeAbstractCommand {
    static final Set<String> INCORRECT_NAMES = new HashSet<>(Arrays.asList("", ".", "..", "/", " ", "//", ","));
    void makeModel(String value, boolean isDirectory, InMemorySystem system) throws AlreadyExistsException {
        if (system.getCurrentModel().getNext().containsKey(value)) {
            throw new AlreadyExistsException("Файл или папка с таким именем уже существует!\n");
        } else if (INCORRECT_NAMES.contains(value) || value == null) {
            throw new IllegalArgumentException("Некорректное имя файла или папки!\n");
        } else {
            system.getCurrentModel().getNext().put(value, isDirectory ? new Directory(system.getCurrentModel()) : new File(system.getCurrentModel()));
        }
    }
}