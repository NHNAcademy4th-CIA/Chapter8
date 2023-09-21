package org.nhnacademy.jungbum;

import java.math.BigInteger;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 3N+1
 */
public class Quiz2 {
    private static Logger logger = LoggerFactory.getLogger(Quiz2.class);
    private Scanner scanner = new Scanner(System.in);

    public Quiz2() {
        String value = "";
        logger.info("종료하고싶으면 0을 입력해주세요");
        while (!(value = scanner.next()).equals("0")) {
            new ThreeNPlusOne(value);

        }


    }
}

/***
 * 3N+1
 */
class ThreeNPlusOne {
    private Logger logger = LoggerFactory.getLogger(ThreeNPlusOne.class);

    private BigInteger value;

    private final BigInteger ONE = new BigInteger("1");
    private final BigInteger TWO = new BigInteger("2");

    private final BigInteger THREE = new BigInteger("3");

    /***
     * 3N+1의 생성자
     * @param value String 숫자값
     * @Exception  NumberFormatException 숫자가 아닌 다른 포맷값이 파라매터로 들어올 때.
     */
    public ThreeNPlusOne(String value) {
        try {
            this.value = new BigInteger(value);
        } catch (NumberFormatException e) {
            logger.info(e.toString());
        }
        printCount();
    }

    /***
     * 짝수인가요?
     * @return 짝수 = true
     */
    public boolean isEven() {
        if (value.mod(TWO).equals(ONE)) {
            return false;
        }
        return true;
    }

    /***
     * print 메소드.
     */
    public void printCount() {
        int count = 0;
        while (!value.equals(ONE)) {
            if (isEven()) {
                value = value.divide(TWO);
            } else {
                value = value.multiply(THREE).add(ONE);
            }
            count++;
        }
        logger.info("{}", count);
    }
}