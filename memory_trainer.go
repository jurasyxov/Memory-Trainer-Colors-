// memory_trainer.go — Go версия

package main

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"os/exec"
	"runtime"
	"strings"
	"time"
)

var colors = map[string]string{
	"red":     "\x1b[31mкрасный\x1b[0m",
	"blue":    "\x1b[34mсиний\x1b[0m",
	"green":   "\x1b[32mзелёный\x1b[0m",
	"yellow":  "\x1b[33mжёлтый\x1b[0m",
	"magenta": "\x1b[35mфиолетовый\x1b[0m",
	"cyan":    "\x1b[36mголубой\x1b[0m",
	"white":   "\x1b[37mбелый\x1b[0m",
}
var colorNames = []string{"red", "blue", "green", "yellow", "magenta", "cyan", "white"}

func clearScreen() {
	cmd := exec.Command("clear")
	if runtime.GOOS == "windows" {
		cmd = exec.Command("cls")
	}
	cmd.Stdout = os.Stdout
	cmd.Run()
}

func main() {
	rand.Seed(time.Now().UnixNano())
	reader := bufio.NewReader(os.Stdin)
	score := 0
	level := 1
	sequence := []string{}

	for {
		seqLen := level + 2
		newColor := colorNames[rand.Intn(len(colorNames))]
		sequence = append(sequence, newColor)

		clearScreen()
		fmt.Println("🎨 Memory Trainer (Colors) (Go)")
		fmt.Printf("Уровень %d: Запомните %d цветов!\n", level, len(sequence))
		fmt.Print("Показываю: ")
		for _, c := range sequence {
			fmt.Print(colors[c] + " ")
		}
		fmt.Println("\n" + strings.Repeat("=", 30))
		time.Sleep(1500 * time.Millisecond)
		clearScreen()

		fmt.Printf("Введите %d цветов через пробел:\n", len(sequence))
		input, _ := reader.ReadString('\n')
		input = strings.TrimSpace(input)
		userColors := strings.Fields(input)

		if len(userColors) != len(sequence) {
			fmt.Println("\x1b[31m❌ Нужно ввести", len(sequence), "цветов. Попробуйте ещё раз.\x1b[0m")
			time.Sleep(1 * time.Second)
			continue
		}

		correct := true
		for i, exp := range sequence {
			if userColors[i] != exp {
				correct = false
				break
			}
		}

		if correct {
			fmt.Println("\x1b[32m✅ Правильно! +1 очко.\x1b[0m")
			score++
			level++
		} else {
			fmt.Printf("\x1b[31m❌ Неправильно. Правильная последовательность: %s\x1b[0m\n", strings.Join(sequence, " "))
			fmt.Printf("Ваш счёт: %d\n", score)
			break
		}
		time.Sleep(1 * time.Second)
	}
	fmt.Printf("\nИгра окончена! Счёт: %d\n", score)
}
