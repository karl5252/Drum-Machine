import javax.sound.midi.*;

public class MusicTest1
{
    public void play()
    {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            Sequence seq = new Sequence(Sequence.PPQ,4);

            Track track = seq.createTrack();

            /*ShortMessage mes1 = new ShortMessage();
            mes1.setMessage(144,1,44,100);
            MidiEvent noteOn = new MidiEvent(mes1, 1);
            track.add(noteOn);

            ShortMessage mes2 = new ShortMessage();
            mes1.setMessage(128,1,44,100);
            MidiEvent noteOff = new MidiEvent(mes2, 16);
            track.add(noteOff);*/
            for (int i = 5; i < 61; i+= 4){
                track.add(makeEvent(144,1,44,100,i));

                track.add(makeEvent(128, 1, 44, 100, i + 2));

            }


            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();

        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        MusicTest1 test1 = new MusicTest1();
        test1.play();
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
}
