import java.util.*;
import java.io.*;

public class KnightsTour {
    public static class Chessboard {
        int dim;
        boolean[][] boardFlags;
        int[][] fixedDeg;
        int xDelta[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int yDelta[] = {1, 2, 2, 1, -1, -2, -2, -1};
        int northWest[] = {0,0};
        int northEast[] = {0, dim-1};
        int southWest[] = {dim-1, 0};
        int southEast[] = {dim-1, dim-1};

        // Constructor n: dim of the chessboard
        public Chessboard(int n) {
            dim = n;
            // set all flags to false
            boardFlags = new boolean[dim][dim];
            fixedDeg = new int[dim][dim];
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    boardFlags[i][j] = false;
                    fixedDeg[i][j] = getDynamicDeg(i,j);
                }
            }
        }

        // judge whether a coordinate is a valid move
        public boolean isValidPos (int row,int col) {
            // the node is within the boundary and not marked
            return (row < dim) && (row >= 0) && (col < dim) && (col >= 0) && (!boardFlags[row][col]);
        }

        // shorted distance to 4 corners
        public int distToCorner(int row, int col) {
            int[] dist = new int[4];
            dist[0] = row - northWest[0] + col - northWest[1];
            dist[1] = row - northEast[0] - col + northEast[1];
            dist[2] = - row + southWest[0] + col - southWest[1];
            dist[3] = - row + southWest[0] - col + southWest[1];
            int minimum = dist[0];
            for (int ind = 0; ind < 4; ind++) {
                if (dist[ind] < minimum) { minimum = dist[ind]; }
            }
            return minimum;
        }

        // clear Chessboard
        public void clearBoard() {
            boardFlags = new boolean[dim][dim];
        }

        // get valid neighbors of a given node
        public ArrayList<int[]> getNeighbors(int row, int col) {
            // initialize neighbor array
            ArrayList<int[]> neighbors = new ArrayList<int[]>(0);
            int nbr_x = 0;
            int nbr_y = 0;
            // check each possible neighbor loc is valid or not
            for (int ind = 0; ind < 8; ind++) {
                nbr_x = row + xDelta[ind];
                nbr_y = col + yDelta[ind];
                if (isValidPos(nbr_x, nbr_y)) {
                    // if the neighbor position is valid, add to output
                    neighbors.add(new int[] {nbr_x, nbr_y});
                }
            }
            return neighbors;
        }

        // get degree of the input node
        public int getDynamicDeg(int row, int col) {
            int deg = 0;
            int nbr_x = 0;
            int nbr_y = 0;
            for (int ind = 0; ind < 8; ind++) {
                nbr_x = row + xDelta[ind];
                nbr_y = col + yDelta[ind];
                if (isValidPos(nbr_x, nbr_y))
                    deg++;
            }
            if (isReachable(1,2,row,col))
                deg++;
            return deg;
        } 

        // check if node 1 is reachable from node 2
        public boolean isReachable(int row1, int col1, int row2, int col2) {
            int nbr_x = 0;
            int nbr_y = 0;
            for (int ind = 0; ind < 8; ind++) {
                nbr_x = row2 + xDelta[ind];
                nbr_y = col2 + yDelta[ind];
                if ((nbr_x == row1) && (nbr_y == col1))
                    return true;
            }
            return false;
        }

        // get dimension of the chessboard
        public int getDim() { return dim; }

        public boolean isFullMarked() {
            int score = 0;
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    if (boardFlags[i][j] == true) { score ++; }
                }
            }
            if (score == dim * dim) {return true;} else {return false;}
        }

        // mark a node on board
        public void markNode(int row, int col) {
            // make sure that the node hasn't marked before
            assert boardFlags[row][col] == false;
            boardFlags[row][col] = true;
        }

        // unmark a node on board
        public void unmarkNode(int row, int col) {
            // make sure that the node has marked before
            assert boardFlags[row][col] == true;
            boardFlags[row][col] = false;
        }

        public int[] getDynDegOfNeighbors(int row, int col) {
            ArrayList<int[]> nbrs = getNeighbors(row, col);
            int[] dynDegList = new int[nbrs.size()];
            for (int ind = 0; ind < nbrs.size(); ind++) {
                int[] tmp = nbrs.get(ind);
                dynDegList[ind] = getDynamicDeg(tmp[0], tmp[1]);
            }
            return dynDegList;
        }
    }

    static boolean isAlive(Chessboard board, int row, int col, int ST) {
        // get degree of this node
        if (ST == 0 || ST == 1 || ST == 2 || ST == 4 || ST == 6) {
            ArrayList<int[]> nbrs = board.getNeighbors(row, col);
            if (nbrs.size() > 0) {return true;} else {return false;}
        } else if (ST == 3 || ST == 5) {
            // get degree of this node
            ArrayList<int[]> nbrs = board.getNeighbors(row, col);
            int deg = nbrs.size();
            // get dynDeg
            int[] dynDegList = board.getDynDegOfNeighbors(row, col);
            // get number of 1s in dynamic degree
            int numOfOne = 0;
            for (int ind = 0; ind < dynDegList.length; ind++) {
                // System.out.println("deg before count 1: " + dynDegList[ind]);
                if (dynDegList[ind] == 1)
                    numOfOne++;
            }
            if (numOfOne > 1 || deg == 0) {
                return false;
            } else { return true; }
        } else {
            System.out.println("rules are not supported at this time!");
            System.exit(0);
            return false;
        }
    }

    static ArrayList<int[]> randomSortAccordFixDeg(Chessboard board,
            ArrayList<int[]> candNodes) {
        int length = candNodes.size();
        // System.out.println("length is " + length);
        ArrayList<int[]> sortedCandNode = new ArrayList<int[]>();
        // get fix deg of each node in candNodes
        int[][] composedList = new int[length][3];
        // get fixed degree
        for (int ind = 0; ind < candNodes.size(); ind++) {
            int[] node = candNodes.get(ind);
            int fixDeg = board.fixedDeg[node[0]][node[1]];
            // System.out.println("Fix deg of this node is: " + fixDeg);
            composedList[ind] = new int[] {fixDeg, node[0], node[1]};
        }
        
        for (int ind = 0; ind < 9; ind++) {
            int curDeg = 9 - ind; 
            // System.out.println("current degree: " + curDeg);
            ArrayList<int[]> curNodes = new ArrayList<int[]>();
            // find candidate nodes with deg curDeg
            for (int jnd = 0; jnd < length; jnd++) {
                if (composedList[jnd][0] == curDeg) {
                    curNodes.add(composedList[jnd]);
                }
            }
            // System.out.println("num of nodes with this deg: " + curNodes.size());
            if (curNodes.size() > 0) { 
                Collections.shuffle(curNodes); 
                for (int knd = 0; knd < curNodes.size(); knd++) {
                    int[] tmp = curNodes.get(knd);
                    sortedCandNode.add(new int[] {tmp[1], tmp[2]});
                }
            }
        }
        // System.out.println("length of returned node list: " + sortedCandNode.size());
        return sortedCandNode;
    }

    static ArrayList<int[]> randomSortAccordDynDeg(Chessboard board,
            ArrayList<int[]> candNodes) {
        int length = candNodes.size();
        ArrayList<int[]> sortedCandNode = new ArrayList<int[]>();
        // get fix deg of each node in candNodes
        int[][] composedList = new int[length][3];
        // get fixed degree
        for (int ind = 0; ind < candNodes.size(); ind++) {
            int[] node = candNodes.get(ind);
            int dynDeg = board.getDynamicDeg(node[0], node[1]);
            composedList[ind] = new int[] {dynDeg, node[0], node[1]};
        }
        
        for (int ind = 0; ind < 9; ind++) {
            int curDeg = 8 - ind; 
            ArrayList<int[]> curNodes = new ArrayList<int[]>();
            // find candidate nodes with deg curDeg
            for (int jnd = 0; jnd < length; jnd++) {
                if (composedList[jnd][0] == curDeg) {
                    curNodes.add(composedList[jnd]);
                }
            }
            if (curNodes.size() > 0) { 
                Collections.shuffle(curNodes); 
                for (int knd = 0; knd < curNodes.size(); knd++) {
                    int[] tmp = curNodes.get(knd);
                    sortedCandNode.add(new int[] {tmp[1], tmp[2]});
                }
            }
        }
        return sortedCandNode;
    }

    static ArrayList<int[]> sortAccordDynDegAndDistToCorner(Chessboard board,
            ArrayList<int[]> candNodes) {
        int length = candNodes.size();
        ArrayList<int[]> sortedCandNode = new ArrayList<int[]>();
        // get fix deg of each node in candNodes
        int[][] composedList = new int[length][4];
        // get fixed degree
        for (int ind = 0; ind < candNodes.size(); ind++) {
            int[] node = candNodes.get(ind);
            int dynDeg = board.getDynamicDeg(node[0], node[1]);
            int distToCorner = board.distToCorner(node[0], node[1]);
            composedList[ind] = new int[] {dynDeg, distToCorner, node[0], node[1]};
        }
        
        for (int ind = 0; ind < 9; ind++) {
            int curDeg = 8 - ind; 
            ArrayList<int[]> curNodes = new ArrayList<int[]>();
            // find candidate nodes with deg curDeg
            for (int jnd = 0; jnd < length; jnd++) {
                if (composedList[jnd][0] == curDeg) {
                    curNodes.add(composedList[jnd]);
                }
            }
            if (curNodes.size() > 0) { 
                // Collections.shuffle(curNodes); 
                Collections.sort(curNodes, (o1, o2) -> o2[1] - o1[1]);
                for (int knd = 0; knd < curNodes.size(); knd++) {
                    int[] tmp = curNodes.get(knd);
                    sortedCandNode.add(new int[] {tmp[2], tmp[3]});
                }
            }
        }
        return sortedCandNode;
    }

    static ArrayList<int[]> sortAccordFixDeg(Chessboard board,
            ArrayList<int[]> candNodes) {
        int length = candNodes.size();
        ArrayList<int[]> sortedCandNode = new ArrayList<int[]>();
        // get fix deg of each node in candNodes
        int[][] composedList = new int[length][3];
        // get fixed degree
        for (int ind = 0; ind < candNodes.size(); ind++) {
            int[] node = candNodes.get(ind);
            int fixDeg = board.fixedDeg[node[0]][node[1]];
            composedList[ind] = new int[] {fixDeg, node[0], node[1]};
        }
        // complete argsort in decreasing order
        Arrays.sort(composedList, (o1, o2) -> o2[0] - o1[0]);
        // fill in the sortedCandNode ArrayList
        for (int ind = 0; ind < candNodes.size(); ind++) {
            int[] tup = composedList[ind];
            // System.out.println("deg: " + tup[0] + " (" + tup[1] + "," + tup[2] + ")");
            sortedCandNode.add(new int[] {tup[1],tup[2]});
        }
        return sortedCandNode;
    }

    static ArrayList<int[]> sortAccordDynDeg(Chessboard board,
            ArrayList<int[]> candNodes) {
        int length = candNodes.size();
        ArrayList<int[]> sortedCandNode = new ArrayList<int[]>(length);
        // get fix deg of each node in candNodes
        int[][] composedList = new int[length][3];
        // get fixed degree
        for (int ind = 0; ind < candNodes.size(); ind++) {
            int[] node = candNodes.get(ind);
            int dynDeg = board.getDynamicDeg(node[0], node[1]);
            // if (dynDeg == 0)
            //     dynDeg = dynDeg + 8;
            composedList[ind] = new int[] {dynDeg, node[0], node[1]};
        }
        // complete argsort in decreasing order
        Arrays.sort(composedList, (o1, o2) -> o2[0] - o1[0]);
        // fill in the sortedCandNode ArrayList
        for (int ind = 0; ind < candNodes.size(); ind++) {
            int[] tup = composedList[ind];
            // System.out.println("deg: " + tup[0] + " (" + tup[1] + "," + tup[2] + ")");
            sortedCandNode.add(new int[] {tup[1],tup[2]});
        }
        return sortedCandNode;
    }

    static ArrayList<int[]> oneDegDom(Chessboard board,
            ArrayList<int[]> candNodes) {
        int numOfOne = 0;
        int oneIndex = 0;
        int length = candNodes.size();
        ArrayList<int[]> sortedCandNode = new ArrayList<int[]>();
        // get fixed degree
        for (int ind = 0; ind < candNodes.size(); ind++) {
            int[] node = candNodes.get(ind);
            int dynDeg = board.getDynamicDeg(node[0], node[1]);
            if (dynDeg == 1) {
                // System.out.println("One degree node domination!");
                numOfOne++; 
                oneIndex = ind;
            }
        }
        if (numOfOne == 1) {
            // only one nbr has deg 1
            sortedCandNode.add(candNodes.get(oneIndex));
            return sortedCandNode;
        } else {
            sortedCandNode = candNodes;
            return sortedCandNode;        
        }
    }

    static ArrayList<int[]> reorderNbrs(Chessboard board, ArrayList<int[]> candNodes, int ST) {
        int length = candNodes.size();
        if (ST == 0) {
            // choose randomly
            Collections.shuffle(candNodes);
        } else if (ST == 1) {
            // choose node with the smallest fix degree
            candNodes = randomSortAccordFixDeg(board, candNodes);
            // System.out.println("is lenght == candNodes.size() ?" + (candNodes.size() == length));
            // candNodes = sortAccordFixDeg(board, candNodes);
        } else if (ST == 2) {
            // choose node with the smallest dynamic degree
            candNodes = randomSortAccordDynDeg(board, candNodes);
        } else if (ST == 3) {
            candNodes = randomSortAccordFixDeg(board, candNodes);
        } else if (ST == 4) {
            candNodes = randomSortAccordFixDeg(board, candNodes);
            candNodes = oneDegDom(board, candNodes);
        } else if (ST == 5) {
            candNodes = randomSortAccordDynDeg(board, candNodes);
            candNodes = oneDegDom(board, candNodes);
        } else if (ST == 6) {
            candNodes = sortAccordDynDegAndDistToCorner(board, candNodes);
        }
        return candNodes;
    }

    static void printPath(Chessboard board, ArrayList<int[]> arrList) {
        int[][] pathGrid = new int[board.dim][board.dim];
        for (int ind = 0; ind < arrList.size(); ind++) {
            int[] tmp = arrList.get(ind);
            pathGrid[tmp[0]][tmp[1]] = ind;
        }
        for (int i = 0; i < board.dim; i++) {
            System.out.print("|");
            for (int j = 0; j < board.dim; j++) {
                System.out.print(pathGrid[i][j] + "\t" + "|");
            }
            System.out.print("\n");
        }
    }

    static void printArrList(ArrayList<int[]> arrList) {
        for (int ind = 0; ind < arrList.size(); ind++) {
            int[] tup = arrList.get(ind);
            System.out.println("The " + (ind+1) + " elements in the ArrayList " 
                    + "is: (" + tup[0] + "," + tup[1] + ")");
        }
    }

    static boolean isDone(Chessboard board, int curRow, int curCol, int stRow,
            int stCol, ArrayList<int[]> path) {
        // check if path is full
        if ((path.size() == board.dim * board.dim) 
                && (board.isReachable(stRow, stCol, curRow, curCol)) ) {
                // && (board.getDynamicDeg(curRow, curCol) == 1)) {
            // System.out.println(board.isFullMarked());
            return true; 
        } else { return false; }
        // if (path.size() == board.dim * board.dim) {
        //     board.boardFlags[stRow][stCol] = false;
        //     ArrayList<int[]> nbrs = board.getNeighbors(curRow, curCol);
        //     if (nbrs.size() == 1) {
        //         int[] node = nbrs.get(0);
        //         if (node[0] == stRow && node[1] == stCol) {
        //             // tour is close!
        //             board.boardFlags[stRow][stCol] = true;
        //             return true; 
        //         } else {
        //             // tour is open!
        //             board.boardFlags[stRow][stCol] = true;
        //             return false;
        //         }
        //     } else {
        //         // run into bugs!
        //         board.boardFlags[stRow][stCol] = true;
        //         System.out.println(" Bug! ");
        //         return false;
        //     }
        // } else {return false;}
    }

    static void writePathToFile(Chessboard board, ArrayList<int[]> path, 
            String fileName, int ST, int stRow, int stCol, int numOfKTfound) {
        try{
            PrintWriter writer = new PrintWriter(new FileOutputStream(
                        new File(fileName), true));
            writer.print(numOfKTfound + ": ");
            for (int ind = 0; ind < path.size(); ind++) {
                int[] pos = path.get(ind);
                writer.print((pos[0]+1) + "," + (pos[1]+1) + " ");
            }
            writer.print("\n");
            writer.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
            System.exit(0);
        }
    }

    static double mean(int[] m) {
        double sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i];
        }
        return sum / m.length;
    }

    static int solveKT(Chessboard board, int stRow, int stCol, int ST,
            int numIter, String outPath) {
        // check input starting points
        assert board.isValidPos(stRow, stCol);

        // init path list and stack
        int iter = 0;
        int numOfKTfound = 0;
        ArrayList<int[]> path = new ArrayList<int[]>(64);
        Stack<int[]> stack = new Stack<int[]>();

        // start iterative DFS using stack
        stack.push(new int[] {stRow, stCol});

        while (iter < numIter && !stack.isEmpty()) {
            // pop new node to list
            int[] curNode = stack.peek();
            // System.out.println("Current path is:");
            // printArrList(path);
            // printPath(board,path);
            // System.out.println("\n");
            if (path.size() >= 1) {
                int[] lastNode = path.get(path.size() - 1);
                if (curNode[0] == lastNode[0] && curNode[1] == lastNode[1]) {
                    stack.pop();
                    path.remove(path.size() - 1);
                    board.unmarkNode(curNode[0], curNode[1]);
                    continue;
                }
            }
            path.add(curNode);
            // System.out.println("Current node is: (" + curNode[0] + "," + curNode[1]
            //         + ")");

            // mark the node on board
            board.markNode(curNode[0], curNode[1]);

            // check if done
            if (isDone(board, curNode[0], curNode[1], stRow, stCol, path)) {
                // print path to file
                // System.out.println("Knights'Tour is found!");
                numOfKTfound++;
                writePathToFile(board, path, outPath, 0, stRow, stCol, numOfKTfound);
                // printPath(board, path);

                // System.out.println("Finding new tour ...");
                // change path and stack
                path.remove(path.size() - 1);
                stack.pop();
                board.unmarkNode(curNode[0], curNode[1]);
                // push start node to stack
                // stack.push(new int[] {stRow, stCol});
                // increase number of iteration
                iter++;
                continue;
            }

            // judge if failure / alive
            if ( isAlive(board, curNode[0], curNode[1], ST) ) {
                // find its neighbors
                ArrayList<int[]> nbrs = board.getNeighbors(curNode[0], curNode[1]);
                // System.out.println("Neighbors are: ");
                // printArrList(nbrs);
                // reorder its neighbors accord. to requirements
                nbrs = reorderNbrs(board, nbrs, ST);
                // push its neighbors into stack
                stack.addAll(nbrs);
                // System.out.println("Iteration: " + iter);
                // System.out.println("Current new path is:");
                // printPath(board,path);
                iter++;
            } else {
                // failure
                // System.out.println("Fail!");
                stack.pop();
                path.remove(path.size() - 1);
                board.unmarkNode(curNode[0], curNode[1]);
            }
            // start new loop
            // iter++;
        }
        // No closed knight tour found

        return numOfKTfound;
    }

    public static void main(String[] args)
    {
        // initialize parameters
        int dimension = 8;
        int[] startNode = {1,2};
        int ST = 6;
        int numIter = 1000000;
        int numOfRuns = 30;
        int numOfFoundList[] = new int[numOfRuns];
        String outputPath = "../outputs/paths-" + ST + ".txt";


        for (int ind = 0; ind < numOfRuns; ind ++) {
            // create chessboard obj
            Chessboard board = new Chessboard(dimension);

            // create output file
            try{
                PrintWriter writer = new PrintWriter(outputPath);
                writer.println("KT: " + board.dim + "x" + board.dim + ", strategy = " 
                        + ST + ", start = " + (startNode[0]+1) + "," + (startNode[1]+1) + "\n");
                writer.close();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
                System.exit(0);
            }

            int numOfKTfound = solveKT(board, startNode[0], startNode[1], ST,
                    numIter, outputPath);
            System.out.println(numOfKTfound + " knights' tour are found in total");
            numOfFoundList[ind] = numOfKTfound;
        }

        double meanNumOfFound = mean(numOfFoundList);
        System.out.println("Average number of founded knight's tour: " + meanNumOfFound);


    }
}
