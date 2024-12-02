package DayTwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PartOne {
    public static void main(String[] args) {
        String input;
        try {
            input = Files.readString(Path.of("src", "main", "java", "DayTwo", "input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        int res = 0;
        for(String line: input.split("\n")) {
            line = line.strip();
            var in = line.split(" ");
            int prev = 0;
            boolean isSafe = true;
            for(int i = 0; i < in.length - 1; i++) {
                Integer firstNum = Integer.parseInt(in[i]);
                Integer secondNum = Integer.parseInt(in[i + 1]);

                // Check if the difference in greater than 3
                if (Math.abs(firstNum - secondNum) > 3) {
                    isSafe = false;
                    break;
                }

                if( i == 0){
                    prev = firstNum - secondNum;
                    continue;
                }
                // Check if there is a change from strictly increasing to decreasing or vice versa
                int cur = firstNum - secondNum;
                if((prev >= 0 && cur <= 0) || (prev <= 0 && cur >= 0)) {
                    isSafe = false;
                    break;
                }
            }
            if(isSafe) {
                res++;
            }
        }
        System.out.println(res);
    }
}
