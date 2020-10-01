import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class MusicTest1 implements ControllerEventListener
{
    static JFrame frame = new JFrame("Dancing rectumtangles");
    static  MyDrawPanel my;


    public static void main(String[] args)
    {
        MusicTest1 test1 = new MusicTest1();
        test1.play();
    }
    public void play()
    {
        setupGui();
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(this, new int[] {127});

            Sequence seq = new Sequence(Sequence.PPQ,4);

            Track track = seq.createTrack();


            for (int i = 5; i < 61; i+= 4){
                track.add(makeEvent(144,1,44,100,i));
                //test event
                track.add(makeEvent(176,1,127,100,i));

                track.add(makeEvent(128, 1, 44, 100, i + 2));

            }


            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();

        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    public void setupGui()
    {
        my = new MyDrawPanel();
        frame.setContentPane(my);
        frame.setBounds(30,30,300,300);
        frame.setVisible(true);
    }

    public static MidiEvent makeEvent (int comd, int chan, int one, int two, int tick)
    {
        MidiEvent event = null;

        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(comd, chan, one, two);
            event = new MidiEvent(message, tick);

        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public void controlChange(ShortMessage event) {
        //will register event occurence
        System.out.println("la");
        my.controlChange(event);

    }

    class MyDrawPanel extends JPanel implements ControllerEventListener {
        boolean msgReceived = false;

        @Override
        public void controlChange(ShortMessage event) {
            msgReceived = true;
            repaint(); //refresh the rectumtangle

        }
        public void paintComponent(Graphics g)
        {
            if(msgReceived){
                Graphics2D g2d = (Graphics2D) g; //cast

                int red = (int) (Math.random() * 255 );
                int green = (int) (Math.random() * 255 );
                int blue = (int) (Math.random() * 255 );
                g.setColor(new Color(red,green,blue));
                //rectumtangle dim

                int rectumHeight = (int) (Math.random() * 128 ) + 10;
                int rectumWidth = (int) (Math.random() * 128 ) + 10;
                int posX = (int) (Math.random() * 40 ) + 10;
                int posY = (int) (Math.random() * 40 ) + 10;
                g.fillRect(posX,posY,rectumWidth,rectumHeight);
                msgReceived = false;



            }
        }
    }


}
