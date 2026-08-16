

### 1. `memory_trainer.py` (Python)

```python
# memory_trainer.py — Python версия

import random
import sys
import time
import os
from colorama import init, Fore, Style

init(autoreset=True)

COLORS = {
    'red': Fore.RED + 'красный' + Style.RESET_ALL,
    'blue': Fore.BLUE + 'синий' + Style.RESET_ALL,
    'green': Fore.GREEN + 'зелёный' + Style.RESET_ALL,
    'yellow': Fore.YELLOW + 'жёлтый' + Style.RESET_ALL,
    'magenta': Fore.MAGENTA + 'фиолетовый' + Style.RESET_ALL,
    'cyan': Fore.CYAN + 'голубой' + Style.RESET_ALL,
    'white': Fore.WHITE + 'белый' + Style.RESET_ALL
}
COLOR_NAMES = list(COLORS.keys())

def clear_screen():
    os.system('cls' if os.name == 'nt' else 'clear')

def get_user_input(prompt):
    return input(prompt).strip().lower().split()

def play_game():
    score = 0
    level = 1
    sequence = []

    while True:
        # Генерируем новый цвет для последовательности
        seq_len = level + 2  # начинаем с 3 цветов
        new_color = random.choice(COLOR_NAMES)
        sequence.append(new_color)

        # Показываем последовательность
        clear_screen()
        print(f"🎨 Memory Trainer (Colors) (Python)")
        print(f"Уровень {level}: Запомните {len(sequence)} цветов!")
        print("Показываю: ", end="")
        for c in sequence:
            print(COLORS[c], end=" ")
        print("\n" + "=" * 30)
        time.sleep(1.5)
        clear_screen()

        # Запрашиваем ввод
        print(f"Введите {len(sequence)} цветов через пробел:")
        user_input = get_user_input("> ")

        # Проверяем
        if len(user_input) != len(sequence):
            print(Fore.RED + f"❌ Нужно ввести {len(sequence)} цветов. Попробуйте ещё раз.")
            time.sleep(1)
            continue

        # Сравниваем (игнорируем регистр)
        correct = True
        for i, expected in enumerate(sequence):
            if user_input[i] != expected:
                correct = False
                break

        if correct:
            print(Fore.GREEN + f"✅ Правильно! +1 очко.")
            score += 1
            level += 1
        else:
            print(Fore.RED + f"❌ Неправильно. Правильная последовательность: " + " ".join(sequence))
            print(f"Ваш счёт: {score}")
            break

        # Небольшая пауза
        time.sleep(1)

    print(f"\nИгра окончена! Счёт: {score}")

def main():
    try:
        play_game()
    except KeyboardInterrupt:
        print("\nВыход...")

if __name__ == "__main__":
    main()
