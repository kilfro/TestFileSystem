package system;

import exception.AlreadyExistsException;
import exception.NotFoundException;
import model.AbstractModel;
import model.Directory;
import model.File;
import model.TransferModel;

import java.util.*;

/**
 * Created by kirill on 22.04.17.
 */
public class InMemorySystem implements SystemInterface {
    private static final AbstractModel ROOT = new Directory(null);
    private AbstractModel currentModel = ROOT;
    private StringBuilder pwd = new StringBuilder();

    @Override
    public boolean mkdir(String value) throws AlreadyExistsException {
        if (value == null || INCORRECT_NAMES.contains(value)) {
            throw new IllegalArgumentException("Некорректное имя файла!\n");
        }
        AbstractModel model = currentModel;
        currentModel = ROOT;
        String[] values = value.split("/");
        for (int i = 0; i < values.length; i++) {
            String name = values[i];
            try {
                makeModel(name, true);
            } catch (AlreadyExistsException e) {
                if (i == values.length - 1) {
                    throw e;
                }
            }
            currentModel = currentModel.getNext().get(name);
        }
        currentModel = model;
        return true;
    }

    @Override
    public boolean mkfile(String value) throws AlreadyExistsException {
        if (value == null || INCORRECT_NAMES.contains(value)) {
            throw new IllegalArgumentException("Некорректное имя файла!\n");
        }
        AbstractModel model = currentModel;
        currentModel = ROOT;
        String[] values = value.split("/");
        for (int i = 0; i < values.length; i++) {
            String name = values[i];
            if (i == values.length - 1) {
                makeModel(name, false);
            } else {
                try {
                    makeModel(name, true);
                } catch (AlreadyExistsException e) {

                }
                currentModel = currentModel.getNext().get(name);
            }
        }
        currentModel = model;
        return true;
    }

    @Override
    public boolean cd(String value) throws NotFoundException {
        if (value == null) {
            throw new NotFoundException("Путь не найден!\n");
        } else if ("/".equals(value)) {
            currentModel = ROOT;
            pwd.delete(0, pwd.length());
        } else {
            AbstractModel bufModel = currentModel;
            StringBuilder bufPwd = pwd;
            currentModel = ROOT;
            pwd.delete(0, pwd.length());
            for (String name : value.split("/")) {
                currentModel = currentModel.getNext().get(name);
                if (currentModel == null || currentModel.isFile()) {
                    currentModel = bufModel;
                    pwd = bufPwd;
                    throw new NotFoundException("Путь не найден!\n");
                }
                pwd.append("/").append(name);
            }
        }
        return true;
    }

    @Override
    public String pwd() {
        return pwd.toString();
    }

    @Override
    public List<TransferModel> ls() {
        List<TransferModel> toList = new ArrayList<>();
        for (Map.Entry<String, AbstractModel> pair : currentModel.getNext().entrySet()) {
            toList.add(new TransferModel(pair.getKey(), pair.getValue().isDirectory(), pair.getValue().getSize()));
        }
        return toList;
    }

    private void makeModel(String value, boolean isDirectory) throws AlreadyExistsException {
        if (currentModel.getNext().containsKey(value)) {
            throw new AlreadyExistsException("Файл или папка с таким именем уже существует!\n");
        } else if (INCORRECT_NAMES.contains(value) || value == null) {
            throw new IllegalArgumentException("Некорректное имя файла или папки!\n");
        } else {
            currentModel.getNext().put(value, isDirectory ? new Directory(currentModel) : new File(currentModel));
        }
    }

    AbstractModel getRoot() {
        return ROOT;
    }
}