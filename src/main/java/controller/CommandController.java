package controller;

import exception.InterruptOperationException;
import system.InMemorySystem;
import system.SystemInterface;

import util.ConsoleHelper;

/**
 * Created by kirill on 21.04.17.
 */
public class CommandController {
    private SystemInterface system = new InMemorySystem();
    private ConsoleHelper console = new ConsoleHelper();

    public void runSystem() {
        while (true) {
            try {
                console.printMessage(system.pwd() + ": ");
                String[] args = console.readString().split(" ");
                String operation = args[0];
                String value;
                try {
                    value = args[1];
                } catch (IndexOutOfBoundsException e) {
                    value = "";
                }
                if ("exit".equals(operation)) {
                    console.askExit();
                } else if ("help".equals(operation)) {
                    console.printHelp(value);
                } else if ("cd".equals(operation)) {
                    system.cd(value);
                } else if ("mkdir".equals(operation)) {
                    system.mkdir(value);
                } else if ("mkfile".equals(operation)) {
                    system.mkfile(value);
                } else if ("ls".equals(operation)) {
                    console.printContent(system.ls());
                } else {
                    console.printMessage("Неверная команда!\n");
                }
            } catch (InterruptOperationException e) {
                console.printMessage(e.getMessage());
                break;
            } catch (Exception e) {
                console.printMessage(e.getMessage());
            }
        }
    }
}