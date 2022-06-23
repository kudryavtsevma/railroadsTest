package com.railroads.test;

import com.railroads.data.Const;
import com.railroads.generators.DataExpectations;
import com.railroads.utils.Assert;
import com.railroads.utils.AppUtils;

import com.railroads.utils.Test;

import java.util.ArrayList;

import static com.railroads.utils.AppUtils.runCommand;
import static com.railroads.TestRun.step;


public class PositiveTests {

    @Test("Вагонов ровно 1000")
    public static void thousandWagonsTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "thousandWagons", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что результат положительный");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("thousandWagons"));
    }

    @Test("Вагоны в порядке убывания")
    public static void wagonsInDescendingOrderTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "wagonsInDescendingOrder", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что результат положительный");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("wagonsInDescendingOrder"));
    }

    @Test("Поезд состоящий из 1-го вагона")
    public static void oneWagonTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "oneWagon", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что результат положительный");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("oneWagon"));
    }

    @Test("Вагоны не попорядку с отрицательным результатом")
    public static void wagonsOutOfOrderWithNegativeResultTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "outOfOrderWithNegativeResult", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что результат отрицательный");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("outOfOrderWithNegativeResult"));
    }

    @Test("Вагоны частично не попорядку с положительным результатом")
    public static void wagonsPartiallyOutOfServiceWithPositiveResultTest() throws Exception {
        step("Выполняем команду для запуска программы");
        String file = runCommand(String.format("sh startApp.sh %s" + "wagonsPartiallyOutOfServiceWithPositiveResult", Const.basePath));

        step("Информацию из файла заворачиваем в массив");
        ArrayList<ArrayList<String>> arrayResults = AppUtils.getObjectFromFile(file);

        step("Проверяем, что результат положительный");
        Assert.assertEquals(arrayResults, DataExpectations.getExpectationArrayResult("wagonsPartiallyOutOfServiceWithPositiveResult"));
    }
}

