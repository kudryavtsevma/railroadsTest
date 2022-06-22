package com.railroads.utils;

import com.railroads.dto.BlockTrains;
import com.railroads.exceptions.InvalidNumberFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class FileUtils {

    /**
     * Метод создания пустого файла
     *
     * @return Path to file
     */
    public static Path createFile() throws Exception {
        String pathInString = String.format("%s.txt", UUID.randomUUID());

        File output = new File(pathInString);
        if (output.createNewFile()) {
            return Paths.get(pathInString);
        } else {
            throw new Exception("The file already exists");
        }
    }

    /**
     * Метод записи корректного результата
     *
     * @return File
     */
    public static File writeInFile(Path path, ArrayList<String> results) throws IOException {
        Files.write(path, results, StandardCharsets.UTF_8);
        return path.toFile();
    }

    /**
     * Метод записи ошибочного результата
     *
     * @return File
     */
    public static File writeErrorInFile(Path path, String error) throws IOException {
        Files.write(path, Collections.singleton(error), StandardCharsets.UTF_8);
        return path.toFile();
    }

    /**
     * Метод чтения информации и проверки соответсвия формата данных
     * Возвращает список блоков с поездами
     *
     * @return ArrayList<BlockTrains>
     */
    public static ArrayList<BlockTrains> readFile(String path) throws Exception {
        ArrayList<BlockTrains> blockTrains = new ArrayList();

        FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        int countBlocks = getCountBlocks(path);

        for (int i = 0; i < countBlocks; i++) {
            int countWagons;
            String firstLineInBlock = bufferedReader.readLine();
            try {
                countWagons = Integer.parseInt(firstLineInBlock); // число вагонов
            } catch (Exception e) {
                throw new InvalidNumberFormat("The file is in the wrong format or there are not numbers in the file");
            }

            ArrayList<String> trains = new ArrayList();
            String line = bufferedReader.readLine();

            while (!line.equals("0")) { // считываем поезда из блока, пока не наткнемся на 0
                trains.add(line);

                line = bufferedReader.readLine();
            }
            blockTrains.add(new BlockTrains(countWagons, trains));
        }
        return blockTrains;
    }

    /**
     * Метод подсчета блоков в файле
     */
    private static int getCountBlocks(String path) throws IOException, InvalidNumberFormat {
        FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        int zeroCount = 0;
        while (line != null) {
            if (line.equals("0")) {
                zeroCount++;
            }
            line = bufferedReader.readLine();
        }
        if (zeroCount <= 1) throw new InvalidNumberFormat("Incorrect file format");
        return zeroCount - 1;
    }
}

