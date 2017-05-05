package util;

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

    public static void printMessage(String message) {
        System.out.print(message);
    }

    public static String readString(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printContent(List<TransferModel> ls) {
        printMessage(String.format("%-3s|%-20s|%-6s\n", "Тип", "Имя", "Размер"));
        for (TransferModel model : ls) {
            printMessage(String.format("%s\t%-21.21s\t%s\n",
                    model.isDirectory() ? "d" : "f",
                    model.getName(),
                    model.getSize() == 0 ? "" : model.getSize()));
        }
    }
}