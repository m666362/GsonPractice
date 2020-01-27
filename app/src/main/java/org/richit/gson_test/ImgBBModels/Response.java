package org.richit.gson_test.ImgBBModels;

import android.os.Parcel;
import android.os.Parcelable;

public class Response implements Parcelable {
    boolean success;
    int status;
    Data data;

    protected Response(Parcel in) {
        success = in.readByte() != 0;
        status = in.readInt();
        data = in.readParcelable( Data.class.getClassLoader() );
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response( in );
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte( (byte) (success ? 1 : 0) );
        dest.writeInt( status );
        dest.writeParcelable( data, flags );
    }
}
