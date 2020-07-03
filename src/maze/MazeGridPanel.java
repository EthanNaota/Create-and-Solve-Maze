package maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;

public class MazeGridPanel extends JPanel{
	private int rows;
	private int cols;
	private Cell[][] maze;



	// extra credit
	public void genDFSMaze() {
            boolean[][] visited = new boolean[rows][cols];
            Stack<Cell> stack  = new Stack<Cell>();
            Cell start = maze[0][0];
            int random = 0;
            boolean[] checked = new boolean[4];

            checked = reset();

            stack.push(start);
            visited[0][0] = true;

            while(!stack.isEmpty()){
                random = randoNum();
                switch (random) {
                    // north
                    case 0:
                        // if its not in the top row
                        if(stack.peek().row != 0){
                            try{
                                // if the cell above hasnt been visited and hasnt been checked already
                                if(visited[stack.peek().row-1][stack.peek().col] == false && checked[0]==false){
                                    //northwall false
                                    stack.peek().northWall = false;
                                    try{
                                        //southwall of cell above false
                                        maze[stack.peek().row-1][stack.peek().col].southWall = false;
                                    } catch(Exception E){ 
                                    }
                                    // add cell above to stay
                                    stack.add(maze[stack.peek().row-1][stack.peek().col]);
                                    
                                    visited[stack.peek().row][stack.peek().col] = true;
                                    // checked reset
                                    checked = reset();
                                    
                                } else{
                                    if (checked[0]==true && checked[1]==true && checked[2]==true && checked[3]==true){
                                        stack.pop();
                                        checked = reset();
                                    }
                                    checked[0]=true;
                                }
                            } catch(Exception E){
                                checked[0]=true;
                                
                            }
                            
                        }else {
                            checked[0]=true;
                        }
                        break;
                    case 1:
                        // if its not in the bottom row
                        if(stack.peek().row != rows-1){
                            try{
                                // if the cell above hasnt been visited and hasnt been checked already
                                if(visited[stack.peek().row+1][stack.peek().col] == false && checked[1]==false){
                                    //northwall false
                                    stack.peek().southWall = false;
                                    try{
                                        //southwall of cell above false
                                        maze[stack.peek().row+1][stack.peek().col].northWall = false;
                                    } catch(Exception E){ 
                                    }
                                    // add cell above to stay
                                    stack.add(maze[stack.peek().row+1][stack.peek().col]);
                                    // checked reset
                                    visited[stack.peek().row][stack.peek().col] = true;
                                    checked = reset();
                                    
                                } else{
                                    if (checked[0]==true && checked[1]==true && checked[2]==true && checked[3]==true){
                                        stack.pop();
                                        checked = reset();
                                    }
                                    checked[1]=true;
                                }
                            } catch(Exception E){
                                checked[1]=true;
                                
                            }
                            
                        }else {
                            checked[1]=true;
                        }
                        break;
                    case 2:
                        // if its not in the left column
                        if(stack.peek().col != 0){
                            try{
                                // if the cell above hasnt been visited and hasnt been checked already
                                if(visited[stack.peek().row][stack.peek().col-1] == false && checked[2]==false){
                                    //northwall false
                                    stack.peek().westWall = false;
                                    try{
                                        //southwall of cell above false
                                        maze[stack.peek().row][stack.peek().col-1].eastWall = false;
                                    } catch(Exception E){ 
                                    }
                                    // add cell above to stay
                                    stack.add(maze[stack.peek().row][stack.peek().col-1]);
                                    // checked reset
                                    visited[stack.peek().row][stack.peek().col] = true;
                                    checked = reset();
                                    
                                } else{
                                    if (checked[0]==true && checked[1]==true && checked[2]==true && checked[3]==true){
                                        stack.pop();
                                        checked = reset();
                                    }
                                    checked[2]=true;
                                }
                            } catch(Exception E){
                                checked[2]=true;
                                
                            }
                            
                        } else {
                            checked[2]=true;
                        }
                        break;
                        case 3:
                        // if its not in the top row
                        if(stack.peek().col != cols-1){
                            try{
                                // if the cell above hasnt been visited and hasnt been checked already
                                if(visited[stack.peek().row][stack.peek().col+1] == false && checked[3]==false){
                                    //northwall false
                                    stack.peek().eastWall = false;
                                    try{
                                        //southwall of cell above false
                                        maze[stack.peek().row][stack.peek().col+1].westWall = false;
                                    } catch(Exception E){ 
                                    }
                                    // add cell above to stay
                                    stack.add(maze[stack.peek().row][stack.peek().col+1]);
                                    // checked reset
                                    visited[stack.peek().row][stack.peek().col] = true;
                                    checked = reset();
                                    
                                } else{
                                    if (checked[0]==true && checked[1]==true && checked[2]==true && checked[3]==true){
                                        stack.pop();
                                        checked = reset();
                                    }
                                    checked[3]=true;
                                }
                            } catch(Exception E){
                                checked[3]=true;
                                
                            }
                            
                        }else {
                            checked[3]=true;
                        }
                        break;
                    default:
                        break;
                }

            }
        }
        
        public boolean[] reset(){
            boolean[] a = new boolean[4];
            for(int i = 0; i < 4; i++){
                a[i] = false;
            }
            return a;
        }
        public int randoNum() {
            int min = 0;
            int max = 3;
            if (min >= max) {
                throw new IllegalArgumentException("max must be greater than min");
            }

            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        }

	//homework
	public void solveMaze() {
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows-1][cols-1];
		finish.setBackground(Color.RED);
                
		stack.push(start);
                
                while(!stack.isEmpty()){
                    
                    if(!stack.peek().northWall && !visited(stack.peek().row - 1, stack.peek().col)){
                        Cell north = maze[stack.peek().row - 1][stack.peek().col];
                        
                        if(north.getBackground() == Color.RED){
                            stack.peek().setBackground(Color.blue);
                            start.setBackground(Color.GREEN);
                            break;
                            
                        } else {
                        
                            stack.peek().setBackground(Color.blue);
                            start.setBackground(Color.GREEN);
                            stack.push(north);
                        }  
                    } else if (!stack.peek().southWall && !visited(stack.peek().row + 1, stack.peek().col)){
                        Cell south = maze[stack.peek().row + 1][stack.peek().col];
                        
                        if(south.getBackground() == Color.RED){
                            stack.peek().setBackground(Color.blue);
                            start.setBackground(Color.GREEN);
                            break;
                            
                        } else {
                        
                            stack.peek().setBackground(Color.blue);
                            start.setBackground(Color.GREEN);
                            stack.push(south); 
                        }
                    } else if (!stack.peek().eastWall && !visited(stack.peek().row, stack.peek().col + 1)){
                        Cell east = maze[stack.peek().row][stack.peek().col + 1];
                        
                        if(east.getBackground() == Color.RED){
                            stack.peek().setBackground(Color.blue);
                            start.setBackground(Color.GREEN);
                            break;
                        } else {
                        
                            stack.peek().setBackground(Color.blue);
                            start.setBackground(Color.GREEN);
                            stack.push(east); 
                        }
                    } else if (!stack.peek().westWall && !visited(stack.peek().row, stack.peek().col - 1)){
                        Cell west = maze[stack.peek().row][stack.peek().col - 1];
                        
                        if (west.getBackground() == Color.RED){
                            stack.peek().setBackground(Color.blue);
                            start.setBackground(Color.GREEN);
                            break;
                            
                        } else {
                            
                            stack.peek().setBackground(Color.blue);
                            start.setBackground(Color.GREEN);
                            stack.push(west);
                        }
                    } else {
                        stack.peek().setBackground(Color.YELLOW);
                        stack.pop();
                    }
                }
	}


	

	public void solveMazeQueue() {
		Queue<Cell> toVisitNext = new LinkedList<Cell>();
		
		Cell start = maze[0][0];
		start.setBackground(Color.CYAN);
		
		
		Cell finish = maze[rows -5][cols - 5];
		finish.setBackground(Color.RED);
		
		
		toVisitNext.offer(start);
		boolean done = false;
		while(!done && !toVisitNext.isEmpty()) {
			Cell current = toVisitNext.poll();
			current.setBackground(Color.BLUE);
			if(current == finish) {
				done = true;
			} else {
				
				if( !current.northWall  &&  !this.visited(current.row-1, current.col) ) {
					toVisitNext.offer(maze[current.row-1][current.col]);
					maze[current.row-1][current.col].setBackground(Color.GREEN);
				}
				if( !current.southWall  &&  !this.visited(current.row+1, current.col) ) {
					toVisitNext.offer(maze[current.row+1][current.col]);
					maze[current.row+1][current.col].setBackground(Color.GREEN);
				}
				if( !current.eastWall  &&  !this.visited(current.row, current.col+1) ) {
					toVisitNext.offer(maze[current.row][current.col+1]);
					maze[current.row][current.col+1].setBackground(Color.GREEN);
				}
				if( !current.westWall  &&  !this.visited(current.row, current.col-1) ) {
					toVisitNext.offer(maze[current.row][current.col-1]);
					maze[current.row][current.col-1].setBackground(Color.GREEN);
				}
				
				
			}
			
			
			
		}
		
		
	}



	public boolean visited(int row, int col) {
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if(status.equals(Color.WHITE)  || status.equals(Color.RED)  ) {
			return false;
		}


		return true;


	}


	public void genNWMaze() {
		
		for(int row = 0; row  < rows; row++) {
			for(int col = 0; col < cols; col++) {

				if(row == 0 && col ==0) {
					continue;
				}

				else if(row ==0) {
					maze[row][col].westWall = false;
					maze[row][col-1].eastWall = false;
				} else if(col == 0) {
					maze[row][col].northWall = false;
					maze[row-1][col].southWall = false;
				}else {
					boolean north = Math.random()  < 0.5;
					if(north ) {
						maze[row][col].northWall = false;
						maze[row-1][col].southWall = false;
					} else {  // remove west
						maze[row][col].westWall = false;
						maze[row][col-1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}
		this.repaint();
		
	}

	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800,800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows,cols));
		this.maze =  new Cell[rows][cols];
		for(int row = 0 ; row  < rows ; row++) {
			for(int col = 0; col < cols; col++) {

				maze[row][col] = new Cell(row,col);

				this.add(maze[row][col]);
			}

		}

                this.genDFSMaze();
		//this.genNWMaze();
		this.solveMaze();
		
	}




}
