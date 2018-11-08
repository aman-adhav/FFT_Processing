package pitchDetection;

import java.util.ArrayList;

import processing.core.PApplet;
import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

import ddf.minim.analysis.WindowFunction;
import ddf.minim.ugens.FilePlayer;

public class Main extends PApplet
{
	FilePlayer player;
	Minim minim;
	AudioInput in;
	float min;
	float max;
	                         
	float[] frequencies = {110.00f, 116.54f, 123.47f, 130.81f, 138.59f, 146.83f, 155.56f, 164.81f, 174.61f, 185.00f, 196.00f, 207.65f, 220.00f, 233.08f, 246.94f, 261.63f, 277.18f, 293.66f, 311.13f, 329.63f, 349.23f, 369.99f, 392f, 415.30f, 440.00f, 466.16f, 493.88f, 523.25f, 554.37f, 587.33f, 622.25f, 659.25f, 698.26f, 739.99f};
	//String[] spellings = {"D,", "E,", "F,", "G,", "A,", "B,", "C", "D", "E", "F", "G", "A", "B","c", "d", "e", "f", "g", "a", "b", "c'", "d'", "e'", "f'", "g'", "a'", "b'", "c''", "d''"}; 	
	String[] spellings = {"A","A#","B","C","C#","D","D#","E","F","F#","G","G#","A","A#","B","C","C#","D","D#","E","F","F#","G","G#","A","A#","B","C","C#","D","D#","E","F","F#"};
	int sampleRate = 10000;
	int frameSize = 1024;
	FFT fft;
	
	
	public void setup()
	{
		
		size(frameSize, 500);
		smooth();
		minim = new Minim(this);
		
		in = minim.getLineIn(Minim.MONO, frameSize, sampleRate, 16);
		fft = new FFT(frameSize, sampleRate);
		min = Float.MAX_VALUE;
		max = Float.MIN_VALUE;
	}
	
	public String spell(float frequency)
	{
		float minDiff = Float.MAX_VALUE;
		int minIndex = 0;
		for (int i = 0 ; i < frequencies.length; i ++)
		{
			float diff = Math.abs(frequencies[i] - frequency);
			if (diff < minDiff)
			{
				minDiff = diff;
				minIndex = i;
			}
		}
		return spellings[minIndex];
	}
	
	public int countZeroCrossings()
	{
		int count = 0;
		
		for (int i = 1 ; i < in.bufferSize(); i ++)
		{
			if (in.left.get(i - 1) > 0 && in.left.get(i) <= 0)
			{
				count ++;
			}
		}		
		return count;		
	}
	
	public float FFTFreq()
	{
		// Find the higest entry in the FFT and convert to a frequency
		float maxValue = Float.MIN_VALUE;
		int maxIndex = -1;
		for (int i = 0 ; i < fft.specSize() ; i ++)
		{
			if (fft.getBand(i) > maxValue)
			{
				maxValue = fft.getBand(i);
				maxIndex = i;
			}
		}
		return fft.indexToFreq(maxIndex);
	}
	
	public void draw()
	{
		background(0);
		stroke(255);
		float average = 0;
		
		for (int i = 0 ; i < in.bufferSize(); i ++)
		{
			float sample = in.left.get(i);
			if (sample < min)
			{
				min = sample;
			}
			if (sample > max)
			{
				max = sample;
			}
			sample *= 100.0;
			line(i, height / 2, i,  (height / 2) + sample);
			average += Math.abs(in.left.get(i));
			//point(i, (height / 2) + sample);
		}

		
		average /= in.bufferSize();
		
		fft.window(FFT.HAMMING);
		fft.forward(in.left);
		stroke(0, 255, 255);
		for (int i = 0 ; i < fft.specSize() ; i ++)
		{
			line(i, height, i, height - fft.getBand(i)*100);
		}
		
		fill(255);
		text("Amp: " + average, 10, 10);
		int zeroC = countZeroCrossings();		
		
		if (average > 0.001f)
		{
			float freqByZeroC = ((float) sampleRate / (float)in.bufferSize()) * (float) zeroC;
			text("Zero crossings: " + zeroC, 10, 30);
			text("Freq by zero crossings: " + freqByZeroC, 10, 50);
			String zcSpell = spell(freqByZeroC);
			text("Spelling by zero crossings: " + spell(freqByZeroC), 10, 70);
			float freqByFFT = FFTFreq();
			
			String fftSpell = spell(freqByFFT);
			if (freqByFFT >= 110){
				text("Freq by FFT: " + freqByFFT, 10, 90);
				text("Spelling by FFT: " + fftSpell, 10, 110);
			}else{
				text("Freq by FFT: " + "N/A", 10, 90);
				text("Spelling by FFT: " + "N/A", 10, 110);
			}
									
		}
		float smallRadius = 50;
		float bigRadius = (smallRadius * 2) + (average * 500);
		
		stroke(0, 255, 0);
		fill(0, 255, 0);
		ellipse(width / 2, height / 2, bigRadius, bigRadius);
		stroke(0);
		fill(0);
		ellipse(width / 2, height / 2, smallRadius, smallRadius);		
		//ellipse(width/2, height/2, smallRadius, smallRadius);
	}
}
