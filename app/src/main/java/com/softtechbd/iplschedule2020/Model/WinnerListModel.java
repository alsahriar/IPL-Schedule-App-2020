package com.softtechbd.iplschedule2020.Model;

public class WinnerListModel {
    private int picture;
    private String year,winner,runnerUp,orangeCap,purpleCap,manoftheMatch,playeroftheTournament;


    public WinnerListModel() {
    }

    public WinnerListModel(int picture, String year, String winner, String runnerUp, String orangeCap, String purpleCap, String manoftheMatch, String playeroftheTournament) {
        this.picture = picture;
        this.year = year;
        this.winner = winner;
        this.runnerUp = runnerUp;
        this.orangeCap = orangeCap;
        this.purpleCap = purpleCap;
        this.manoftheMatch = manoftheMatch;
        this.playeroftheTournament = playeroftheTournament;
    }

    public int getPicture() {
        return picture;
    }

    public String getYear() {
        return year;
    }

    public String getWinner() {
        return winner;
    }

    public String getRunnerUp() {
        return runnerUp;
    }

    public String getOrangeCap() {
        return orangeCap;
    }

    public String getPurpleCap() {
        return purpleCap;
    }

    public String getManoftheMatch() {
        return manoftheMatch;
    }

    public String getPlayeroftheTournament() {
        return playeroftheTournament;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setRunnerUp(String runnerUp) {
        this.runnerUp = runnerUp;
    }

    public void setOrangeCap(String orangeCap) {
        this.orangeCap = orangeCap;
    }

    public void setPurpleCap(String purpleCap) {
        this.purpleCap = purpleCap;
    }

    public void setManoftheMatch(String manoftheMatch) {
        this.manoftheMatch = manoftheMatch;
    }

    public void setPlayeroftheTournament(String playeroftheTournament) {
        this.playeroftheTournament = playeroftheTournament;
    }
}
