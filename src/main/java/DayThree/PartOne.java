package DayThree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
    public static void main(String[] args) {
        String input;
        try {
            input = Files.readString(Path.of("src", "main", "java", "DayThree", "input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        input = input.strip();

        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);

        int res = 0;

        while (matcher.find()) {
            String match = matcher.group();
            match = match.substring(4, match.length() - 1);
            var nums = match.split(",");
            res += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
        }

        System.out.println(res);
    }
}
