package xyz.toor.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chalypeng on 2017/9/21.
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public String getPhotoFilename(){
        return "IMG_" + getId().toString()+".jpg";
    }

    private String mSuspect;

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Crime() {
        this(UUID.randomUUID());
    }
    public Crime(UUID id){
        mId = id;
        mDate = new Date();
    }
}
