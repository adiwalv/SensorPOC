package io.qio.sensorpoc.domain;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("xaxis")
    private Object xaxis;

    @SerializedName("yaxis")
    private Object yaxis;

    @SerializedName("zaxis")
    private Object zaxis;

    public Event(Object xaxis, Object yaxis, Object zaxis) {
        this.xaxis = xaxis;
        this.yaxis = yaxis;
        this.zaxis = zaxis;
    }

    public void setXaxis(Object xaxis) {
        this.xaxis = xaxis;
    }

    public void setYaxis(Object yaxis) {
        this.yaxis = yaxis;
    }

    public void setZaxis(Object zaxis) {
        this.zaxis = zaxis;
    }

    public Event() {
    }

    public Object getXaxis() {
        return xaxis;
    }

    public Object getYaxis() {
        return yaxis;
    }

    public Object getZaxis() {
        return zaxis;
    }
}
