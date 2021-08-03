import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Frame extends JFrame {

    JPanel upper, center, upper2, upper3, centerleft, centermid, centerright, lower, centerup, lowerup, lowercenter, centerrightdown;
    JTextField Processes, TimeQuantum, ATA,AWT;
    JButton enter, calculate, random, clear, next, prev;
    JLabel plabel,tqlabel;
    JTable table1, table2;
    DefaultTableModel dtm1, dtm2;
    JScrollPane scroll1, scroll2, scroll3, scrollcodesim;
    JPanel row;
    JTextArea codesim;
    JButton[] ganttchart = new JButton[1000];
    int[] arrival;
    int[] burst;
    double averageturnaroundtime;
    double averagewaitingtime;
    int click = 0;

    public Frame(){
        super("CPU SCHEDULING");

        random = new JButton(new AbstractAction("Random") {
            @Override
            public void actionPerformed(ActionEvent e) {
                double r1;

                //sets random values to Table A
                int size = Integer.parseInt(Processes.getText());
                for(int i = 0; i < size; i++) {
                        int parsingDouble;
                        r1 = Math.random() * (size - 1 + 1) + 1;
                        parsingDouble = (int) r1;
                        dtm1.setValueAt(parsingDouble, i, 0);

                }

                double r2;
                for(int i=0; i<size; i++){
                        int parsingInt;
                        r2 = Math.random() * (100-1 + 1) + 1;
                        parsingInt = (int) r2;
                        dtm1.setValueAt(parsingInt, i, 1);
                }

                int parse;
                double r3;
                r3 = Math.random()*(10-1+1)+1;
                parse = (int) r3;
                TimeQuantum.setText(String.valueOf(parse));
            }
        });

        enter = new JButton(new AbstractAction("Enter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(Processes.getText());
                if(size <=0){
                    JOptionPane.showMessageDialog(null,"Input a number greater than 0", "WARNING",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    dtm1 = new DefaultTableModel(size, 2);
                    dtm1.setColumnIdentifiers(new Object[]{"Arrival Time", "Burst Time"});
                    table1 = new JTable(dtm1);
                    scroll1 = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    dtm2 = new DefaultTableModel(size, 3);
                    dtm2.setColumnIdentifiers(new Object[]{"Completion Time", "Waiting Time", "Turnaround Time"});
                    table2 = new JTable(dtm2);
                    scroll2 = new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    row = new JPanel();
                    row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
                    for (int i = 1; i <= size; i++) {
                        JLabel asd = new JLabel("P" + i);
                        row.add(asd);
                    }
                    JViewport jv = new JViewport();
                    jv.setView(row);
                    scroll1.setRowHeader(jv);

                    TimeQuantum = new JTextField(7);
                    tqlabel = new JLabel("Enter Time Quantum: ");
                    centerup.add(tqlabel);
                    centerup.add(TimeQuantum);
                    centerup.add(random);
                    centerup.add(calculate);

                    centermid.add(scroll2);
                    centermid.updateUI();
                    centerleft.add(scroll1);
                    centerleft.updateUI();
                }
            }
        });

        calculate = new JButton(new AbstractAction("Calculate") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tq = Integer.parseInt(TimeQuantum.getText());
                int size = Integer.parseInt(Processes.getText());
                arrival = new int[size];
                burst = new int[size];
                for(int i=0; i<size;i++){
                    arrival[i] = Integer.parseInt(String.valueOf(dtm1.getValueAt(i,0)));
                    System.out.print(arrival[i] + " ");
                }
                System.out.println();
                for(int i=0; i<size;i++){
                    burst[i] = Integer.parseInt(String.valueOf(dtm1.getValueAt(i,1)));
                    System.out.print(burst[i] + " ");
                }
                RRScheduling(arrival,burst,tq,size);

                ATA = new JTextField(30);
                ATA.setText("Average Turnaround Time: " + averageturnaroundtime);
                lowerup.add(ATA);

                AWT = new JTextField(30);
                AWT.setText("Average Waiting Time: " + averagewaitingtime);
                lowerup.add(AWT);

                ATA.setEditable(false);
                AWT.setEditable(false);
                lowerup.updateUI();
            }
        });

        next = new JButton(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {
                click++;
                Iteration(click);
                if(click == 4){
                    click = 0;
                }
            }
        });

        prev = new JButton(new AbstractAction("Prev") {
            @Override
            public void actionPerformed(ActionEvent e) {
                click--;
                Iteration(click);
                if(click == 0){
                    click = 4;
                }
            }
        });

        clear = new JButton(new AbstractAction("Clear") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Processes.setText("");
                TimeQuantum.setText("");
                centerup.removeAll();
                centerup.revalidate();
                centerup.repaint();
                centerleft.removeAll();
                centerleft.revalidate();
                centerleft.repaint();
                centermid.removeAll();
                centermid.revalidate();
                centermid.repaint();
                lowercenter.removeAll();
                lowercenter.revalidate();
                lowercenter.repaint();
                lowerup.removeAll();
                lowerup.revalidate();
                lowerup.repaint();
            }
        });

        upper = new JPanel(new BorderLayout());
        upper2 = new JPanel(new FlowLayout());
        upper3 = new JPanel(new FlowLayout());

        Processes = new JTextField(7);
        plabel = new JLabel("Enter number of Processes: ");

        center = new JPanel(new BorderLayout());

        centerleft = new JPanel(new BorderLayout());

        centermid = new JPanel(new BorderLayout());

        centerright = new JPanel(new BorderLayout());
        centerright.setPreferredSize(new Dimension(400,100));

        centerrightdown = new JPanel(new FlowLayout());
        centerrightdown.add(prev);
        centerrightdown.add(next);

        codesim = new JTextArea();

        scrollcodesim = new JScrollPane(codesim, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centerright.add(scrollcodesim, BorderLayout.CENTER);
        centerright.add(centerrightdown,BorderLayout.SOUTH);

        centerup = new JPanel(new FlowLayout());

        lower = new JPanel(new BorderLayout());
        lower.setPreferredSize(new Dimension(300,200));

        lowerup = new JPanel(new FlowLayout());

        lowercenter = new JPanel(new FlowLayout());

        scroll3 = new JScrollPane(lowercenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        lower.add(scroll3, BorderLayout.CENTER);
        lower.add(lowerup,BorderLayout.NORTH);
        center.add(centerup, BorderLayout.NORTH);
        center.add(centerright,BorderLayout.EAST);
        center.add(centermid,BorderLayout.CENTER);
        center.add(centerleft,BorderLayout.WEST);
        upper2.add(enter);
        upper2.add(clear);
        upper3.add(plabel);
        upper3.add(Processes);
        upper.add(upper3, BorderLayout.CENTER);
        upper.add(upper2, BorderLayout.SOUTH);
        add(upper, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(lower, BorderLayout.SOUTH);
        setSize(1280,720);
        setVisible(true);
    }
    public void RRScheduling(int[] arrtime, int[] bursttime, int timequantum, int size){
            int[] remainingtime = new int[size];
            int[] waitingtime = new int[size];
            int[] completiontime = new int[size];
            int[] turnaroundtime = new int[size];

            int total = 0;
            int total1 = 0;

            for(int i=0; i<size; i++){
                remainingtime[i] = bursttime[i];
                waitingtime[i] = 0;
            }
            int time = 0;

            //time quantum = 3
            do {
                boolean stopper = true;
               for (int i=0; i<size; i++) {

                  if (remainingtime[i] > 0) {
                      stopper = false;
                       if (remainingtime[i] > timequantum) {
                           //susubtract yung burst time sa time quantum until remainingtime ng 5 = 0;
                           remainingtime[i] = remainingtime[i] - timequantum; //records remaining time for that index 2 = 5-3
                           //remainingtime[i] = bursttime[i];
                           time = time + timequantum; //3 = 0+3
                           completiontime[i] = time;
                           System.out.println("if");
                           System.out.println("Remaining Time: " + i + ": " + remainingtime[i]);
                           ganttchart[i] = new JButton("P"+String.valueOf(i+1));
                           lowercenter.add(ganttchart[i]);
                           System.out.println(i + ": " + time);
                           dtm2.setValueAt(time,i,0);

                       } else {
                           //if bursttime is less than timequantum wala na dapat remaining time to so deretso completion time na for that shit?
                           time = time + remainingtime[i]; //completion time
                           completiontime[i] = time; //pang store lang ng completion time for that index
                           remainingtime[i] = 0; //wala naman nang natira
                           System.out.println("else");
                           System.out.println(i + ": " + time);
                           ganttchart[i] = new JButton("P"+String.valueOf(i+1));
                           lowercenter.add(ganttchart[i]);
                           dtm2.setValueAt(time,i,0);
                       }
                   }

                   ganttchart[i].setEnabled(false);
                   ganttchart[i].setPreferredSize(new Dimension(100,40));
                   lowercenter.updateUI();
                   turnaroundtime[i] = completiontime[i] - arrtime[i];
                   dtm2.setValueAt(turnaroundtime[i], i, 2);
                   waitingtime[i] = turnaroundtime[i] - bursttime[i];
                   System.out.println("Waiting Time: " + i + ": " + waitingtime[i]);
                   dtm2.setValueAt(waitingtime[i],i,1);
               }
               if(stopper){
                   break;
               }
            }while(true);

            for(int i=0; i<turnaroundtime.length; i++){
                total = total + turnaroundtime[i];
            }
         averageturnaroundtime = total / turnaroundtime.length;
        System.out.println(averageturnaroundtime);

        for(int i=0; i<waitingtime.length; i++){
            total1 = total1 + waitingtime[i];
        }
        averagewaitingtime = (double)total1 / (double)waitingtime.length;
        System.out.println(averagewaitingtime);
    }
    public void Iteration (int click){
        if(click == 1){
            codesim.setText("int[] remainingtime = new int[size];\n" +
                    "            int[] waitingtime = new int[size];\n" +
                    "            int[] completiontime = new int[size];\n" +
                    "            int[] turnaroundtime = new int[size];\n" +
                    "\n" +
                    "            int total = 0;\n" +
                    "            int total1 = 0;\n" +
                    "\n" +
                    "            for(int i=0; i<size; i++){\n" +
                    "                remainingtime[i] = bursttime[i];\n" +
                    "                waitingtime[i] = 0;\n" +
                    "            }\n" +
                    "            int time = 0;\n" +
                    "            int j=0;");

        }
        if(click == 2){
            codesim.setText("  do {\n" +
                    "                boolean stopper = true;\n" +
                    "               for (int i=0; i<size; i++) {\n" +
                    "\n" +
                    "                  if (remainingtime[i] > 0) {\n" +
                    "                      stopper = false;\n" +
                    "                       if (remainingtime[i] > timequantum) {\n" +
                    "                           remainingtime[i] = remainingtime[i] - timequantum;\n" +
                    "                           time = time + timequantum; \n" +
                    "                           completiontime[i] = time;\n" +
                    "                           ganttchart[i] = new JButton(String.valueOf(i+1));\n" +
                    "                           lowercenter.add(ganttchart[i]);\n" +
                    "                           System.out.println(i + \": \" + time);\n" +
                    "                           dtm2.setValueAt(time,i,0);\n" +
                    "\n" +
                    "                       } else {\n" +
                    "                           time = time + remainingtime[i]; //completion time\n" +
                    "                           completiontime[i] = time; \n" +
                    "                           remainingtime[i] = 0; \n" +
                    "                           ganttchart[i] = new JButton(String.valueOf(i+1));\n" +
                    "                           lowercenter.add(ganttchart[i]);\n" +
                    "                           dtm2.setValueAt(time,i,0);\n" +
                    "                       }\n" +
                    "                   }\n" +
                    "\n" +
                    "                   ganttchart[i].setEnabled(false);\n" +
                    "                   ganttchart[i].setPreferredSize(new Dimension(100,40));\n" +
                    "                   lowercenter.updateUI();\n" +
                    "                   turnaroundtime[i] = completiontime[i] - arrtime[i];\n" +
                    "                   dtm2.setValueAt(turnaroundtime[i], i, 2);\n" +
                    "                   waitingtime[i] = turnaroundtime[i] - bursttime[i];\n" +
                    "                   dtm2.setValueAt(waitingtime[i],i,1);\n" +
                    "               }\n" +
                    "               if(stopper){\n" +
                    "                   break;\n" +
                    "               }\n" +
                    "            }while(true);");
        }
        if (click == 3){
            codesim.setText(" for(int i=0; i<turnaroundtime.length; i++){\n" +
                    "                total = total + turnaroundtime[i];\n" +
                    "            }\n" +
                    "         averageturnaroundtime = total / turnaroundtime.length;\n" +
                    "\n" +
                    "        for(int i=0; i<waitingtime.length; i++){\n" +
                    "            total1 = total1 + waitingtime[i];\n" +
                    "        }\n" +
                    "        averagewaitingtime = (double)total1 / (double)waitingtime.length;\n");
        }

    }
}
