package ir.otoplay.tools.Utils;

import android.content.Context;
import android.content.Intent;

public class SendMessage {
    Context context;

    public SendMessage(Context context) {
        this.context = context;
    }
    public void SendInformation(String Target,String Command,String Value){
        Intent intent = new Intent();
        intent.setAction("Tools.Message");
        intent.putExtra("Target",Target);
        intent.putExtra("Command",Command);
        intent.putExtra("Value",Value);
        context.sendBroadcast(intent);
    }
    public void SendDeviceInformation(String Target,String Command,String Value){
        Intent intent = new Intent();
        intent.setAction("ServiceManger.Message");
        intent.putExtra("Target",Target);
        intent.putExtra("Command",Command);
        intent.putExtra("Value",Value);
        context.sendBroadcast(intent);
    }
    public void SendSafeMemory(String Target,String Key,String Data){
        Intent intent = new Intent();
        intent.setAction("SafeMemory.Message");
        intent.putExtra("Target",Target);
        intent.putExtra("Key",Key);
        intent.putExtra("Data",Data);
        context.sendBroadcast(intent);
    }
}
