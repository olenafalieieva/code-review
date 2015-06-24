package ua.goit.alg;

import java.io.InputStream;
import java.util.Scanner;

public class DivideNumbers {

    public static void main(String[] args) {
        longDiv(System.in);
    }

    public static void longDiv(InputStream stream) {
        System.out.println("Enter two integers through whitespace:");
        Scanner sc = new Scanner(stream);
        int dividend = sc.nextInt(); 
        int first = dividend;
        int divisor = sc.nextInt();
        int second = divisor;
        sc.close();
        
        boolean isPoint = false;
        StringBuilder divProcess = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int accuracy = 5;
        int decimal = 0;

        if (divisor == 0) {
            throw new IllegalArgumentException("Error: Divide by zero!");
        }

        int signDetector = dividend * divisor;
        if (signDetector < 0) {
            result.append("-");
        } 

        if (Math.abs(dividend) < Math.abs(divisor)) {
            result.append("0.");
            isPoint = true;
        }

        int shift = 0;
        while ((dividend != 0) && (decimal < accuracy)) {
            if (Math.abs(dividend) > Math.abs(divisor)) {
                dividend = doNextDividend(dividend, divisor, divProcess, shift, result); 
                decimal++;
                shift++;
            }
            if (Math.abs(dividend) < Math.abs(divisor)) {
                if (dividend != 0) {
                    if (!isPoint) {
                        result.append(".");
                        isPoint = true;
                    }
                    dividend *= 10;
                    divProcess.append(addShift(shift));
                    divProcess.append(dividend + "\n");
                    dividend = doNextDividend(dividend, divisor, divProcess, shift, result); 
                }
                decimal++;
                shift++;
            }
        }

        System.out.print(" " + first + "|" + second + "\n");
        System.out.println(addShift(shift) + "|" + result);
        System.out.println(divProcess);
    }

    private static int doNextDividend(int dividend, int divisor,
            StringBuilder divProcess, int shift, StringBuilder result) {
        divProcess.append(addShift(shift));
        if (dividend < 0) {
            divProcess.append("+" + (-1)*(dividend/divisor*divisor) + "\n");
        } else {
            divProcess.append("-" + dividend/divisor*divisor + "\n");
        }
        result.append(Math.abs(dividend/divisor));
        return dividend = dividend - dividend/divisor*divisor;
    }

    private static StringBuilder addShift(int shift) {
        StringBuilder toShift = new StringBuilder();
        for (int i = 0; i <= shift; i++) {
            toShift.append(" ");
        }
        return toShift;
    }
}