import javafx.scene.image.Image
import java.io.File

class GameField {
    val field = createMatrix(4, 4, Tile(0, Image(File("images/0.jpg").toURI().toString())))
    val list = listOf(1, 2, 3, 0, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 4)
    val list2 = createTiles().shuffled().toMutableList()
    init{
        var count = 0
        for (i in 0..3){
            for (j in 0..3){
                field[i,j] = Tile(list[count], Image(File("images/${list[count]}.jpg").toURI().toString()))
                count++
            }
        }
    }
    /* init {
            var count = 0
            val empty = Tile(0,Image(File("images/0.jpg").toURI().toString()))
            for (i in 0..3){
                for (j in 0..3){
                    if (i == 3 && j == 3 ) {
                        field[j,i] = empty
                        break
                    }
                    field[i,j] = list2[count]
                    count++
                }
            }

    }

}
*/
}
fun createTiles(): List<Tile> {
    val list = mutableListOf<Tile>()
    for (i in 0..15) {
        val tile = Tile(i, Image(File("images/${i}.jpg").toURI().toString()))
        list.add(tile)
    }
    return list
}

