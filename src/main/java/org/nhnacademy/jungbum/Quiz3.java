package org.nhnacademy.jungbum;

import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 아라비안 숫자는 로마로 로마숫자는 아라비안숫자로
 */
public class Quiz3 {
    private Scanner scanner = new Scanner(System.in);

    private Logger logger = LoggerFactory.getLogger(Quiz3.class);

    public Quiz3() {
        logger.info("1~3999까지의 숫자만 입력해주세요. 혹은");
        logger.info("1~3999사이의 아라비안 숫자만 입력해주세요.");
        String line;
        while (!((line = scanner.next()) == "")) {
            new Roma(line);
        }
    }
}

/***
 * 로마 -> 아라비안
 * 아라비안 -> 로마
 */
class Roma {
    private Logger logger = LoggerFactory.getLogger(Roma.class);
    private int arabian;
    private String roma;
    /***
     *  M    1000            X   10
     * CM    900            IX   9
     * D     500            V    5
     * CD    400            IV   4
     * C     100            I    1
     * XC     90
     * L      50
     * XL     40
     */
    private final HashMap<Integer, String> charterTable;

    /***
     * 생성자
     * @param line 빈칸 입력전까지 무한으로 실행
     */
    public Roma(String line) {
        charterTable = new HashMap<>();
        tableInit();
        int num;

        if (!line.chars().allMatch(Character::isDigit)) {
            int arabian = toArabian(line);
            if (arabian == 0) {
                logger.warn("roma글자가 아닌 글자가 있습니다");
            }
            logger.info("{}", toInt());
            return;
        }
        num = Integer.parseInt(line);

        logger.info(toString());
    }

    /***
     * 테이블 초기화 메소드.
     */
    private void tableInit() {
        charterTable.put(1, "I");
        charterTable.put(4, "IV");
        charterTable.put(5, "V");
        charterTable.put(9, "IX");
        charterTable.put(10, "X");
        charterTable.put(40, "XL");
        charterTable.put(50, "L");
        charterTable.put(90, "XC");
        charterTable.put(100, "C");
        charterTable.put(400, "CD");
        charterTable.put(500, "D");
        charterTable.put(900, "CM");
        charterTable.put(1000, "M");

    }

    /***
     * 아라비안 숫자를 로마 숫자로 변경하는 메소드
     * @param num 아라비안 숫자
     * @return 로마 숫자
     */
    private String toRoma(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(thousand(num));
        num %= 1000;
        stringBuilder.append(thousandEtc(num, 100));
        num %= 100;
        stringBuilder.append(thousandEtc(num, 10));
        num %= 10;
        stringBuilder.append(thousandEtc(num, 1));
        roma = stringBuilder.toString();
        return stringBuilder.toString();
    }

    /***
     * 천의자리
     * @param num 아라비안숫자
     * @return 로마숫자
     */
    private String thousand(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        logger.info("{} {}", num, num / 1000
        );
        while (!(num / 1000 < 1)) {
            num -= 1000;
            stringBuilder.append("M");
        }
        return stringBuilder.toString();
    }

    /***
     * 천의 자리 외
     * @param num 아라비안 숫자
     * @param digit 자리수
     * @return 로마숫자
     */
    private String thousandEtc(int num, int digit) {
        StringBuilder stringBuilder = new StringBuilder();
        while (!(num / digit <= 0)) {
            if (num / digit == 9) {
                stringBuilder.append(charterTable.get(9 * digit));
                num -= digit * 9;
                continue;
            }
            if (num / digit >= 5) {
                stringBuilder.append(charterTable.get(5 * digit));
                num -= digit * 5;
                continue;
            }
            if (num / digit == 4) {
                stringBuilder.append(charterTable.get(4 * digit));
                num -= digit * 4;
                continue;
            }
            stringBuilder.append(charterTable.get(digit));
            num -= digit;

        }
        return stringBuilder.toString();
    }

    /***
     * 로마숫자를 아라비안숫자로 변경
     * @param value 로마숫자
     * @return 아라비안 숫자
     */
    private int toArabian(String value) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        for (int i =0; i < value.length(); i++) {
            logger.info("{}", value.charAt(i));
            stack.push(value.charAt(i));
        }
        while (!stack.isEmpty()) {
            int baseValue = serachTable(stack.pop());
            int afterValue;
            try {
                afterValue = serachTable(stack.peek());
            } catch (EmptyStackException e) {
                logger.warn("마지막 자리입니다");
                afterValue = baseValue;
            }
            logger.info("{} {}", baseValue, afterValue);
            if (baseValue == 0 || afterValue == 0) {
                return 0;
            }
            if (afterValue > baseValue) {
                stack.pop();
                answer += afterValue - baseValue;
                continue;
            }
            answer += baseValue;
        }
        arabian = answer;
        return answer;
    }

    /***
     * 값으로 테이블 키 조회
     * @param c 값
     * @return 찾은 키
     */
    private int serachTable(char c) {
        for (Map.Entry<Integer, String> mp : charterTable.entrySet()) {
            if (mp.getValue().equals(Character.toString(c))) {
                return mp.getKey();
            }
        }
        return 0;
    }

    public int toInt() {
        return arabian;
    }

    @Override
    public String toString() {
        return roma;
    }

}