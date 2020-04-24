package com.openclassrooms.entrevoisins.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour extends ArrayList<Parcelable> implements Parcelable {

    /**
     * Identifier
     */
    private long id;

    /**
     * Full name
     */
    private String name;

    /**
     * Avatar
     */
    private String avatarUrl;
    /**
     * address
     */
    private String address;

    /**
     * phone number
     */
    private Integer phone;

    /**
     * aboutMe
     */
    private String aboutMe;

    /**
     * Constructor
     *
     * @param id
     * @param name
     * @param avatarUrl
     */
    public Neighbour(long id, String name, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.address = "";
        this.phone = 0;
        this.aboutMe = "";
    }

    /**
     * Constructor
     *
     * @param id
     * @param name
     * @param avatarUrl
     * @param address
     * @param phone
     * @param aboutMe
     */
    public Neighbour(long id, String name, String avatarUrl, String address, Integer phone, String aboutMe) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.address = address;
        this.phone = phone;
        this.aboutMe = aboutMe;
    }

    protected Neighbour(Parcel in) {
        if (in.readByte() == 0) {
            id = 0;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        avatarUrl = in.readString();
    }

    public static final Creator<Neighbour> CREATOR = new Creator<Neighbour>() {
        @Override
        public Neighbour createFromParcel(Parcel in) {
            return new Neighbour(in);
        }

        @Override
        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
        dest.writeString(avatarUrl);
    }
}
