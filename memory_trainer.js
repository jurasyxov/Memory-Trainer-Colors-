// memory_trainer.js — JavaScript версия

const readline = require('readline');

const colors = {
    red: '\x1b[31mкрасный\x1b[0m',
    blue: '\x1b[34mсиний\x1b[0m',
    green: '\x1b[32mзелёный\x1b[0m',
    yellow: '\x1b[33mжёлтый\x1b[0m',
    magenta: '\x1b[35mфиолетовый\x1b[0m',
    cyan: '\x1b[36mголубой\x1b[0m',
    white: '\x1b[37mбелый\x1b[0m'
};
const colorNames = Object.keys(colors);

function clearScreen() {
    console.clear();
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function playGame() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
    const question = (prompt) => new Promise(resolve => rl.question(prompt, resolve));

    let score = 0;
    let level = 1;
    let sequence = [];

    while (true) {
        const newColor = colorNames[Math.floor(Math.random() * colorNames.length)];
        sequence.push(newColor);

        clearScreen();
        console.log('🎨 Memory Trainer (Colors) (JavaScript)');
        console.log(`Уровень ${level}: Запомните ${sequence.length} цветов!`);
        process.stdout.write('Показываю: ');
        for (const c of sequence) {
            process.stdout.write(colors[c] + ' ');
        }
        console.log('\n' + '='.repeat(30));
        await sleep(1500);
        clearScreen();

        console.log(`Введите ${sequence.length} цветов через пробел:`);
        const input = await question('> ');
        const userColors = input.trim().toLowerCase().split(/\s+/);

        if (userColors.length !== sequence.length) {
            console.log(`\x1b[31m❌ Нужно ввести ${sequence.length} цветов. Попробуйте ещё раз.\x1b[0m`);
            await sleep(1000);
            continue;
        }

        let correct = true;
        for (let i = 0; i < sequence.length; i++) {
            if (userColors[i] !== sequence[i]) {
                correct = false;
                break;
            }
        }

        if (correct) {
            console.log('\x1b[32m✅ Правильно! +1 очко.\x1b[0m');
            score++;
            level++;
        } else {
            console.log(`\x1b[31m❌ Неправильно. Правильная последовательность: ${sequence.join(' ')}\x1b[0m`);
            console.log(`Ваш счёт: ${score}`);
            break;
        }
        await sleep(1000);
    }
    console.log(`\nИгра окончена! Счёт: ${score}`);
    rl.close();
}

playGame().catch(console.error);
