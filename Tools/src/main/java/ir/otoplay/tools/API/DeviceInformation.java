package ir.otoplay.tools.API;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import ir.otoplay.tools.Intefaces.OnDeviceInformation;
import ir.otoplay.tools.Utils.SendMessage;

public class DeviceInformation {
    Context context;
    private DeviceInformationReceiver deviceInformationReceiver=new DeviceInformationReceiver();
    private OnDeviceInformation onDeviceInformation;
    SendMessage sendMessage;
    public DeviceInformation(Context context) {
        this.context = context;
        sendMessage=new SendMessage(context);
        IntentFilter filter=new IntentFilter();
        filter.addAction("ServiceManager.Notification");
        context.registerReceiver(deviceInformationReceiver,filter);
    }
    public void SendInformation(String Target,String Command,String Value){
        sendMessage.SendDeviceInformation(Target,Command,Value);
    }
    public class DeviceInformationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String Target=intent.getStringExtra("Target");
                String Command=intent.getStringExtra("Command");
                String value=intent.getStringExtra("value");
                if(onDeviceInformation!=null){
                    onDeviceInformation.onDeviceInformation(Target,Command,value);
                }
            }
            catch (Exception e){

            }
        }
    }
    public void setOnDeviceInformation(OnDeviceInformation onDeviceInformation){
        this.onDeviceInformation=onDeviceInformation;
    }
}
