package system;

import exception.AlreadyExistsException;
import exception.NotFoundException;
import model.AbstractModel;
import model.Directory;
import model.File;
import model.TransferModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by kirill on 22.04.17.
 */
public class InMemorySystem implements SystemInterface {
    private static final List<String> INCORRECT_NAMES = Arrays.asList("", ".", "..", "/", " ");
    private final AbstractModel root = new Directory(null);
    private AbstractModel currentModel = root;
    private StringBuilder pwd = new StringBuilder();

    @Override
    public void mkdir(String value) throws AlreadyExistsException {
        if (currentModel.getNext().containsKey(value)) {
            throw new AlreadyExistsException("Папка с таким именем уже существует!\n");
        } else if (INCORRECT_NAMES.contains(value)) {
            throw new IllegalArgumentException("Некорректное имя папки!\n");
        } else {
            currentModel.getNext().put(value, new Directory(currentModel));
        }
    }

    @Override
    public void mkfile(String value) throws AlreadyExistsException {
        if (currentModel.getNext().containsKey(value)) {
            throw new AlreadyExistsException("Файл с таким именем уже существует!\n");
        } else if (INCORRECT_NAMES.contains(value)) {
            throw new IllegalArgumentException("Некорректное имя файла!\n");
        } else {
            currentModel.getNext().put(value, new File(currentModel));
        }
    }

    @Override
    public void cd(String value) throws NotFoundException {
        if ("/".equals(value)) {
            pwd.delete(0, pwd.length());
            currentModel = root;
        } else if ("".equals(value)) {
            throw new NotFoundException("Путь не найден!\n");
        } else {
            if ("..".equals(value)) {
                if (currentModel == root) {
                    throw new NotFoundException("Путь не найден!\n");
                } else {
                    pwd.delete(pwd.lastIndexOf("/"), pwd.length());
                    currentModel = currentModel.getPrevious();
                }
            } else {
                AbstractModel model = currentModel;
                for (String name : value.split("/")) {
                    if (name.equals("")) {
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
    }

    @Override
    public String pwd() {
        if (currentModel == root) {
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