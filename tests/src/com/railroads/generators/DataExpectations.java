package com.railroads.generators;

import java.util.*;

public class DataExpectations {

    static public ArrayList<ArrayList<String>> getExpectationArrayResult(String fileName) {
        HashMap expectedResults = new HashMap();
        String[][] invalidNumberResult = {{"InvalidNumberFormat"}, {"Yes"}};
        expectedResults.put("invalidNumber", invalidNumberResult);

        String[][] overThousandWagons = {{"No"}};
        expectedResults.put("overThousandWagons", overThousandWagons);

        String[][] thousandWagons = {{"Yes"}};
        expectedResults.put("thousandWagons", thousandWagons);

        String[][] wagonsInDescendingOrder = {
                {"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes"},
                {"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes"}
        };
        expectedResults.put("wagonsInDescendingOrder", wagonsInDescendingOrder);

        String[][] oneWagon = {{"Yes"}};
        expectedResults.put("oneWagon", oneWagon);

        String[][] numberOfWagonDoesNotMatch = {{"InvalidCountWagons", "InvalidCountWagons"}};
        expectedResults.put("numberOfWagonDoesNotMatch", numberOfWagonDoesNotMatch);

        String[][] charInFirstLineInBlock = {{"InvalidNumberFormat"}};
        expectedResults.put("charInFirstLineInBlock", charInFirstLineInBlock);

        String[][] notTextFile = {{"InvalidNumberFormat"}};
        expectedResults.put("notTextFile.mp3", notTextFile);

        String[][] outOfOrderWithNegativeResult = {{"No"}};
        expectedResults.put("outOfOrderWithNegativeResult", outOfOrderWithNegativeResult);

        String[][] wagonsPartiallyOutOfServiceWithPositiveResult = {{"Yes"}};
        expectedResults.put("wagonsPartiallyOutOfServiceWithPositiveResult", wagonsPartiallyOutOfServiceWithPositiveResult);

        String[][] identicalWagonsNumbers = {{"No"}};
        expectedResults.put("identicalWagonsNumbers", identicalWagonsNumbers);

        String[][] fileWithoutZeros = {{"InvalidNumberFormat"}};
        expectedResults.put("fileWithoutZeros", fileWithoutZeros);

        String[][] wagonsWithNegativeNumbers = {{"InvalidNumberFormat"}};
        expectedResults.put("wagonsWithNegativeNumbers", wagonsWithNegativeNumbers);


        return toArrayList((String[][]) expectedResults.get(fileName));
    }

    private static ArrayList<ArrayList<String>> toArrayList(String[][] list) {
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            arrayList.add(new ArrayList<>());
            for (int j = 0; j < list[i].length; j++) {
                arrayList.get(i).add(list[i][j]);
            }
        }
        return arrayList;
    }
}
