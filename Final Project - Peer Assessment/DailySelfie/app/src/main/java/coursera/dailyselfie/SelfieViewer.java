package coursera.dailyselfie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class SelfieViewer extends Activity {
    public static final int ACTION_SHOW_NEXT = 1;
    public static final int ACTION_SHOW_PREW = 2;
    public static final int ACTION_CANCEL = 3;
    public static final String PHOTO_ID = "photo_id";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_selfieviewer);
        Intent intent = getIntent ();
        String absolutePath = intent.getStringExtra (MainActivity.INTENT_PATH);
        String lastMod = intent.getStringExtra (MainActivity.INTENT_DATE);
        ImageView image = (ImageView) findViewById (R.id.showSelfie);
        BitmapFactory.Options options = new BitmapFactory.Options ();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap original = BitmapFactory.decodeFile (absolutePath, options);
        image.setImageBitmap (original);
        Toast.makeText (this, lastMod, Toast.LENGTH_SHORT).show ();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.menu_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected (item);
    }
}
