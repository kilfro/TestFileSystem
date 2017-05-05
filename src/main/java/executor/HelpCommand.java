package executor;

import exception.NotFoundException;
import system.InMemorySystem;

import static util.ConsoleHelper.printMessage;

/**
 * Created by kirill on 05.05.17.
 */
class HelpCommand implements Command {
    @Override
    public void execute(String value, InMemorySystem system) throws NotFoundException {
        if (value == null) {
            printMessage("Список всех команд:\n\nhelp [name]\nexit\ncd dir\nls\nmkdir name\nmkfile name\n");
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
                    printMessage(value + " dir - переход к выбраной директории по абсолютному или отностительному пути.\n");
                    printMessage(value + " / - переход к корневой директории.\n");
                    break;
                case "ls":
                    printMessage(value + " - содержимое директории.\n");
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
}