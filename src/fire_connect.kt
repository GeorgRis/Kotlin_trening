const val ROWS = 6
const val COLUMNS = 7

class FirePaRad {
    private val board = Array(ROWS) { Array(COLUMNS) { ' '} }

    fun printBoard(){
        for (row in board){
            println(row.joinToString("|", "|", "|"))
        }
        println(" 0 | 1 | 2 | 3 | 4 | 5 | 6 ")
    }

    fun dropDisc(column: Int, player: Char): Boolean {
        if (column < 0 || column >= COLUMNS) {
            println("Invalid column. Please choose between 0 and 6.")
            return false
        }

        for (row in ROWS - 1 downTo 0) {
            if (board[row][column] == ' ') {
                board[row][column] = player
                return true
            }
        }

        println("Column $column is full. Choose another column.")
        return false
    }

    fun checkWin(player: Char): Boolean {
        // Check horizontal
        for (row in 0 until ROWS) {
            for (col in 0 until COLUMNS - 3) {
                if (board[row][col] == player && board[row][col + 1] == player &&
                    board[row][col + 2] == player && board[row][col + 3] == player) {
                    return true
                }
            }
        }

        // Check vertical
        for (col in 0 until COLUMNS) {
            for (row in 0 until ROWS - 3) {
                if (board[row][col] == player && board[row + 1][col] == player &&
                    board[row + 2][col] == player && board[row + 3][col] == player) {
                    return true
                }
            }
        }

        // Check diagonal (bottom-left to top-right)
        for (row in 3 until ROWS) {
            for (col in 0 until COLUMNS - 3) {
                if (board[row][col] == player && board[row - 1][col + 1] == player &&
                    board[row - 2][col + 2] == player && board[row - 3][col + 3] == player) {
                    return true
                }
            }
        }

        // Check diagonal (top-left to bottom-right)
        for (row in 0 until ROWS - 3) {
            for (col in 0 until COLUMNS - 3) {
                if (board[row][col] == player && board[row + 1][col + 1] == player &&
                    board[row + 2][col + 2] == player && board[row + 3][col + 3] == player) {
                    return true
                }
            }
        }

        return false
    }

    fun playGame() {
        var currentPlayer = 'R' // R for Red, Y for Yellow
        var moves = 0

        while (true) {
            printBoard()
            println("Player $currentPlayer, choose a column (0-6):")
            val input = readLine()

            if (input != null && input.toIntOrNull() != null) {
                val column = input.toInt()
                if (dropDisc(column, currentPlayer)) {
                    moves++
                    if (checkWin(currentPlayer)) {
                        printBoard()
                        println("Player $currentPlayer wins!")
                        break
                    }
                    if (moves == ROWS * COLUMNS) {
                        printBoard()
                        println("It's a draw!")
                        break
                    }
                    currentPlayer = if (currentPlayer == 'R') 'Y' else 'R' // Switch player
                }
            } else {
                println("Invalid input. Please enter a number between 0 and 6.")
            }
        }
    }
}

fun main() {
    val game = FirePaRad()
    game.playGame()
}


