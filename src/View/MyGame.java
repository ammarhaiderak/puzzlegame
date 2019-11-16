/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author ammar
, MyView
*/

public class MyGame extends JFrame implements ActionListener,MyView {

    //private JButton b00, b01, b02, b10, b11, b12, b20, b21, bRef;
    private JPanel panel,timerpanel;
    private String folder;
    private String sourcefolder="/images";
    private String basepath="/home/ammar/NetBeansProjects/PuzzleGame/src";
    
    private static int size;
    private ArrayList<JButton> arr;
    private ArrayList<Integer> imgs;
//    private ImageIcon cross;
//    private ImageIcon tick;
    
    private ImageIcon current;
    private final Controller c;
    private JButton currBtn;

    public MyGame(int size,char path) {
       
        this.folder=path+"/";
        if(size==16){
           sourcefolder+="2"; 
        }
        sourcefolder+="/";
        this.basepath=this.basepath+sourcefolder;
        this.basepath=this.basepath+folder;
        
        
        arr=new ArrayList<JButton>(size);
        imgs=new ArrayList<Integer>(size);
        this.size=size;
        c = new Controller(this, new Grid(this,size));
        initGui();
        
    }
    
   
    

    private void initGui() {

        // Container c = getContentPane();
        panel = new JPanel();
        //timerpanel=new JPanel();
        int rows=(int)sqrt(size);
        panel.setLayout(new GridLayout(rows, rows));
        //timerpanel.setLayout(new FlowLayout());
        //timerpanel.setSize(rows*200,100);
        
        
        
        
        setSize(rows*200, rows*200);
       
        //add(timerpanel);
       
        
        add(panel);
        
//        System.out.println(this.sizee);
//        ArrayList<JButton> 
        //arr=new ArrayList<JButton>();
        
       
        
        
        for (int i=0;i<size;i++){
            arr.add(new JButton(""));
            arr.get(i).addActionListener(this);
            imgs.add(i);
        }
        
        Collections.shuffle(imgs);
        c.initGrid(imgs);
        
        
        for(int i=0;i<size;++i){
            int imageno=imgs.get(i);
            //System.out.println(basepath+imageno+".jpg");

            if (imageno!=0){
                arr.get(i).setIcon(new ImageIcon(basepath+imageno+".jpg"));
              //  arr.get(i).setName(i+"");
                
            }
            else
            {
                arr.get(i).setVisible(false);
            }
            panel.add(arr.get(i));
        }
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void solve(){
    
        c.solve();
        c.printgrid();
    
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        currBtn = (JButton) e.getSource();
       // String index = currBtn.getName();
        
       //System.out.println(index);
       int i=0;
       for(;i<size;++i){
           if(arr.get(i)==currBtn){
               break;
           }
       }
       
       
       int x=c.markOnGrid(i);
       
       
       if (x!=-1){
       
       JButton X=arr.get(x);
    
       currBtn.setVisible(false);
       arr.get(x).setIcon(new ImageIcon(basepath+imgs.get(i)+".jpg"));
       arr.get(x).setVisible(true);
       
       
       int a=imgs.get(i);
       int b=imgs.get(x);
       
       imgs.set(i,b);
       imgs.set(x,a);
       
       
       c.printgrid();
       
       
       }
       
       System.out.println(i);
       System.out.println(x);
        
//       panel.notifyAll();
       
       
    }

    @Override
    public void updateGrid() {  //called from controller
        currBtn.setIcon(current);
    }

    @Override
    public void displayMessage(int status) {
  
        String msg="";
        
        if(status==0){
            msg="Sorry Try Again!";
        }
        
        
        else if (status==1)
        {
        msg="Congratulations you have successfully completed the puzzle! Click Ok to move to next stage";
        
        }
        else if(status==2){
        
            msg="you ran out of time!";
        }
        
        
        JOptionPane.showMessageDialog(null, msg);
        
        dispose();
    }
    
    public boolean evaluate(){    
        return c.evaluate();
    }
   

}

