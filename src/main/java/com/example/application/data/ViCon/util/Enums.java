package com.example.application.data.ViCon.util;

public class Enums {
    public enum Preference{
        Unselected,         //grey
        Root,               //blue
        SpecificConsent,    //yellow
        BroadConsent,       //green
        BroadRefusal,       //red
        BroadConsentWith,   //green + ?
        BroadRefusalWith    //red + ?
    }
    public enum PreferenceClassification{
        Akteure, Rechte, Zwecke, Datentyp
    }
    public enum ConsentType{
        Research, Treatment
    }
}
