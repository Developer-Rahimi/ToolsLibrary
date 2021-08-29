package ir.otoplay.tools.API;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

public class BrightnessManager {
    Context context;

    public BrightnessManager(Context context) {
        this.context = context;
    }
    public void SetMode(int mode){
        if(mode<0)mode=2;
        int brightness=0;
        if(mode==1)brightness=80;
        else if(mode==2)brightness=255;

        ContentResolver cResolver = context.getContentResolver();
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
    }
    public void SetValue(int value){
        ContentResolver cResolver = context.getContentResolver();
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, value);
    }
}
