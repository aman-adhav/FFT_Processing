Uses Minim part of Processing-3.3 IDE for Audio processing. This program detects the musical pitch of the audio file/audio input. FFT algorithms inbuilt in the Minim library are used to calculate max frequency per frame (512 frames). 

Requirements : 

- Eclipse/Netbeans/Other IDE's that support Java
- Microphone (20KHz)
- Processing 3.3 or 2.1

# Installation :

Include the following in the file path from Processing folder : 
- core.java
- jsminim.java
- minim.java


# VM arguments
```
  -Djava.library.path=C:\Users\processing-2.2.1\processing-2.2.1\core\library
  -Djava.library.path=C:\Users\processing-2.2.1\processing-2.2.1\modes\java\libraries\minim\library

```

<h1>FFT_AudioProcessing: <em>Fast Fourier Transform Pitch Detection at 60fps</em></h1>

<iframe src="https://giphy.com/embed/t7ugl980nwxelPoBa9" width="480" height="234" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p><a href="https://giphy.com/gifs/fast-fourier-transform-t7ugl980nwxelPoBa9">via GIPHY</a></p>
<sup><em>f1: Working sample of FFT Pitch Detection.</em></sup>

by: Aman Adhav <a href="https://linkedin.com/in/amanadhav"><img src="https://raw.githubusercontent.com/jrobchin/phormatics/master/screenshots/linkedin.png" height="20px"></a> <a href="https://github.com/dreamincodeforlife"><img src="https://raw.githubusercontent.com/jrobchin/phormatics/master/screenshots/github.png" height="20px"></a>

[HackPrinceton Fall 2017](https://hackprinceton.com/) project developed in 36 hours, focusing on using A.I. and NLP to build a speech to latex converter for Mathematics/Physics Lectures.

This project was the runner up for "Best Use of Azure Services" as awarded by [Microsoft](https://azure.microsoft.com/en-ca/). 

### Flask API:
This app runs as an API built with Flask and PyTorch. Because of Microsoft's key interest in this idea I am unable to add my complete code to this GitHub repo. Key word analyses and graph generation & estimation is performed by the [Flask](http://flask.pocoo.org/) API. To make it short and sweet, Git_Math_Done is a translation tool that converts plain English Speech Input into readable Latex text. It incorporates algorithms that find specific mathematical symbols/terminology and converts it to Latex. Ex: Speech Input : ("Limit of X approaches 0"), Latex Text Output : {$\lim_{x\to\0}

The application reads in the speech input and tries to finds any functions/equations/mathematical terminology in the speech input stream. Once it finds possible matches, it converts parts of the string into Latex. Once the entire speech input stream has been parsed, the data is stored in a text file, the contents of the file can be copied into Microsoft Word or other applications that support Latex. 


### Currently Supported Functions:

- Graph: *Graphing a function only there was a function called previously*
- Basic Mathematics Operators: *Derivative of x^3 + 4x^2 -2*
- Calculating Answer to a Question with full steps: *Solution powered by Wolfram's API*

### Usage:

You'll need a few things:

* [Python 3.5.0+](https://www.python.org/downloads/)
* [NGROK](https://ngrok.com/), to run our Microsoft API an AZURE Key ([AZure](https://azure.microsoft.com/en-ca/))
* [Git](https://git-scm.com/), to clone this repository
* A Browser, to view the website of course!

First we must install Microsoft Client Via pip. We assume that you have Git, Python, and NGROK installed. If you don't, please visit the links above to install them.

First, clone the git repository:
```
$ git clone https://github.com/dreamInCoDeforlife/git_math_done.git
```

Then, cd into the repository:
```
$ cd git_math_done
```

Install all dependencies inside requirements.txt file for python:
```
$ pip install requirements.txt
```

Now let's initialize the flask application:
```
$ python demo.py
```

After that, you should get a response that looks something like this:

```
$ FLASK_APP=demoo.py flask run
 * Running on http://localhost:5000/
```
Now we can turn this private local API public with NGROK:

```
$ ngrok https 5000
ngrok by @inconshreveable                                                                               (Ctrl+C to quit)

Session Status                online
Session Expires               7 hours, 59 minutes
Version                       2.2.8
Region                        United States (us)
Web Interface                 http://127.0.0.1:4040
Forwarding                    http://58a205b8.ngrok.io -> localhost:5000
Forwarding                    https://58a205b8.ngrok.io -> localhost:5000

Connections                   ttl     opn     rt1     rt5     p50     p90
                              0       0       0.00    0.00    0.00    0.00
```
Note : The url address changes every 7 hours.

Now via browser setup a connection endpoint that sends converted speech as text value via POST request (Node.JS works great with this).




