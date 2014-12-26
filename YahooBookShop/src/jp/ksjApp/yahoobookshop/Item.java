package jp.ksjApp.yahoobookshop;

import android.os.Parcel;
import android.os.Parcelable;


public class Item implements Parcelable{
    public String id;
    public String name;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }//FileDescriptorñ¢égópÇÃèÍçáÇÕ0

    // ç°ÇÕíËå^ï∂Ç∆Ç¢Ç§îFéØÇ≈
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}

