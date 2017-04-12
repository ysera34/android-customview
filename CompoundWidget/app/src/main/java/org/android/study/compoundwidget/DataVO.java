package org.android.study.compoundwidget;

/**
 * Created by 26019 on 2016-04-29.
 */
public class DataVO {

    private String name;
    private int imageID;

    public DataVO() {
    }

    public DataVO(String name, int imageID) {
        this.name = name;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    @Override
    public String toString() {
        return "DataVO{" +
                "name='" + name + '\'' +
                ", imageID=" + imageID +
                '}';
    }
}
