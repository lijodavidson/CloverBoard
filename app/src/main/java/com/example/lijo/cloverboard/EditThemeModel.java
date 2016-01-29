package com.example.lijo.cloverboard;

/**
 * Created by LIJO on 1/28/2016.
 */
public class EditThemeModel {



    String radioName;

 /*  int radioArt;*/


    /*String radioDial;*/


    public EditThemeModel(String radioName /*,int radioArt*/) {
        this.radioName = radioName;
        /*this.radioArt = radioArt;*/

       /* this.radioDial = radioDial;*/
    }

    public String getRadioName() {

        return radioName;
    }


    public void setRadioName(String radioName) {
        this.radioName = radioName;
    }

 /*  public int getRadioArt() {
        return radioArt;
    }*/


/*   public void setRadioArt(int radioArt) {
        this.radioArt = radioArt;
    }*/

    /*public String getRadioDial() {
        return radioDial;
    }

    public void setRadioDial(String radioDial) {
        this.radioDial = radioDial;
    }*/


}
