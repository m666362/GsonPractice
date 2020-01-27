package org.richit.gson_test.ImgBBModels;

import android.os.Parcel;
import android.os.Parcelable;

public class UniversalImage implements Parcelable {
    String filename;
    String url;
    String extension;

    protected UniversalImage(Parcel in) {
        filename = in.readString();
        url = in.readString();
        extension = in.readString();
    }

    public static final Creator<UniversalImage> CREATOR = new Creator<UniversalImage>() {
        @Override
        public UniversalImage createFromParcel(Parcel in) {
            return new UniversalImage( in );
        }

        @Override
        public UniversalImage[] newArray(int size) {
            return new UniversalImage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( filename );
        dest.writeString( url );
        dest.writeString( extension );
    }
}
