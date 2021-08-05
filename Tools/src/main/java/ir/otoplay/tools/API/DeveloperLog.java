package ir.otoplay.tools.API;

import android.content.Context;
import android.util.Log;

public class DeveloperLog {
    Context context;
    public DeveloperLog(Context context) {
        this.context = context;
    }

    public void SendInformationLog(String function,String Class,String Target,String Text){
        Log.i(context.getPackageName(),"Function-->"+function+"Class-->"+Class+"Target-->"+Target+"Text-->"+Text);
    }
    public void SendErrorLog(String function,String Class,String Target,String Text){
        Log.e(context.getPackageName(),"Function-->"+function+"Class-->"+Class+"Target-->"+Target+"Text-->"+Text);
    }
}
