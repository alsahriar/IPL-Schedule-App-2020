package com.softtechbd.iplschedule2020.Model;

public class ModelClass2 {
    private int player_profile;
    private String player_name;
    private String player_price;
    private int player_status;
    private String role;
    private String batting_style;
    private String bowling_style;
    private String nationality;
    private int Colour;


    public ModelClass2() {
    }

    public ModelClass2(int player_profile, String player_name, String player_price, int player_status, String role, String batting_style, String bowling_style, String nationality, int colour) {
        this.player_profile = player_profile;
        this.player_name = player_name;
        this.player_price = player_price;
        this.player_status = player_status;
        this.role = role;
        this.batting_style = batting_style;
        this.bowling_style = bowling_style;
        this.nationality = nationality;
        Colour=colour;
    }

    public int getPlayer_profile() {
        return player_profile;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public String getPlayer_price() {
        return player_price;
    }
    public int getPlayer_status() {
        return player_status;
    }
    public String getRole(){
        return role;
    }
    public String getBatting_style(){
        return batting_style;
    }
    public String getBowling_style(){
        return bowling_style;
    }
    public String getNationality(){
        return nationality;
    }

    public int getColour() {
        return Colour;
    }

    public void setColour(int colour) {
        Colour = colour;
    }

    public void setPlayer_profile(int player_profile) {
        this.player_profile = player_profile;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public void setPlayer_price(String player_price) {
        this.player_price = player_price;
    }public void setPlayer_status(int player_status) {
        this.player_status = player_status;
    }
    public void setRole(String role){
        this.role = role;
    }
    public void setBatting_style(String batting_style){
        this.batting_style = batting_style;
    }
    public void setBowling_style(String bowling_style){
        this.bowling_style = bowling_style;
    }
    public void setNationality(String nationality){
        this.nationality = nationality;
    }
}
