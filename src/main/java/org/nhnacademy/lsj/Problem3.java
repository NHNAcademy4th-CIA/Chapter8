package org.nhnacademy.lsj;

import java.util.Scanner;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 생성자 2개 필요 , int(아라비아수) , String(로마자)가 올때 .
 * int -> String , String -> int 해주는 프로그램.
 */
public class Problem3 {

    private static final Logger logger = LoggerFactory.getLogger(Problem3.class);

    /**
     * 프로그램 시작.
     */
    public static void problem3() {


        Scanner sc = new Scanner(System.in);

        String number = sc.nextLine();

        RomanNumerals romanNumerals;

        if (Character.isDigit(number.charAt(0))) { // 아라비아 -> 로마자
            romanNumerals = new RomanNumerals(Integer.parseInt(number));
            logger.info("{}", romanNumerals);
        } else { // 로마자 -> 아라비아
            romanNumerals = new RomanNumerals(number);
            logger.info("{}", romanNumerals.toInt());
        }


    }


}

/**
 * 로마자 를 저장하는 enum 클래스 .
 */
enum RomanNumber {


    M(1000),

    CM(900),


    D(500),

    CD(400),

    C(100),

    XC(90),


    L(50),

    XL(40),

    X(10),

    IX(9),

    V(5),

    IV(4),
    I(1);


    // 인트로 받거나 , 문자열로 받거나

    // 문자열로 받으면 그냥 로마자 자체
    // 인트로 받으면 로마자로 바꿔줘야 함

    private int value;

    RomanNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}

/**
 * 로마자와 아라비아 숫자 두개를 갖고있는 클래스.
 */
class RomanNumerals {

    private static final Logger logger = LoggerFactory.getLogger(RomanNumerals.class);

    private int value;


    private String stringValue;

    RomanNumerals(int value) { // 아라비아로 받았을떄 -> 로마자로
        if (!(value >= 1 && value <= 3999)) { // 범위초과
            logger.info("잘못된 입력입니다.");
            throw new IllegalArgumentException();
        }

        this.value = value;

        stringValue = "";


        while (value > 0) {
            for (RomanNumber num : RomanNumber.values()) {
                if (value >= num.getValue()) {
                    this.stringValue += num.name();
                    value -= num.getValue();
                    break;
                }
            }
        }


    }

    RomanNumerals(String value) { // 로마자로 받았을때

        Stack<Integer> st = new Stack<>();

        String isVaidate = value;


        for (RomanNumber n : RomanNumber.values()) {
            isVaidate = isVaidate.replace(n.name().charAt(0), ' ');
        }

        isVaidate = isVaidate.trim();

        if (!isVaidate.isEmpty()) {
            logger.warn("입력이 잘못됐습니다.");
            throw new IllegalArgumentException();
        }

        this.stringValue = value;

        for (int i = 0; i < value.length(); i++) {

            int number = RomanNumber.valueOf(String.valueOf(value.charAt(i))).getValue();

            if (st.isEmpty()) {
                st.push(number);
            } else {
                if (st.peek() < number) {
                    st.push(number - st.pop());
                    continue;
                }
                st.push(number);
            }

        }

        int sum = 0;
        for (Integer n : st) {
            sum += n;
        }

        this.value = sum;

    }


    @Override
    public String toString() {
        return this.stringValue;
    }

    public int toInt() {
        return this.value;
    }

}
