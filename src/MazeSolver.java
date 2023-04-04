/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam, Aarav Gupta
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // Create a stack to hold the cells in the solution path
        Stack<MazeCell> cells = new Stack<MazeCell>();
        MazeCell last = maze.getEndCell();
        MazeCell first = maze.getStartCell();
        cells.add(last);
        MazeCell current = last.getParent();
        // Traverse the parent cells from the end to the start of the maze, adding them to the stack
        while (current != null && current != first)
        {
            cells.push(current);
            current = current.getParent();
        }
        cells.push(first);
        // Create a new ArrayList to hold the cells in the correct order
        ArrayList<MazeCell> solved = new ArrayList<MazeCell>();
        while (!cells.empty())
        {
            solved.add(cells.pop());
        }
        return solved;
    }


    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */

    public ArrayList<MazeCell> solveMazeDFS() {
        // Initialize a Stack of MazeCell objects
        Stack<MazeCell> cellsToVisit = new Stack<MazeCell>();

        // Start at the beginning cell and add it to the stack
        MazeCell current = maze.getStartCell();
        cellsToVisit.push(current);

        while (!cellsToVisit.isEmpty())
        {
            // Pop the top cell off the stack and mark it as explored
            current.setExplored(true);
            current = cellsToVisit.pop();

            // If the current cell is the end cell, return the solution path
            if (current == maze.getEndCell())
            {
                return getSolution();
            }
            int row = current.getRow();
            int col = current.getCol();
            MazeCell neighbor;

            // Explore the neighbors of the current cell in the order: NORTH, EAST, SOUTH, WEST
            if (maze.isValidCell(row - 1, col))
            {
                neighbor = maze.getCell(row - 1, col);
                neighbor.setParent(current);
                current.setExplored(true);
                cellsToVisit.push(neighbor);
            }
            if (maze.isValidCell(row, col + 1))
            {
                neighbor = maze.getCell(row, col + 1);
                neighbor.setParent(current);
                current.setExplored(true);
                cellsToVisit.push(neighbor);
            }
            if (maze.isValidCell(row + 1, col))
            {
                neighbor = maze.getCell(row + 1, col);
                neighbor.setParent(current);
                current.setExplored(true);
                cellsToVisit.push(neighbor);
            }
            if (maze.isValidCell(row , col - 1))
            {
                neighbor = maze.getCell(row, col - 1);
                neighbor.setParent(current);
                current.setExplored(true);
                cellsToVisit.push(neighbor);
            }

        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS()
    {
        // Initialize a Queue of MazeCell objects

        Queue<MazeCell> cellsToVisit = new LinkedList<>();

        // Start at the beginning cell and add it to the queue
        MazeCell current = maze.getStartCell();
        cellsToVisit.add(current);
        while (!cellsToVisit.isEmpty())
        {
            current.setExplored(true);
            current = cellsToVisit.remove();

            // If the current cell is the end cell, return the solution path
            if (current == maze.getEndCell())
            {
                return getSolution();
            }
            int row = current.getRow();
            int col = current.getCol();
            MazeCell neighbor;

            // Explore the neighbors of the current cell
            // in the order: NORTH, EAST, SOUTH, WEST
            if (maze.isValidCell(row - 1, col))
            {
                neighbor = maze.getCell(row - 1, col);
                neighbor.setParent(current);
                current.setExplored(true);
                cellsToVisit.add(neighbor);
            }
            if (maze.isValidCell(row , col + 1))
            {
                neighbor = maze.getCell(row, col + 1);
                neighbor.setParent(current);
                current.setExplored(true);
                cellsToVisit.add(neighbor);
            }
            if (maze.isValidCell(row + 1 , col))
            {
                neighbor = maze.getCell(row + 1, col);
                neighbor.setParent(current);
                current.setExplored(true);
                cellsToVisit.add(neighbor);
            }
            if (maze.isValidCell(row , col - 1))
            {
                neighbor = maze.getCell(row, col - 1);
                neighbor.setParent(current);
                current.setExplored(true);
                cellsToVisit.add(neighbor);
            }
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
