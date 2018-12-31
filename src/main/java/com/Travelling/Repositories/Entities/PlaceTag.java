package com.Travelling.Repositories.Entities;

import com.Travelling.Repositories.Entities.CompositeIds.PlaceTagId;

import javax.persistence.*;

@Entity
@Table(name = "placetag")
@IdClass(PlaceTagId.class)
public class PlaceTag {
    @Id
    @Column(name = "pid")
    private int pid;

    @Id
    @Column(name = "tid")
    private int tid;

    public PlaceTag(){
        pid = -1;
        tid = -1;
    }

    public PlaceTag(int pid, int tid){
        this.pid = pid;
        this.tid = tid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
