<?php
// memory_trainer.php — PHP версия

$COLORS = [
    'red' => "\033[31mкрасный\033[0m",
    'blue' => "\033[34mсиний\033[0m",
    'green' => "\033[32mзелёный\033[0m",
    'yellow' => "\033[33mжёлтый\033[0m",
    'magenta' => "\033[35mфиолетовый\033[0m",
    'cyan' => "\033[36mголубой\033[0m",
    'white' => "\033[37mбелый\033[0m"
];
$COLOR_NAMES = array_keys($COLORS);

function clear_screen() {
    echo "\033[2J\033[1;1H";
}

function play_game() {
    global $COLORS, $COLOR_NAMES;
    $score = 0;
    $level = 1;
    $sequence = [];

    while (true) {
        $new_color = $COLOR_NAMES[array_rand($COLOR_NAMES)];
        $sequence[] = $new_color;

        clear_screen();
        echo "🎨 Memory Trainer (Colors) (PHP)\n";
        echo "Уровень $level: Запомните " . count($sequence) . " цветов!\n";
        echo "Показываю: ";
        foreach ($sequence as $c) {
            echo $COLORS[$c] . " ";
        }
        echo "\n" . str_repeat("=", 30) . "\n";
        usleep(1500000);
        clear_screen();

        echo "Введите " . count($sequence) . " цветов через пробел:\n";
        echo "> ";
        $input = trim(fgets(STDIN));
        $user_colors = explode(' ', strtolower($input));

        if (count($user_colors) != count($sequence)) {
            echo "\033[31m❌ Нужно ввести " . count($sequence) . " цветов. Попробуйте ещё раз.\033[0m\n";
            usleep(1000000);
            continue;
        }

        $correct = true;
        for ($i = 0; $i < count($sequence); $i++) {
            if ($user_colors[$i] != $sequence[$i]) {
                $correct = false;
                break;
            }
        }

        if ($correct) {
            echo "\033[32m✅ Правильно! +1 очко.\033[0m\n";
            $score++;
            $level++;
        } else {
            echo "\033[31m❌ Неправильно. Правильная последовательность: " . implode(' ', $sequence) . "\033[0m\n";
            echo "Ваш счёт: $score\n";
            break;
        }
        usleep(1000000);
    }
    echo "\nИгра окончена! Счёт: $score\n";
}

play_game();
?>
