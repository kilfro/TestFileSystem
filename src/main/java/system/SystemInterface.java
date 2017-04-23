package system;

import exception.AlreadyExistsException;
import exception.NotFoundException;
import model.AbstractModel;
import model.TransferModel;

import java.util.List;

/**
 * Created by kirill on 22.04.17.
 */
public interface SystemInterface {
    void mkdir(String value) throws AlreadyExistsException;

    void mkfile(String value) throws AlreadyExistsException;

    void cd(String value) throws NotFoundException;

    String pwd();

    List<TransferModel> ls();

    AbstractModel getRoot();
}