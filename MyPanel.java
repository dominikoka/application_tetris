import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.nio.file.Files;
import java.nio.file.Paths;
 
class MyPanel extends JPanel implements KeyListener{
 
        public MyPanel () {        	
                setPreferredSize(new Dimension(750, 500));
                addKeyListener(this);
        }
        List<Integer> wins = new ArrayList<>();
        int totalResult = 0;
        Random r = new Random();
        int numberOfFigure = r.nextInt(7)+1;
        //int numberOfFigure = 3;
        int x1,x2,x3,x4,y1,y2,y3,y4;
        boolean switchNewFigure = true;
        List<Boolean> asd = Collections.nCopies(5,true);
        
        int totalMove = 0;
        int totalMoveZero = 20;
        int taskDelay = 500;
        boolean randomFigure = true;
        int randomF;
        int lvl = 0;
        boolean theEndInformation = false;
        public void run() throws InterruptedException {
          timer.start();
          proccesor();
          asd.set(2, true);
        }
        public void proccesor()
        {
          if(randomFigure)
          {
            randomF = r.nextInt(7)+1;
            //randomF = 3;
            repaint();
            randomFigure = false;
          }
          map();
          boolean colision = collision(x1,x2,x3,x4,y1,y2,y3,y4,0);
          if(colision)
          {
            timer.stop();
            timer = new Timer(taskDelay, taskPerformer);
            timer.start();
            switchNewFigure=true;
            numberOfFigure = randomF;
            randomFigure = true;
          }
          if(switchNewFigure)
          {
           if(totalMove == 1)
           {
            switchNewFigure = false;
            timer.stop();
            theEndInformation = true;
           }
            totalMove=0;
           List<Integer> wholeLine =  wholeLine();  
           if(wholeLine.size()>0)
            {
            deleteWholeLine(wholeLine);
            rebuildAfterDelete(wholeLine);
            }
            figure(numberOfFigure);
            repaint();
            switchNewFigure=false;
          }
          map();
        }
        ActionListener taskPerformer = new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
              y1=y1+25;
              y2=y2+25;
              y3=y3+25;
              y4=y4+25;
              proccesor();
              repaint();
              totalMove++;
          }
      };
      public void lvlup()
      {
        if(totalResult%50 == 0)
        {
          if(lvl<7){
          timer.stop();
          taskDelay = taskDelay-50;
          timer = new Timer(taskDelay, taskPerformer);
          timer.start();
          lvl++;}
        }
      }
      Timer timer = new Timer(taskDelay,taskPerformer);
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
              
              colorList.set(minLine+counterMoveDown, colorList.get(minLine));
              colorList.set(minLine,0);
            }
            minLine--;
          }
        }
        public void deleteWholeLine(List<Integer> wholeLine)
        {
          for(int i=0; i< wholeLine.size(); i++)
          {
            int line = wholeLine.get(i)*10;
            int limit= line+10;
            while(line<limit)
            {
              pointsValue.set(line, true);
              colorList.set(line, 0);
              line++;
            }
          }
          totalResult = totalResult+10;
          lvlup();
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
              lineList.add(line);
            }  
          }
          return lineList;
        }
        Color figureColor;
        public void figure(int number)
        {
          switch(number){
            case 1:
              x1=375;x2=375;x3=375;x4=375;y1=0;y2=25;y3=50;y4=75;
              break;
            case 2:
              x1=350;x2=375;x3=350;x4=375;y1=0;y2=0;y3=25;y4=25;
              break;
            case 3:
              x1=350;x2=375;x3=375;x4=375;y1=25;y2=0;y3=25;y4=50;
              break;
            case 4:
              x1=350;x2=350;x3=375;x4=400;y1=0;y2=25;y3=25;y4=25;
              break;
            case 5:
              x1=375;x2=400;x3=350;x4=375;y1=0;y2=0;y3=25;y4=25;
              break;
            case 6:
              x1=400;x2=350;x3=375;x4=400;y1=0;y2=25;y3=25;y4=25;
              break;
            case 7:
              x1=350;x2=375;x3=375;x4=400;y1=0;y2=0;y3=25;y4=25;
          }
        }
        public List<Integer> color(int number)
        {
          List<Integer> colorRGB = new ArrayList<Integer>();
          switch(number)
          {
            case 1:
            colorRGB.add(0, 235);
            colorRGB.add(1, 64);
            colorRGB.add(2, 52);
            break;
            case 2:
            colorRGB.add(0, 235);
            colorRGB.add(1, 205);
            colorRGB.add(2, 52);
            break;
            case 3:
            colorRGB.add(0, 162);
            colorRGB.add(1, 235);
            colorRGB.add(2, 52);
            break;
            case 4:
            colorRGB.add(0, 52);
            colorRGB.add(1, 125);
            colorRGB.add(2, 235);
            break;
            case 5:
            colorRGB.add(0, 153);
            colorRGB.add(1, 52);
            colorRGB.add(2, 235);
            break;
            case 6:
            colorRGB.add(0, 235);
            colorRGB.add(1, 52);
            colorRGB.add(2, 220);
            break;
            case 7:
            colorRGB.add(0, 28);
            colorRGB.add(1, 26);
            colorRGB.add(2, 28);
            break;
          }
          return colorRGB;
        }
        public void figureRotation(int number)
        {
          boolean move = true;
          switch(number)
          {
            case 1:
              if(x1==x2 && move && !collision(x1-50, x2-25, x3, x4+25, y2, y2, y2, y2, 1))
              {
                y1=y2; y3=y2; y4=y2; x1=x1-50; x2=x2-25; x4=x4+25;
                move = false;
              }
              if(x1!=x2&& move &&!collision(x1+50, x2+25, x3, x4-25, y1-25, y2, y3+25, y4+50, 1))
              {
                y1=y1-25;  y3=y3+25;y4=y4+50; x1=x1+50; x2=x2+25; x4=x4-25;
                move = false;
              }
              break;
            case 3:
              if(x2==x3 && x3==x4 && x1+25==x2 && move && !collision(x1+25, x2+25, x3, x4-25, y1-25, y2+25, y3, y4-25,1))
              {
                x1=x1+25;y1=y1-25;y2=y2+25;x2=x2+25;y4=y4-25;x4=x4-25;
                move = false;
              }
              if(y2==y3 && y3==y4 && y1+25 ==y2 && move&& !collision(x1, x2-50, x3-25, x4, y1+25, y2-25, y3, y4+25,1) )
              {
                y1=y1+25;x2=x2-50;y2=y2-25;x3=x3-25;y4=y4+25;
                move = false;
              }
              if(x2==x3 && x3==x4 && x1-25==x2 && move && !collision(x1, x2+50, x3+25, x4, y1, y2, y3-25, y4-50,1))
              {
                x2=x2+50;x3=x3+25;y3=y3-25;y4=y4-50;
                move=false;
              }
              if(y2==y3 && y3==y4 && y1-25 ==y2 && move && !collision(x1-25, x2-25  , x3, x4+25, y1, y2, y3+25, y4+50,1))
              {
                x1=x1-25;x2=x2-25;y3=y3+25;x4=x4+25;y4=y4+50;
                move = false;
              }
              break;
            case 4:
              if(y2== y3 && y3 == y4 && y1+25==y2 &&move &&!collision(x1+25, x2, x3-25, x4-50, y1, y2-25, y3, y4+25, 1))
              {
                x1=x1+25;y2=y2-25;x3=x3-25;x4=x4-50;y4=y4+25;
                move = false;
              }
              if(x2==x3 && x3==x4 &&x1-25==x2 && move && !collision(x1+25, x2+50, x3+25, x4, y1+25, y2, y3-25, y4-50, 1))
              {
                y4=y4-50;y3=y3-25;x3=x3+25;x2=x2+50;x1=x1+25;y1=y1+25;
                move=false;
              }
              if(y2==y3&& y3==y4 &&y1-25==y2 && move&& !collision(x1-25, x2-25, x3, x4, y1, y2, y3+50, y4+50, 1) )
              {
                x1=x1-25;x2=x2-25;y3=y3+50;y4=y4+50;
                move=false;
              }
              if(x2==x3 && x3==x1 && y3==y4 && move && !collision(x1-25, x2-25, x3, x4+50, y1-25, y2+25, y3-25, y4-25, 1))
              {
                x1=x1-25;y1=y1-25;x2=x2-25;y2=y2+25;y3=y3-25;x4=x4+50;y4=y4-25;
                move = false;
              }
              break;
              case 5:
              {
                if(y1==y2 && y3-25 == y2 && move&& !collision( x1, x2-25, x3, x4-25,y1+25, y2+50, y3-25, y4, 1))
                {
                  y1=y1+25;x2=x2-25;y2=y2+50;y3=y3-25;x4=x4-25; 
                  move = false;
                }
                if(y1+25 == y2 && move && !collision(x1, x2+25 , x3, x4+25, y1-25, y2-50, y3+25, y4, 1))
                {
                  y1=y1-25;x2=x2+25;y2=y2-50;y3=y3+25;x4=x4+25;
                  move = false;
                }
                break;
              }
              case 7:
              {
                if(y1==y2 && y3==y4 && x2==x3 && move && !collision(x1+25, x2, x3-25, x4-50, y1, y2+25, y3, y4+25, 1))
                {
                  x1=x1+25; x3=x3-25;x4=x4-50;y4=y4+25;y2=y2+25; 
                  move = false;
                }
                if(x3+25 == x2 && move && !collision(x1-25,x2,x3+25,x4+50,y1,y2-25,y3,y4-25,1))
                {
                  x1=x1-25;x3=x3+25;x4=x4+50;y4=y4-25;y2=y2-25;
                  move = false;
                }
                break;
              }
              case 6:
              {
                if(x1==x4 && y2==y3 && y1+25 ==y2 && move && !collision(x1-25, x2, x3-25, x4-50, y1+50, y2-25, y3, y4+25, 1))
                {
                  x1=x1-25;y1=y1+50;y2=y2-25;x3=x3-25;x4=x4-50;y4=y4+25;
                  move = false;
                }
                if(x2==x3&& x3==x4&&y2+50==y4 && move &&!collision(x1-25, x2+50, x3+25, x4, y1-25, y2, y3-25, y4-50, 1))
                {
                  x1=x1-25;y1=y1-25;x2=x2+50;x3=x3+25;y3=y3-25;y4=y4-50;
                  move=false;
                }
                if(y2==y3&& y3==y4&&x1==x4 && move && !collision(x1, x2-25, x3, x4+25, y1-25, y2+50, y3+25, y4, 1))
                {
                  y1=y1-25;x2=x2-25;y2=y2+50;y3=y3+25;x4=x4+25;
                  move=false;
                }
                if(y1==y4 &&x2==x3 && move && !collision(x1+50, x2-25, x3, x4+25, y1, y2-25, y3, y4+25, 1))
                {
                  x1=x1+50;x2=x2-25;y2=y2-25;x4=x4+25;y4=y4+25;
                  move=false;
                }
                break;
              }
            }
          }

        List<Boolean> pointsValue = new ArrayList<>(Collections.nCopies(200, true));        
        List<Integer>colorList = new ArrayList<>(Collections.nCopies(200,0));
        
        Integer[][] points;
        
        public void map()
        {
          points = new Integer[][]
          {
            {250,0},  {275,0},  {300,0},  {325,0},  {350,0},  {375,0},  {400,0},  {425,0},  {450,0},  {475,0},
            {250,25}, {275,25}, {300,25}, {325,25}, {350,25}, {375,25}, {400,25}, {425,25}, {450,25}, {475,25},
            {250,50}, {275,50}, {300,50}, {325,50}, {350,50}, {375,50}, {400,50}, {425,50}, {420,50}, {475,50},
            {250,75}, {275,75}, {300,75}, {325,75}, {350,75}, {375,75}, {400,75}, {425,75}, {450,75}, {475,75},
            {250,100},{275,100},{300,100},{325,100},{350,100},{375,100},{400,100},{425,100},{450,100},{475,100},
            {250,125},{275,125},{300,125},{325,125},{350,125},{375,125},{400,125},{425,125},{450,125},{475,125},
            {250,150},{275,150},{300,150},{325,150},{350,150},{375,150},{400,150},{425,150},{450,150},{475,150},
            {250,175},{275,175},{300,175},{325,175},{350,175},{375,175},{400,175},{425,175},{450,175},{475,175},
            {250,200},{275,200},{300,200},{325,200},{350,200},{375,200},{400,200},{425,200},{450,200},{475,200},
            {250,225},{275,225},{300,225},{325,225},{350,225},{375,225},{400,225},{425,225},{450,225},{475,225},
            {250,250},{275,250},{300,250},{325,250},{350,250},{375,250},{400,250},{425,250},{450,250},{475,250},
            {250,275},{275,275},{300,275},{325,275},{350,275},{375,275},{400,275},{425,275},{450,275},{475,275},
            {250,300},{275,300},{300,300},{325,300},{350,300},{375,300},{400,300},{425,300},{450,300},{475,300},
            {250,325},{275,325},{300,325},{325,325},{350,325},{375,325},{400,325},{425,325},{450,325},{475,325},
            {250,350},{275,350},{300,350},{325,350},{350,350},{375,350},{400,350},{425,350},{450,350},{475,350},
            {250,375},{275,375},{300,375},{325,375},{350,375},{375,375},{400,375},{425,375},{450,375},{475,375},
            {250,400},{275,400},{300,400},{325,400},{350,400},{375,400},{400,400},{425,400},{450,400},{475,400},
            {250,425},{275,425},{300,425},{325,425},{350,425},{375,425},{400,425},{425,425},{450,425},{475,425},
            {250,450},{275,450},{300,450},{325,450},{350,450},{375,450},{400,450},{425,450},{450,450},{475,450},
            {250,475},{275,475},{300,475},{325,475},{350,475},{375,475},{400,475},{425,475},{450,475},{475,475},        
          };
        }
        public boolean collision(int x1, int x2, int x3, int x4, int y1, int y2, int y3, int y4, int switchFigure)
        {
          if((y1==475 ||y2==475||y3==475||y4==475) && switchFigure==0)
          {
            saveFigure();
            return true;
          }
          if((y1>=475 ||y2>=475||y3>=475||y4>=475) && switchFigure==1)
          {
            return true;
          }
          for (int i = 0; i < points.length; i++) {
              boolean arg1 = x1 == points[i][0]&& y1 == points[i][1] -25&& pointsValue.get(i) == false;
              boolean arg2 = x2 == points[i][0] && y2 == points[i][1] -25&& pointsValue.get(i) == false;
              boolean arg3 = x3 == points[i][0] && y3 == points[i][1] -25&& pointsValue.get(i) == false;
              boolean arg4 = x4 == points[i][0] && y4 == points[i][1] -25&& pointsValue.get(i) == false;
              if((arg1 ||arg2 || arg3 || arg4) && switchFigure == 0)
              {
                saveFigure();
                return true;
            }
            boolean arg1rotation = x1 == points[i][0]&& y1 == points[i][1] && pointsValue.get(i) == false;
            boolean arg2rotation = x2 == points[i][0] && y2 == points[i][1] && pointsValue.get(i) == false;
            boolean arg3rotation = x3 == points[i][0] && y3 == points[i][1] && pointsValue.get(i) == false;
            boolean arg4rotation = x4 == points[i][0] && y4 == points[i][1] && pointsValue.get(i) == false;
            if((arg1rotation ||arg2rotation || arg3rotation || arg4rotation) && switchFigure == 1)
              {
                return true;
              }
              if(((x1>=500 || x2>=500 || x3>=500 || x4>=500) &&switchFigure == 1) || ((x1<250 || x2<250 || x3<250 || x4<250) &&switchFigure == 1))
              {
                return true;
              }
          }
          return false;
        }
        public void saveFigure()
        {
          for (int i = 0; i < points.length; i++) {
            {
              if(x1== points[i][0] && y1 == points[i][1])
              {
                pointsValue.set(i, false);
                colorList.set(i, numberOfFigure);
              }
              if(x2== points[i][0] && y2 == points[i][1])
              {
                pointsValue.set(i, false);
                colorList.set(i, numberOfFigure);
              }
              if(x3== points[i][0] && y3 == points[i][1])
              {
                pointsValue.set(i, false);
                colorList.set(i, numberOfFigure);
              }
              if(x4== points[i][0] && y4 == points[i][1])
              {
                pointsValue.set(i, false);
                colorList.set(i, numberOfFigure);
              }
              }    
            }
        }
        boolean isPause=false;
        boolean keyboard=true;
        public void pause()
        {
          if(isPause)
          {
            isPause = false;
            keyboard = true;
            timer.start();
            repaint();
          }
          else
          {
            isPause=true;
            keyboard = false;
            timer.stop();
            repaint();
          }
        }
        public boolean tryMove(int edge, int move)
        {
          if(x1==edge || x2 == edge || x3 == edge || x4 == edge)
          {
            return false;
          }
          for(int i = 0; i<points.length; i++)
          {
            if(x1+move == points[i][0] && y1 == points[i][1] && pointsValue.get(i) == false)
            {
              return false;
            }
            if(x2+move == points[i][0] && y2 == points[i][1] && pointsValue.get(i) == false)
            {
              return false;
            }
            if(x3+move == points[i][0] && y3 == points[i][1] && pointsValue.get(i) == false)
            {
              return false;
            }
            if(x4+move == points[i][0] && y4 == points[i][1] && pointsValue.get(i) == false)
            {
              return false;
            }
          }
          return true;
        }
        //
        public void saveGame() throws IOException
        {
          timer.stop();
          JFileChooser save = new JFileChooser("C:\\folderek");
          int resultClick = save.showSaveDialog(null);
          if(resultClick == JFileChooser.CANCEL_OPTION)
          {
            timer.start();
          }
          else
          {
            File file = save.getSelectedFile();
            String fullPath = file.getAbsolutePath()+".txt";
            FileWriter fileWriter = new FileWriter(fullPath);
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.println(x1);
            writer.println(x2);
            writer.println(x3);
            writer.println(x4);
            writer.println(y1);
            writer.println(y2);
            writer.println(y3);
            writer.println(y4);
            for(int i=0; i<pointsValue.size();i++)
            {
              writer.println(pointsValue.get(i));
            }
            for (int i = 0; i < colorList.size(); i++) {
              writer.println(colorList.get(i));
            }

            writer.println(totalResult);
            writer.close();
            timer.start();
          }
        }
        public void loadGame() throws IOException
        {
          timer.stop();
          JFileChooser load = new JFileChooser("C:\\folderek");
          int resultClick = load.showOpenDialog(null);
          if(resultClick == JFileChooser.CANCEL_OPTION)
          {
            timer.start();
          }
          else
          {
          String file = load.getSelectedFile().toString();
          List<String> allLines = Files.readAllLines(Paths.get(file));
          x1 = Integer.parseInt(allLines.get(0));
          x2 = Integer.parseInt(allLines.get(1));
          x3 = Integer.parseInt(allLines.get(2));
          x4 = Integer.parseInt(allLines.get(3));
          y1 = Integer.parseInt(allLines.get(4));
          y2 = Integer.parseInt(allLines.get(5));
          y3 = Integer.parseInt(allLines.get(6));
          y4 = Integer.parseInt(allLines.get(7));
          for(int i = 8; i<208; i++)
          {
            boolean value = Boolean.parseBoolean(allLines.get(i));
            pointsValue.set(i-8,  value);
          }
          for (int i = 208; i < allLines.size()-1; i++) {
            Integer arg = Integer.parseInt(allLines.get(i));
            colorList.set(i-208, arg);
          }
          totalResult = Integer.parseInt(allLines.get(allLines.size()-1));
          repaint();
          timer.start();}
        }
        public void newGame()
        {
          wins.add(totalResult);
          sortWins();
          switchNewFigure = true;
          pointsValue = new ArrayList<>(Collections.nCopies(200, true));
          theEndInformation = false;
          totalResult = 0;
          timer.stop();
          taskDelay = 500;
          timer = new Timer(taskDelay, taskPerformer);
          timer.start();
        }
        public void sortWins()
        {
          for (int i = 0; i < wins.size(); i++) {
            for (int j = i+1; j < wins.size(); j++) {
              int a = wins.get(i);
              int b = wins.get(j);
              if(a<b)
              {
                wins.set(i, b);
                wins.set(j, a);
              }
            }
          }
        }
        public List<Integer> figureCreate(int number)
        {
          List<Integer> listXY = new ArrayList<>();
          switch(number){
            case 1:
              listXY.add(0,50);
              listXY.add(1, 75);
              listXY.add(2, 100);
              listXY.add(3, 125);
              listXY.add(4, 300);
              listXY.add(5, 300);
              listXY.add(6, 300);
              listXY.add(7, 300);
            break;
            case 2:
            listXY.add(0, 50);
            listXY.add(1, 75);
            listXY.add(2, 75);
            listXY.add(3, 50);
            listXY.add(4, 300);
            listXY.add(5, 300);
            listXY.add(6, 325);
            listXY.add(7, 325);
              break;
            case 3:
            listXY.add(0, 50);
            listXY.add(1, 75);
            listXY.add(2, 75);
            listXY.add(3, 75);
            listXY.add(4, 325);
            listXY.add(5, 300);
            listXY.add(6, 325);
            listXY.add(7, 350);
            break;
            case 4:
            listXY.add(0, 50);
            listXY.add(1, 50);
            listXY.add(2, 75);
            listXY.add(3, 100);
            listXY.add(4, 300);
            listXY.add(5, 325);
            listXY.add(6, 325);
            listXY.add(7, 325);
              break;
            case 5:
            listXY.add(0, 50);
            listXY.add(1, 75);
            listXY.add(2, 75);
            listXY.add(3, 100);
            listXY.add(4, 300);
            listXY.add(5, 300);
            listXY.add(6, 325);
            listXY.add(7, 325);
            break;
            case 6:
            listXY.add(0, 50);
            listXY.add(1, 75);
            listXY.add(2, 100);
            listXY.add(3, 100);
            listXY.add(4, 325);
            listXY.add(5, 325);
            listXY.add(6, 325);
            listXY.add(7, 300);
            break;
            case 7:
            listXY.add(0, 50);
            listXY.add(1, 75);
            listXY.add(2, 75);
            listXY.add(3, 100);
            listXY.add(4, 300);
            listXY.add(5, 300);
            listXY.add(6, 325);
            listXY.add(7, 325);
            break;
          }

          return listXY;
        }
        public void paintComponent (Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;
            g2.drawString("Every 5 lines you gain a level,", 50, 465);
            g2.drawString("and the blocks move faster.", 50, 485);

            
            g2.drawString("FULL LINES: " + totalResult/10, 50, 70);
            if(lvl == 6)
            {
              g2.drawString("MAXIMUM LVL !!", 50, 50);
            }
            else
            {
              g2.drawString("YOUR LEVEL: " + lvl, 50, 50);
            }
            g2.drawString("F: DROP", 50, 100);
            g2.drawString("A: LEFT", 50, 120);
            g2.drawString("W: RIGHT", 50, 140);
            g2.drawString("S: DOWN", 50, 160);
            g2.drawString("SPACE: ROTATE", 50, 180);
            g2.drawString("X: SAVE GAME", 50, 200);
            g2.drawString("C: LOAD GAME", 50, 220);
            g2.drawString("Z: NEW GAME", 50, 240);
            g2.drawString("P: PAUSE", 50, 260);
            g2.drawString("NEXT: ", 50, 280);
             //g2.drawString(""+randomF, 50, 300);
             List<Integer> listOfFigure = figureCreate(randomF);
             g2.drawRect(listOfFigure.get(0), listOfFigure.get(4), 25, 25);
             g2.drawRect(listOfFigure.get(1), listOfFigure.get(5), 25, 25);
             g2.drawRect(listOfFigure.get(2), listOfFigure.get(6), 25, 25);
             g2.drawRect(listOfFigure.get(3), listOfFigure.get(7), 25, 25);

             g2.drawString("YOUR SCORE: " + totalResult, 50, 450);


            if(isPause)
            {
              g2.drawString("PAUSE", 370, 100);
            }
            if(theEndInformation)
            {
              g2.drawString("YOU LOOSE. YOUR SCORE: " + totalResult, 370, 100);
              theEndInformation = false;
            }
            g2.drawString("BEST SCORE: ", 570, 50);
            for (int i = 0; i < wins.size(); i++) {
              if(i<3){
              g2.drawString(i+1 +" " + wins.get(i) , 570, 50+((i+1)*20));
              }
            }

            g2.setColor(Color.WHITE);
            g2.drawLine(250,0,250,700);
            g2.drawLine(500,0,500,700);
            g2.setColor(new Color(color(numberOfFigure).get(0),color(numberOfFigure).get(1),color(numberOfFigure).get(2)));
            g2.fillRect(x1, y1, 25, 25);
            g2.fillRect(x2, y2, 25, 25);
            g2.fillRect(x3, y3, 25, 25);
            g2.fillRect(x4, y4, 25, 25);
            
              for (int i = 0; i < points.length; i++) {
                if(pointsValue.get(i) == false)
                {
                  List<Integer> color = color(colorList.get(i));
                   g2.setColor(new Color(color.get(0), color.get(1), color.get(2)));
                  g2.fillRect(points[i][0], points[i][1], 25, 25);

                }
              }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
       
        @Override
        public void keyPressed(KeyEvent e) {
          Integer c= e.getKeyCode();
          if(!keyboard && !(c==80 || c==88))
          {c=0;}
          switch(c){
            case 83:
              y1=y1+25; y2=y2+25; y3=y3+25; y4=y4+25;
              totalMove++;
                repaint();
                proccesor();
              break;
            case 68:
              if(tryMove(475,25))
              {
              x1=x1+25; x2=x2+25; x3=x3+25; x4=x4+25;
              //paint(getGraphics());
              repaint();
              proccesor();
              }
              break;
            case 65:
              if(tryMove(250,-25)){
              x1=x1-25; x2=x2-25; x3=x3-25; x4=x4-25;
              //paint(getGraphics());
              repaint();
              proccesor();}
              break;
            case 32:
              figureRotation(numberOfFigure);
              //paint(getGraphics());
              repaint();
              break;
            case 88:
              try {
                saveGame();
              } catch (IOException e1) {
                timer.start();
              }
              break;
            case 90:
              newGame();
              break;
            case 67:
              try {
                loadGame();
              } catch (IOException e1) {
              }
              break;
            case 81:
              break;
            case 70:
              timer.stop();
              timer = new Timer(1, taskPerformer);
              timer.start();
              break;
            case 80:
              pause();
              break;
          }
          }
        @Override
        public void keyReleased(KeyEvent e) {
        }
}