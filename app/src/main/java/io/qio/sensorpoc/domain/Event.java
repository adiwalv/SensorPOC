package io.qio.sensorpoc.domain;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("xAxis")
    private Object xAxis;

    @SerializedName("yAxis")
    private Object yAxis;

    @SerializedName("zAxis")
    private Object zAxis;

    public Event(Object xAxis, Object yAxis, Object zAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.zAxis = zAxis;
    }

    public void setxAxis(Object xAxis) {
        this.xAxis = xAxis;
    }

    public void setyAxis(Object yAxis) {
        this.yAxis = yAxis;
    }

    public void setzAxis(Object zAxis) {
        this.zAxis = zAxis;
    }

    public Event() {
    }

    public Object getxAxis() {
        return xAxis;
    }

    public Object getyAxis() {
        return yAxis;
    }

    public Object getzAxis() {
        return zAxis;
    }
}
