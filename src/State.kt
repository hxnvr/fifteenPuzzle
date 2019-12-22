import javafx.scene.image.Image
import java.io.File

 class State (g: Int, h: Int, f: Int, field: Matrix<Tile>) {
    var g = 0
    var h = 0
    var f = 0
    var parent = State(0,0,0,field)
    private var field = createMatrix(4,4,Tile(0, Image(File("images/0.jpg").toURI().toString())))
    fun getField(): Matrix<Tile>{
        return field
    }
    fun setField(field: Matrix<Tile>) {
        this.field = field
    }

}