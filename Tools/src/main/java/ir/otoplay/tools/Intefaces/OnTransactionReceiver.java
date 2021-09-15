package ir.otoplay.tools.Intefaces;

import org.json.JSONObject;

public interface OnTransactionReceiver {
    void onReceive(String Command, JSONObject data);
}
