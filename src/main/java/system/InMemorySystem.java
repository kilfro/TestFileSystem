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
    private static final Set<String> INCORRECT_NAMES = new HashSet<>(Arrays.asList("", ".", "..", "/", " ", "//", ","));
    private final AbstractModel root = new Directory(null);
    private AbstractModel currentModel = root;
    private StringBuilder pwd = new StringBuilder();

    private void makeModel(String value, boolean isDirectory) throws AlreadyExistsException {
        if (currentModel.getNext().containsKey(value)) {
            throw new AlreadyExistsException("Файл с таким именем уже существует!\n");
        } else if (INCORRECT_NAMES.contains(value)) {
            throw new IllegalArgumentException("Некорректное имя файла!\n");
        } else {
            currentModel.getNext().put(value, isDirectory ? new Directory(currentModel) : new File(currentModel));
        }
    }

    @Override
    public boolean mkdir(String value) throws AlreadyExistsException {
        if (value.contains("//") || "/".equals(value)) {
            throw new IllegalArgumentException("Некорректное имя файла!\n");
        }
        AbstractModel model = currentModel;
        String[] values = value.split("/");
        int start = 0;
        if ("".equals(values[0])) {
            start = 1;
            currentModel = root;
        }
        for (; start < values.length; start++) {
            String name = values[start];
            try {
                makeModel(name, true);
            } catch (AlreadyExistsException e) {
                if (start == values.length - 1) {
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
        if (value.contains("//") || "/".equals(value)) {
            throw new IllegalArgumentException("Некорректное имя файла!\n");
        }
        AbstractModel model = currentModel;
        String[] values = value.split("/");
        int start = 0;
        if ("".equals(values[0])) {
            start = 1;
            currentModel = root;
        }
        for (; start < values.length; start++) {
            String name = values[start];
            if (start == values.length - 1) {
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
        if ("/".equals(value)) {
            pwd.delete(0, pwd.length());
            currentModel = root;
        } else if ("".equals(value)) {
            throw new NotFoundException("Путь не найден!\n");
        } else {
            if ("..".equals(value)) {
                if (currentModel.equals(root)) {
                    throw new NotFoundException("Путь не найден!\n");
                } else {
                    pwd.delete(pwd.lastIndexOf("/"), pwd.length());
                    currentModel = currentModel.getPrevious();
                }
            } else {
                AbstractModel model = currentModel;
                for (String name : value.split("/")) {
                    if ("".equals(name)) {
                        pwd.delete(0, pwd.length());
                        model = root;
                    } else {
                        pwd.append("/").append(name);
                        model = model.getNext().get(name);
                    }
                }
                if (model == null || model.isFile()) {
                    throw new NotFoundException("Путь не найден!\n");
                } else {
                    currentModel = model;
                }
            }
        }
        return true;
    }

    @Override
    public String pwd() {
        if (currentModel.equals(root)) {
            return "/";
        } else {
            return pwd.toString();
        }
    }

    @Override
    public List<TransferModel> ls() {
        List<TransferModel> toList = new ArrayList<>();
        for (Map.Entry<String, AbstractModel> pair : currentModel.getNext().entrySet()) {
            toList.add(new TransferModel(pair.getKey(), pair.getValue().isDirectory(), pair.getValue().getSize()));
        }
        return toList;
    }

    public AbstractModel getRoot() {
        return root;
    }
}