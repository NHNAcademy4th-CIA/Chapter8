package org.nhnacademy.minju;

import java.math.BigInteger;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .write a program that prints 3N+1 sequences with starting values specified by the user
 * catch NumberFormatException if input is not a legal Integer
 * check input number is greater than zero
 * just output error message
 */
public class Exercise2 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise2.class);

    /**
     * .빈 줄이나 빈 칸이 입력되면 프로그램을 종료합니다.
     * 입력값을 BigInteger형으로 변환해 매개변수로 넘깁니다.
     * exception을 받으면 catch로 메세지 출력 후 다시 입력 받습니다.
     */
    public static void exercise2() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.isEmpty() || input.isBlank()) {
                break;
            }

            try {
                BigInteger bigInteger = new BigInteger(input);
                logger.info("count = {}", sequence3N_1(bigInteger));
            } catch (ArithmeticException | IllegalArgumentException e) {
                logger.warn("warn : {}", e.getMessage());
            }
        }
    }

    /**
     * .3N+1 sequence
     *
     * @param N BigInteger
     * @return 사용자의 입력 N이 legal이면 수열의 항 수를 출력합니다.
     */
    private static int sequence3N_1(BigInteger N) {
        int count = 0;
        if (N.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("smaller than 0s");
        }
        if (N.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new ArithmeticException("bigger than max value");
        }
        while (N.compareTo(BigInteger.ONE) != 0) {
            if (!N.testBit(0)) {  // If N is even...
                N = N.divide(BigInteger.TWO);
            } else {
                N = N.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE);
            }
            count++;
        }
        return count;
    }

}
