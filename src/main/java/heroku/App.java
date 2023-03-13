package heroku;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to trigonometric calculation." + System.lineSeparator() +
                "Enter in console \"help\" for help or \"exit\" for exit from the program.");
        String in;
        while (true)  {
            System.out.println(System.lineSeparator() + "Enter function:");
            in = new Scanner(System.in).nextLine();
            if (in.trim().equals("exit")) {
                break;
            }
            if (in.trim().equals("help")) {
                System.out.println("******** help ********");
                System.out.println("Available functions:" + System.lineSeparator() +
                        "sin, cos, tan, ctan, sec, cosec, asin, acos, atan, sinh, cosh, tanh" +
                        System.lineSeparator() + System.lineSeparator() +
                        "If your value is in degrees, type 'deg' in the end" + System.lineSeparator() +
                        "e.g. 'sin 9.5' 'cos(20.5)' 'tan 90 deg' 'ctan(45) deg'");
                System.out.println("******** help ********" + System.lineSeparator());
                continue;
            }
            try {
                double value = Double.parseDouble(in
                        .replaceAll(",+", ".")
                        .replaceAll("[^.0-9]", " "));
                if (in.trim().toLowerCase().endsWith("deg")) {
                    value = Math.toRadians(value);
                }
                if (in.toLowerCase().startsWith("sin")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), Math.sin(value));
                } else if (in.toLowerCase().startsWith("sec")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), 1 / Math.cos(value));
                } else if (in.toLowerCase().startsWith("cos")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), Math.cos(value));
                } else if (in.toLowerCase().startsWith("cosec")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), 1 / Math.sin(value));
                } else if (in.toLowerCase().startsWith("tan")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), Math.tan(value));
                } else if (in.toLowerCase().startsWith("ctan")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), 1 / Math.tan(value));
                } else if (in.toLowerCase().startsWith("asin")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), Math.asin(value));
                } else if (in.toLowerCase().startsWith("acos")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), Math.acos(value));
                } else if (in.toLowerCase().startsWith("atan")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), Math.atan(value));
                } else if (in.toLowerCase().startsWith("sinh")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), Math.sinh(value));
                } else if (in.toLowerCase().startsWith("cosh")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), Math.cosh(value));
                } else if (in.toLowerCase().startsWith("tanh")) {
                    System.out.printf("= %.9f" + System.lineSeparator(), Math.tanh(value));
                } else {
                    System.out.println(System.lineSeparator() + "Error: unknown function" + System.lineSeparator());
                }
            } catch (NumberFormatException e) {
                System.out.println(System.lineSeparator() + "Error: incorrect values" + System.lineSeparator());
            }
        }
    }
}
