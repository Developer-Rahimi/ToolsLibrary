package ir.otoplay.tools.API;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


import ir.otoplay.tools.Intefaces.OnInformationListener;
import ir.otoplay.tools.Utils.SendMessage;


public class Information {
    Context context;
    private InformationReceiver informationReceiver=new InformationReceiver();
    private OnInformationListener onInformationListener;
    SendMessage sendMessage;
    public Information(Context context) {
        this.context = context;
        sendMessage=new SendMessage(context);
        IntentFilter filter=new IntentFilter();
        filter.addAction("Tools.Notification");
        context.registerReceiver(informationReceiver,filter);
    }
    public void GetInformation(){
        sendMessage.SendInformation("Information","Get","");
    }
    private class InformationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
                try {
                    String Target=intent.getStringExtra("Target");
                    String Command=intent.getStringExtra("Command");
                    String Value=intent.getStringExtra("Value");
                    if(onInformationListener!=null){
                        onInformationListener.onInformation(Value);
                    }
                }
                catch (Exception e){

                }
        }
    }
    public void setOnInformationListener(OnInformationListener onInformationListener){
        this.onInformationListener=onInformationListener;
    }
}
