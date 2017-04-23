package util;

import exception.InterruptOperationException;
import model.TransferModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by kirill on 21.04.17.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void printMessage(String message) {
        System.out.print(message);
    }

    public String readString(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printHelp(String value) {
        if (value == null) {
            printMessage("Список всех команд:\n\nhelp [name]\nexit\ncd dir\nls\nmkdir name\nmkfile name\npwd\n");
        } else {
            switch (value) {
                case "help":
                    printMessage(value + " - список всех команд.\n");
                    printMessage(value + " name - подсказка о команде.\n");
                    break;
                case "exit":
                    printMessage(value + " - выход из программы.\n");
                    break;
                case "cd":
                    printMessage(value + " dir - переход к выбраной директории.\n");
                    printMessage(value + " / - переход к корневой директории.\n");
                    printMessage(value + " .. - переход к директории на уровень выше.\n");
                    break;
                case "ls":
                    printMessage(value + " - содержимое директории.\n");
                    break;
                case "pwd":
                    printMessage(value + " - отображение текущей директории.\n");
                    break;
                case "mkdir":
                    printMessage(value + " name - создание директории с указаным именем.\n");
                    break;
                case "mkfile":
                    printMessage(value + " name - создание файла с указаным именем.\n");
                    break;
                default:
                    throw new IllegalArgumentException("Неверная команда!\nДля получения справки набери help.\n");
            }
        }
    }

    public void askExit() throws InterruptOperationException {
        printMessage("Действительно хотите выйти? (yes / no): ");
        if ("yes".equals(readString().toLowerCase())) {
            throw new InterruptOperationException("До свидания!\n");
        }
    }

    public void printContent(List<TransferModel> ls) {
        String format = "%s\t%-20.20s\t%s\n";
        for (TransferModel model : ls) {
            printMessage(String.format(format, model.isDirectory() ? "d" : "f", model.getName(), model.getSize() == 0 ? "" : model.getSize()));
        }
    }
}