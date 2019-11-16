/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.ArrayList;

/**
 *
 * @author ammar
 */
public class Controller {

    private MyView v;       //view
    private Grid g;         //model

    public Controller(MyView v, Grid g) {
        this.v = v;
        this.g = g;
    }

    
    public void initGrid(ArrayList<Integer>imgs){
        g.initGrid(imgs);
        g.printgrid();
    }
    
    public void printgrid(){
        g.printgrid();
    
    }
    
    public int markOnGrid(int index) {
        
        int i,j,ind;
        i=0;
        ind=0;
        j=0;
        
        for(;i<g.getrows();++i){
            j=0;
            for(;j<g.getrows();++j){
                if(index==ind)
                    return g.swap(i, j, index);
                ind++;
            }
        }
        
        return -1;
         
        
    }
    
    public void solve(){
        g.solve();
    
    }

    public boolean evaluate(){
        if(g.evaluate()){
          v.displayMessage(1);
        }
        return g.evaluate();
    
    }
    
    
}
