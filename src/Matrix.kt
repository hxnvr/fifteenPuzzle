@file:Suppress("UNUSED_PARAMETER", "unused")


data class Cell(val row: Int, val column: Int)


interface Matrix<E> {

    val height: Int


    val width: Int


    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E


    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}


fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height <= 0 || width <= 0) throw IllegalArgumentException()
    return MatrixImpl(height, width, e)
}


class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {

    private val array = MutableList(height) { MutableList(width) { e } }

    override fun get(row: Int, column: Int): E = array[row][column]

    override fun get(cell: Cell): E = array[cell.row][cell.column]

    override fun set(row: Int, column: Int, value: E) {
        array[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        array[cell.row][cell.column] = value
    }

    override fun equals(other: Any?) =
        (other is MatrixImpl<*>) && (height == other.height) && (width == other.width)

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("[")
        for (row in 0 until height) {
            sb.append("[")
            for (column in 0 until width) {
                sb.append(this[row, column], ": ")
            }
            sb.append("]")
        }
        sb.append("]")
        return "$sb"
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + array.hashCode()
        return result
    }

}
