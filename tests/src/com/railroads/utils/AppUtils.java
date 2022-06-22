package com.railroads.utils;

import java.io.*;
import java.util.ArrayList;

public class AppUtils {

    /**
     * Считывает информацию из файла и заворачивает все в массив
     */
    public static ArrayList<ArrayList<String>> getObjectFromFile(String path) throws Exception {
        ArrayList<ArrayList<String>> results = new ArrayList<>();

        FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        int countBlocks = 0;

        while (line != null) {
            if (line.length() == 0) break;
            results.add(new ArrayList<>());
            int countResults = 0;
            while (line.length() != 0) {
                results.get(countBlocks).add(line);
                line = bufferedReader.readLine();
                countResults++;
                if (line == null) break;
            }
            countBlocks++;
            line = bufferedReader.readLine();

        }
        return results;
    }

    /**
     * Метод, с помощью которого запускаем shell-скрипт
     */
    public static String runCommand(final String command) {
        OutputStream out = null;
        InputStream in = null;
        try {
            Process child = Runtime.getRuntime().exec(command);
            // Выходной и входной потоки
            out = child.getOutputStream();
            in = child.getInputStream();

            //Входной поток может что-нибудь вернуть
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            String result = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;

            if (result.contains("RESULT FILE: ")) {
                return result.replace("RESULT FILE: ", "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return command;
    }
}
