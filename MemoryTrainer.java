// MemoryTrainer.java — Java версия

import java.util.*;
import java.util.concurrent.TimeUnit;

public class MemoryTrainer {
    private static final Map<String, String> COLORS = new LinkedHashMap<>();
    static {
        COLORS.put("red", "\u001B[31mкрасный\u001B[0m");
        COLORS.put("blue", "\u001B[34mсиний\u001B[0m");
        COLORS.put("green", "\u001B[32mзелёный\u001B[0m");
        COLORS.put("yellow", "\u001B[33mжёлтый\u001B[0m");
        COLORS.put("magenta", "\u001B[35mфиолетовый\u001B[0m");
        COLORS.put("cyan", "\u001B[36mголубой\u001B[0m");
        COLORS.put("white", "\u001B[37mбелый\u001B[0m");
    }
    private static final List<String> COLOR_NAMES = new ArrayList<>(COLORS.keySet());
    private static final Scanner scanner = new Scanner(System.in);

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int score = 0;
        int level = 1;
        List<String> sequence = new ArrayList<>();

        while (true) {
            String newColor = COLOR_NAMES.get(rand.nextInt(COLOR_NAMES.size()));
            sequence.add(newColor);

            clearScreen();
            System.out.println("🎨 Memory Trainer (Colors) (Java)");
            System.out.printf("Уровень %d: Запомните %d цветов!\n", level, sequence.size());
            System.out.print("Показываю: ");
            for (String c : sequence) {
                System.out.print(COLORS.get(c) + " ");
            }
            System.out.println("\n" + "=".repeat(30));
            sleep(1500);
            clearScreen();

            System.out.printf("Введите %d цветов через пробел:\n", sequence.size());
            String input = scanner.nextLine().trim().toLowerCase();
            String[] userColors = input.split("\\s+");

            if (userColors.length != sequence.size()) {
                System.out.printf("\u001B[31m❌ Нужно ввести %d цветов. Попробуйте ещё раз.\u001B[0m\n", sequence.size());
                sleep(1000);
                continue;
            }

            boolean correct = true;
            for (int i = 0; i < sequence.size(); i++) {
                if (!userColors[i].equals(sequence.get(i))) {
                    correct = false;
                    break;
                }
            }

            if (correct) {
                System.out.println("\u001B[32m✅ Правильно! +1 очко.\u001B[0m");
                score++;
                level++;
            } else {
                System.out.printf("\u001B[31m❌ Неправильно. Правильная последовательность: %s\u001B[0m\n", String.join(" ", sequence));
                System.out.printf("Ваш счёт: %d\n", score);
                break;
            }
            sleep(1000);
        }
        System.out.printf("\nИгра окончена! Счёт: %d\n", score);
        scanner.close();
    }
}
