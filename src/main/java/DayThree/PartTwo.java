package DayThree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {
    public static void main(String[] args) {
        String input;
        try {
            input = Files.readString(Path.of("src", "main", "java", "DayThree", "input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        input = input.strip();
        int res = 0;

        var matcher = getMatches(input);
        var doIndexes = getDoIndexes(input);
        var dontIndexes = getDontIndexes(input);

        List<Pair> all = new ArrayList<>();
        all.addAll(matcher);
        all.addAll(dontIndexes);
        all.addAll(doIndexes);

        all.sort(Comparator.comparing(Pair::value));

        boolean mul = true;
        for(int i = 0; i < all.size(); i++) {
            Pair pair = all.get(i);
            if(pair.key.equals("do")){
                mul = true;
                continue;
            }
            if(pair.key.equals("dont")){
                mul = false;
                continue;
            }

            if(mul){
                res += multiplier(pair.key);
            }
        }

        System.out.println(res);

    }

    public static List<Pair> getDoIndexes(String input){
        Pattern doPattern = Pattern.compile("do\\(\\)",Pattern.MULTILINE);
        Matcher doMatcher = doPattern.matcher(input);


        List<Pair> doIndexes = new ArrayList<>();
        while (doMatcher.find()) {
            doIndexes.add(new Pair(doMatcher.start(), "do"));
        }

        return doIndexes;
    }

    public static List<Pair> getDontIndexes(String input){
        Pattern dontPattern = Pattern.compile("don't\\(\\)",Pattern.MULTILINE);
        Matcher dontMatcher = dontPattern.matcher(input);

        List<Pair> dontIndexes = new ArrayList<>();
        while (dontMatcher.find()) {
            dontIndexes.add(new Pair(dontMatcher.start(), "dont"));
        }

        return dontIndexes;
    }

    public static List<Pair> getMatches(String input){
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);

        List<Pair> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(new Pair(matcher.start(), matcher.group()));
        }

        return matches;
    }

    public static Integer multiplier(String match){
        match = match.substring(4, match.length() - 1);
        System.out.println(match);
        var nums = match.split(",");
        return Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
    }

    public  record Pair(Integer value, String key){

    }

}
