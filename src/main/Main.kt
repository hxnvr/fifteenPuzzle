package main
import solver.solver
import javafx.application.Application
import javafx.scene.image.Image
import javafx.stage.Stage
import java.io.File

val emptyTile = Tile(0, Image(File("images/0.jpg").toURI().toString()))
val backfield = GameField()
val matrix = backfield.field
val list = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0)

class Main : Application() {
    fun isSolvable():Boolean{
        val  list = backfield.list
        var e = 0
        var N = 0
        for (i in 0 until list.size - 1){
            if (list[i] == 0) {
                e = (i / 4) + 1
            }
            for (j in i+1 until list.size - 1) {
                if (list[j] < list[i]) N++
            }
        }
        N += e
        return N % 2 == 0
    }

    fun initial(): State {
        val initialState = State(0, 0, 0, matrix)
        initialState.setField(matrix)
        return initialState
    }

    override fun start(stage: Stage) {
        if (isSolvable()) {
            solver(initial())
        } else {
            print("решения головоломки не существует")
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
