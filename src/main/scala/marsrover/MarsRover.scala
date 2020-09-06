package marsrover

import scala.io.Source
import scala.collection.mutable.ListBuffer


object MarsRover {
    
    case class Rover(x: Int, y: Int, orientation: Char)
    case class UpperRight(x: Int, y: Int)

    def main(args: Array[String]) = {

        val filename = if(args.length > 1) args(1) else "input.txt"
        val upperRight = UpperRight(
            parseFile(filename)(0).split(" ")(0).toInt,
            parseFile(filename)(0).split(" ")(1).toInt
        )

        val finalrover = parseFile(filename).tail.foldLeft(Rover(0,0,'N')){ (acc, i) => {
            if(i.contains(" ")) parseRover(i) else printRover(executeRoverInstructions(acc, i, upperRight))
        } }
    }

    def parseFile(filename: String) = {
        val fileInput: ListBuffer[String] = ListBuffer()
        for ((line)<- Source.fromFile(filename).getLines) {
            fileInput += line
        }
        fileInput
    }

    def printRover(rover: Rover) = {
        println(rover.x + " " + rover.y + " " + rover.orientation)
        rover
    }
    
    def executeRoverInstructions(rover: Rover, instructions: String, upperRight: UpperRight): Rover = {
        instructions.foldLeft(rover){ (acc, i) => i match {
            case 'L' => turnLeft(acc)
            case 'R' => turnRight(acc)
            case 'M' => move(acc, upperRight)
            case _ => acc
        } }
    }

    def parseRover(stringRover: String): Rover ={
        Rover(stringRover.split(" ")(0).toInt ,stringRover.split(" ")(1).toInt ,stringRover.split(" ")(2).head.toChar)
    }

    def move(rover: Rover, upperRight: UpperRight): Rover = rover match {
        case Rover(_,_,'N') => Rover(rover.x, if(rover.y == upperRight.y) rover.y else rover.y+1, rover.orientation)
        case Rover(_,_,'E') => Rover(if(rover.x==upperRight.x) rover.x else rover.x+1, rover.y, rover.orientation)
        case Rover(_,_,'S') => Rover(rover.x, if(rover.y == 0) rover.y else rover.y-1, rover.orientation)
        case Rover(_,_,'W') => Rover(if(rover.x==0)rover.x else rover.x-1, rover.y, rover.orientation)
        case _ => rover
    }

    def turnLeft(rover: Rover): Rover = rover match {
        case Rover(_,_,'N') => Rover(rover.x, rover.y, 'W')
        case Rover(_,_,'E') => Rover(rover.x, rover.y, 'N')
        case Rover(_,_,'S') => Rover(rover.x, rover.y, 'E')
        case Rover(_,_,'W') => Rover(rover.x, rover.y, 'S')
        case _ => rover
    }

    def turnRight(rover: Rover): Rover = rover match {
        case Rover(_,_,'N') => Rover(rover.x, rover.y, 'E')
        case Rover(_,_,'E') => Rover(rover.x, rover.y, 'S')
        case Rover(_,_,'S') => Rover(rover.x, rover.y, 'W')
        case Rover(_,_,'W') => Rover(rover.x, rover.y, 'N')
        case _ => rover
    }
}