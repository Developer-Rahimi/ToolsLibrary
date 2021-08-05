package ir.otoplay.tools.API;

import android.content.Context;

import java.io.File;

public class LockManager {
    Context context;
    public final static String TimeYear="/sys/module/pcf8563/parameters/Year";
    public LockManager(Context context) {
        this.context = context;
    }
    public boolean CheckLock(){
        int Year=Integer.parseInt(new TextManager().Read(new File(TimeYear)).trim().replace("\"",""));
        if(Year==0){
            return true;
        }
        else return false;
    }
}
