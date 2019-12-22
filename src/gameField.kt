import javafx.scene.image.Image
import java.io.File

class GameField {
    val field = createMatrix(4,4, Tile(0, Image(File("images/0.jpg").toURI().toString())))
    private val list = createTiles().shuffled().toMutableList()
    init {
        var count = 0
        val empty = Tile(0,Image(File("images/0.jpg").toURI().toString()))
        for (i in 0..3){
            for (j in 0..3){
                if (i == 3 && j == 3 ) {
                    field[j,i] = empty
                    break
                }
                field[j,i] = list[count]
                count++
            }
        }

    }
}

fun createTiles(): List<Tile> {
    val list = mutableListOf<Tile>()
    for (i in 0..14) {
        val tile = Tile(i+1, Image(File("images/${i+1}.jpg").toURI().toString()))
        list.add(tile)
    }
    return list
}
