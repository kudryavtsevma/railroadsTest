package com.railroads;

import com.railroads.dto.BlockTrains;
import com.railroads.exceptions.InvalidCountWagons;
import com.railroads.exceptions.InvalidNumberFormat;
import com.railroads.utils.FileUtils;
import com.railroads.utils.Logging;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;

public class Railroads {
    private final File input;

    public Railroads(File input) {
        this.input = input;
    }

    /**
     * Метод получения результата
     *
     * @return Path to file
     */
    public Path getResult() throws Exception {
        // Создаем файл для результата
        Logging.log(Level.INFO, "Creating a result file");

        Path resultFile = FileUtils.createFile();

        ArrayList<BlockTrains> dataFromFile;

        // читаем файл и кладем данные в  объект 'BlockTrains' и формируем список блоков
        Logging.log(Level.INFO, String.format("Try to reading data from file %s and save info in object", input.getPath()));
        try {
            dataFromFile = FileUtils.readFile(input.getPath());

            Logging.log(Level.INFO, "File successfully read and saved in object");
        } catch (Exception e) {
            Logging.log(Level.SEVERE, String.format("Error reading information from file %s", e));

            FileUtils.writeErrorInFile(resultFile, e.getClass().getSimpleName()); // если ловим исключение, записываем тип ошибки в файл для результата
            System.out.println("RESULT FILE: " + resultFile);
            return resultFile;
        }

        // список с результатами
        ArrayList<String> results = new ArrayList<>();

        int blockNumber = 1;
        Logging.log(Level.INFO, "Starting checking rolling stock");

        for (BlockTrains blockTrains : dataFromFile) {

            for (int j = 0; j < blockTrains.trains.size(); j++) {

                Logging.log(Level.INFO, String.format("Checking the result of %s train %s from blocks", j + 1, blockNumber));

                // проверяем, что количество вагонов подходит по условию
                if (blockTrains.countWagons > 1000) {
                    results.add("No");
                } else {
                    try {
                        // проверяем возможность переставить вагоны и сохраняем результат
                        results.add(
                                checkReorganizing(getIntArrayWagons(blockTrains.trains.get(j)), blockTrains.countWagons)
                        );
                    } catch (Exception e) {
                        // если поймали какую либо ошибку, то записываем ее тип вместо результата
                        results.add(e.getClass().getSimpleName());
                    }
                    if (j == blockTrains.trains.size() - 1) results.add("");
                }
            }
            blockNumber++;
        }
        Logging.log(Level.INFO, "Verification completed");

        Logging.log(Level.INFO, "Saving the results to a file");

        FileUtils.writeInFile(resultFile, results);

        Logging.log(Level.INFO, "Information successfully saved to file: " + resultFile);
        System.out.println("RESULT FILE: " + resultFile);

        Thread.sleep(500);
        return resultFile;
    }

    /**
     * Метод, который проверяет, возможно ли перевормировать подвижной состав  в правильном порядке
     *
     * @return Yes or No
     */
    private static String checkReorganizing(ArrayList<Integer> wagons, int numberOfCount) throws InvalidCountWagons {
        if (wagons.size() > 1000) return "No"; // по условию, вагонов должно быть не больше 1000
        else if (wagons.size() != numberOfCount)
            throw new InvalidCountWagons("The number of wagons does not match the first digit in the block");

        int next = getMaxNumberFromArray(wagons); // Ищем вагон с самым большим номером, он долен идти первым
        boolean isOk = true;
        Stack<Integer> station = new Stack<>();

        for (int wagon : wagons) {
            if (wagon <= 0) return "InvalidNumberFormat"; // если вагон с отрицательным номером, записываем ошибку в файл
            else if (wagon == next) {               // если нашли максимальный вагон
                next--;                          // то он идет на станцию затем на второй путь, ищем следующий после него
                if (station.size() != 0) {       // если на станции есть вагоны,
                    if (station.peek() == next) {  // то проверяем, возможно наверху стека находится следующий искомый вагон
                        station.pop();             // если да, то переводим его на второй путь
                        next--;
                    }
                }
            } else station.push(wagon); // Если вагон не максимальный, то отправляем его на станцию
        }

        // когда закончились все вагоны на первом пути, то перебираем вагоны в стеке(на станции)
        for (int i = 1; i <= station.size(); i++) {
            if (station.peek() == next) { // если нашли следующий вагон, то удаляем его из стека и ищем следующий
                station.pop();
                next--;
            } else isOk = false;  //иначе результат отрицательный, вагоны невозможно переформировать в нужном порядке
        }
        return (isOk) ? "Yes" : "No";
    }


    /**
     * Метод возвращает числовой массив вагонов
     */
    private static ArrayList<Integer> getIntArrayWagons(String trainInString) throws InvalidNumberFormat {
        ArrayList<Integer> arrayWagons = new ArrayList<>();

        String[] arrayWagonsInString = trainInString.split(" ");

        try {
            for (String wagon : arrayWagonsInString) {
                arrayWagons.add(Integer.parseInt(wagon));
            }
        } catch (Exception e) {
            throw new InvalidNumberFormat("The train has a car with an incorrect number");
        }
        return arrayWagons;
    }

    private static int getMaxNumberFromArray(ArrayList<Integer> arrayWagons) {
        int max = arrayWagons.get(0);

        for (int item : arrayWagons) {
            if (item > max) max = item;
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        String path = args[0];
        Railroads app = new Railroads(new File(path));
        app.getResult();
    }
}
