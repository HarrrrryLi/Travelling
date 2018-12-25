package com.Travelling.Repositories.Entities.CompositeIds;

import java.io.Serializable;

public class PlaceTagId implements Serializable {
    private int tid;
    private int pid;

    public PlaceTagId(){
        pid = -1;
        tid = -1;
    }

    public PlaceTagId(int pid, int tid){
        this.pid = pid;
        this.tid = tid;
    }

    public PlaceTagId(String str){
        String[] parmas = str.split(";");
        pid = Integer.parseInt(parmas[0].split(":")[1]);
        tid = Integer.parseInt(parmas[1].split(":")[1]);
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString(){
        return  String.format("pid:%d;tid:%d", pid, tid);
    }
}
