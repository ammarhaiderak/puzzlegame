/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JFrame;



/**
 *
 * @author ammar
 */
public class PuzzleGame {

    private MyGame obj;
    private char level='1';
    private int difficulty=0;
    private Integer diff[];
    
    public PuzzleGame(){
        diff=new Integer[]{9,16};
    
    }
    
    public void run(){
        obj=new MyGame(diff[difficulty],level);
        new stage1(this);
    }
    
    
    private void changeStage(){
        this.level++;
        if(level=='7'){
          level='1';
        
        if(difficulty<1)
            difficulty++;
        }
        run();
    }
    
    
    public boolean evaluate(){
    
        if(this.obj.evaluate())
        {
        this.obj.dispose();
        this.changeStage();
        return true;
        }
        return false;
    
    }
    
    public void failed(){
        this.obj.displayMessage(2);
        this.obj=new MyGame(9,level);
        new stage1(this);       //restarting same stage
    }
    
    public void solve(){
    
        this.obj.solve();
    }
    
    
}
