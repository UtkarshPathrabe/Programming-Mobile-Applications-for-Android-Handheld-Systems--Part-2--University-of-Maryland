package coursera.dailyselfie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.File;
import java.util.ArrayList;

public class SelfieReader extends AsyncTask<File, Void, ArrayList<Selfie>> {
    private ProgressBar loadingProgress;
    private ArrayList<Selfie> photos;

    public SelfieReader (ProgressBar loadingSpinner) {
        this.loadingProgress = loadingSpinner;
    }

    @Override
    protected void onPreExecute () {
        super.onPreExecute ();
        loadingProgress.setVisibility (View.VISIBLE);
    }

    @Override
    protected void onPostExecute (ArrayList<Selfie> selfies) {
        super.onPostExecute (selfies);
        loadingProgress.setVisibility (View.GONE);
    }

    @Override
    protected void onProgressUpdate (Void... values) {
        super.onProgressUpdate (values);
    }

    @Override
    protected ArrayList<Selfie> doInBackground (File... files) {
        File f = files[0];
        photos = new ArrayList<Selfie> ();
        if (f != null) {
            for (String file : f.list ()) {
                //get the picture's full path
                String absolutePath = f.getAbsolutePath () + "/" + file;
                File rawPic = new File (absolutePath);
                //create a bitmap from the image stored on external storage
                BitmapFactory.Options options = new BitmapFactory.Options ();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                //resize the bitmap for the listview, the full image will be shown only if the user click an item from the list
                Bitmap original = BitmapFactory.decodeFile (absolutePath, options);
                //keep the image's ratio
                Bitmap selfieImage = this.resizeImage (original, 120);
                //create a new Selfie with the available properties
                Selfie selfie = new Selfie (absolutePath, file, selfieImage, rawPic.lastModified ());
                photos.add (selfie);
            }
        }
        return photos;
    }

    private Bitmap resizeImage (Bitmap original, int width) {
        if (original != null) {
            int height = original.getHeight () / (original.getWidth () / 120);
            return Bitmap.createScaledBitmap (original, 120, height, false);
        }
        return null;
    }
}
