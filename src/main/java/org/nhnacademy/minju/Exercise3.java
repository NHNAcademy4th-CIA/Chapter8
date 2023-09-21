package org.nhnacademy.minju;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .입력값에 따라 로마 숫자와 아라비아 숫자를 출력합니다.
 */
public class Exercise3 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise3.class);
    private static final String numRegex = "[0-9]*";

    /**
     * .입력값을 정규표현식을 통해 숫자만인지, 문자만인지 판별하고
     * RomanNumeral 객체를 생성한다.
     */
    public static void exercise3() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.isBlank() || input.isEmpty()) {
                break;
            }
            try {
                if (Pattern.matches(numRegex, input)) {
                    RomanNumerals romanNumerals = new RomanNumerals(Integer.parseInt(input));
                    logger.info("{} to Roman numeral : {}", input, romanNumerals.toString());
                    continue;
                }
                RomanNumerals romanNumerals = new RomanNumerals(input);
                logger.info("{} to Arabic : {}", input, romanNumerals.toInt());
            } catch (NumberFormatException e) {
                logger.warn(e.getMessage());
            }
        }
    }
}

class RomanNumerals {
    private int number;
    private String roman;
    private LinkedHashMap<String, Integer> romanSet;

    private void initMap() {
        romanSet = new LinkedHashMap<>();
        romanSet.put("M", 1000);
        romanSet.put("CM", 900);
        romanSet.put("D", 500);
        romanSet.put("CD", 400);
        romanSet.put("C", 100);
        romanSet.put("XC", 90);
        romanSet.put("L", 50);
        romanSet.put("XL", 40);
        romanSet.put("X", 10);
        romanSet.put("IX", 9);
        romanSet.put("V", 5);
        romanSet.put("IV", 4);
        romanSet.put("I", 1);
    }

    RomanNumerals(String roman) {
        // roman to int
        // throw NumberFormatException
        initMap();
        if (!romanCheck(roman)) {
            throw new NumberFormatException("not a legal Roman numeral");
        }
        roman = roman.toUpperCase();
        // XIX = 19, 이진법처럼 읽기
        int total = 0;
        int j = 0;
        String[] splitRoman = roman.split("");
        while (j < roman.length()) {
            String str1 = splitRoman[j];
            j++;
            if (j == roman.length()) {
                total += romanSet.get(str1);
                break;
            } else {
                String str2 = splitRoman[j];
                if (romanSet.get(str1) < romanSet.get(str2) &&
                        romanSet.get(roman.substring(j - 1, j + 1)) == null) {
                    throw new NumberFormatException("not a legal Roman numeral");
                } else if (romanSet.get(str1) < romanSet.get(str2) &&
                        (romanSet.get(roman.substring(j - 1, j + 1)) != null)) {
                    total += romanSet.get(str1 + str2);
                    j++;
                    continue;
                }
                total += romanSet.get(str1);
            }
        }


        if (total > 3999) {
            throw new NumberFormatException("not a legal Roman numeral");
        }
        this.number = total;
    }

    RomanNumerals(int number) {
        initMap();
        if (number < 1 || number > 3999) {
            throw new NumberFormatException("int is outside the range 1 to 3999");
        }
        this.number = number;
    }

    /**
     * roman -> true
     *
     * @param roman input string
     * @return true or false
     */
    private boolean romanCheck(String roman) {
        if (roman.isEmpty() || roman.isBlank()) {
            return false;
        }
        String[] strSplit = roman.split("");
        int count = 0;
        for (int i = 0; i < roman.length(); i++) {
            if (count == 3) {
                return false;
            }
            if (i < roman.length() - 1 && strSplit[i].equals(strSplit[i + 1])) {
                count++;
                continue;
            }
            count = 0;
        }
        return true;
    }


    /**
     * @return string(roman numeral)
     */
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        int num = number;

        for (var entry : romanSet.entrySet()) {
            while (num >= entry.getValue()) {
                num -= entry.getValue();
                temp.append(entry.getKey());
            }
        }
        return temp.toString();
    }

    /**
     * @return value of roman numeral
     */
    public int toInt() {
        return number;
    }
}
