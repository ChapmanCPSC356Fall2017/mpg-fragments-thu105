package heinmoethu.gascostcalculator.models;

import android.os.Parcel;
import android.os.Parcelable;

public class InputModel implements Parcelable {
    private double mpg,ppg,length;

    public InputModel(){
        mpg=0;
        ppg=0;
        length=0;
    }

    private InputModel(Parcel in) {
        mpg = in.readDouble();
        ppg = in.readDouble();
        length = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(mpg);
        dest.writeDouble(ppg);
        dest.writeDouble(length);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InputModel> CREATOR = new Creator<InputModel>() {
        @Override
        public InputModel createFromParcel(Parcel in) {
            return new InputModel(in);
        }

        @Override
        public InputModel[] newArray(int size) {
            return new InputModel[size];
        }
    };
    public double getMpg() {
        return mpg;
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    public double getPpg() {
        return ppg;
    }

    public void setPpg(double ppg) {
        this.ppg = ppg;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
