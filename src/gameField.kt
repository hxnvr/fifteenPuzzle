import javafx.scene.image.Image
import java.io.File

class GameField {
    val field = createMatrix(4, 4, Tile(0, Image(File("images/0.jpg").toURI().toString())))
    val list = listOf( 1, 0, 3, 4, 5, 2, 7, 8, 9, 6, 11, 12, 13, 10, 14, 15)
    val list2 = createTiles().shuffled().toMutableList()
    init{
        var count = 0
        for (j in 0..3){
            for (i in 0..3){
                field[i,j] = Tile(list[count], Image(File("images/${list[count]}.jpg").toURI().toString()))
                count++
            }
        }
    }

}
fun createTiles(): List<Tile> {
    val list = mutableListOf<Tile>()
    for (i in 0..15) {
        val tile = Tile(i, Image(File("images/${i}.jpg").toURI().toString()))
        list.add(tile)
    }
    return list
}

