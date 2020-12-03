package com.example.footballdb;

public class Model_football {
    //https://image.tmdb.org/t/p/w500/k68nPLbIST6NP96JmTxmZijEvCA.jpg
    String strTeam;
    String strDescriptionEN;
    String strTeamLogo;
    String strTeamBadge;
    int intLoved;
    String strStadium;
    int intFormedYear;
    int idTeam;

    public Model_football() {
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setId(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public void setStrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public void setStrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }

    public String getStrTeamLogo() {
        return strTeamLogo;
    }

    public void setStrTeamLogo(String strTeamLogo) {
        this.strTeamLogo = strTeamLogo;
    }

    public int getIntLoved() {
        return intLoved;
    }

    public void setIntLoved(int intLoved) {
        this.intLoved = intLoved;
    }

    public String getStrStadium() {
        return strStadium;
    }

    public void setStrStadium(String strStadium) {
        this.strStadium = strStadium;
    }

    public int getIntFormedYear() {
        return intFormedYear;
    }

    public void setIntFormedYear(int intFormedYear) {
        this.intFormedYear = intFormedYear;
    }

    public String getStrTeamBadge() {
        return strTeamBadge;
    }

    public void setStrTeamBadge(String strTeamBadge) {
        this.strTeamLogo = strTeamBadge;
    }

}