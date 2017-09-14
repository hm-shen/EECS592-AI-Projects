import java.util.*;

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

    static void solveKT(Chessboard board, int stRow, int stCol)
    {
        // check input starting points
        assert board.isValidPos(stRow, stCol);

        // init paths list and stack
        ArrayList<int[]> paths = new ArrayList<int[]>();
        Stack<int[]> stack = new Stack<int[]>();

        // start iterative DFS using stack
        stack.push(new int[] {stRow, stCol});
        while (!stack.isEmpty())
        {
            // pop new node to list
            int[] curNode = stack.pop();
            paths.add(curNode);

            // mark the node on board
            board.markNode(curNode[0], curNode[1]);

            // judge if failure / alive / done 
            // if alive
                // find its neighbors

                // sort its neighbors accord. to requirements

                // push its neighbors into stack

            // else if done
                // set done flag to true, break

            // else (failure)
                // unmark the current node
                // remove current node from path (go back to upper level) 
            
            // start new loop
        }
    }

    public static void main(String[] args)
    {
        int dimension = 5;
        Chessboard board = new Chessboard(dimension);
        board.testChessboard();
    }
}
