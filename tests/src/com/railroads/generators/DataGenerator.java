package com.railroads.generators;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;


/**
 * нигде не используется
 * здесь можно сгенерировать файлы с большим количеством блоков, поездов и вагонов
 */
public class DataGenerator {

    public static Path generateFile(
            int countBlocks,
            int countTrains,
            int countWagonsInTrain,
            boolean isAscending
    ) throws Exception {
        String pathInString = String.format("Test-%s", UUID.randomUUID());

        File output = new File(pathInString);
        if (output.createNewFile()) {
            ArrayList<ArrayList<String>> results = new ArrayList<>();

            for (int b = 1; b <= countBlocks; b++) {
                ArrayList<String> block = new ArrayList<>();
                block.add(Integer.toString(countWagonsInTrain));

                if (isAscending) {
                    for (int t = 1; t <= countTrains; t++) {
                        String wagons = "";
                        for (int w = 1; w <= countWagonsInTrain; w++) {
                            wagons += w + " ";
                        }
                        block.add(wagons);
                    }
                } else {
                    int count = countWagonsInTrain;
                    for (int t = 1; t <= countTrains; t++) {
                        String wagons = "";
                        for (; count > 0; count--) {
                            wagons += count + " ";
                        }
                        block.add(wagons);
                        count = countWagonsInTrain;
                    }
                }

                block.add("0");
                if (b == countBlocks) block.add("0");
                results.add(block);
            }
            for (ArrayList<String> block : results) {
                for (String line : block) {
                    Files.write(Path.of(pathInString), Collections.singleton(line), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                }
            }


            return Paths.get(pathInString);
        } else {
            throw new Exception("The file already exists");
        }
    }
}
