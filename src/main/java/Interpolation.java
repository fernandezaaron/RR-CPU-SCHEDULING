import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Interpolation extends JFrame
{
    JScrollPane mainScroll, finalarrayscroll, codesimscrollpane, centerscroll;
    JPanel upper, upper2, upper3, center, contentPanel, row, centerPanel, rightPanel, indexNumberHeader, storedValuePanel, finalAnswerPanel, codeSimulationPanel, nextPrevPanel, codeSimulationPanelPanel;
    JLabel enterNumber, indexNumber, indexoffinalanswer;
    JTextField numberEntered, numberSearched, finalAnswer;
    JButton enter, clear, calculate, random, next, previous;
    JTextArea cs;


    JButton[] storedValue = new JButton[1000];
    JButton[] unsortedValue = new JButton[1000];
    int[] placeholder = new int[1000];

    JTextField[] gh = new JTextField[1000];
    int finalIndex;
    int click = 0;


    public Interpolation()
    {
        super("Interpolation Algorithm");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        enterNumber = new JLabel("Enter Array Count: ");
        enterNumber.setBounds(10,10,300,40);

        numberEntered = new JTextField(5);
        numberEntered.setBounds(120,20,50,20);

        finalAnswer = new JTextField(4);

        //Panel Settings
        //1
        upper = new JPanel();
        upper.setBackground(Color.WHITE);
        upper.setLayout(new BorderLayout());

        //2
        upper2 = new JPanel();
        upper2.setLayout(new BorderLayout());
        upper2.setPreferredSize(new Dimension(1280,50));

        //3
        center = new JPanel();
        center.setLayout(new BorderLayout());
        center.setPreferredSize(new Dimension(0,0));

        //4
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        //5
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        centerscroll = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerscroll.setPreferredSize(new Dimension(20,20));

        //6
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(1050,0));

        //7
        indexNumberHeader = new JPanel();
        indexNumberHeader.setLayout(new FlowLayout(FlowLayout.LEFT));

        //8
        upper3 = new JPanel();
        upper3.setLayout(new FlowLayout());
        upper3.setPreferredSize(new Dimension(600,50));

        //9
        finalAnswerPanel = new JPanel();
        finalAnswerPanel.setLayout(new FlowLayout());
        finalAnswerPanel.setBackground(Color.lightGray);

        //10
        codeSimulationPanel = new JPanel();
        codeSimulationPanel.setLayout(new BorderLayout());
        codeSimulationPanel.setBackground(Color.pink);

        //11
        nextPrevPanel = new JPanel();
        nextPrevPanel.setLayout(new FlowLayout());
        nextPrevPanel.setBackground(Color.lightGray);

        //12
        codeSimulationPanelPanel = new JPanel();
        codeSimulationPanelPanel.setLayout(new BorderLayout());
        codeSimulationPanelPanel.setPreferredSize(new Dimension(800,400));

        indexNumber = new JLabel("Index No.");

        mainScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScroll.setPreferredSize(new Dimension(0,0));

        indexNumberHeader.add(indexNumber);

        upper2.add(upper3, BorderLayout.CENTER);
        upper2.add(indexNumberHeader, BorderLayout.WEST);

        upper3.add(enterNumber);
        upper3.add(numberEntered);

        center.add(mainScroll, BorderLayout.CENTER);

        upper.add(upper2, BorderLayout.NORTH);
        upper.add(center, BorderLayout.CENTER);

        add(upper);
        contentPanel.add(centerscroll);

        //Enter button settings
        enter = new JButton(new AbstractAction("Enter")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int size = Integer.parseInt(numberEntered.getText());

                if(size > 0) //checks if the input is greater than 0
                {
                    enter.setEnabled(false);
                    numberEntered.setEditable(false);
                    upper2.setVisible(true);

                    row = new JPanel();
                    row.setLayout(new GridBagLayout());
                    GridBagConstraints numberHeader = new GridBagConstraints();
                    numberHeader.fill = GridBagConstraints.BOTH;
                    numberHeader.gridx = 0;
                    numberHeader.weighty = 1;


                    mainScroll.setRowHeaderView(row);

                    for (int i = 1; i <= size; i++) //generates the textfields for input
                    {
                        String n = String.valueOf(i);

                        gh[i] = new JTextField();
                        gh[i].setPreferredSize(new Dimension(20,20));

                        JLabel bh = new JLabel(n);
                        bh.setPreferredSize(new Dimension(40,20));

                        centerPanel.add(gh[i]);
                        row.add(bh,numberHeader);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                contentPanel.add(rightPanel, BorderLayout.EAST);
                calculate.setEnabled(true);
                clear.setEnabled(true);
            }
        });

        next = new JButton(new AbstractAction("Next")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int size = Integer.parseInt(numberEntered.getText());
                click++;
                System.out.println(click);
                if(click == 1){
                    for(int i=1; i<=size; i++){ //views the unsorted arrays
                        unsortedValue[i].setVisible(true);
                        storedValue[i].setVisible(false);
                        finalAnswer.setVisible(false);
                    }
                    cs.setText(" for (int i = 0; i < arr.length; i++)\n" +
                            "        {\n" +
                            "            for (int j = i + 1; j < arr.length; j++)\n" +
                            "            {\n" +
                            "                int temp;\n" +
                            "\n" +
                            "                if(arr[i] > arr[j])\n" +
                            "                {\n" +
                            "                    temp = arr[i];\n" +
                            "                    arr[i] = arr[j];\n" +
                            "                    arr[j] = temp;\n" +
                            "                }\n" +
                            "            }");
                }
               else if(click == 2){
                    for(int i=1; i<=size; i++){
                        unsortedValue[i].setVisible(false);
                        storedValue[i].setVisible(true);
                        finalAnswer.setVisible(true);
                    }
                    cs.setText(" int low = 1;\n" +
                            "            int high = arr.length - 1;\n" +
                            "\n" +
                            "            while(search >= arr[low] && search <= arr[high] && low <= high)\n" +
                            "            {\n" +
                            "                int position = low + ((high - low) * (search - arr[low])) / ((arr[high] - arr[low]));\n" +
                            "\n" +
                            "                if(high == low)\n" +
                            "                {\n" +
                            "                    if(arr[low] == search)\n" +
                            "                    {\n" +
                            "\n" +
                            "                        return low;\n" +
                            "                    }\n" +
                            "                    else\n" +
                            "                    {\n" +
                            "                        return -1;\n" +
                            "                    }\n" +
                            "                }\n" +
                            "\n" +
                            "                if(arr[position] == search)\n" +
                            "                {\n" +
                            "                    return position;\n" +
                            "                }\n" +
                            "\n" +
                            "                if(arr[position] < search)\n" +
                            "                {\n" +
                            "                    low = position + 1;\n" +
                            "                }\n" +
                            "                else\n" +
                            "                {\n" +
                            "                    high = position - 1;\n" +
                            "                }\n" +
                            "            }\n" +
                            "        }\n" +
                            "        return -1;\n" +
                            "    }");
                }
               else {
                   click = 0;
                }

            }
        });

        previous = new JButton(new AbstractAction("Previous")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int size = Integer.parseInt(numberEntered.getText());
                click--;
                if(click == 0){
                    cs.setText("CODE SIM PRESS NEXT");
                }
                if(click == 1){
                    for(int i=1; i<=size; i++){ //views the unsorted arrays
                        unsortedValue[i].setVisible(true);
                        storedValue[i].setVisible(false);
                        finalAnswer.setVisible(false);
                    }
                    cs.setText(" for (int i = 0; i < arr.length; i++)\n" +
                            "        {\n" +
                            "            for (int j = i + 1; j < arr.length; j++)\n" +
                            "            {\n" +
                            "                int temp;\n" +
                            "\n" +
                            "                if(arr[i] > arr[j])\n" +
                            "                {\n" +
                            "                    temp = arr[i];\n" +
                            "                    arr[i] = arr[j];\n" +
                            "                    arr[j] = temp;\n" +
                            "                }\n" +
                            "            }");
                }
                else if(click == 2){
                    for(int i=1; i<=size; i++){
                        unsortedValue[i].setVisible(false);
                        storedValue[i].setVisible(true);
                        finalAnswer.setVisible(true);
                    }
                    cs.setText(" int low = 1;\n" +
                            "            int high = arr.length - 1;\n" +
                            "\n" +
                            "            while(search >= arr[low] && search <= arr[high] && low <= high)\n" +
                            "            {\n" +
                            "                int position = low + ((high - low) * (search - arr[low])) / ((arr[high] - arr[low]));\n" +
                            "\n" +
                            "                if(high == low)\n" +
                            "                {\n" +
                            "                    if(arr[low] == search)\n" +
                            "                    {\n" +
                            "\n" +
                            "                        return low;\n" +
                            "                    }\n" +
                            "                    else\n" +
                            "                    {\n" +
                            "                        return -1;\n" +
                            "                    }\n" +
                            "                }\n" +
                            "\n" +
                            "                if(arr[position] == search)\n" +
                            "                {\n" +
                            "                    return position;\n" +
                            "                }\n" +
                            "\n" +
                            "                if(arr[position] < search)\n" +
                            "                {\n" +
                            "                    low = position + 1;\n" +
                            "                }\n" +
                            "                else\n" +
                            "                {\n" +
                            "                    high = position - 1;\n" +
                            "                }\n" +
                            "            }\n" +
                            "        }\n" +
                            "        return -1;\n" +
                            "    }");
                }
                else{
                    click = 3;
                }

            }
        });

        //calculate button
        calculate = new JButton(new AbstractAction("Search")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int search = Integer.parseInt(numberSearched.getText());
                int showValue = Integer.parseInt(numberEntered.getText());
                int[] arr = new int[showValue + 1];

                //9
                storedValuePanel = new JPanel();
                storedValuePanel.setLayout(new GridBagLayout());

                finalarrayscroll = new JScrollPane(storedValuePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                finalarrayscroll.setPreferredSize(new Dimension(80,0));

                System.out.println("Values that are stored in Array: ");
                for (int i = 1; i <= showValue; i++)
                {
                    Integer.parseInt(gh[i].getText());
                    arr[i] = Integer.parseInt(gh[i].getText());
                    placeholder[i] = arr[i];
                    System.out.println("- " + arr[i]);
                    gh[i].setEditable(false);
                }

                for(int i=0; i<arr.length; i++){
                    for(int j=i+1; j<arr.length; j++){
                        int tmp=0;
                        if(arr[i] > arr[j]){
                            tmp = arr[i];
                            arr[i] = arr[j];
                            arr[j] = tmp;
                        }
                    }
                    System.out.println(arr[i]);
                }

                for (int i = 1; i <= showValue; i++)
                {
                    unsortedValue[i] = new JButton(String.valueOf(placeholder[i])); //unsorted array
                    GridBagConstraints buttonHeader1 = new GridBagConstraints();
                    buttonHeader1.fill = GridBagConstraints.HORIZONTAL;
                    buttonHeader1.gridx = i;
                    unsortedValue[i].setPreferredSize(new Dimension(80, 20));
                    storedValuePanel.add(unsortedValue[i], buttonHeader1);
                    unsortedValue[i].setVisible(false);

                    storedValue[i] = new JButton(String.valueOf(arr[i])); //sorted array and final array value
                    GridBagConstraints buttonHeader = new GridBagConstraints();
                    buttonHeader.fill = GridBagConstraints.HORIZONTAL;
                    buttonHeader.gridx = i;
                    storedValue[i].setPreferredSize(new Dimension(80, 20));
                    storedValuePanel.add(storedValue[i], buttonHeader);
                }

                finalAnswer.setEditable(false);
                int index = interpolationSearch(arr, search);
                finalIndex = index;
                indexoffinalanswer = new JLabel("Index of Final Answer: ");
                finalAnswer.setText(String.valueOf(finalIndex));
                storedValue[index].setBackground(Color.pink);

                calculate.setEnabled(false);
                finalAnswerPanel.add(indexoffinalanswer);
                finalAnswerPanel.add(finalAnswer);

                cs = new JTextArea("CODE SIM PRESS NEXT");
                codesimscrollpane = new JScrollPane(cs, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                nextPrevPanel.add(previous);
                nextPrevPanel.add(next);
                codeSimulationPanel.add(nextPrevPanel, BorderLayout.SOUTH);
                codeSimulationPanelPanel.add(codesimscrollpane,BorderLayout.CENTER);
                codeSimulationPanel.add(codeSimulationPanelPanel,BorderLayout.CENTER);
                rightPanel.add(codeSimulationPanel, BorderLayout.SOUTH);
                codeSimulationPanel.add(finalAnswerPanel, BorderLayout.NORTH);
                rightPanel.add(finalarrayscroll, BorderLayout.CENTER);
                cs.setEditable(false);
                rightPanel.updateUI();
            }
        });

        calculate.setEnabled(false);

        numberSearched = new JTextField(5);

        //Search button
        random = new JButton(new AbstractAction("Random")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int showValue = Integer.parseInt(numberEntered.getText());
                gh[showValue+1] = new JTextField();
                for(int i=1; i<=showValue; i++) { //randomize inputs with a maximum value of 100 and minimum of 1
                    double rand = Math.random() * (100 - 1 + 1) + 1;
                    int converter = (int) rand;
                    System.out.println(converter);
                    gh[i].setText(String.valueOf(converter));
                    while(rand == Integer.parseInt(gh[i].getText())){
                        rand = Math.random() * (100 - 1 + 1) + 1;
                        converter = (int) rand;
                        System.out.println(converter);
                        gh[i].setText(String.valueOf(converter));
                    }
                }
            }
        });

   //     clear button
        clear = new JButton(new AbstractAction("Clear")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                numberEntered.setText("");
                numberEntered.setEditable(true);
                enter.setEnabled(true);
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                storedValuePanel.removeAll();
                storedValuePanel.revalidate();
                storedValuePanel.repaint();
                finalAnswerPanel.removeAll();
                finalAnswerPanel.revalidate();
                finalAnswerPanel.repaint();
                centerPanel.removeAll();
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        clear.setEnabled(false);

        upper3.add(enter);
        upper3.add(random);
        upper3.add(calculate);
        upper3.add(numberSearched);
        upper3.add(clear);

        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static int interpolationSearch(int[] arr, int search)
    {
        for (int i = 0; i < arr.length; i++) //this for loop sorts the array
        {
            for (int j = i + 1; j < arr.length; j++)
            {
                int temp;

                if(arr[i] > arr[j])
                {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            int low = 1;
            int high = arr.length - 1;

            while(search >= arr[low] && search <= arr[high] && low <= high) //while search is greater than the lowest array and less than high array
            {
                int position = low + ((high - low) * (search - arr[low])) / ((arr[high] - arr[low]));

                if(high == low)
                {
                    if(arr[low] == search)
                    {

                        return low; //returns the lowest value
                    }
                    else
                    {
                        return -1; //if not found return -1
                    }
                }

                if(arr[position] == search)
                {
                    return position;
                }

                if(arr[position] < search)
                {
                    low = position + 1;
                }
                else
                {
                    high = position - 1;
                }
            }
        }
        return -1;
    }
}