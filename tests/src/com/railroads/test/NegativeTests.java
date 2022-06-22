package com.railroads.test;

import com.railroads.data.Const;
import com.railroads.generators.DataExpectations;
import com.railroads.utils.AppUtils;
import com.railroads.utils.Assert;
import com.railroads.utils.Test;

import java.util.ArrayList;

import static com.railroads.utils.AppUtils.runCommand;
import static com.railroads.TestRun.step;

public class NegativeTests {

    @Test("Количество вагонов в блоке не совпадают с первым числом в блоке")
    public static void theNumberOfWagonsDoesNotMatchTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "numberOfWagonDoesNotMatch", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Сравниваем результат с ожиданием");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("numberOfWagonDoesNotMatch"));
    }

    @Test("В первой строке блока не число")
    public static void charInFirstLineInBlockTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "charInFirstLineInBlock", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Сравниваем результат с ожиданием");
        ;
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("charInFirstLineInBlock"));
    }

    @Test("Номер вагона содержит не число")
    public static void notCorrectWagonNumberTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "invalidNumber", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что в результате есть ошибка о невалидном номере вагона");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("invalidNumber"));
    }

    @Test("Вагонов больше 1000")
    public static void overThousandWagonsTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "overThousandWagons", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что результат отрицательный");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("overThousandWagons"));
    }

    @Test("Не текстовый файл")
    public static void notTextFileTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "notTextFile.mp3", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что результат отрицательный");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("notTextFile.mp3"));
    }

    @Test("Одинаковые номера вагонов")
    public static void identicalWagonsNumbersTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "identicalWagonsNumbers", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что результат отрицательный");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("identicalWagonsNumbers"));
    }

    @Test("Файл без нулей")
    public static void fileWithoutZerosTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "fileWithoutZeros", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что результат отрицательный");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("fileWithoutZeros"));
    }

    @Test("Вагоны с отрицательными номерами")
    public static void wagonsWithNegativeNumbersTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "wagonsWithNegativeNumbers", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что результат отрицательный");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("wagonsWithNegativeNumbers"));
    }
}
