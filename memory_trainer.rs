// memory_trainer.rs — Rust версия

use rand::seq::SliceRandom;
use std::collections::HashMap;
use std::io::{self, Write};
use std::thread;
use std::time::Duration;

fn main() {
    let mut colors = HashMap::new();
    colors.insert("red", "\x1b[31mкрасный\x1b[0m");
    colors.insert("blue", "\x1b[34mсиний\x1b[0m");
    colors.insert("green", "\x1b[32mзелёный\x1b[0m");
    colors.insert("yellow", "\x1b[33mжёлтый\x1b[0m");
    colors.insert("magenta", "\x1b[35mфиолетовый\x1b[0m");
    colors.insert("cyan", "\x1b[36mголубой\x1b[0m");
    colors.insert("white", "\x1b[37mбелый\x1b[0m");
    let color_names: Vec<&str> = colors.keys().cloned().collect();

    let mut rng = rand::thread_rng();
    let mut score = 0;
    let mut level = 1;
    let mut sequence: Vec<&str> = Vec::new();

    loop {
        let new_color = color_names.choose(&mut rng).unwrap();
        sequence.push(new_color);

        // Очистка экрана
        print!("\x1B[2J\x1B[1;1H");
        io::stdout().flush().unwrap();

        println!("🎨 Memory Trainer (Colors) (Rust)");
        println!("Уровень {}: Запомните {} цветов!", level, sequence.len());
        print!("Показываю: ");
        for c in &sequence {
            print!("{} ", colors.get(c).unwrap());
        }
        println!("\n{}", "=".repeat(30));
        thread::sleep(Duration::from_millis(1500));

        print!("\x1B[2J\x1B[1;1H");
        io::stdout().flush().unwrap();

        println!("Введите {} цветов через пробел:", sequence.len());
        print!("> ");
        io::stdout().flush().unwrap();
        let mut input = String::new();
        io::stdin().read_line(&mut input).unwrap();
        let user_colors: Vec<&str> = input.trim().split_whitespace().collect();

        if user_colors.len() != sequence.len() {
            println!("\x1b[31m❌ Нужно ввести {} цветов. Попробуйте ещё раз.\x1b[0m", sequence.len());
            thread::sleep(Duration::from_millis(1000));
            continue;
        }

        let mut correct = true;
        for i in 0..sequence.len() {
            if user_colors[i] != sequence[i] {
                correct = false;
                break;
            }
        }

        if correct {
            println!("\x1b[32m✅ Правильно! +1 очко.\x1b[0m");
            score += 1;
            level += 1;
        } else {
            println!("\x1b[31m❌ Неправильно. Правильная последовательность: {}\x1b[0m", sequence.join(" "));
            println!("Ваш счёт: {}", score);
            break;
        }
        thread::sleep(Duration::from_millis(1000));
    }
    println!("\nИгра окончена! Счёт: {}", score);
}
