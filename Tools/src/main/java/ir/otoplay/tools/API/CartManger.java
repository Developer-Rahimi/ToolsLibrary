package ir.otoplay.tools.API;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import org.json.JSONArray;
import org.json.JSONObject;

public class CartManger {
    Context context;
    private ServiceMangerReceiver serviceMangerReceiver=new ServiceMangerReceiver();
    public CartManger(Context context) {
        this.context = context;
        IntentFilter filter=new IntentFilter();
        filter.addAction("CartManager.Notification");
        context.registerReceiver(serviceMangerReceiver,filter);
    }
    public class ServiceMangerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String Action =intent.getStringExtra("Command");
            String Value =intent.getStringExtra("Value");
            //Log.i("TestModule",Action+"-->"+Value.toString());
            try {
                JSONObject jsonObject=new JSONObject(Value);
                if(onCartListener!=null){
                    onCartListener.Detect(Action,jsonObject);
                }
            }
            catch (Exception e){

            }

        }
    }
    public interface OnCartListener{
        void Detect(String Action, JSONObject data);
    }
    private  OnCartListener onCartListener;
    public void setOnCartListener(OnCartListener onCartListener){
        this.onCartListener=onCartListener;
    }
    public void SetAmount(String amount){
        Send("Amount",amount+"");
    }
    public void SetTerminalID(String val){
        Send("TerminalID",val+"");
    }
    public void SetTransactionState(boolean state){
        Send("TransactionState",state+"");
    }
    public void SetCartDriverType(JSONArray data){
        Send("CartDriverType",data+"");
    }
    public void SetCartPassengerType(JSONArray data){
        Send("CartPassengerType",data+"");
    }
    public void Login(){
        Send("Login","");
    }
    public void Send(String Command,String Value){
        Intent intent = new Intent();
        intent.setAction("CartManager.Message");
        intent.putExtra("Target","CartManager");
        intent.putExtra("Command",Command);
        intent.putExtra("Value",Value);
        context.sendBroadcast(intent);
    }
}
