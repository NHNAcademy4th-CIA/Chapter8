package org.nhnacademy.lsj;

import java.math.BigInteger;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 빅 인티저를 이용한 3N+1 문제 풀이.
 */
public class Problem2 {

    private static final Logger logger = LoggerFactory.getLogger(Problem2.class);

    /**
     * 3N+1 문제풀이.
     */
    public static void problem2() {

        Scanner sc = new Scanner(System.in);

        BigInteger bigInteger;

        while (true) {

            try {
                bigInteger = new BigInteger(sc.nextLine());
                if (!bigInteger.equals(bigInteger.abs())) {
                    throw new IllegalArgumentException(); // numberformat Exception
                }
                break;
            } catch (IllegalArgumentException e) {
                logger.warn(e.getMessage());
            }
            logger.info("다시 입력해 주세요");

        }

        solve3N(bigInteger);

    }


    /**
     * 짝수면 N/2 , 홀수면 N*3+1.
     *
     * @param bigInteger 1이되는지 확인할 숫자.
     */
    public static void solve3N(BigInteger bigInteger) {

        logger.info("{}", bigInteger);

        if (bigInteger.equals(BigInteger.ONE)) {
            return;
        }

        if (bigInteger.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) {
            solve3N(bigInteger.divide(BigInteger.valueOf(2)));
        } else if (bigInteger.remainder(BigInteger.TWO).equals(BigInteger.ONE)) {
            solve3N(bigInteger.multiply(BigInteger.valueOf(3)).add(BigInteger.valueOf(1)));
        }

    }


}
