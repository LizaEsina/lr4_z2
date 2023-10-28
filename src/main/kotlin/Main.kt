fun main() {
    val alphabet = "АБВГДЕЖЗИЙЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
    val table = generateTable(alphabet)

    println("Введите исходное сообщение:")
    val message = readLine() ?: return

    println("Введите вспомогательный символ:")
    val helperChar = readLine() ?: return

    println("Выберите тип таблицы: 1 - типовая таблица, 2 - случайная таблица")
    val choice = readLine() ?: return

    val encryptedMessage = encryptMessage(message, table, helperChar, choice)

    println("Исходное сообщение: $message")
    println("Зашифрованное сообщение: $encryptedMessage")
    println("Шифровальная таблица:")
    printTable(table)
}

// Генерация шифровальной таблицы
fun generateTable(alphabet: String): Array<Array<String>> {
    val table = Array(20) { Array(20) { "" } }

    for (i in 0 until alphabet.length) {
        val row = i / 5
        val col = i % 5
        table[row][col] = alphabet[i].toString()
    }

    return table
}
// Шифрование сообщения

fun encryptMessage(message: String, table: Array<Array<String>>, helperChar: String, choice: String): String {
    var encryptedMessage = ""

    for (i in 0 until message.length step 2) {
        if (i + 1 >= message.length) {
            encryptedMessage += message[i]
            break
        }

        val char1 = message[i].toString().toUpperCase()
        val char2 = message[i + 1].toString().toUpperCase()

        val row1: Int
        val col1: Int
        val row2: Int
        val col2: Int
        // Использование типовой таблицы

        if (choice == "1") {
            row1 = table.indexOfFirst { it.contains(char1) }
            col1 = table[row1].indexOf(char1)
            row2 = table.indexOfFirst { it.contains(char2) }
            col2 = table[row2].indexOf(char2)
        }
        else { // Использование случайной таблицы с вспомогательным символом
                row1 = table.indexOfFirst { it.contains(helperChar) }
            col1 = table[row1].indexOf(helperChar)
            row2 = table.indexOfFirst { it.contains(helperChar) }
            col2 = table[row2].indexOf(helperChar)
        }

        val encryptedPair = "${row1 + 1}${col1 + 1}${row2 + 1}${col2 + 1}"
        encryptedMessage += " $encryptedPair"
    }

    return encryptedMessage.trim()
}

// Вывод шифровальной таблицы
fun printTable(table: Array<Array<String>>) {
    for (row in table) {
        for (cell in row) {
            print("$cell ")
        }
        println()
    }
}
