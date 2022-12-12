package vsu.cs.menshikovnik.cmd;

import vsu.cs.menshikovnik.logic.Solution;
import vsu.cs.menshikovnik.logic.Utils;

import java.io.IOException;

public class Main {

    public static void printHelp() {
        System.out.println("""
                Использование: java vsu.cs.menshikovnik.cmd.Main INPUT OUTPUT
                Ищет дружественные элементы, создает новый массив с количеством дружественных элементов на их местах. 
                Результат записывается в файл OUTPUT.
                """);
    }

    public static InputArgs parseCmdArgs(String[] args) throws CmdParseArgsError {
        if (args.length != 2) {
            printHelp();
            throw new CmdParseArgsError();
        }
        return new InputArgs(args[0], args[1]);
    }

    public static void main(String[] args) {
        String in1 = "tests/input1.txt";
        String out1 = "tests/output1.txt";

        InputArgs inputArgs = null;
        try {
            inputArgs = parseCmdArgs(args);
        } catch (CmdParseArgsError e) {
            printHelp();
            System.err.println("Ошибка разбора аргументов командной строки");
            System.exit(1);
        }

        int[][] sourceMatrix = new int[0][0];

        try {
            sourceMatrix = Utils.readIntMatrixFromFile(inputArgs.inputFile);
        } catch (IOException e) {
            System.err.printf("Ошибка при чтении исходного файла %s", e.toString());
            System.exit(2);
        }
        int[][] a = Solution.calc(sourceMatrix);

        try {
            Utils.writeIntMatrixToFile(inputArgs.outputFile, a);
        } catch (IOException e) {
            System.err.printf("Ошибка при записи массива в файл %s", e.toString());
            System.exit(3);
        }
    }
}