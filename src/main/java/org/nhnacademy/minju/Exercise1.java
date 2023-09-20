package org.nhnacademy.minju;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .allow the user to specify values for A, B, and C
 * if an error occurs, your program should catch that error and print an error message.
 * The program should continue until the user answers no.
 */
public class Exercise1 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise1.class);

    /**
     * .run root
     */
    public static void exercise1() {
        Scanner scanner = new Scanner(System.in);
        double n1, n2, n3;
        String input;

        try{
            do {
                logger.info("Enter equation");
                logger.info("A = ");
                n1 = scanner.nextDouble();
                logger.info("B = ");
                n2 = scanner.nextDouble();
                logger.info("C = ");
                n3 = scanner.nextDouble();

                root(n1, n2, n3);

                logger.info("Want to Enter another equation?");
                input = scanner.nextLine();
            } while (!input.equals("no"));
        } catch (InputMismatchException e) {
            logger.warn("equation input should be type double");
        }
    }

    /**
     * Returns the larger of the two roots of the quadratic equation
     * A*x*x + B*x + C = 0, provided it has any roots.  If A == 0 or
     * if the discriminant, B*B - 4*A*C, is negative, then an exception
     * of type IllegalArgumentException is thrown.
     */
    static public double root(double A, double B, double C)
            throws IllegalArgumentException {
        if (A == 0) {
            throw new IllegalArgumentException("A can't be zero.");
        } else {
            double disc = B * B - 4 * A * C;
            if (disc < 0) {
                throw new IllegalArgumentException("Discriminant < zero.");
            }
            return (-B + Math.sqrt(disc)) / (2 * A);
        }
    }
}
