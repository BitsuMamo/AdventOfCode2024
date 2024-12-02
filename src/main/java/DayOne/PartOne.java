package DayOne;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PartOne {
    public static void main(String[] args) {
        String input;
        try {
            input = Files.readString(Path.of("src", "main", "java", "DayOne", "input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        for(String line : input.split("\n")){
            line = line.strip();
            var in = line.split("   ");
            firstList.add(Integer.parseInt(in[0]));
            secondList.add(Integer.parseInt(in[1]));
        }

        firstList.sort(Integer::compareTo);
        secondList.sort(Integer::compareTo);

        int res = 0;

        for(int i = 0; i < firstList.size(); i++){
            res += Math.abs(firstList.get(i) - secondList.get(i));
        }

        System.out.println(res);
    }
}
