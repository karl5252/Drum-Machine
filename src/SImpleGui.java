import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SImpleGui
{
    JFrame frame;
    JLabel label;
    int x = 0;
    int y = 0;

    public static void main(String[] args) {
        SImpleGui gui = new SImpleGui();
        gui.go();
    }

    public void go()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton = new JButton("Change label");
        labelButton.addActionListener(new LabelListener());

        JButton colorButton = new JButton("Change color");
        colorButton.addActionListener(new ColorListener());

        label= new JLabel("Just a label");
        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);
        frame.setSize(350,300);
        frame.setVisible(true);

        for (int i = 0; i < 150; i ++)
        {
            x++;
            y++;
            drawPanel.repaint();

            try{
                Thread.sleep(5);
            }catch (Exception e){
                System.out.println(e.toString());
            }

        }
    }





    private class LabelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("Ouch");

        }
    }

    private class ColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            frame.repaint();


        }
    }
    private class MyDrawPanel extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            /*g.setColor((Color.green));
            g.fillOval(x,y,100,100);*/
            g.setColor(Color.white);
            g.fillRect(0,0,this.getWidth(), this.getHeight());
            Graphics2D g2d = (Graphics2D) g;
            int red = (int) (Math.random() * 255 );
            int green = (int) (Math.random() * 255 );
            int blue = (int) (Math.random() * 255 );
            Color startColor = new Color(red,green,blue);

            red = (int) (Math.random() * 255 );
            green = (int) (Math.random() * 255 );
            blue = (int) (Math.random() * 255 );
            Color endColor = new Color (red,green,blue);

            GradientPaint gradientPaint = new GradientPaint(70,70, startColor,150,150, endColor);
            g2d.setPaint(gradientPaint);
            //g2d.fillOval(70,70,100,100);
            g2d.fillOval(x,y,10,10);
        }
    }

}
