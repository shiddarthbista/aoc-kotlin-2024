import kotlin.math.abs
import kotlin.math.absoluteValue

fun main() {

    fun isWithinThreeInts(list: List<Int>): Boolean {
        val isAscending = list.zipWithNext().all { (a, b) -> a < b }
        val isDescending = list.zipWithNext().all { (a, b) -> a > b }
        val isSafe =  list.zipWithNext().all { (a, b) -> abs(a - b) in 1..3 }
        return (isDescending || isAscending) && isSafe
    }

    fun isSafeWithDampener(list: List<Int>): Boolean {
        return isWithinThreeInts(list) || list.indices.any { i ->
            val modifiedList = list.filterIndexed { index, _ -> index != i }
            isWithinThreeInts(modifiedList)
        }
    }


    fun part1(input: List<String>): Int {
        val new = input.map {
            it.split(" ") .map { it.toInt() }
        }

        return new.count { isWithinThreeInts(it) }
    }

    fun part2(input: List<String>): Int {
        val new = input.map {
            it.split(" ") .map { it.toInt() }
        }

        return new.count { isSafeWithDampener(it) }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
