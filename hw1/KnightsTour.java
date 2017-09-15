import java.util.*;
import java.io.*;


public class KnightsTour
{
    public static class Chessboard
    {
        int dim;
        boolean[][] boardFlags;
        int xDelta[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int yDelta[] = {1, 2, 2, 1, -1, -2, -2, -1};

        // Constructor n: dim of the chessboard
        public Chessboard(int n)
        {
            dim = n;
            // set all flags to false
            boardFlags = new boolean[dim][dim];
            for (int i = 0; i < dim; i++)
                for (int j = 0; j < dim; j++)
                    boardFlags[i][j] = false;
        }

        // judge whether a coordinate is a valid move
        public boolean isValidPos (int row,int col)
        {
            // the node is within the boundary and not traced
            return (row < dim) && (row >= 0) && (col < dim) && (col >= 0) 
                && (!boardFlags[row][col]);
        }

        // get valid neighbors of a given node
        public ArrayList<int[]> getNeighbors(int row, int col)
        {
            // initialize neighbor array
            ArrayList<int[]> neighbors = new ArrayList<int[]>(0);
            int nbr_x = 0;
            int nbr_y = 0;
            // check each possible neighbor loc is valid or not
            for (int ind = 0; ind < 8; ind++)
            {
                nbr_x = row + xDelta[ind];
                nbr_y = col + yDelta[ind];
                if (isValidPos(nbr_x, nbr_y))
                {
                    // if the neighbor position is valid, add to output
                    neighbors.add(new int[] {nbr_x, nbr_y});
                }
            }
            return neighbors;
        }

        // get degree of the input node
        public int getDegree(int row, int col)
        {
            int deg = 0;
            int nbr_x = 0;
            int nbr_y = 0;
            for (int ind = 0; ind < 8; ind++)
            {
                nbr_x = row + xDelta[ind];
                nbr_y = col + yDelta[ind];
                if (isValidPos(nbr_x, nbr_y))
                    deg++;
            }
            return deg;
        } 

        // get dimension of the chessboard
        public int getDim()
        {
            return dim;
        }

        // mark a node on board
        public void markNode(int row, int col)
        {
            boardFlags[row][col] = true;
        }

        // unmark a node on board
        public void unmarkNode(int row, int col)
        {
            boardFlags[row][col] = false;
        }

        // test chessboard class
        public void testChessboard()
        {
            // judge whether (5,5) is a valid position or not
            int row = 4;
            int col = 4;
            System.out.println("(" + row + "," + col + ") is a valid position");
            System.out.println(isValidPos(row,col));
            ArrayList<int[]> neighbors = getNeighbors(row,col);
            System.out.println("The neighbors of node (" + row + "," + col
                    + ") are ");
            for (int ind = 0; ind < neighbors.size(); ind++)
            {
                int[] nbr = neighbors.get(ind);
                System.out.println("neighbor: " + nbr[0] + "," + nbr[1]);
            }
            System.out.println("The degree of node (" + row + "," + col
                    + ") is " + getDegree(row, col));
        }
    }
    static boolean isAlive(Chessboard board, int row, int col, boolean ruleFlag)
    {
        // get degree of this node
        if (ruleFlag)
        {
            int deg = board.getDegree(row, col);
            if (deg > 0) {return true;} else {return false;}
        }
        else 
        {
            System.out.println("rules are not supported at this time!");
            System.exit(0);
            return false;
        }
    }

    static void reorderNbrs(Chessboard board, ArrayList<int[]> candNodes)
    {
        int candLength = candNodes.size();
        boolean randFlag = true;
        boolean fixFlag = false;
        boolean dynFlag = false;

        // if (randFlag)
        // {
        // choose randomly
        Collections.shuffle(candNodes);
        // }

        // choose accord. to fix degree

        // choose accord. to dynamic degree
    }

    static boolean isDone(Chessboard board, int curRow, int curCol, int stRow,
            int stCol, ArrayList<int[]> path)
    {
        if ((path.size() == board.dim * board.dim) && (curRow == stRow) && 
            (curCol == stCol))
        { return true; }
        else 
        { return false; }
    }

    static void writePathToFile(Chessboard board, ArrayList<int[]> path, 
            String fileName, int ST, int stRow, int stCol)
    {
        try{
        PrintWriter writer = new PrintWriter(fileName);
        writer.println("KT: " + board.dim + "x" + board.dim + ", strategy = " 
                + ST + ", start = " + stRow + "," + stCol + "\n");
        writer.println("001: ");
        for (int ind = 0; ind < path.size(); ind++)
        {
            int[] pos = path.get(ind);
            writer.println(pos[0] + "," + pos[1] + " ");
        }
        writer.close();
        } catch (IOException e) {
            System.out.println("Writing Error!");
            System.exit(0);
        }
    }

    static boolean solveKT(Chessboard board, int stRow, int stCol, boolean ruleFlag)
    {
        // check input starting points
        assert board.isValidPos(stRow, stCol);

        // init path list and stack
        ArrayList<int[]> path = new ArrayList<int[]>();
        Stack<int[]> stack = new Stack<int[]>();

        // start iterative DFS using stack
        stack.push(new int[] {stRow, stCol});
        while (!stack.isEmpty())
        {
            // pop new node to list
            int[] curNode = stack.pop();
            path.add(curNode);

            // mark the node on board
            board.markNode(curNode[0], curNode[1]);

            // check if done
            if (isDone(board, curNode[0], curNode[1], stRow, stCol, path))
            {
                // print path to file
                writePathToFile(board, path, "output.txt", 0, stRow, stCol);
                return true;
            }

            // judge if failure / alive
            if ( isAlive(board, curNode[0], curNode[1], ruleFlag) )
            {
                // find its neighbors
                ArrayList<int[]> nbrs = board.getNeighbors(curNode[0], curNode[1]);
                // reorder its neighbors accord. to requirements
                reorderNbrs(board, nbrs);
                // push its neighbors into stack
                stack.addAll(nbrs);
            }
            else
            {
                // failure
                path.remove(path.size() - 1);
                board.unmarkNode(curNode[0], curNode[1]);
            }

            // start new loop
        }
        return false;
    }

    public static void main(String[] args)
    {
        int dimension = 5;
        boolean ruleFlag = false;
        Chessboard board = new Chessboard(dimension);
        // board.testChessboard();
    }
}
