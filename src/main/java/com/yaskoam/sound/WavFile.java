package com.yaskoam.sound;

import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Q-YAA
 */
public class WavFile {

    private AudioInputStream audioInputStream;

    public WavFile(String fileName) {
        audioInputStream = createAudioInputStream(fileName);
    }

    public WavFile(AudioInputStream audioInputStream) {
        this.audioInputStream = audioInputStream;
    }

    public void write(String fileName) {
        try {
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(fileName));
        }
        catch (IOException e) {
            throw new IllegalStateException(String.format("Can't write audio to file '%s'.", fileName));
        }
    }

    public WavFile merge(WavFile wavFile) {
        SequenceInputStream sequenceInputStream = new SequenceInputStream(audioInputStream, wavFile.getAudioInputStream());
        long resultFrameLength = audioInputStream.getFrameLength() + wavFile.getAudioInputStream().getFrameLength();

        AudioInputStream resultAudioInputStream = new AudioInputStream(
            sequenceInputStream, audioInputStream.getFormat(), resultFrameLength);

        return new WavFile(resultAudioInputStream);
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }

    private AudioInputStream createAudioInputStream(String fileName) {
        try {
            return AudioSystem.getAudioInputStream(new File(fileName));
        }
        catch (UnsupportedAudioFileException e) {
            throw new IllegalStateException("Wrong audio file.", e);
        }
        catch (IOException e) {
            throw new IllegalStateException("Can't load audio file.", e);
        }
    }
}
