package com.softtechbd.iplschedule2020.Model;

public class Records_Previews_M {
    private String Pos, TopPlayerName,TopRecords;

    public Records_Previews_M() {
    }

    public Records_Previews_M(String pos, String topPlayerName, String topRecords) {
        Pos = pos;
        TopPlayerName = topPlayerName;
        TopRecords = topRecords;
    }

    public String getPos() {
        return Pos;
    }

    public void setPos(String pos) {
        Pos = pos;
    }

    public String getTopPlayerName() {
        return TopPlayerName;
    }

    public void setTopPlayerName(String topPlayerName) {
        TopPlayerName = topPlayerName;
    }

    public String getTopRecords() {
        return TopRecords;
    }

    public void setTopRecords(String topRecords) {
        TopRecords = topRecords;
    }
}
