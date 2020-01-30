package gui

import javafx.scene.control.Button
import javafx.scene.layout.Pane

class Gui {
    private val up = Button("UP")
    private val down = Button("DOWN")
    private val left = Button("LEFT")
    private val right = Button("RIGHT")
    private val root = Pane()
    /**
    private fun createContent(): Pane {
    main.getRoot.setPrefSize(600.0, 400.0)
    draw()
    return main.getRoot
    }



    fun drawTiles(i: Int, k: Int): Image {
    val a = main.getMatrix[i, k]
    return when (a) {
    is main.Tile -> a.image
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
    main.getRoot.children.add(imgv)
    }
    main.getUp.layoutX = 500.0
    main.getUp.layoutY = 100.0
    main.getRoot.children.add(main.getUp)
    main.getDown.layoutX = 485.0
    main.getDown.layoutY = 150.0
    main.getRoot.children.add(main.getDown)
    main.getLeft.layoutX = 450.0
    main.getLeft.layoutY = 125.0
    main.getRoot.children.add(main.getLeft)
    main.getRight.layoutX = 540.0
    main.getRight.layoutY = 125.0
    main.getRoot.children.add(main.getRight)
    }

     **/


    /** fun buttons() {
    var emptyPos = Pair(3, 3)
    var emptyCoord = Pair(300.0, 300.0)
    main.getDown.setOnAction {
    if (emptyCoord.second > 0.0) {
    val main.getEmptyTile = main.getEmptyTile
    val tile = main.getMatrix[emptyPos.first, emptyPos.second - 1]
    val img = ImageView(tile.image)
    img.x = emptyCoord.first
    img.y = emptyCoord.second
    main.getRoot.children.add(img)
    val imgEmpty = ImageView(main.getEmptyTile.image)
    imgEmpty.x = emptyCoord.first
    imgEmpty.y = emptyCoord.second - 100.0
    main.getRoot.children.add(imgEmpty)
    main.getMatrix[emptyPos.first, emptyPos.second] = main.getMatrix[emptyPos.first, emptyPos.second - 1]
    main.getMatrix[emptyPos.first, emptyPos.second - 1] = main.getMatrix[emptyPos.first, emptyPos.second]
    emptyPos = Pair(emptyPos.first, emptyPos.second - 1)
    emptyCoord = Pair(emptyCoord.first, emptyCoord.second - 100)
    if (solver.isSolved(main.getMatrix)) end()
    }
    }
    main.getUp.setOnAction {
    if (emptyCoord.second < 300.0) {
    val main.getEmptyTile = main.getEmptyTile
    val tile = main.getMatrix[emptyPos.first, emptyPos.second + 1] as main.Tile
    val img = ImageView(tile.image)
    img.x = emptyCoord.first
    img.y = emptyCoord.second
    main.getRoot.children.add(img)
    val imgEmpty = ImageView(main.getEmptyTile.image)
    imgEmpty.x = emptyCoord.first
    imgEmpty.y = emptyCoord.second + 100.0
    main.getRoot.children.add(imgEmpty)
    main.getMatrix[emptyPos.first, emptyPos.second] = main.getMatrix[emptyPos.first, emptyPos.second + 1]
    main.getMatrix[emptyPos.first, emptyPos.second + 1] = main.getMatrix[emptyPos.first, emptyPos.second]
    emptyPos = Pair(emptyPos.first, emptyPos.second + 1)
    emptyCoord = Pair(emptyCoord.first, emptyCoord.second + 100)
    if (solver.isSolved(main.getMatrix)) end()
    }
    }
    main.getRight.setOnAction {
    if (emptyCoord.first > 0.0) {
    val main.getEmptyTile = main.getEmptyTile
    val tile = main.getMatrix[emptyPos.first - 1, emptyPos.second] as main.Tile
    val img = ImageView(tile.image)
    img.x = emptyCoord.first
    img.y = emptyCoord.second
    main.getRoot.children.add(img)
    val imgEmpty = ImageView(main.getEmptyTile.image)
    imgEmpty.x = emptyCoord.first - 100.0
    imgEmpty.y = emptyCoord.second
    main.getRoot.children.add(imgEmpty)
    main.getMatrix[emptyPos.first, emptyPos.second] = main.getMatrix[emptyPos.first - 1, emptyPos.second]
    main.getMatrix[emptyPos.first - 1, emptyPos.second] = main.getMatrix[emptyPos.first, emptyPos.second]
    emptyPos = Pair(emptyPos.first - 1, emptyPos.second)
    emptyCoord = Pair(emptyCoord.first - 100.0, emptyCoord.second)
    if (solver.isSolved(main.getMatrix)) end()
    }
    }
    main.getLeft.setOnAction {
    if (emptyCoord.first < 300.0) {
    val main.getEmptyTile = main.getEmptyTile
    val tile = main.getMatrix[emptyPos.first + 1, emptyPos.second] as main.Tile
    val img = ImageView(tile.image)
    img.x = emptyCoord.first
    img.y = emptyCoord.second
    main.getRoot.children.add(img)
    val imgEmpty = ImageView(main.getEmptyTile.image)
    imgEmpty.x = emptyCoord.first + 100.0
    imgEmpty.y = emptyCoord.second
    main.getRoot.children.add(imgEmpty)
    main.getMatrix[emptyPos.first, emptyPos.second] = main.getMatrix[emptyPos.first + 1, emptyPos.second]
    main.getMatrix[emptyPos.first + 1, emptyPos.second] = main.getMatrix[emptyPos.first, emptyPos.second]
    emptyPos = Pair(emptyPos.first + 1, emptyPos.second)
    emptyCoord = Pair(emptyCoord.first + 100.0, emptyCoord.second)
    if (solver.isSolved(main.getMatrix)) end()
    }
    }
    }
    private fun end(): Pane{
    main.getRoot.children.clear()
    return main.getRoot
    }

     **/
}