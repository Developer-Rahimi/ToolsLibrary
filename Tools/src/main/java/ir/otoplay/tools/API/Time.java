package ir.otoplay.tools.API;

import android.app.AlarmManager;
import android.content.Context;
import android.provider.Settings;
import android.text.format.DateFormat;

import java.io.File;
import java.util.Calendar;

public class Time {
    TextManager textManager;
    Context context;
    public final static String TimeCommand="/sys/module/pcf8563/parameters/command";
    public final static String TimeSec="/sys/module/pcf8563/parameters/Sec";
    public final static String TimeMin="/sys/module/pcf8563/parameters/Min";
    public final static String TimeHour="/sys/module/pcf8563/parameters/Hour";
    public final static String TimeYear="/sys/module/pcf8563/parameters/Year";
    public final static String TimeWeek="/sys/module/pcf8563/parameters/DayOfWeek";
    public final static String TimeMonth="/sys/module/pcf8563/parameters/Month";
    public final static String TimeDay="/sys/module/pcf8563/parameters/Day";
    public Time(Context context) {
        this.context=context;
        textManager=new TextManager();
    }
    public void GetTime(){
        textManager.Write(TimeCommand,"GetTime");
        int Year=Integer.parseInt(textManager.Read(new File(TimeYear)).trim().replace("\"",""));
        int Month=Integer.parseInt(textManager.Read(new File(TimeMonth)).trim().replace("\"",""));
        int Day=Integer.parseInt(textManager.Read(new File(TimeDay)).trim().replace("\"",""));
        int DayOfWeek=Integer.parseInt(textManager.Read(new File(TimeWeek)).trim().replace("\"",""));
        int Hour=Integer.parseInt(textManager.Read(new File(TimeHour)).trim().replace("\"",""));
        int Min=Integer.parseInt(textManager.Read(new File(TimeMin)).trim().replace("\"",""));
        int Sec=Integer.parseInt(textManager.Read(new File(TimeSec)).trim().replace("\"",""));
        try {
            if (!DateFormat.is24HourFormat(context))
            {
                Settings.System.putString(context.getContentResolver(), Settings.System.TIME_12_24, "24");
            }
            if(Year!=0) {
                Calendar c = Calendar.getInstance();
                c.set(Year + 2000, Month - 1, Day, Hour, Min, Sec);
                AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                am.setTime(c.getTimeInMillis());
            }else {
                Calendar c = Calendar.getInstance();
                c.set(2021, 2, 21, 12, 30, 30);
                AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                am.setTime(c.getTimeInMillis());
            }
        }catch (Exception e){
        }
    }
    public void SetTime(){
        Calendar c = Calendar.getInstance();
        textManager.Write(TimeYear,c.get(Calendar.YEAR)-2000+"");
        textManager.Write(TimeMonth,c.get(Calendar.MONTH)+1+"");
        textManager.Write(TimeDay,c.get(Calendar.DAY_OF_MONTH)+"");
        textManager.Write(TimeWeek,c.get(Calendar.DAY_OF_WEEK)+"");
        textManager.Write(TimeHour,c.get(Calendar.HOUR_OF_DAY)+"");
        textManager.Write(TimeMin,c.get(Calendar.MINUTE)+"");
        textManager.Write(TimeSec,c.get(Calendar.SECOND)+"");
        textManager.Write(TimeCommand,"SetTime");
    }
    public void SetTime(int Year,int Month,int Day,int Hour,int Min,int Sec){
        Calendar c = Calendar.getInstance();
        c.set(Year + 2000, Month - 1, Day, Hour, Min, Sec);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setTime(c.getTimeInMillis());
    }
}
