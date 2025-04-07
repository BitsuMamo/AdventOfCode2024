package DayFour;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
    public static void main(String[] args){
        String input;
        try {
            input = Files.readString(Path.of("src", "main", "java", "DayFour", "testinput.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int cols = (int)(input.length()/input.lines().count());

        List<char[]> chars = new ArrayList<>();
        input.lines().forEach(l -> {
            chars.add(l.toCharArray());
        });


        System.out.println(chars.stream().map(Arrays::toString).toList().toString());

        List<List<Character>> colChars = new ArrayList<>();
        List<List<Character>> diagChars = new ArrayList<>();

        for(int i = 0; i < chars.getFirst().length; i++){
            colChars.add(new ArrayList<>());
        }

        diagChars.add(new ArrayList<>());
        diagChars.add(new ArrayList<>());

        for(int i = 0; i < chars.size(); i ++){
            for(int j = 0; j < chars.getFirst().length; j++){
                if(i==j){
                    diagChars.get(0).add(chars.get(i)[j]);
                }
                if((i + j) == (chars.size() - 1)){
                    diagChars.get(1).add(chars.get(i)[j]);
                }
                colChars.get(j).add(chars.get(i)[j]);
            }
        }

        long count = 0;
        for (char[] aChar : chars) {

            StringBuilder sb = new StringBuilder();
            for (char c : aChar) {
                sb.append(c);
            }
            String out = sb.toString();

            Pattern pattern = Pattern.compile("XMAS|SAMX");
            Matcher matcher = pattern.matcher(out);
            count += matcher.results().count();
        }

        for(List<Character> cChar: colChars){
            StringBuilder sb = new StringBuilder();
            for (char c : cChar) {
                sb.append(c);
            }
            String out = sb.toString();
            Pattern pattern = Pattern.compile("XMAS|SAMX");
            Matcher matcher = pattern.matcher(out);
            count += matcher.results().count();
        }
//
//        for(List<Character> dChar: diagChars){
//            StringBuilder sb = new StringBuilder();
//            for (char c : dChar) {
//                sb.append(c);
//            }
//            String out = sb.toString();
//            Pattern pattern = Pattern.compile("XMAS|SAMX");
//            Matcher matcher = pattern.matcher(out);
//            count += matcher.results().count();
//        }

        System.out.println(count);

    }
}