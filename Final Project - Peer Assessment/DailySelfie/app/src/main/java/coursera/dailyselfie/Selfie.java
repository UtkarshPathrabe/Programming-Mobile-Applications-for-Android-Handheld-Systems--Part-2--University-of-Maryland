package coursera.dailyselfie;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Selfie implements Comparable, Parcelable{
    private String fullPath;
    private String name;
    private Bitmap data;
    private long lastModified;

    public Selfie (String fullPath, String name, Bitmap data, long lastModified) {
        this.data = data;
        this.name = name;
        this.fullPath = fullPath;
        this.lastModified = lastModified;
    }

    public Selfie (Parcel parcel) {
        this.data = parcel.readParcelable (null);
        String[] str = new String[2];
        parcel.readStringArray (str);
        this.name = str[0];
        this.fullPath = str[1];
        this.lastModified = parcel.readLong ();
    }

    public String getFullPath () {
        return fullPath;
    }

    public void setFullPath (String fullPath) {
        this.fullPath = fullPath;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Bitmap getData () {
        return data;
    }

    public void setData (Bitmap data) {
        this.data = data;
    }

    public long getLastModifiedLong () {
        return lastModified;
    }

    public String getLastModifiedString () {
        Date date = new Date (lastModified);
        SimpleDateFormat df = new SimpleDateFormat ("dd/MMMM/yyyy");
        String dateText = df.format (date);
        return dateText;
    }

    public void setLastModified (long lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public int compareTo (Object o) {
        if (o instanceof Selfie) {
            Selfie other = (Selfie)o;
            return other.getName ().compareTo (this.getName ());
        }
        return 0;
    }

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel parcel, int i) {
        parcel.writeParcelable (data, 0);
        parcel.writeLong (lastModified);
        String[] str = new String[2];
        str[0] = name;
        str[1] = fullPath;
        parcel.writeStringArray (str);
    }

    public static final Parcelable.Creator<Selfie> CREATOR = new Parcelable.Creator<Selfie> () {
        public Selfie createFromParcel (Parcel in) {
            return new Selfie(in);
        }

        public Selfie[] newArray (int size) {
            return new Selfie[size];
        }
    };
}
