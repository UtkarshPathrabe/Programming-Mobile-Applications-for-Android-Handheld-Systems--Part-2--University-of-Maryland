Quiz 07
=======  

|Attempts|Score|  
|:------:|:---:|  
|  1/100 |16/16|  

Question 01
-----------  
The GraphicsPaint example application displays several TextViews. These TextViews are specified in the res/layout/main.xml file. Which of the following Paint-related attributes were specified for every one of the TextViews?  

* android:typeface  
* android:textColorHint  
* android:textAllCaps  
* android:textColor  

### Answer  

* android:typeface  
* android:textColor  

Question 02
-----------  
When your application uses a Drawable, such as a ShapeDrawable, what Drawable method must the application invoke to draw the Drawable.  

* `Canvas.drawBitmap()`.  
* `View.onDraw()`.  
* `View.paint()`.  
* None. Drawable is a graphic that goes into a View. The drawing of these graphics is handled by the View system.  

### Answer  

* None. Drawable is a graphic that goes into a View. The drawing of these graphics is handled by the View system.  

Question 03  
-----------  
Suppose your application creates and uses a custom View subclass. When this View needs to be redrawn, which two of the following methods will most likely be invoked?  

* `View.onDraw()`.  
* `View.requestLayout()`.  
* `View.invalidate()`.  
* `Canvas.drawView()`.  

### Answer  

* `View.onDraw()`.  
* `View.invalidate()`.  

Question 04
-----------  
The GraphicsFrameAnimation application includes a res/drawable/view_animation.xml file. This file has an <animation-list> tag. To what Android class does this tag correspond? See: [http://developer.android.com/guide/topics/resources/animation-resource.html](http://developer.android.com/guide/topics/resources/animation-resource.html) for more documentation.  

* AnimationSet.  
* AnimationDrawable.  
* FrameAnimation.  
* TweenAnimation.  

### Answer  

* AnimationDrawable.  

Question 05
-----------  
View animation differs from Property animation in which of the following ways? See: [http://developer.android.com/guide/topics/graphics/prop-animation.html](http://developer.android.com/guide/topics/graphics/prop-animation.html) for more information.   

* View animation typically requires less code to setup and use than Property animation does.  
* View animation does not actually modify the View Object, just its appearance on the screen, while Property animation will modify the Object being animated.  
* View animation is for Views only. Property animation animates more than just Views.  
* View animation is limited to only a few View properties. Property animation can animate a wider set of properties.  

### Answer  

* View animation typically requires less code to setup and use than Property animation does.  
* View animation does not actually modify the View Object, just its appearance on the screen, while Property animation will modify the Object being animated.  
* View animation is for Views only. Property animation animates more than just Views.  
* View animation is limited to only a few View properties. Property animation can animate a wider set of properties.  

Question 06
-----------  
Several of this lesson's example applications make use of interpolators, such as the LinearInterpolator and the AccelerateDecelerateInterpolator. Which one of the following statements best captures the main purpose of an interpolator?  

* It sets the values of a View property.  
* It defines how specific values in an animation are calculated as a function of time.  
* It defines the duration of an animation.  
* It defines the starting time of the animation.  

### Answer  

* It defines how specific values in an animation are calculated as a function of time.  

Question 07
-----------  
A gesture usually starts with a motion event that has which one of the following action codes?  

* `ACTION_DOWN`.  
* `ACTION_POINTER_UP`.  
* `ACTION_POINTER_DOWN`.  
* `ACTION_UP`.  

### Answer  

* `ACTION_DOWN`.  

Question 08
-----------  
Gestures can end with motion events that have which of the following action codes?  

* `ACTION_DOWN`.  
* `ACTION_POINTER_UP`.  
* `ACTION_UP`.  
* `ACTION_CANCEL`.  
* `ACTION_POINTER_DOWN`.  

### Answer  

* `ACTION_UP`.  
* `ACTION_CANCEL`.  

Question 09
-----------  
Which of the following statements represent consistency properties that Android tries to guarantee about the MotionEvents comprising touch gestures?  

* Pointers come up one at time.  
* Pointers move as a group.  
* Every Pointer that goes down eventually comes up.  
* Pointers go down one at a time.  

### Answer  

* Pointers come up one at time.  
* Pointers move as a group.  
* Pointers go down one at a time.  

Question 10
-----------  
Suppose your application has a generic View and when the user touches that View, you want the doWork() method of Object1 to be called. Which of the following strategies can you use to guarantee that?  

* Attach Object1 as a listener of the View and call doWork() from Object1's onTouch() method.  
* Call Object1.doWork() from the View's onTouchEvent() method.  
* Call startActivity (Object1) from the View's onTouch() method.  
* Call Object1.doWork() from the View's OnClickListener.  

### Answer  

* Attach Object1 as a listener of the View and call doWork() from Object1's onTouch() method.  
* Call Object1.doWork() from the View's onTouchEvent() method.  

Question 11
-----------  
The TouchGestureViewFlipper application uses a GestureDetector. In the constructor for the GestureDetector, the application passes in a Listener. What class was that Listener?  

### Answer  

SimpleOnGestureListener  

Question 12
-----------  
Which method is used to determine whether an actual user gesture matches a custom application-defined gesture?  

* `GestureLibrary.recognize()`.  
* `GestureLibrary.match()`.  
* `GestureOverlayView.onGesturePerformed()`.  
* `GestureLibrary.load()`.  

### Answer  

* `GestureLibrary.recognize()`.  

Question 13
-----------  
`(True or False)` In Android the term "Ringtone" refers exclusively to the sounds that are played when a device receives a phone call?  

### Answer  

* False  

#### Explanation  

* In Android, the term `Ringtone` refers to sounds played not only when the phone rings, but also when notifications arrive, alarms go off, etc.  

Question 14
-----------  
Suppose your application uses a MediaPlayer. Which of the following MediaPlayer methods will likely be called before the MediaPlayer.start() method is called? See: [http://developer.android.com/reference/android/media/MediaPlayer.html](http://developer.android.com/reference/android/media/MediaPlayer.html) for more documentation.  

* `getDefaultStream()`.  
* `prepare()`.  
* `setDataSource()`.  
* `playback()`.  

### Answer  

* `prepare()`.  
* `setDataSource()`.  

Question 15
-----------  
Which of the following functions does the MediaRecorder class support?  

* Recording infrared light signals.  
* Recording ambient temperature readings.  
* Recording video.  
* Recording audio.  

### Answer  

* Recording video.  
* Recording audio.  

Question 16
-----------  
`(True or False)` The Camera.open() method may not be able to return a instance of the Camera to the caller.  

### Answer  

* True  