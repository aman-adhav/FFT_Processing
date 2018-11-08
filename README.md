<h1>FFT_AudioProcessing: <em>Fast Fourier Transform Pitch Detection at 60fps</em></h1>

<img src="https://media.giphy.com/media/t7ugl980nwxelPoBa9/giphy.gif"></br>
<sup><em>f1: Working example of pitch detection at 512 fps detecting Pitch, Crossing Over, Amplitude</em></sup>

by: Aman Adhav <a href="https://linkedin.com/in/amanadhav"><img src="https://raw.githubusercontent.com/jrobchin/phormatics/master/screenshots/linkedin.png" height="20px"></a> <a href="https://github.com/dreamincodeforlife"><img src="https://raw.githubusercontent.com/jrobchin/phormatics/master/screenshots/github.png" height="20px"></a>

Part of the project, [Hummit](https://devpost.com/software/hummit-4m9rco) developed at [HackTheNorth Fall 2017](https://hackthenorth.com). Repository focuses on detection and filteration of sounds, finding amplitudes, frequencies and pitch of the notes.


### Executable Program & Web Application:
Uses Minim part of Processing-3.3 IDE for Audio processing. I built a Windows-based app that uses Java and Javascript as the main programming languages. In our algorithm, we apply Fast Fourier Transform(FFT) at a specific 512 fps to find out the frequency of human per frame. Using that, we are able to predict musical notes. These musical notes are then compared with those in our current database and using a regression model we are able to predict the song. Once the application guesses the song, the user has the option of buying the song using Bitcoin from websites such as HMV or iTunes.

### Currently Supported Functions:

- Record.java: *Able to Record sounds upto 2 mins*
- Pitch Detection (PitchDetection_1.java): *Find pitch, frequency, cross over, amplitude of recorded sounds*

### Usage:
Requirements : 

* Eclipse/Netbeans/Other IDE's that support Java
* Microphone (20KHz)
* Minim (processing) 3.3 or 2.1

### Installation :

You'll need a few things : 
* [Git](https://git-scm.com/), to clone this repository
* [Java](https://www.java.com/en/download/)
* [Eclipse](https://www.eclipse.org/downloads/)
* [Gradle] (https://gradle.org/) Plugin in eclipse marketplace

If running the .jar/.exe files skip the installation steps

### VM arguments

Clone this project:
```
$ git clone https://github.com/dreamInCoDeforlife/fft_processing.git
```
cd into the project repository
```
$ cd FFT_Processing
```
Add the following VM commands in Eclipse:
```
  -Djava.library.path=C:\Users\processing-2.2.1\processing-2.2.1\core\library
  -Djava.library.path=C:\Users\processing-2.2.1\processing-2.2.1\modes\java\libraries\minim\library

```

Now you can build your gradle project through either the bash or Eclipse Gradle
```
$ gradle build .
```
