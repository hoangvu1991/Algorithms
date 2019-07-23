package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import java.util.Scanner;

public class PassBanking {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String s = sc.next();
            int score = 0;
            int count_digit = 0;
            boolean hasAlphabet = false;
            boolean hasDigit = false;
            boolean hasSymbol = false;

            for (char ch: s.toCharArray()) {
                if (Character.isLowerCase(ch)) { // lowercase alphabet
                    score += ch - 'a' + 1;
                    hasAlphabet = true;
                }
                else if (Character.isUpperCase(ch)) { // uppercase alphabet
                    score += ch - 'A' + 1;
                    hasAlphabet = true;
                }
                else if (Character.isDigit(ch)) { // digit
                    score += (ch - '0') * (1 << count_digit);
                    count_digit++;
                    hasDigit = true;
                }
                else { // symbol
                    score += 10;
                    hasSymbol = true;
                }
            }
            if (hasAlphabet && hasDigit && hasSymbol) {
                System.out.println(score >= 100 ? "strong" : "normal");
            }
            else {
                System.out.println("weak");
            }
        }
    }
}

/*
- stdin
big-o12345
@tu1den9
toikhongbiet
-  stdout
strong
normal
weak
*/
