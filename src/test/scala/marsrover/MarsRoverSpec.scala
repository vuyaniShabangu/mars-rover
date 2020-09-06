package marsrover

import org.scalatest.FunSuite

class MarsRoverSpec extends FunSuite {

    val upperRight = MarsRover.UpperRight(5,5)

    test("the rover x coordinate should increase by 1 when moved while facing East") {
        val rover = MarsRover.Rover(1,1,'E')
        val newRover = MarsRover.move(rover, upperRight)
        assert(newRover == MarsRover.Rover(2,1,'E'))
    }
    
    test("the rover x coordinate should decrease by 1 when moved while facing West") {
        val rover = MarsRover.Rover(1,1,'W')
        val newRover = MarsRover.move(rover, upperRight)
        assert(newRover == MarsRover.Rover(0,1,'W'))
    }

    test("the rover y coordinate should increase by 1 when moved while facing North") {
        val rover = MarsRover.Rover(1,1,'N')
        val newRover = MarsRover.move(rover, upperRight)
        assert(newRover == MarsRover.Rover(1,2,'N'))
    }

    test("the rover y coordinate should decrease by 1 when moved while facing South") {
        val rover = MarsRover.Rover(1,1,'S')
        val newRover = MarsRover.move(rover, upperRight)
        assert(newRover == MarsRover.Rover(1,0,'S'))
    }

    test("the rover should not move further than the North border") {
        val rover = MarsRover.Rover(5,5,'N')
        val newRover = MarsRover.move(rover, upperRight)
        assert(newRover == MarsRover.Rover(5,5,'N'))
    }

    test("the rover should not move further than the East border") {
        val rover = MarsRover.Rover(5,5,'E')
        val newRover = MarsRover.move(rover, upperRight)
        assert(newRover == MarsRover.Rover(5,5,'E'))
    }

    test("the rover should not move further than the South border") {
        val rover = MarsRover.Rover(0,0,'S')
        val newRover = MarsRover.move(rover, upperRight)
        assert(newRover == MarsRover.Rover(0,0,'S'))
    }

    test("the rover should not move further than the West border") {
        val rover = MarsRover.Rover(0,0,'W')
        val newRover = MarsRover.move(rover, upperRight)
        assert(newRover == MarsRover.Rover(0,0,'W'))
    }

    test("the rover should face East after being turned right from North") {
        val rover = MarsRover.Rover(0,0,'N')
        val newRover = MarsRover.turnRight(rover)
        assert(newRover == MarsRover.Rover(0,0,'E'))
    }

    test("the rover should face North after being turned right from West") {
        val rover = MarsRover.Rover(0,0,'W')
        val newRover = MarsRover.turnRight(rover)
        assert(newRover == MarsRover.Rover(0,0,'N'))
    }

    test("the rover face West after being turned left from North") {
        val rover = MarsRover.Rover(0,0,'N')
        val newRover = MarsRover.turnLeft(rover)
        assert(newRover == MarsRover.Rover(0,0,'W'))
    }

    test("the rover face South after being turned left from West") {
        val rover = MarsRover.Rover(0,0,'W')
        val newRover = MarsRover.turnLeft(rover)
        assert(newRover == MarsRover.Rover(0,0,'S'))
    }
}
