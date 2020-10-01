import javax.sound.midi.*;

public class MusicTest1 implements ControllerEventListener
{
    public static void main(String[] args)
    {
        MusicTest1 test1 = new MusicTest1();
        test1.play();
    }
    public void play()
    {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] eventsIWant = {127};
            sequencer.addControllerEventListener(this, eventsIWant);

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

    }
}
