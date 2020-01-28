import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.text.Font
import javafx.stage.Stage
import java.io.File
/** В комментариях код для GUI**/
class Main : Application() {
    val emptyTile = Tile(0, Image(File("images/0.jpg").toURI().toString()))
    private val up = Button("UP")
    private val down = Button("DOWN")
    private val left = Button("LEFT")
    private val right = Button("RIGHT")
    val backfield = GameField()
    private val root = Pane()
    val matrix = backfield.field
    val list = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0)



    fun initial():State{
        val initialState = State(0,0,0,matrix)
        initialState.setField(matrix)
        return initialState
    }





  /** fun buttons() {
        var emptyPos = Pair(3, 3)
        var emptyCoord = Pair(300.0, 300.0)
        down.setOnAction {
            if (emptyCoord.second > 0.0) {
                val emptyTile = emptyTile
                val tile = matrix[emptyPos.first, emptyPos.second - 1]
                val img = ImageView(tile.image)
                img.x = emptyCoord.first
                img.y = emptyCoord.second
                root.children.add(img)
                val imgEmpty = ImageView(emptyTile.image)
                imgEmpty.x = emptyCoord.first
                imgEmpty.y = emptyCoord.second - 100.0
                root.children.add(imgEmpty)
                matrix[emptyPos.first, emptyPos.second] = matrix[emptyPos.first, emptyPos.second - 1]
                matrix[emptyPos.first, emptyPos.second - 1] = matrix[emptyPos.first, emptyPos.second]
                emptyPos = Pair(emptyPos.first, emptyPos.second - 1)
                emptyCoord = Pair(emptyCoord.first, emptyCoord.second - 100)
                if (isSolved(matrix)) end()
            }
        }
        up.setOnAction {
            if (emptyCoord.second < 300.0) {
                val emptyTile = emptyTile
                val tile = matrix[emptyPos.first, emptyPos.second + 1] as Tile
                val img = ImageView(tile.image)
                img.x = emptyCoord.first
                img.y = emptyCoord.second
                root.children.add(img)
                val imgEmpty = ImageView(emptyTile.image)
                imgEmpty.x = emptyCoord.first
                imgEmpty.y = emptyCoord.second + 100.0
                root.children.add(imgEmpty)
                matrix[emptyPos.first, emptyPos.second] = matrix[emptyPos.first, emptyPos.second + 1]
                matrix[emptyPos.first, emptyPos.second + 1] = matrix[emptyPos.first, emptyPos.second]
                emptyPos = Pair(emptyPos.first, emptyPos.second + 1)
                emptyCoord = Pair(emptyCoord.first, emptyCoord.second + 100)
                if (isSolved(matrix)) end()
            }
        }
        right.setOnAction {
            if (emptyCoord.first > 0.0) {
                val emptyTile = emptyTile
                val tile = matrix[emptyPos.first - 1, emptyPos.second] as Tile
                val img = ImageView(tile.image)
                img.x = emptyCoord.first
                img.y = emptyCoord.second
                root.children.add(img)
                val imgEmpty = ImageView(emptyTile.image)
                imgEmpty.x = emptyCoord.first - 100.0
                imgEmpty.y = emptyCoord.second
                root.children.add(imgEmpty)
                matrix[emptyPos.first, emptyPos.second] = matrix[emptyPos.first - 1, emptyPos.second]
                matrix[emptyPos.first - 1, emptyPos.second] = matrix[emptyPos.first, emptyPos.second]
                emptyPos = Pair(emptyPos.first - 1, emptyPos.second)
                emptyCoord = Pair(emptyCoord.first - 100.0, emptyCoord.second)
                if (isSolved(matrix)) end()
            }
        }
        left.setOnAction {
            if (emptyCoord.first < 300.0) {
                val emptyTile = emptyTile
                val tile = matrix[emptyPos.first + 1, emptyPos.second] as Tile
                val img = ImageView(tile.image)
                img.x = emptyCoord.first
                img.y = emptyCoord.second
                root.children.add(img)
                val imgEmpty = ImageView(emptyTile.image)
                imgEmpty.x = emptyCoord.first + 100.0
                imgEmpty.y = emptyCoord.second
                root.children.add(imgEmpty)
                matrix[emptyPos.first, emptyPos.second] = matrix[emptyPos.first + 1, emptyPos.second]
                matrix[emptyPos.first + 1, emptyPos.second] = matrix[emptyPos.first, emptyPos.second]
                emptyPos = Pair(emptyPos.first + 1, emptyPos.second)
                emptyCoord = Pair(emptyCoord.first + 100.0, emptyCoord.second)
                if (isSolved(matrix)) end()
            }
        }
    }
    private fun end(): Pane{
        root.children.clear()
        return root
    }

   **/

    override fun start(stage: Stage) {
        solver(initial())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
    fun isSolved(state: State):Boolean{
        val resmatrix = createMatrix(4,4, Tile(0,Image(File("images/0.jpg").toURI().toString())))
        var count = 0
        for (j in 0..3){
            for (i in 0..3){
                resmatrix[i,j] = Tile(list[count], Image(File("images/${list[count]}.jpg").toURI().toString()))
                count++
            }
        }
        val resState = State(0,0,0, resmatrix)
        resState.setField(resmatrix)
        if (state.equals(resState)) return true
        return false
    }
/**
    private fun createContent(): Pane {
        root.setPrefSize(600.0, 400.0)
        draw()
        return root
    }



    fun drawTiles(i: Int, k: Int): Image {
        val a = matrix[i, k]
        return when (a) {
            is Tile -> a.image
            else -> Image(File("images/empty.jpg").toURI().toString())
        }
    }

    fun draw() {
        var x: Double
        var y: Double
        for (i in 0..3)
            for (k in 0..3) {
                val imgv = ImageView(drawTiles(i, k))
                x = 100.0 * i
                y = 100.0 * k
                imgv.x = x
                imgv.y = y
                root.children.add(imgv)
            }
        up.layoutX = 500.0
        up.layoutY = 100.0
        root.children.add(up)
        down.layoutX = 485.0
        down.layoutY = 150.0
        root.children.add(down)
        left.layoutX = 450.0
        left.layoutY = 125.0
        root.children.add(left)
        right.layoutX = 540.0
        right.layoutY = 125.0
        root.children.add(right)
    }

 **/
    fun moveDown(matrix: Matrix<Tile>): Matrix<Tile>{
        var zeroPos = Pair(0,0)
        val newMatrix = createMatrix(4,4,emptyTile)

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
    fun moveUp(matrix: Matrix<Tile>): Matrix<Tile>{
        var zeroPos = Pair(0,0)
        val newMatrix = createMatrix(4,4,emptyTile)

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
    fun moveLeft(matrix: Matrix<Tile>): Matrix<Tile>{
        var zeroPos = Pair(0,0)
        val newMatrix = createMatrix(4,4,emptyTile)

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
    fun moveRight(matrix: Matrix<Tile>): Matrix<Tile>{
        var zeroPos = Pair(0,0)
        val newMatrix = createMatrix(4,4,emptyTile)
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
    fun findMinF(open : List<State>): State{
        var result = State(0,0,0, matrix)
        result.setField(matrix)
        var min = 999
        for (state in open){
            if (state.f < min){
                min = state.f
                result = state
            }
        }
        return result
    }




    fun theEnd(){
        print("done")
    }
    fun getNeighbors(state: State): Set<State>{
        val res = mutableSetOf<State>()
        val currentMatrix = state.getField()
        val downState = State(0,0,0, matrix)
        downState.setField(moveDown(currentMatrix))
        if (!downState.equals(state)) res.add(downState)
        val upState = State(0,0,0, matrix)
        upState.setField(moveUp(currentMatrix))
        if (!upState.equals(state)) res.add(upState)
        val leftState = State(0,0,0, matrix)
        leftState.setField(moveLeft(currentMatrix))
        if (!leftState.equals(state)) res.add(leftState)
        val rightState = State(0,0,0, matrix)
        rightState.setField(moveRight(currentMatrix))
        if (!rightState.equals(state)) res.add(rightState)
        return res
     }

    fun solver(startState: State){
        val open = mutableListOf<State>()
        val close = mutableSetOf<State>()
        var prov = false
        var currentG = 0
        open.add(startState)
        startState.g = 0
        startState.h = getH(startState)
        startState.f = startState.g + startState.h
        while (open.isNotEmpty()){
            val current = findMinF(open)
            open.remove(open.filter { it.equals(current)}[0])

            if (isSolved(current)) {
                theEnd()
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
                    prov = false
            }
            }
            currentG++

        }
    }
}
