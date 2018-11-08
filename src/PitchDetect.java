import java.util.ArrayList;

import processing.core.PApplet;
import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import ddf.minim.analysis.WindowFunction;

public class Main extends PApplet
{
	Minim minim;
	AudioInput in;
	float min;
	float max;
	                         
	int sampleRate = 44100;
	
	FFT fft;
	
	public int countZeroCrossings()
	{
		int count = 0;
		for (int i = 1 ; i < in.bufferSize() ; i ++)
		{
			if ((in.left.get(i - 1) > 0) && (in.left.get(i) <= 0))
			{
				count ++;
			}
		}
		return count;
	}
	
	public void setup()
	{
		size(2048, 500);
		smooth();
		minim = new Minim(this);
		
		in = minim.getLineIn(Minim.MONO, width, sampleRate, 16);
		
		fft = new FFT(width, sampleRate);
	}
	
	float lastRadius = 0;
	float lerpSpeed = 0.1f;
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
		
		stroke(0, 255, 255);
		
		fill(255);
		text("Amp: " + average, 10, 10);
		
		int zc = countZeroCrossings();
		float freq = (float) zc * ((float) sampleRate / (float) width);
		
		text("Frequency: " + freq, 10, 50);
		
		float smallRadius = 50;
		float bigRadius = (smallRadius * 2) + (average * 500);
		float myRadius = lerp(lastRadius, bigRadius, lerpSpeed);
		stroke(0, 255, 0);
		fill(0, 255, 0);
		ellipse(width / 2, height / 2, myRadius, myRadius);
		stroke(0);
		fill(0);
		ellipse(width / 2, height / 2, smallRadius, smallRadius);		
		lastRadius = myRadius;
		
		fft.forward(in.left);
		stroke(0, 255, 255);
		for (int i = 0 ; i < fft.specSize() ; i ++)
		{
			line(i, height, i, height - (fft.getBand(i) * 10));
		}
	}
		
	public static void main(String[] args)
	{		
		PApplet.main(Main.class.getName());	
	}
}
