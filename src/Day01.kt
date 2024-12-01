import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        input.forEach {
            val (left, right) = it.split("   ")
            leftList.add(left.toInt())
            rightList.add(right.toInt())
        }

        leftList.sort()
        rightList.sort()

        val difference = (0 until leftList.size).sumOf {
            abs(leftList[it] - rightList[it])
        }

        return difference
    }

    fun part2(input: List<String>): Int {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        input.forEach {
            val (left, right) = it.split("   ")
            leftList.add(left.toInt())
            rightList.add(right.toInt())
        }

        return leftList.sumOf { element ->
            element * rightList.count { it == element }
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
