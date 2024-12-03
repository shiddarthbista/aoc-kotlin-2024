fun main() {

    fun part1(input: List<String>): Int {
        val corruptedMemory = input.joinToString(separator = "")

        val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")

        val sum = regex.findAll(corruptedMemory)
            .map { matchResult ->
                val (x, y) = matchResult.destructured
                x.toInt() * y.toInt()
            }
            .sum()

        println("The sum of all valid mul results is: $sum")
        return sum

    }

    fun part2(input: List<String>): Int {
        val corruptedMemory = input.joinToString(separator = "")

        var isMulEnabled = true // Start with mul enabled
        var sum = 0
        var currentIndex = 0

        while (currentIndex < corruptedMemory.length) {
            when {
                // Check for "do()" instruction
                corruptedMemory.startsWith("do()", currentIndex) -> {
                    isMulEnabled = true
                    currentIndex += 4 // Move past "do()"
                }

                // Check for "don't()" instruction
                corruptedMemory.startsWith("don't()", currentIndex) -> {
                    isMulEnabled = false
                    currentIndex += 7 // Move past "don't()"
                }

                // Check for valid "mul(X,Y)" instruction
                corruptedMemory.startsWith("mul(", currentIndex) -> {
                    val startIndex = currentIndex + 4 // Start after "mul("
                    val endIndex = corruptedMemory.indexOf(")", startIndex)

                    if (endIndex != -1) {
                        val arguments = corruptedMemory.substring(startIndex, endIndex).split(",")
                        if (arguments.size == 2) {
                            val x = arguments[0].trim().toIntOrNull()
                            val y = arguments[1].trim().toIntOrNull()

                            if (isMulEnabled && x != null && y != null) {
                                sum += x * y
                            }
                        }
                        currentIndex = endIndex // Move past the current "mul(...)" instruction
                    }
                }

                else -> {
                    // Move to the next character if no valid instruction is found
                    currentIndex++
                }
            }
        }

        println("The sum of all valid and enabled mul results is: $sum")

        return sum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
