// memory_trainer.cs — C# версия

using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;

class MemoryTrainer
{
    static Dictionary<string, string> colors = new Dictionary<string, string>()
    {
        {"red", "\u001B[31mкрасный\u001B[0m"},
        {"blue", "\u001B[34mсиний\u001B[0m"},
        {"green", "\u001B[32mзелёный\u001B[0m"},
        {"yellow", "\u001B[33mжёлтый\u001B[0m"},
        {"magenta", "\u001B[35mфиолетовый\u001B[0m"},
        {"cyan", "\u001B[36mголубой\u001B[0m"},
        {"white", "\u001B[37mбелый\u001B[0m"}
    };
    static List<string> colorNames = colors.Keys.ToList();

    static void ClearScreen()
    {
        Console.Clear();
    }

    static void Sleep(int ms)
    {
        Thread.Sleep(ms);
    }

    public static void Main()
    {
        Random rand = new Random();
        int score = 0;
        int level = 1;
        List<string> sequence = new List<string>();

        while (true)
        {
            string newColor = colorNames[rand.Next(colorNames.Count)];
            sequence.Add(newColor);

            ClearScreen();
            Console.WriteLine("🎨 Memory Trainer (Colors) (C#)");
            Console.WriteLine($"Уровень {level}: Запомните {sequence.Count} цветов!");
            Console.Write("Показываю: ");
            foreach (var c in sequence)
                Console.Write(colors[c] + " ");
            Console.WriteLine("\n" + new string('=', 30));
            Sleep(1500);
            ClearScreen();

            Console.WriteLine($"Введите {sequence.Count} цветов через пробел:");
            string input = Console.ReadLine().Trim().ToLower();
            string[] userColors = input.Split(new[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);

            if (userColors.Length != sequence.Count)
            {
                Console.WriteLine($"\u001B[31m❌ Нужно ввести {sequence.Count} цветов. Попробуйте ещё раз.\u001B[0m");
                Sleep(1000);
                continue;
            }

            bool correct = true;
            for (int i = 0; i < sequence.Count; i++)
            {
                if (userColors[i] != sequence[i]) { correct = false; break; }
            }

            if (correct)
            {
                Console.WriteLine("\u001B[32m✅ Правильно! +1 очко.\u001B[0m");
                score++;
                level++;
            }
            else
            {
                Console.WriteLine($"\u001B[31m❌ Неправильно. Правильная последовательность: {string.Join(" ", sequence)}\u001B[0m");
                Console.WriteLine($"Ваш счёт: {score}");
                break;
            }
            Sleep(1000);
        }
        Console.WriteLine($"\nИгра окончена! Счёт: {score}");
    }
}
