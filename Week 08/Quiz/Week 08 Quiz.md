Quiz 08
=======  

|Attempts|Score|  
|:------:|:---:|  
|  1/100 |12/12|  

Question 01
-----------  
When registering a Listener for SensorEvents, you can set the rate at which the Sensor returns its readings. Which of the following Sensor constants represents to the most frequent rate? See: [http://developer.android.com/reference/android/hardware/SensorManager.html](http://developer.android.com/reference/android/hardware/SensorManager.html) for more information.  

* `SENSOR_DELAY_NORMAL`  
* `SENSOR_DELAY_UI`  
* `SENSOR_DELAY_GAME`  
* `SENSOR_DELAY_FASTEST`  

### Answer  

* `SENSOR_DELAY_FASTEST`  

Question 02
-----------  
SensorEvents have which of the following fields? See: [http://developer.android.com/reference/android/hardware/SensorEvent.html](http://developer.android.com/reference/android/hardware/SensorEvent.html) for more documentation.  

* Sensor-specific measurement values.  
* A sensor identifier.  
* A measurement accuracy.  
* A timestamp.  

### Answer  

* Sensor-specific measurement values.  
* A sensor identifier.  
* A measurement accuracy.  
* A timestamp.  

Question 03  
-----------  
`(True or False)` High-pass filters are used when you want to emphasize the transient changes in a Sensor's readings, while deemphasizing the constant portions of the reading?  

### Answer  

* True  

Question 04
-----------  
Which of the following Sensor types did the SensorCompass application use?  

* `TYPE_MAGNETIC_FIELD`.  
* `TYPE_ACCELEROMETER`.  
* `TYPE_PRESSURE`.  
* `TYPE_LINEAR_ACCELERATION`.  

### Answer  

* `TYPE_MAGNETIC_FIELD`.  
* `TYPE_ACCELEROMETER`.  

Question 05
-----------  
Which type of LocationProvider gets its readings from Global Positioning System satellites?   

* `NETWORK_PROVIDER`.  
* `GPS_PROVIDER`.  
* `PASSIVE_PROVIDER`.  

### Answer  

* `GPS_PROVIDER`.    

Question 06
-----------  
Recently Android added an improved API for acquiring Location information via Google Play Services. I have created a new version of the LocationGetLocation application called LocationGetLocationServices. This application is in the source code repository for the class. Which LocationClient method is used in this new application to acquire the last known location reading?  

### Answer  

`getLastLocation`  

Question 07
-----------  
Which class and method did the MapEarthQuakeMap application use in order to create a marker pin for each earthquake? Give your answer in the form `"ClassName.MethodName"`.  

### Answer  

`GoogleMap.addMarker`  

Question 08
-----------  
With the Google Maps Android API, you can change a map's zoom level by calling methods of the CameraUpdate class, such as zoomTo(float). At what zoom level would the width of the earth be roughly 1024dp? See: [https://developers.google.com/maps/documentation/android/views#zoom](https://developers.google.com/maps/documentation/android/views#zoom) for more documentation.  

* `8`  
* `0`  
* `4`  
* `2`  

### Answer  

* `2`  

Question 09
-----------  
In the DataManagementPreferenceFragment application, Android invokes a method when the user changes the username preference. The code in this method then updates the display with the new username. What method does Android call?  

### Answer  

`SharedPreferences.OnSharedPreferenceChangeListener.onSharedPreferenceChanged`  

Question 10
-----------  
In the DataManagementFileInternalMemory application, which method was called to open the file from which the application reads the text to display? Give your answer in the form `"ClassName.MethodName"`.  

### Answer  

`Context.openFileInput`  

Question 11
-----------  
If your application wants to write to external memory, it should determine whether that external storage is available by calling the Environment.getExternalStorageState() method. What constant value will this method return if external storage is mounted and available for writing?  

### Answer  

`MEDIA_MOUNTED`  

Question 12
-----------  
The DataManagementSQL example application called the delete(String table, String whereClause, String[] whereArgs) method. This method takes 3 parameters. Which one of the following statements correctly describes the purpose of the 2nd parameter? See [http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html](http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html) for more documentation.  

* It helps to determine which rows to extract from the database.  
* It names the specific database table from where to extract data.  
* It provides specific values for parameters to an SQL WHERE clause.  
* It gives the location of the database.  

### Answer  

* It helps to determine which rows to extract from the database.  