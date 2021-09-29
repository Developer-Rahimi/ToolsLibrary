package ir.otoplay.tools.API;

import android.content.Context;
import android.content.Intent;

public class StatusBarManager {
    Context context;
    public static final String AvIn="AvIn";
    public static final String BTMusic="BTMusic";
    public static final String CarInfo="CarInfo";
    public static final String SystemDeviceInfo="SystemDeviceInfo";
    public static final String Display="Display";
    public static final String DisplayNightLight="DisplayNightLight";
    public static final String BTHandsFree="BTHandsFree";
    public static final String Media="Media";
    public static final String Menu="Menu";
    public static final String MirrorLink="MirrorLink";
    public static final String BTPhoneBook="BTPhoneBook";
    public static final String Radio="Radio";
    public static final String SDMusic="SDMusic";
    public static final String SDPhoto="SDPhoto";
    public static final String SDVideo="SDVideo";
    public static final String Setup="Setup";
    public static final String Sound="Sound";
    public static final String SoundBalFad="SoundBalFad";
    public static final String SoundNaviMix="SoundNaviMix";
    public static final String SoundSpeaker="SoundSpeaker";
    public static final String System="System";
    public static final String SystemLanguage="SystemLanguage";
    public static final String SystemTimeDay="SystemTimeDay";
    public static final String DisplayDayLight="DisplayDayLight";
    public static final String USBMusic="USBMusic";
    public static final String USBPhoto="USBPhoto";
    public static final String USBVideo="USBVideo";
    public StatusBarManager(Context context) {
        this.context = context;
    }
    public void SetTitle(String title){
        SendSystemUI("Title",title);
    }
    public void SetWifiState(boolean state){
        if(state){
            SendSystemUI("Wifi","ON");
        }
        else {
            SendSystemUI("Wifi","OFF");
        }

    }
    public void SetBluetoothState(boolean state){
        if(state){
            SendSystemUI("BLT","ON");
        }
        else {
            SendSystemUI("BLT","OFF");
        }
    }
    public void SetPhoneState(boolean state){
        if(state){
            SendSystemUI("Phone","ON");
        }
        else {
            SendSystemUI("Phone","OFF");
        }
    }
    public void SetAuxState(boolean state){
        if(state){
            SendSystemUI("AUX","ON");
        }
        else {
            SendSystemUI("AUX","OFF");
        }
    }
    public void SetSDState(boolean state){
        if(state){
            SendSystemUI("SD","ON");
        }
        else {
            SendSystemUI("SD","OFF");
        }
    }
    public void SetUSBState(boolean state){
        if(state){
            SendSystemUI("USB","ON");
        }
        else {
            SendSystemUI("USB","OFF");
        }
    }
    public void SetBatState(int state){
        if(state==0){
            SendSystemUI("Battery","Empty");
        }
        else if(state==1){
            SendSystemUI("Battery","One");
        }
        else if(state==2){
            SendSystemUI("Battery","Two");
        }
        else if(state==3){
            SendSystemUI("Battery","Three");
        }
        else {
            SendSystemUI("Battery","Full");
        }
    }
    public void SetSignalState(int state){
        if(state==0){
            SendSystemUI("Signal","Empty");
        }
        else if(state==1){
            SendSystemUI("Signal","One");
        }
        else if(state==2){
            SendSystemUI("Signal","Two");
        }
        else if(state==3){
            SendSystemUI("Signal","Three");
        }
        else {
            SendSystemUI("Signal","Full");
        }
    }

    public void SendSystemUI(String Action,String Target){
        Intent intent = new Intent();
        intent.setAction("SystemUI.Command");
        intent.putExtra("Action",Action);
        intent.putExtra("Target",Target);
        context.sendBroadcast(intent);
    }
}
