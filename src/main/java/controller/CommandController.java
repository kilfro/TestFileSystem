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
                if (operation.equals("exit")) {
                    console.askExit();
                } else if (operation.equals("help")) {
                    console.printHelp(value);
                } else if (operation.equals("cd")) {
                    system.cd(value);
                } else if (operation.equals("mkdir")) {
                    system.mkdir(value);
                } else if (operation.equals("mkfile")) {
                    system.mkfile(value);
                } else if (operation.equals("ls")) {
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