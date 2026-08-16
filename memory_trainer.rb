# memory_trainer.rb — Ruby версия

COLORS = {
  'red' => "\e[31mкрасный\e[0m",
  'blue' => "\e[34mсиний\e[0m",
  'green' => "\e[32mзелёный\e[0m",
  'yellow' => "\e[33mжёлтый\e[0m",
  'magenta' => "\e[35mфиолетовый\e[0m",
  'cyan' => "\e[36mголубой\e[0m",
  'white' => "\e[37mбелый\e[0m"
}
COLOR_NAMES = COLORS.keys

def clear_screen
  system('clear') || system('cls')
end

def play
  score = 0
  level = 1
  sequence = []

  loop do
    new_color = COLOR_NAMES.sample
    sequence << new_color

    clear_screen
    puts "🎨 Memory Trainer (Colors) (Ruby)"
    puts "Уровень #{level}: Запомните #{sequence.size} цветов!"
    print "Показываю: "
    sequence.each { |c| print COLORS[c] + " " }
    puts "\n" + "=" * 30
    sleep 1.5
    clear_screen

    puts "Введите #{sequence.size} цветов через пробел:"
    print "> "
    input = gets.chomp.strip.downcase
    user_colors = input.split

    if user_colors.size != sequence.size
      puts "\e[31m❌ Нужно ввести #{sequence.size} цветов. Попробуйте ещё раз.\e[0m"
      sleep 1
      next
    end

    correct = user_colors == sequence
    if correct
      puts "\e[32m✅ Правильно! +1 очко.\e[0m"
      score += 1
      level += 1
    else
      puts "\e[31m❌ Неправильно. Правильная последовательность: #{sequence.join(' ')}\e[0m"
      puts "Ваш счёт: #{score}"
      break
    end
    sleep 1
  end
  puts "\nИгра окончена! Счёт: #{score}"
end

play
