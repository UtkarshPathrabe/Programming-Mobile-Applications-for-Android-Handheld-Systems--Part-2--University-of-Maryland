package coursera.dailyselfie;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity implements DialogInterface.OnDismissListener {
    public static  final String TAG = "coursera.dailyselfie";
    public static final String INTENT_PATH = "intent_path";
    public static final String INTENT_DATE = "intent_date";
    public static final String ALARM_UP = "alarm_up";
    public static final String ALARM_HOUR = "alarm_hour";
    public static final String ALARM_MIN = "alarm_min";
    private static final String ALBUM_NAME = "DailySelfie";
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    private static final int ACTION_TAKE_PHOTO = 1;
    private static final int ACTION_SHOW_PHOTO = 2;
    private static final String PARCEL_OBJ = "parcel_";
    private String mCurrentPhotoPath;
    private String mCurrentPhotoName;
    private long takenAt;
    private SelfieAdapter adapter;
    private ArrayList<Selfie> photos;
    private ListView listView;
    private AlarmManager mAlarmManager;
    private Intent mNotificationReceiverIntent;
    private PendingIntent mNotificationReceiverPendingIntent;
    private ProgressBar loadingSpinner;
    private int selectedCount;
    private coursera.dailyselfie.TimePickerDialog timerDialog;
    private SharedPreferences sp;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        listView = (ListView) findViewById (R.id.listView);
        photos = new ArrayList<Selfie> ();
        loadingSpinner = (ProgressBar)findViewById (R.id.loading);
        //prepare all the pictures to be shown in the listview
        if (savedInstanceState == null) {
            SelfieReader reader = new SelfieReader (loadingSpinner);
            try {
                photos = reader.execute (this.getAlbumDir ()).get ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            } catch (ExecutionException e) {
                e.printStackTrace ();
            }
        } else {
            for (int i = 0; i < savedInstanceState.size () - 1; ++i) {
                Selfie selfie = savedInstanceState.getParcelable (PARCEL_OBJ + i);
                photos.add (selfie);
            }
        }
        //sort photos by date taken - newest first
        Collections.sort (photos, new SelfieComparator ());
        //set up the new adapter for the listview
        adapter = new SelfieAdapter (this, photos);
        listView.setAdapter (adapter);
        //set up the onclicklistener for the items
        listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int i, long l) {
                Selfie selectedSelfie = (Selfie)adapter.getItem (i);
                Intent showSelfie = new Intent (getBaseContext (), SelfieViewer.class);
                showSelfie.putExtra (INTENT_PATH, selectedSelfie.getFullPath ());
                showSelfie.putExtra (INTENT_DATE, selectedSelfie.getLastModifiedString ());
                startActivity (showSelfie);
            }
        });
        listView.setChoiceMode (ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener (new AbsListView.MultiChoiceModeListener () {
            @Override
            public void onItemCheckedStateChanged (ActionMode actionMode, int i, long l, boolean b) {
                adapter.toggleSelection (i);
                selectedCount = adapter.getSelectedItemsCount ();
                actionMode.setTitle (selectedCount + " Selected");
            }
            @Override
            public boolean onCreateActionMode (ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater ().inflate (R.menu.context_menu, menu);
                return true;
            }
            @Override
            public boolean onPrepareActionMode (ActionMode actionMode, Menu menu) {
                return false;
            }
            @Override
            public boolean onActionItemClicked (ActionMode actionMode, MenuItem menuItem) {
                final ActionMode mode = actionMode;
                if (menuItem.getItemId () == R.id.action_delete) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder (MainActivity.this);
                    alertDialog.setTitle (R.string.delete_selfies);
                    alertDialog.setMessage (R.string.delete_question);
                    alertDialog = alertDialog.setPositiveButton (R.string.yes, new DialogInterface.OnClickListener () {
                        @Override
                        public void onClick (DialogInterface dialogInterface, int i) {
                            adapter.removeSelectedItems ();
                            mode.finish ();
                        }
                    });
                    alertDialog.setNegativeButton (R.string.no, new DialogInterface.OnClickListener () {
                        @Override
                        public void onClick (DialogInterface dialogInterface, int i) {
                            //
                        }
                    });
                    alertDialog.show ();
                }
                return false;
            }
            @Override
            public void onDestroyActionMode (ActionMode actionMode) {
                adapter.removeSelections ();
                actionMode = null;
            }
        });
        timerDialog = new coursera.dailyselfie.TimePickerDialog (this);
        timerDialog.setOnDismissListener (this);
        timerDialog.setTitle (R.string.timer_title);
        // Set up the alarm. Get the AlarmManager Service
        mAlarmManager = (AlarmManager) getSystemService (ALARM_SERVICE);
        // Create an Intent to broadcast to the AlarmNotificationReceiver
        mNotificationReceiverIntent = new Intent (MainActivity.this, NotificationReceiver.class);
        // Create an PendingIntent that holds the NotificationReceiverIntent
        mNotificationReceiverPendingIntent = PendingIntent.getBroadcast (MainActivity.this, 0, mNotificationReceiverIntent, 0);
        sp = this.getSharedPreferences (TAG, MODE_PRIVATE);
        boolean alarmUp = sp.getBoolean (ALARM_UP, false);
        if (alarmUp) {
            int hour = sp.getInt (ALARM_HOUR, 0);
            int minute = sp.getInt (ALARM_MIN, 0);
            Calendar calendar = Calendar.getInstance ();
            calendar.set (Calendar.HOUR_OF_DAY, hour);
            calendar.set (Calendar.MINUTE, minute);
            calendar.set (Calendar.SECOND, 0);
            timerDialog.setTime (alarmUp, calendar);
            mAlarmManager.setRepeating (AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis (), 24 * 60 * 60 * 1000, mNotificationReceiverPendingIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            timerDialog.show ();
            return true;
        }
        if (id == R.id.action_camera) {
            this.takePicture (ACTION_TAKE_PHOTO);
            return true;
        }
        return super.onOptionsItemSelected (item);
    }

    private void takePicture (int actionCode) {
        Intent takePictureIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        File f = null;
        try {
            f = setUpPhotoFile ();
            mCurrentPhotoPath = f.getAbsolutePath ();
            mCurrentPhotoName = f.getName ();
            takenAt = f.lastModified ();
            takePictureIntent.putExtra (MediaStore.EXTRA_OUTPUT, Uri.fromFile (f));
        } catch (IOException e) {
            e.printStackTrace ();
            f = null;
            mCurrentPhotoPath = null;
        }
        startActivityForResult (takePictureIntent, actionCode);
    }

    private File getAlbumStorageDir () {
        return new File (Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_PICTURES), ALBUM_NAME);
    }

    private File getAlbumDir () {
        File storageDir = null;
        if (Environment.MEDIA_MOUNTED.equals (Environment.getExternalStorageState ())) {
            storageDir = this.getAlbumStorageDir ();
            if (storageDir != null) {
                if (!storageDir.mkdirs ()) {
                    if (!storageDir.exists ()){
                        Log.d (TAG, "Failed to Create Directory");
                        return null;
                    }
                }
            }
        } else {
            Log.v (TAG, "External storage is not mounted READ/WRITE.");
        }
        return storageDir;
    }

    private File createImageFile () throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat ("yyyyMMdd_HHmmss").format (new Date ());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File albumF = getAlbumDir ();
        File imageF = File.createTempFile (imageFileName, JPEG_FILE_SUFFIX, albumF);
        return imageF;
    }

    private File setUpPhotoFile () throws IOException {
        File f = createImageFile ();
        mCurrentPhotoPath = f.getAbsolutePath ();
        return f;
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTION_TAKE_PHOTO: {
                if (resultCode == RESULT_OK) {
                    //refresh the listview
                    BitmapFactory.Options options = new BitmapFactory.Options ();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    //resize the bitmap for the listview, the full image will be shown only if the user click an item from the list
                    Bitmap taken = BitmapFactory.decodeFile (mCurrentPhotoPath, options);
                    if (taken != null) {
                        Bitmap small = resizeImage (taken);
                        Selfie selfie = new Selfie (mCurrentPhotoPath, mCurrentPhotoName, small, takenAt);
                        adapter.addItem (selfie);
                    }
                }
                break;
            }
            case ACTION_SHOW_PHOTO: {
                switch (resultCode) {
                    case SelfieViewer.ACTION_SHOW_NEXT : {
                        //show the next photo
                        break;
                    }
                    case SelfieViewer.ACTION_SHOW_PREW: {
                        //show previous photo
                        break;
                    }
                    case SelfieViewer.ACTION_CANCEL: {
                        //show the listview
                        break;
                    }
                }
            }
        }
    }

    private Bitmap resizeImage (Bitmap original) {
        int height = original.getHeight () / (original.getWidth () / 120);
        return Bitmap.createScaledBitmap (original, 120, height, false);
    }

    @Override
    public void onDismiss (DialogInterface dialogInterface) {
        SharedPreferences.Editor editor = sp.edit ();
        if (timerDialog.isNotificationActive ()) {
            Calendar notifTime = timerDialog.getTime ();
            Calendar now = GregorianCalendar.getInstance ();
            if (notifTime.getTimeInMillis () < now.getTimeInMillis ()) {
                notifTime.set (Calendar.DAY_OF_YEAR, now.get (Calendar.DAY_OF_YEAR) + 1);
            }
            Log.d (TAG, "TIME: " + notifTime.get (Calendar.HOUR_OF_DAY) + "");
            mAlarmManager.setRepeating (AlarmManager.RTC_WAKEUP, notifTime.getTimeInMillis (), 24 * 60 * 60 * 1000, mNotificationReceiverPendingIntent);
            editor.putInt (ALARM_HOUR, notifTime.get (Calendar.HOUR_OF_DAY));
            editor.putInt (ALARM_MIN, notifTime.get (Calendar.MINUTE));
            editor.putBoolean (ALARM_UP, true);
            editor.commit ();
        } else {
            if (sp.getBoolean (ALARM_UP, false) == true) {
                mAlarmManager.cancel (mNotificationReceiverPendingIntent);
                editor.putBoolean (ALARM_UP, false);
                editor.commit ();
            }
        }
        Toast.makeText (this, "Notification changes saved!", Toast.LENGTH_SHORT).show ();
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        int i = 0;
        for (Selfie selfie: photos) {
            outState.putParcelable (PARCEL_OBJ + i, selfie);
            ++i;
        }
        super.onSaveInstanceState (outState);
    }
}