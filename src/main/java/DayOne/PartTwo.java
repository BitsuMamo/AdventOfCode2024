package DayOne;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartTwo {
    public static void main(String[] args) {
        String input;
        try {
            input = Files.readString(Path.of("src", "main", "java", "DayOne", "input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Integer> firstList = new ArrayList<>();
        Map<Integer, Integer> replication = new HashMap<>();

        for(String line : input.split("\n")) {
            line = line.strip();
            var in = line.split("   ");
            Integer firstNumber = Integer.parseInt(in[0]);
            Integer secondNumber = Integer.parseInt(in[1]);
            firstList.add(firstNumber);

            if (replication.containsKey(secondNumber)) {
                replication.put(secondNumber, replication.get(secondNumber) + 1);
            } else {
                replication.put(secondNumber, 1);
            }
        }
        int res = 0;

        for(int i = 0; i < firstList.size(); i++){
            res += firstList.get(i) * replication.getOrDefault(firstList.get(i), 0);
        }

        System.out.println(res);
    }
}
