Quiz 05
=======  

|Attempts|Score|  
|:------:|:---:|  
|  1/100 | 9/9 |  

Question 01
-----------  
`(True or False)` Java Threads running in the same Process share dynamically-allocated memory.  

### Answer  
* True  

Question 02
-----------  
`(True or False)` Once a Java Thread is created, it typically starts running as soon as the operating system can schedule it.  

### Answer  
* False  

Question 03  
-----------  
If your Activity creates its user interface hierarchy during a call to its onCreate() method, how do you know in which Thread onCreate() was called? Choose the single best answer.  

* The onCreate() is always run in the UI thread.  
* Examine the application's AndroidManifest.xml file.  
* Use LogCat to print out the Thread's ID.  
* Examine the application's code to determine whether the call occurred in an application-generated Thread or in one of the system's Threads.  

### Answer  
* The onCreate() is always run in the UI thread.  

Question 04
-----------  
Which of the following methods is guaranteed to run on the application's UI Thread?  

* Activity.runOnUIThread().  
* View.post().  
* AsyncTask.doInBackground().  
* Handler.sendMessage().  

### Answer  
* Activity.runOnUIThread().  
* View.post().  

Question 05
-----------  
Which of the following statements correctly capture why an application that uses a Handler, might send Messages to the Handler, rather than post Runnables to it, or vice versa?  

* Messages are used when the Handler implements the Message response.  
* Runnables are used when the Sender implements the action to be taken.  
* Messages can take parameters. Runnables can't.  
* Runnables are less efficient than messages.  

### Answer  
* Messages are used when the Handler implements the Message response.  
* Runnables are used when the Sender implements the action to be taken.  

Question 06
-----------  
Android supports several HTTP clients. Which one of the following HTTP clients will be Android's preferred HTTP client in the future? See [http://android-developers.blogspot.com/2011/09/androids-http-clients.html](http://android-developers.blogspot.com/2011/09/androids-http-clients.html) for more information.  

* DefaultHttpClient  
* URLStream  
* HttpURLConnection  
* AndroidHttpClient  

### Answer  
* HttpURLConnection  

Question 07
-----------  
Which of the following data formats did we discuss in these lectures?  

* JSON  
* XML  
* Python  
* KML  

### Answer  
* JSON  
* XML  

Question 08
-----------  
Which of the following statements are generally true about DOM parsers?  

* DOM parsers provide iterators that pull XML content into an application on demand.  
* DOM parsers use a streaming model in which the parser calls back into the application when specific elements are parsed.  
* DOM parsers convert an XML document into a tree structure, which can make it easier to do whole document analyses.  
* DOM parsers tend to use more memory than the other kinds of Parsers we discussed in this lesson.  

### Answer  
* DOM parsers convert an XML document into a tree structure, which can make it easier to do whole document analyses.  
* DOM parsers tend to use more memory than the other kinds of Parsers we discussed in this lesson.  

Question 09
-----------  
The JavaScript Object Notation Language represents information using two types of data structures. Which two of the following are those data structure?  

* Lists  
* Trees  
* Bags  
* Maps  

### Answer  
* Lists  
* Maps  