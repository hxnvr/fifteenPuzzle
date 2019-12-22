import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.text.Font
import javafx.stage.Stage
import java.io.File

class Main : Application() {
    val emptyTile = Tile(0, Image(File("images/0.jpg").toURI().toString()))
    private val up = Button("UP")
    private val down = Button("DOWN")
    private val left = Button("LEFT")
    private val right = Button("RIGHT")
    val backfield = GameField()
    private val root = Pane()
    val matrix = backfield.field

    val list = createTiles()
    val open = mutableListOf<State>()
    val close = mutableSetOf<State>()
    fun resMatrix(): Matrix<Any>{
        val resmatrix = createMatrix(4,4,Any())
        var count = 0
        for (i in 0..3){
            for (j in 0..3){
                if (i == 3 && j == 3 ) {
                    resmatrix[j,i] = emptyTile
                    break
                }
                resmatrix[j,i] = list[count]
                count++
            }
        }
        return resmatrix
    }
    fun movement(){

    }

    fun buttons() {
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

    override fun start(stage: Stage) {
        stage.scene = Scene(createContent())
        stage.show()
        buttons()
        resMatrix()
        print(checknum(0, 0))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
    fun isSolved(matrix: Matrix<Tile>):Boolean{
        val resmatrix = resMatrix()
        for (i in 0..3){
            for (j in 0..3){
                if (matrix[i,j] == resmatrix[i,j]) return true
            }
        }
        return false
    }

    private fun createContent(): Pane {
        root.setPrefSize(600.0, 400.0)
        draw()
        return root
    }

    fun checknum(i: Int, k: Int): Int {

        val a = matrix[i, k]
        return when (a) {
            is Tile -> a.number
            else -> 0
        }
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
    fun moveDown(matrix: Matrix<Tile>): Matrix<Tile>{
        var zeroPos = Pair(0,0)
        val newMatrix = createMatrix(4,4, Tile(0, Image(File("images/0.jpg").toURI().toString())))
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
        val newMatrix = createMatrix(4,4, Tile(0, Image(File("images/0.jpg").toURI().toString())))
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
        val newMatrix = createMatrix(4,4, Tile(0, Image(File("images/0.jpg").toURI().toString())))
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
        val newMatrix = createMatrix(4,4, Tile(0, Image(File("images/0.jpg").toURI().toString())))
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
                    if (matrix[i, j].number != i * matrix.height + j + 1) h++
                } else {
                    if (j != 3 && i != 3) h++
                }
            }
        }
        return h
    }
    fun findMinF(open : List<State>): State{
        var result = State(0,0,0, matrix)
        var min = 0
        for (state in open){
            if (state.f < min){
                min = state.f
                result = state
            }
        }
        return result
    }
    fun getNeighbors(state: State): Set<State>{
        val res = mutableSetOf<State>()
        val currentMatrix = state.getField()
        val neighbor1 = moveDown(currentMatrix)
        val neighbor2 = moveUp(currentMatrix)
        val neighbor3 = moveLeft(currentMatrix)
        val neighbor4 = moveRight(currentMatrix)
        res.add(State(0,0,0,moveDown(currentMatrix)))
        res.add(State(0,0,0,moveLeft(currentMatrix)))
        res.add(State(0,0,0,moveUp(currentMatrix)))
        res.add(State(0,0,0,moveRight(currentMatrix)))
        for (neighbor in res){
            if(neighbor.getField() == currentMatrix) res.remove(neighbor)
        }
        return res
     }
    fun solver(startState: State){
        open.add(startState)
        startState.g = 0
        startState.h = getH(startState)
        startState.f = startState.g + startState.h
        while (open.isNotEmpty()){
            val current = findMinF(open)
            if (isSolved(current.getField())) TODO()
            open.remove(current)
            close.add(current)
            val neighbors = getNeighbors(current)
            for (neighbor in neighbors){
                for (close in close){
                    if(close.getField() == neighbor.getField())
                        continue
                }

            }
        }
    }
}
