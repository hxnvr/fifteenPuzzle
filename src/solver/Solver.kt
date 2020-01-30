package solver

import main.Matrix
import main.State
import main.Tile
import main.createMatrix
import main.emptyTile
import javafx.scene.image.Image
import main.list
import main.matrix
import java.io.File
import java.util.*

fun isSolved(state: State):Boolean{
    val resmatrix = createMatrix(4, 4, Tile(0, Image(File("images/0.jpg").toURI().toString())))
    var count = 0
    for (j in 0..3){
        for (i in 0..3){
            resmatrix[i,j] =
                Tile(list[count], Image(File("images/${list[count]}.jpg").toURI().toString()))
            count++
        }
    }
    val resState = State(0, 0, 0, resmatrix)
    resState.setField(resmatrix)
    if (state.equals(resState)) return true
    return false
}

fun moveDown(matrix: Matrix<Tile>): Matrix<Tile> {
    var zeroPos = Pair(0,0)
    val newMatrix = createMatrix(4, 4, emptyTile)

    for (i in 0 until matrix.width){
        for (j in 0 until matrix.height){
            if (matrix[i,j].number == 0) zeroPos = Pair(i,j)
            newMatrix[i,j] = matrix[i,j]
        }
    }
    if (zeroPos.second < 3){
        val hr = newMatrix[zeroPos.first, zeroPos.second]
        newMatrix[zeroPos.first, zeroPos.second] = newMatrix[zeroPos.first, zeroPos.second + 1]
        newMatrix[zeroPos.first, zeroPos.second + 1] = hr}
    return newMatrix
}
fun moveUp(matrix: Matrix<Tile>): Matrix<Tile> {
    var zeroPos = Pair(0,0)
    val newMatrix = createMatrix(4, 4, emptyTile)

    for (i in 0 until matrix.width){
        for (j in 0 until matrix.height){
            if (matrix[i,j].number == 0) zeroPos = Pair(i,j)
            newMatrix[i,j] = matrix[i,j]
        }
    }
    if (zeroPos.second > 0){
        val hr = newMatrix[zeroPos.first, zeroPos.second]
        newMatrix[zeroPos.first, zeroPos.second] = newMatrix[zeroPos.first, zeroPos.second - 1]
        newMatrix[zeroPos.first, zeroPos.second - 1] = hr}
    return newMatrix
}
fun moveLeft(matrix: Matrix<Tile>): Matrix<Tile> {
    var zeroPos = Pair(0,0)
    val newMatrix = createMatrix(4, 4, emptyTile)

    for (i in 0 until matrix.width){
        for (j in 0 until matrix.height){
            if (matrix[i,j].number == 0) zeroPos = Pair(i,j)
            newMatrix[i,j] = matrix[i,j]
        }
    }
    if (zeroPos.first>0){
        val hr = newMatrix[zeroPos.first, zeroPos.second]
        newMatrix[zeroPos.first, zeroPos.second] = newMatrix[zeroPos.first - 1, zeroPos.second]
        newMatrix[zeroPos.first - 1, zeroPos.second ] = hr}
    return newMatrix
}
fun moveRight(matrix: Matrix<Tile>): Matrix<Tile> {
    var zeroPos = Pair(0,0)
    val newMatrix = createMatrix(4, 4, emptyTile)
    for (i in 0 until matrix.width){
        for (j in 0 until matrix.height){
            if (matrix[i,j].number == 0) zeroPos = Pair(i,j)
            newMatrix[i,j] = matrix[i,j]
        }
    }
    if(zeroPos.first< 3){
        val hr = newMatrix[zeroPos.first, zeroPos.second]
        newMatrix[zeroPos.first, zeroPos.second] = newMatrix[zeroPos.first + 1, zeroPos.second]
        newMatrix[zeroPos.first + 1, zeroPos.second] = hr}
    return newMatrix
}

fun getH(state: State):Int {
    var h = 0
    val matrix = state.getField()
    for(i in 0 until matrix.height){
        for (j in 0 until matrix.width) {
            if (matrix[i, j].number != 0) {
                if (matrix[i, j].number != j * matrix.width + i + 1) h++
            } else {
                if (j != 3 && i != 3) h++
            }
        }
    }
    return h
}

fun findMinF(open : List<State>): State {
    var result = State(0, 0, 0, matrix)
    result.setField(matrix)
    var min = Int.MAX_VALUE
    for (state in open){
        if (state.f < min){
            min = state.f
            result = state
        }
    }
    return result
}


fun theEnd(state: State){
    val resQueue = ArrayDeque<State>()
    if (state.parent != null){
        resQueue.addFirst(state.parent!!)
        theEnd(state.parent!!)
    }
    for (i in 0 until resQueue.size){
        val last = resQueue.pop().toString()
        println(last)
    }
}

fun getNeighbors(state: State): Set<State>{
    val res = mutableSetOf<State>()
    val currentMatrix = state.getField()
    val downState = State(0, 0, 0, matrix)
    downState.setField(moveDown(currentMatrix))
    if (!downState.equals(state)) res.add(downState)
    val upState = State(0, 0, 0, matrix)
    upState.setField(moveUp(currentMatrix))
    if (!upState.equals(state)) res.add(upState)
    val leftState = State(0, 0, 0, matrix)
    leftState.setField(moveLeft(currentMatrix))
    if (!leftState.equals(state)) res.add(leftState)
    val rightState = State(0, 0, 0, matrix)
    rightState.setField(moveRight(currentMatrix))
    if (!rightState.equals(state)) res.add(rightState)
    return res
}

fun solver(startState: State) {
    val open = mutableListOf<State>()
    val close = mutableSetOf<State>()
    var prov = false
    var currentG = 0
    open.add(startState)
    startState.g = 0
    startState.h = getH(startState)
    startState.f = startState.g + startState.h
    while (open.isNotEmpty()) {
        val current = findMinF(open)
        open.remove(current)
        if (isSolved(current)) {
            theEnd(current)
            println(current)
            break
        }
        close.add(current)
        val neighbors = getNeighbors(current)
        for (neighbor in neighbors) {
            for (closeOne in close) {
                if (closeOne.equals(neighbor)) {
                    prov = true
                }
            }
            if (!prov) {
                neighbor.parent = current
                neighbor.g = currentG + 1
                neighbor.h = getH(neighbor)
                neighbor.f = neighbor.g + neighbor.h
                open.add(neighbor)
            } else {
                if (currentG + 1 < neighbor.g) {
                    neighbor.parent = current
                    neighbor.g = currentG + 1
                }
            }

            prov = false
        }
        currentG++
    }
}