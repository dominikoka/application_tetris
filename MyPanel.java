import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
 
class MyPanel extends JPanel implements KeyListener{
 
        public MyPanel () {        	
                setPreferredSize(new Dimension(500, 987));
                addKeyListener(this);
        }

        Random r = new Random();
        int numberOfFigure = r.nextInt(7)+1;
        int x1,x2,x3,x4,y1,y2,y3,y4;
        boolean switchNewFigure = true;
        
        public void proccesor() throws InterruptedException {
          map(); 
          
          boolean colision = collision();
          if(colision)
          {
            //figure(numberOfFigure);
            switchNewFigure=true;
            numberOfFigure = r.nextInt(7)+1;
          }
          if(switchNewFigure)
          {
           List<Integer> wholeLine =  wholeLine();  
           if(wholeLine.size()>0)
            {
            deleteWholeLine(wholeLine);
            rebuildAfterDelete(wholeLine);
            }
            figure(numberOfFigure);
            paint(getGraphics());
            switchNewFigure=false;
          }

          //automaticMoveFigure();
          
        }
        public void automaticMoveFigure() throws InterruptedException
        {
          while(true){
          Thread.sleep(500);
          y1=y1+50;
          y2=y2+50;
          y3=y3+50;
          y4=y4+50;
          paint(getGraphics());
        }
        }

        public void rebuildAfterDelete(List<Integer> wholeLine)
        {
          int minLine=100;
          for(int i=0; i < wholeLine.size(); i++)
          {
            if(minLine>wholeLine.get(i))
            {
              minLine = wholeLine.get(i);
            }
            
          }
          minLine=minLine*10-1;
          int counterMoveDown = wholeLine.size() *10;
          
          while(minLine>=0)
          {
            if(pointsValue.get(minLine) == false)
            {
              pointsValue.set(minLine, true);
              pointsValue.set(minLine+counterMoveDown, false);
            }
            minLine--;
          }
        }
        public void deleteWholeLine(List<Integer> wholeLine)
        {
          for(int i=0; i< wholeLine.size(); i++)
          {
            System.out.println("tutej usuwamy linie o numerze tyn: " + wholeLine.get(i));
            int line = wholeLine.get(i)*10;
            int limit= line+10;
            while(line<limit)
            {
              pointsValue.set(line, true);
              line++;
            }
          }
        }
        public List<Integer> wholeLine()
        {
          List<Integer> lineList = new ArrayList<>();
          int counter = 0;
          for (int i = 0; i < points.length; i++) {
            if(i%10==0)
            {
              counter=0;
            }
            if(pointsValue.get(i)==false)
            {
              counter++;
            }
            if(counter == 10)
            {
              int line = i/10;
              System.out.println(line);
              lineList.add(line);
            }  
          }
          return lineList;
        }
        public void figure(int number)
        {
          switch(number){
            case 1:
              x1=250;x2=250;x3=250;x4=250;y1=0;y2=50;y3=100;y4=150;
              break;
            case 2:
              x1=200;x2=250;x3=200;x4=250;y1=00;y2=0;y3=50;y4=50;
              break;
            case 3:
              x1=200;x2=250;x3=250;x4=250;y1=50;y2=0;y3=50;y4=100;
              break;
            case 4:
              x1=200;x2=200;x3=250;x4=300;y1=0;y2=50;y3=50;y4=50;
              break;
            case 5:
              x1=250;x2=300;x3=200;x4=250;y1=0;y2=0;y3=50;y4=50;
              break;
            case 6:
              x1=300;x2=200;x3=250;x4=300;y1=0;y2=50;y3=50;y4=50;
              break;
            case 7:
              x1=200;x2=250;x3=250;x4=300;y1=0;y2=0;y3=50;y4=50;
          }
        }
        public void figureRotation(int number)
        {boolean move = true;
          switch(number)
          {
            case 1:
              if(x1==x2)
              {
                y1=y2; y3=y2; y4=y2; x1=x1-100; x2=x2-50; x4=x4+50;
                
              }
              else
              {
                y1=y1-50;  y3=y3+50;y4=y4+100; x1=x1+100; x2=x2+50; x4=x4-50;
                
              }
            case 3:
              if(x2==x3 && x3==x4 && x1+50==x2 && move)
              {
                x1=x1+50;y1=y1-50;y2=y2+50;x2=x2+50;y4=y4-50;x4=x4-50;
                move = false;
              }
              if(y2==y3 && y3==y4 && y1+50 ==y2 && move)
              {
                y1=y1+50;x2=x2-100;y2=y2-50;x3=x3-50;y4=y4+50;
                move = false;
              }
              if(x2==x3 && x3==x4 && x1-50==x2 && move)
              {
                x2=x2+100;x3=x3+50;y3=y3-50;y4=y4-100;
                move=false;
              }
              if(y2==y3 && y3==y4 && y1-50 ==y2 && move)
              {
                x1=x1-50;x2=x2-50;y3=y3+50;x4=x4+50;y4=y4+100;
                move = false;
              }
            case 4:
              if(y2== y3 && y3 == y4 && y1+50==y2 &&move)
              {
                x1=x1+50;y2=y2-50;x3=x3-50;x4=x4-100;y4=y4+50;
                move = false;
              }
              if(x2==x3 && x3==x4 &&x1-50==x2 && move)
              {
                y4=y4-100;y3=y3-50;x3=x3+50;x2=x2+100;x1=x1+50;y1=y1+50;
                move=false;
              }
              if(y2==y3&& y3==y4 &&y1-50==y2 && move )
              {
                
                x1=x1-50;x2=x2-50;y3=y3+100;y4=y4+100;
                move=false;
              }
              if(x2==x3 && x3==x1 && y3==y4 && move)
              {
                x1=x1-50;y1=y1-50;x2=x2-50;y2=y2+50;y3=y3-50;x4=x4+100;y4=y4-50;
                move = false;
              }
              break;
              case 5:
              {
                if(y1==y2 && y3-50 == y2 && move)
                {
                   y1=y1+50;x2=x2-50;y2=y2+100;y3=y3-50;x4=x4-50; 
                  //y1=y1+150;
  
                }
                else
                {
                  y1=y1-50;x2=x2+50;y2=y2-100;y3=y3+50;x4=x4+50;
                //   System.out.println("dla x1 " + x1 + " dla y1 " + y1);
                // System.out.println("dla x2 " + x2 + " dla y2 " + y2);
                // System.out.println("dla x3 " + x3 + " dla y3 " + y3);
                // System.out.println("dla x4 " + x4 + " dla y4 " + y4);
                }
                break;
              }
              case 7:
              {
                if(y1==y2 && y3==y4 && x2==x3 && move)
                {
                  x1=x1+50; x3=x3-50;x4=x4-100;y4=y4+50;y2=y2+50; 
                }
                else
                {
                  x1=x1-50;x3=x3+50;x4=x4+100;y4=y4-50;y2=y2-50;
                }
                break;
              }
              case 6:
              {
                if(x1==x4 && y2==y3 && y1+50 ==y2 && move)
                {
                  x1=x1-50;y1=y1+100;y2=y2-50;x3=x3-50;x4=x4-100;y4=y4+50;
                  move = false;
                  
                move=false;
                }
                if(x2==x3&& x3==x4&&y2+100==y4 && move)
                {
                  x1=x1-50;y1=y1-50;x2=x2+100;x3=x3+50;y3=y3-50;y4=y4-100;
                  move=false;
                }
                if(y2==y3&& y3==y4&&x1==x4 && move)
                {
                  y1=y1-50;x2=x2-50;y2=y2+100;y3=y3+50;x4=x4+50;
                  move=false;
                }
                if(y1==y4 &&x2==x3 && move)
                {
                  x1=x1+100;x2=x2-50;y2=y2-50;x4=x4+50;y4=y4+50;
                  move=false;
                  
                }
                break;
              }
            }
          }

        List<Boolean> pointsValue = new ArrayList<>(Collections.nCopies(200, true));
        Integer[][] points;
        
        public void map()
        {
          points = new Integer[][]
          {
            {0,0},  {50,0},  {100,0},  {150,0},  {200,0},  {250,0},  {300,0},  {350,0},  {400,0},  {450,0},
            {0,50}, {50,50}, {100,50}, {150,50}, {200,50}, {250,50}, {300,50}, {350,50}, {400,50}, {450,50},
            {0,100},{50,100},{100,100},{150,100},{200,100},{250,100},{300,100},{350,100},{400,100},{450,100},
            {0,150},{50,150},{100,150},{150,150},{200,150},{250,150},{300,150},{350,150},{400,150},{450,150},
            {0,200},{50,200},{100,200},{150,200},{200,200},{250,200},{300,200},{350,200},{400,200},{450,200},
            {0,250},{50,250},{100,250},{150,250},{200,250},{250,250},{300,250},{350,250},{400,250},{450,250},
            {0,300},{50,300},{100,300},{150,300},{200,300},{250,300},{300,300},{350,300},{400,300},{450,300},
            {0,350},{50,350},{100,350},{150,350},{200,350},{250,350},{300,350},{350,350},{400,350},{450,350},
            {0,400},{50,400},{100,400},{150,400},{200,400},{250,400},{300,400},{350,400},{400,400},{450,400},
            {0,450},{50,450},{100,450},{150,450},{200,450},{250,450},{300,450},{350,450},{400,450},{450,450},
            {0,500},{50,500},{100,500},{150,500},{200,500},{250,500},{300,500},{350,500},{400,500},{450,500},
            {0,550},{50,550},{100,550},{150,550},{200,550},{250,550},{300,550},{350,550},{400,550},{450,550},
            {0,600},{50,600},{100,600},{150,600},{200,600},{250,600},{300,600},{350,600},{400,600},{450,600},
            {0,650},{50,650},{100,650},{150,650},{200,650},{250,650},{300,650},{350,650},{400,650},{450,650},
            {0,700},{50,700},{100,700},{150,700},{200,700},{250,700},{300,700},{350,700},{400,700},{450,700},
            {0,750},{50,750},{100,750},{150,750},{200,750},{250,750},{300,750},{350,750},{400,750},{450,750},
            {0,800},{50,800},{100,800},{150,800},{200,800},{250,800},{300,800},{350,800},{400,800},{450,800},
            {0,850},{50,850},{100,850},{150,850},{200,850},{250,850},{300,850},{350,850},{400,850},{450,850},
            {0,900},{50,900},{100,900},{150,900},{200,900},{250,900},{300,900},{350,900},{400,900},{450,900},
            {0,950},{50,950},{100,950},{150,950},{200,950},{250,950},{300,950},{350,950},{400,950},{450,950},
            
          };
          pointsValue.size();
          
        }
        boolean rysujStatyczny = false;
        public boolean collision()
        {
          System.out.println("-------- m o j   k l o c e k ------------");
          System.out.println("dla x1 " + x1 + " dla y1 " + y1);
          System.out.println("dla x2 " + x2 + " dla y2 " + y2);
          System.out.println("dla x3 " + x3 + " dla y3 " + y3);
          System.out.println("dla x4 " + x4 + " dla y4 " + y4);
          System.out.println("-------------------------------------------");
          List<List<Integer>> lowerEdge=new ArrayList<>(lowerEdgeOfTheFigure());
          for (int i = 0; i < lowerEdge.size(); i++) {
              System.out.println("dla x" + lowerEdge.get(i).get(0) + " y wynosi  " + lowerEdge.get(i).get(1));
          }
          
          if(y1==950 ||y2==950||y3==950||y4==950)
          {
            System.out.println("allarmo");
            
            saveFigure();
            return true;
          }

          for (int i = 0; i < points.length; i++) {
            for(int j = 0; j < lowerEdge.size();j++)
            {
              
              boolean arg1=(int)points[i][0] == (int)lowerEdge.get(j).get(0);
              boolean arg2 =points[i][1] ==lowerEdge.get(j).get(1)+50;

              if(arg1 && arg2 &&pointsValue.get(i)==false)
              {
                System.out.println("u w a g a  KKK o l i z j o n");
                saveFigure();
                return true;
              }
            }
          }
          return false;
        }
        public List<List<Integer>> lowerEdgeOfTheFigure()
        {
          List<Integer> squareOne=new ArrayList<>();
          List<Integer> squareTwo=new ArrayList<>();
          List<Integer> squareThree=new ArrayList<>();
          List<Integer> squareFour=new ArrayList<>();
          squareOne.add(x1);
          squareOne.add(y1);
          squareTwo.add(x2);
          squareTwo.add(y2);
          squareThree.add(x3);
          squareThree.add(y3);
          squareFour.add(x4);
          squareFour.add(y4);
          List<List<Integer>> lowerEdge=new ArrayList<>();
          lowerEdge.add(squareOne);
          lowerEdge.add(squareTwo);
          lowerEdge.add(squareThree);
          lowerEdge.add(squareFour);
        
          for (int i = 0; i < lowerEdge.size(); i++) {
            for (int j = 0; j < lowerEdge.size(); j++) {
              int elOne = lowerEdge.get(i).get(0);
              int elTwo =lowerEdge.get(j).get(0);
              if(elOne == elTwo&& i!=j)
              {
                if(lowerEdge.get(i).get(1) > lowerEdge.get(j).get(1))
                {
                  lowerEdge.remove(j);
                  i=0;
                }
                else
                {
                  lowerEdge.remove(i);
                  i=0;
                }
              }
            }
          }
          return lowerEdge;
        }

        public void saveFigure()
        {
          for (int i = 0; i < points.length; i++) {
            {
              //System.out.println(i);
              if(x1== points[i][0] && y1 == points[i][1])
              {
                System.out.println("zapisuje x1");
                pointsValue.set(i, false);
              }
              if(x2== points[i][0] && y2 == points[i][1])
              {
                System.out.println("zapisuje x2");
                pointsValue.set(i, false);
              }
              if(x3== points[i][0] && y3 == points[i][1])
              {
                System.out.println("zapisuje x3");
                pointsValue.set(i, false);
              }
              if(x4== points[i][0] && y4 == points[i][1])
              {
                System.out.println("zapisuje x4");
                pointsValue.set(i, false);
              }
              }    
            }
        }
 
        public void paintComponent (Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.fillRect(x1, y1, 50, 50);
            g2.fillRect(x2, y2, 50, 50);
            g2.fillRect(x3, y3, 50, 50);
            g2.fillRect(x4, y4, 50, 50);

              for (int i = 0; i < points.length; i++) {
                if(pointsValue.get(i) == false)
                {
                  //System.out.println("rysuje tyle razy");
                  g2.fillRect(points[i][0], points[i][1], 50, 50);
                }
                //rysujStatyczny=false;
              }
            
            //g2.setColor(C);
            
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
         
          Integer c = e.getKeyCode();
          switch(c){
            case 83:
            //figure(numberOfFigure);
              y1=y1+50; y2=y2+50; y3=y3+50; y4=y4+50;
              paint(getGraphics());
              //proccesor();
              try {
                proccesor();
              } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
              }
              break;
            case 68:
              x1=x1+50; x2=x2+50; x3=x3+50; x4=x4+50;
              paint(getGraphics());
              try {
                proccesor();
              } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
              }
              break;
            case 65:
              x1=x1-50; x2=x2-50; x3=x3-50; x4=x4-50;
              paint(getGraphics());
              try {
                proccesor();
              } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
              }
              break;
            case 32:
              figureRotation(numberOfFigure);
              collision();
              paint(getGraphics());
          }
          }

        @Override
        public void keyReleased(KeyEvent e) {
        }
 
}