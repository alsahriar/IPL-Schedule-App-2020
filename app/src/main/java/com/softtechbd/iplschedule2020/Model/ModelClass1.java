package com.softtechbd.iplschedule2020.Model;

import java.util.List;

public class ModelClass1 {
    private String Match_no;
    private int Team1;
    private int Team2;
    private String Vanue;
    private String Date;

    public ModelClass1(List<ModelClass1> scheduleList) {

    }

    public ModelClass1() {
    }

    public ModelClass1(String match_no, int team1, int team2, String vanue, String date) {
        this.Match_no = match_no;
        this.Team1 = team1;
        this.Team2 = team2;
        this.Vanue = vanue;
        this.Date = date;
    }

    public String getMatch_no() {
        return Match_no;
    }

    public int getTeam1() {
        return Team1;
    }

    public int getTeam2() {
        return Team2;
    }

    public String getVanue() {
        return Vanue;
    }

    public String getDate() {
        return Date;
    }

    public void setMatch_no(String match_no) {
        this.Match_no = match_no;
    }

    public void setTeam1(int team1) {
        this.Team1 = team1;
    }

    public void setTeam2(int team2) {
        this.Team2 = team2;
    }

    public void setVanue(String vanue) {
        this.Vanue = vanue;
    }

    public void setDate(String date) {
        this.Date = date;
    }


}
