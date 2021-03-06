package main

import javafx.scene.image.Image
import java.io.File

 class State (g: Int, h: Int, f: Int, field: Matrix<Tile>) {
    var g = 0
    var h = 0
    var f = 0
    var parent: State? = null
    private var field =
        createMatrix(4, 4, Tile(0, Image(File("images/0.jpg").toURI().toString())))
    fun getField(): Matrix<Tile> {
        return field
    }
    fun setField(field: Matrix<Tile>) {
        this.field = field
    }
     override fun toString(): String {
         for (i in 0 until field.height) {
             for (j in 0 until field.width) {
                 if (getField()[j,i].number < 10){
                     print(" " + getField()[j, i].number.toString() + " ")
                 } else {
                     print(getField()[j, i].number.toString() + " ")
                 }
             }
             println()
         }
         return ""
     }

     fun equals(other: State): Boolean {
         for (i in 0..3){
             for (j in 0..3)
                 if (this.field[i,j].number != other.field[i,j].number)
                     return false
         }
         return true
     }
}