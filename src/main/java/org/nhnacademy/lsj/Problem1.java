package org.nhnacademy.lsj;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 이차방정식의 해를 구하는 프로그램 .
 */
public class Problem1 {


    private static final Logger logger = LoggerFactory.getLogger(Problem1.class);

    private static final Scanner sc = new Scanner(System.in);

    /**
     * 이차방정식의 해를 구함.
     */
    public static void problem1() {


        while (true) {
            try {
                logger.info("이차방정식의 계수를 입력해주세요");
                double result = root(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
                logger.info("방정식의 해는 {}", result);
            } catch (IllegalArgumentException e) {
                logger.warn(e.getMessage());
                logger.info("다시 입력해 주세요");
                continue;
            }


            logger.info("새로운 방정식의 해를 구하겠습니까 Press:Y or N");

            if (!isReTry()) {
                break;
            }
        }
    }

    /**
     * 해를 구하는 프로그램의 재시작 여부를 확인함.
     *
     * @return true ,false.
     */
    public static boolean isReTry() {
        String retry = sc.nextLine(); // 버퍼제

        try {
            if (!retry.equals("Y") && !retry.equals("N")) {
                logger.warn("잘못된 입력입니다\n다시 입력해 주세요");
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            isReTry();
        }

        if (retry.equals("Y")) {
            return true;
        }
        return false;
    }


    /**
     * 방정식의 해를 구함.
     *
     * @param number1 x^2의 계수.
     * @param number2 x의 계수.
     * @param number3 상수항.
     * @return 방정식의 해
     * @throws IllegalArgumentException 잘못된 입력에 예외처리.
     */
    public static double root(double number1, double number2, double number3) throws IllegalArgumentException {

        sc.nextLine();

        if (number1 == 0) {
            throw new IllegalArgumentException("A can't be zero.");
        } else {
            double disc = number2 * number2 - 4 * number1 * number3;
            if (disc < 0) {
                throw new IllegalArgumentException("Discriminant < zero.");
            }
            return (-number2 + Math.sqrt(disc)) / (2 * number1);
        }
    }

}
