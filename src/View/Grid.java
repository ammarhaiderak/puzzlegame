/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author ammar
 */
//Model Class
public class Grid {

    private MyView v;
    private Integer grid[][];
    public int size;
    private int rows;
    
    public Grid(MyView v,int size) {
//        grid = new int[][]{
//            {10, 10, 10},
//            {10, 10, 10},
//            {10, 10, 10}
//        };
        
        rows=(int)sqrt(size);
        //rows=3;
        this.v = v;
        this.size=size;
    }

    private int getindex(int val){
          int k=0;
         for(int i=0;i<rows;++i){
             for(int j=0;j<rows;++j){
                 if(grid[i][j]==val){
                 return k;
                 }
                 k++;
             }
         }   
         return -1;
    }
    
    public int getrows(){
        return this.rows;
    }
    
     public void initGrid(ArrayList<Integer> imgs){
         grid=new Integer[rows][rows];
         int k=0;
         for(int i=0;i<rows&&k<imgs.size();++i){
             for(int j=0;j<rows&&k<imgs.size();++j){
                 grid[i][j]=imgs.get(k);
                 k++;
             }
         }
     }
     
     public void printgrid(){
     
         for(int i=0;i<rows;++i){
             for(int j=0;j<rows;++j){
             System.out.print(grid[i][j]+",");
             }
             System.out.println();
         }
     }
     
    
    
    public int swap(int rowIndex, int colIndex, int val) {
        //boolean result = false;
         int left,right,up,down;
         left=-1;
         right=-1;
         up=-1;
         down=-1;
                  
        int newr=0;
        int newc=0;
        
        if(rowIndex==-1 || colIndex==-1){
            return -1;
        }
        
        if(colIndex>0){
        left=grid[rowIndex][colIndex-1];
        if(left==0){
            newr=rowIndex;
            newc=colIndex-1;
        }
        }
        if(colIndex<rows-1){
         right=grid[rowIndex][colIndex+1];
         if(right==0){
            newr=rowIndex;
            newc=colIndex+1;
         }
        }
        if(rowIndex>0){
        up=grid[rowIndex-1][colIndex];
         if(up==0){
            newr=rowIndex-1;
            newc=colIndex;
         }
         
        }
        if(rowIndex<rows-1){
        down=grid[rowIndex+1][colIndex];
         
         if(down==0){
            newr=rowIndex+1;
            newc=colIndex;
         }
         
        }
        
        
        int vall=-1;
        if(up==0||down==0||left==0||right==0){
            
            vall=getindex(0);
            int temp=grid[rowIndex][colIndex];
            grid[rowIndex][colIndex]=grid[newr][newc];
            grid[newr][newc]=temp;
        }

        return vall;
        
        //return result;
    }
    
    
   

    public boolean evaluate() {
        boolean found=true;
       
                int ref=1;
                for(int i=0;i<rows&&found&&ref<size;++i){
                    for(int j=0;j<rows&&found&&ref<size;++j){
                        if(grid[i][j]!=ref){
                            found=false;
                        }
                        ref++;
                    }
                }
            return found;    
           
            //if(found==true){
             //       v.displayMessage(1);
           // }

    }
    
    public void solve(){
                
        
        int ref=1;
        int i=0;
        int j=0;
                for(;i<rows&&ref<size;++i){
                    j=0;
                    for(;j<rows&&ref<size;++j){
                       grid[i][j]=ref;
                       ref++;
                    }
                }
        System.out.println(i-1+" "+j);
        grid[i-1][j]=0;
        //this.evaluate();
    
    }
    
}
