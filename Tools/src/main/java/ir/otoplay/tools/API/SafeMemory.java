package ir.otoplay.tools.API;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ir.otoplay.tools.Intefaces.OnSafeMemoryListener;
import ir.otoplay.tools.Utils.SendMessage;


public class SafeMemory {
    Context context;
    private SafeMemoryReceiver safeMemoryReceiver=new SafeMemoryReceiver();
    private OnSafeMemoryListener safeMemoryListener;
    SendMessage sendMessage;
    private  String K="12345678903216549870987654321012";
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
        try {
            sendMessage.SendSafeMemory("Write",Key,encrypt(Data.getBytes(),GetKey()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private class SafeMemoryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String Target=intent.getStringExtra("Key");
                String Data=intent.getStringExtra("Data");
                Boolean Error=intent.getBooleanExtra("Error",false);
                if(safeMemoryListener!=null){
                    safeMemoryListener.Received(Target,decrypt(Data.getBytes(),GetKey()),Error);
                }
            }
            catch (Exception e){

            }
        }
    }
    public void setOnSafeMemoryListener(OnSafeMemoryListener safeMemoryListener){
        this.safeMemoryListener=safeMemoryListener;
    }
    private   String encrypt(byte[] plaintext, String key) throws Exception {
        byte[] IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] cipherText = cipher.doFinal(plaintext);
        return new String(Base64.encode(cipherText,Base64.DEFAULT));
    }
    private   String decrypt(byte[] cipherText, String key) {
        try {
            byte[] IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(Base64.decode(new String(decryptedText),Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String GetKey(){
        String pak=context.getPackageName();
        return (pak+K).substring(0,32);
    }
}
