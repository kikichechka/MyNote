package com.example.mynote.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private final int id;
    private String title;
    private String description;
    private boolean like;

    public Note(int id, String title, String description, boolean like) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.like = like;
    }

    public Note(int id, String title, String description, Note note) {
        this.id = id;
        this.title = title;
        this.description = description;
    }


    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        like = in.readByte() != 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeByte((byte) (like ? 1 : 0));
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
