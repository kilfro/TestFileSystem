package executor;

import system.InMemorySystem;
import util.ConsoleHelper;

/**
 * Created by kirill on 05.05.17.
 */
class HelpCommand implements Command {
    @Override
    public void execute(String value, InMemorySystem system) throws IllegalArgumentException {
        if (value == null) {
            ConsoleHelper.printMessage("Список всех команд:\n\nhelp [name]\nexit\ncd dir\nls\nmkdir name\nmkfile name\n");
        } else {
            switch (value) {
                case "help":
                    ConsoleHelper.printMessage(value + " - список всех команд.\n");
                    ConsoleHelper.printMessage(value + " name - подсказка о команде.\n");
                    break;
                case "exit":
                    ConsoleHelper.printMessage(value + " - выход из программы.\n");
                    break;
                case "cd":
                    ConsoleHelper.printMessage(value + " dir - переход к выбраной директории по абсолютному или отностительному пути.\n");
                    ConsoleHelper.printMessage(value + " / - переход к корневой директории.\n");
                    break;
                case "ls":
                    ConsoleHelper.printMessage(value + " - содержимое директории.\n");
                    break;
                case "mkdir":
                    ConsoleHelper.printMessage(value + " name - создание директории с указаным именем.\n");
                    break;
                case "mkfile":
                    ConsoleHelper.printMessage(value + " name - создание файла с указаным именем.\n");
                    break;
                default:
                    throw new IllegalArgumentException("Неверная команда!\nДля получения справки набери help.\n");
            }
        }
    }
}