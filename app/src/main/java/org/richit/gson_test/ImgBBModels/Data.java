package org.richit.gson_test.ImgBBModels;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    String display_url;
    UniversalImage image;
    UniversalImage thumb;
    UniversalImage medium;

    protected Data(Parcel in) {
        display_url = in.readString();
        image = in.readParcelable( UniversalImage.class.getClassLoader() );
        thumb = in.readParcelable( UniversalImage.class.getClassLoader() );
        medium = in.readParcelable( UniversalImage.class.getClassLoader() );
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data( in );
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( display_url );
        dest.writeParcelable( image, flags );
        dest.writeParcelable( thumb, flags );
        dest.writeParcelable( medium, flags );
    }
}
