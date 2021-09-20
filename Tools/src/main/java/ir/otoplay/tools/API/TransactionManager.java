package ir.otoplay.tools.API;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import org.json.JSONObject;

import ir.otoplay.tools.Intefaces.OnTransactionReceiver;

public class TransactionManager {
    private Context context;
    private TransactionReceiver transactionReceiver=new TransactionReceiver();
    private OnTransactionReceiver onTransactionReceiver;
    public TransactionManager(Context context) {
        this.context = context;
        IntentFilter filter=new IntentFilter();
        filter.addAction("ReportTransaction.Notification");
        context.registerReceiver(transactionReceiver,filter);
    }
    public void GetTransaction(){
        Send("GetTransaction","");
    }
    public void LoginDriver(String pan){
        Send("LoginDriver",pan);
    }
    public void Logout(){
        Send("Logout","");
    }
    public void SetAmount(String Amount){
        Send("Amount",Amount);
    }
    public void ServerReceive(String ids){
        Send("ServerReceive",ids);
    }
    private class TransactionReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String Action =intent.getStringExtra("Command");
            String Response =intent.getStringExtra("Response");
            //Log.i("TestModule",Action+"-->"+Value.toString());
            try {
                JSONObject jsonObject=new JSONObject(Response);
                if(onTransactionReceiver!=null){
                    onTransactionReceiver.onReceive(Action,jsonObject);
                }
            }
            catch (Exception e){

            }

        }
    }
    public void setOnTransactionReceiver(OnTransactionReceiver onTransactionReceiver){
        this.onTransactionReceiver=onTransactionReceiver;
    }
    private void Send(String Command,String Value){
        Intent intent = new Intent();
        intent.setAction("ReportTransaction.Message");
        intent.putExtra("Command",Command);
        intent.putExtra("Value",Value);
        context.sendBroadcast(intent);
    }
}
