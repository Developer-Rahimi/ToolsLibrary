package ir.otoplay.tools.API;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import ir.otoplay.otoluxtools.Intefaces.OnSafeMemoryListener;
import ir.otoplay.otoluxtools.Utils.SendMessage;


public class SafeMemory {
    Context context;
    private SafeMemoryReceiver safeMemoryReceiver=new SafeMemoryReceiver();
    private OnSafeMemoryListener safeMemoryListener;
    SendMessage sendMessage;
    public SafeMemory(Context context) {
        this.context = context;
        sendMessage=new SendMessage(context);
        IntentFilter filter=new IntentFilter();
        filter.addAction("SafeMemory.Notification");
        context.registerReceiver(safeMemoryReceiver,filter);
    }
    public void ReadKey(String Key){
        sendMessage.SendSafeMemory("Read",Key,"");
    }
    public void WriteKey(String Key,String Data){
        sendMessage.SendSafeMemory("Write",Key,Data);
    }
    public class SafeMemoryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String Target=intent.getStringExtra("Key");
                String Data=intent.getStringExtra("Data");
                Boolean Error=intent.getBooleanExtra("Error",false);
                if(safeMemoryListener!=null){
                    safeMemoryListener.Received(Target,Data,Error);
                }
            }
            catch (Exception e){

            }
        }
    }
    public void setOnSafeMemoryListener(OnSafeMemoryListener safeMemoryListener){
        this.safeMemoryListener=safeMemoryListener;
    }
}
