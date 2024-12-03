package DayTwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartTwo {
    public static void main(String[] args) {
        String input;
        try {
            input = Files.readString(Path.of("src", "main", "java", "DayTwo", "input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<List<Integer>> levels = new ArrayList<>();
        for(String line : input.split("\n")) {
            List<Integer> intLine = new ArrayList<>();
            for(String num: line.strip().split(" ")){
                intLine.add(Integer.parseInt(num));
            }
            levels.add(intLine);
        }

        int res = 0;
        for(List<Integer> level : levels) {
            System.out.println(isValidSequence(level));
            System.out.println(isProperJump(level));
            if(isValidSequence(level) && isProperJump(level)) {
                res++;
            }else{
                if(canBeSafe(level)) {
                    res ++;
                }
            }
        }

        System.out.println(res);
    }

    private static boolean canBeSafe(List<Integer> level) {
        for(int i = 0; i < level.size(); i++){
            List<Integer> newLevel = new ArrayList<>();
            int newIdx = 0;
            for(int j = 0; j < level.size(); j++){
                if(j != i){
                    newLevel.add(level.get(j));
                }
            }
            if(isValidSequence(newLevel) && isProperJump(newLevel)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidSequence(List<Integer> report){
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for(int i = 0; i < report.size() - 1; i++){
            if(report.get(i) > report.get(i + 1)){
                isIncreasing = false;
            }
            if(report.get(i) < report.get(i + 1)){
                isDecreasing = false;
            }
        }

        return isIncreasing || isDecreasing;
    }

    public static boolean isProperJump(List<Integer> report){
        for(int i = 0; i < report.size() - 1; i++){
            int diff = Math.abs(report.get(i) - report.get(i + 1));
            if( (diff> 3) || (diff < 1)){
                return false;
            }
        }
        return true;
    }
}
