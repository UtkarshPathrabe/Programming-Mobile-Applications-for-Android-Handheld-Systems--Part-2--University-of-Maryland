package coursera.dailyselfie;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class SelfieAdapter extends BaseAdapter {
    private final ArrayList<Selfie> photos;
    private ArrayList<Selfie> selectedPhotos;
    private Context contex;

    public SelfieAdapter (Context context, final ArrayList<Selfie> photos) {
        this.contex = context;
        this.photos = photos;
        selectedPhotos = new ArrayList<Selfie> ();
    }

    @Override
    public int getCount () {
        return photos.size ();
    }

    @Override
    public Object getItem (int i) {
        return photos.get (i);
    }

    @Override
    public long getItemId (int i) {
        return i;
    }

    public void addItem (Selfie selfie) {
        photos.add (0, selfie);
        notifyDataSetChanged ();
    }

    public void deleteItem (Selfie selfie) {
        photos.remove (selfie);
    }

    public void deleteItem (int i) {
        photos.remove (i);
    }

    @Override
    public View getView (int i, View view, ViewGroup viewGroup) {
        final Selfie selfie = photos.get (i);
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext ().getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        View itemView;
        if (view == null) {
            itemView = inflater.inflate (R.layout.selfie_row, null);
        } else {
            itemView = view;
        }
        ImageView imageView = (ImageView) itemView.findViewById (R.id.imageViewPriority);
        if (selfie.getData () != null) {
            imageView.setImageBitmap (selfie.getData ());
        }
        TextView textViewTitle = (TextView) itemView.findViewById (R.id.textViewTitle);
        textViewTitle.setText (selfie.getName ());
        TextView subTitle = (TextView) itemView.findViewById (R.id.textViewSubTitle);
        subTitle.setText (selfie.getLastModifiedString ());
        if (isSelected (selfie)) {
            itemView.setBackgroundColor (0x9934B5E4);
        } else {
            itemView.setBackgroundColor (Color.TRANSPARENT);
        }
        return itemView;
    }

    public void toggleSelection (int position) {
        Selfie selfie = photos.get (position);
        boolean found = isSelected (selfie);
        if (!found) {
            selectedPhotos.add (selfie);
        } else {
            selectedPhotos.remove (selfie);
        }
        this.notifyDataSetChanged ();
    }

    public void removeSelections () {
        selectedPhotos.clear ();
        this.notifyDataSetChanged ();
    }

    public int getSelectedItemsCount () {
        return selectedPhotos.size ();
    }

    private boolean isSelected (Selfie selfie) {
        boolean found = false;
        for (Selfie iter : selectedPhotos) {
            if (iter.equals (selfie)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public void removeSelectedItems () {
        for (Selfie item : selectedPhotos) {
            File file = new File (item.getFullPath ());
            if (!file.delete ()) {
                Toast.makeText (contex, file.getName () + " can not be removed!", Toast.LENGTH_SHORT).show ();
            }
            photos.remove (item);
        }
        selectedPhotos.clear ();
        this.notifyDataSetChanged ();
    }
}