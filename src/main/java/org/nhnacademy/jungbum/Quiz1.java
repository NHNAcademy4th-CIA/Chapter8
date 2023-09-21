package org.nhnacademy.jungbum;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 2차 방정식 계산에서 Try Catch사용하기.
 */
public class Quiz1 {
    private static Logger logger = LoggerFactory.getLogger(Quiz1.class);

    /***
     * 이차 방정식의 두 근 중 더 큰 값을 반환합니다.
     * A*x*x + B*x + C = 0(근이 있는 경우). A == 0 또는
     * 판별식 B*B - 4*A*C가 음수인 경우 예외
     * IllegalArgumentException 유형이 발생합니다.
     * @param A 공식의 A
     * @param B 공식의 B
     * @param C 공식의 C
     * @return (- B + Math.sqrt ( disc)) / (2*A)
     * @throws IllegalArgumentException A==0 Or (B*B - 4*A*C )<0
     */
    public static double root(double A, double B, double C)
            throws IllegalArgumentException {
        if (A == 0) {
            throw new IllegalArgumentException("A can't be zero.");
        } else {
            double disc = B * B - 4 * A * C;
            if (disc < 0)
                throw new IllegalArgumentException("Discriminant < zero.");
            logger.info("{}", (-B + Math.sqrt(disc)) / (2 * A));
            return (-B + Math.sqrt(disc)) / (2 * A);
        }
    }

    /***
     * 퀴즈 생성
     */
    public Quiz1() {
        Scanner scanner = new Scanner(System.in);
        do {
            logger.info("a자리 값을 입력하세요.");
            double a = scanner.nextDouble();
            logger.info("b자리 값을 입력하세요.");
            double b = scanner.nextDouble();
            logger.info("c자리 값을 입력하세요.");
            double c = scanner.nextDouble();
            try {
                root(a, b, c);
            } catch (IllegalArgumentException e) {
                logger.info("{}", e.toString());
            }
            logger.info("계속 하시겠습니까? 중단 하고싶다면 no, 아니라면 그 외를 입력해주세요.");
        } while (!scanner.next().equalsIgnoreCase("no"));
    }
}
