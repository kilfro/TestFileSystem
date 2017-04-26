package system;

import exception.AlreadyExistsException;
import exception.NotFoundException;
import model.TransferModel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kirill on 22.04.17.
 */
public interface SystemInterface {
    Set<String> INCORRECT_NAMES = new HashSet<>(Arrays.asList("", ".", "..", "/", " ", "//", ","));

    boolean mkdir(String value) throws AlreadyExistsException;

    boolean mkfile(String value) throws AlreadyExistsException;

    boolean cd(String value) throws NotFoundException;

    String pwd();

    List<TransferModel> ls();
}