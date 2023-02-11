package com.example.proyectoobregon;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.Random;

public class ToastPersonalizado {
    Drawable tDrawable;
    Drawable Icon;
    boolean show;

    public Drawable getIcon() {
        return Icon;
    }

    public void setIcon(Drawable icon) {
        Icon = icon;
    }

    int textColor;
    int textSize;
    android.widget.Toast nToast;
    int ToastTime;
    String text;
    Context context;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public ToastPersonalizado(Context context)
    {   nToast=new android.widget.Toast(context);
        this.context=context;
        ToastTime=0;
        text="";
        tDrawable=getShape(Color.parseColor("#333333"),Color.parseColor("#555555"),Color.parseColor("#333333"),30, GradientDrawable.LINEAR_GRADIENT);
        this.textColor=Color.parseColor("#FFFFFF");
        this.textSize=13;

    }
    //Creo el drawable background
    public void setBackgroundDrawable(Drawable d){tDrawable=d;}
    //por defecto gradient orientacion recto orizontal
    public void setupBackground(int start_color,int center_color,int end_color,int radius_pd,int gradientType)
    {
        tDrawable=getShape(start_color,center_color,end_color,radius_pd,gradientType);
    }
    public void setupBackground(int start_color, int center_color, int end_color, int radius_pd, int gradientType, GradientDrawable.Orientation orientation)
    {
        tDrawable=getShape(start_color,center_color,end_color,radius_pd,gradientType,orientation);
    }
    public void setBackgroundColorRadius(int color,int radius_pd)
    {
        tDrawable=getShape(color,color,color,radius_pd,GradientDrawable.LINEAR_GRADIENT);
    }
    public void setBackgroundColor(int color)
    {
        tDrawable=getShape(color,color,color,20,GradientDrawable.LINEAR_GRADIENT);
    }

    public void setTime(int time)
    {
        ToastTime=time;
    }
    public void cancel()
    {
        nToast.cancel();
    }
    public boolean isShow()
    {
        if(nToast!=null)
            return true;
        else return false;
    }

    public void show()
    {   nToast= android.widget.Toast.makeText(context,text,ToastTime);
        Button b=new Button(context);
        b.setCompoundDrawablesWithIntrinsicBounds(Icon,null,null,null);
        b.setText(text);
        b.setCompoundDrawablePadding(5);
        b.setTextColor(textColor);
        b.setTextSize(textSize);
        b.setPadding(15,15,15,15);
        b.setBackground(tDrawable);
        nToast.setView(b);   //asigno el boton al toast
        nToast.show();
    }



    //Mostrar toast
    public static void showToast(Context c,String message)
    {
        android.widget.Toast.makeText(c,message, android.widget.Toast.LENGTH_SHORT).show();
    }

    //Mostrar toast personalizado
    public static void showToast(Context c,String message,int text_color,int background_color)
    {
        android.widget.Toast toast = android.widget.Toast.makeText(c,message, android.widget.Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 50, 50);
        toast.getView().setPadding(10, 10, 10, 10);
        toast.getView().setBackgroundColor(background_color);
        TextView text = (TextView) toast.getView().findViewById(android.R.id.message);
        text.setTextColor(text_color);
        text.setTextSize(14);
        toast.show();
    }
    //Mensaje de alerta
    public static void showToastAlert(Context c,String message)
    {   android.widget.Toast toast = android.widget.Toast.makeText(c,message, Toast.LENGTH_LONG);
        Button b=new Button(c);
        b.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(c,android.R.drawable.ic_dialog_alert),null,null,null);
        b.setCompoundDrawablePadding(5);
        b.setText(message);
        b.setPadding(45,15,45,15);
        b.setTextColor(Color.WHITE);
       // b.setBackgroundColor(Color.parseColor("#7A1212"));


        b.setBackground(getShape(Color.parseColor("#7A1212"),Color.parseColor("#7A1212"),Color.parseColor("#7A1212"),50, GradientDrawable.LINEAR_GRADIENT));
        toast.setView(b);   //asigno el boton al toast
        toast.show();
    }
    public static void showToastInfo(Context c,String message)
    {
        Toast toast = Toast.makeText(c,message, Toast.LENGTH_SHORT);
        Button b=new Button(c);
        b.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(c,android.R.drawable.ic_dialog_info),null,null,null);
        b.setCompoundDrawablePadding(5);
        b.setText(message);
        b.setPadding(45,15,45,15);
        b.setTextColor(Color.BLACK);
        //b.setBackgroundColor(Color.parseColor("#F1BC19"));
        b.setBackground(getShape(Color.parseColor("#F1BC19"),Color.parseColor("#F1BC19"),Color.parseColor("#F1BC19"),50, GradientDrawable.LINEAR_GRADIENT));

        toast.setView(b);   //asigno el boton al toast
        toast.show();
    }
    public static void showToastLock(Context c,String message)
    {
        android.widget.Toast toast = android.widget.Toast.makeText(c,message, android.widget.Toast.LENGTH_SHORT);
        Button b=new Button(c);
        b.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(c,android.R.drawable.ic_lock_idle_lock),null,null,null);
        b.setCompoundDrawablePadding(5);
        b.setText(message);
        b.setPadding(35,15,35,15);
        b.setTextColor(Color.parseColor("#DDDDDD"));
        //b.setBackgroundColor(Color.parseColor("#4CAF50"));

        b.setBackground(getShape(Color.parseColor("#4CAF50"),Color.parseColor("#4CAF50"),Color.parseColor("#4CAF50"),45,GradientDrawable.LINEAR_GRADIENT));
        toast.setView(b);   //asigno el boton al toast
        toast.show();
    }




    //creo drawable shappe figura
    private static Drawable getShape(int startcolor, int centercolor, int endcolor, int radius_pd, int gradienType,GradientDrawable.Orientation orientation)
    {
        GradientDrawable gradientDrawable=new GradientDrawable(orientation,new int[]{startcolor,centercolor,endcolor});
        gradientDrawable.setGradientType(gradienType);//GradientDrawable.LINEAR_GRADIENT
        gradientDrawable.setCornerRadii(getRandomFloatArray(radius_pd));
        //gradientDrawable.setGradientCenter(0.0f,0.4f);
        return gradientDrawable;
    }
    //creo drawable shappe figura
    private static Drawable getShape(int startcolor, int centercolor, int endcolor, int radius_pd, int gradienType)
    {
        GradientDrawable gradientDrawable=new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,new int[]{startcolor,centercolor,endcolor});
        gradientDrawable.setGradientType(gradienType);//GradientDrawable.LINEAR_GRADIENT
        gradientDrawable.setCornerRadii(getRandomFloatArray(radius_pd));
        //gradientDrawable.setGradientCenter(0.0f,0.4f);
        return gradientDrawable;
    }
    //el randomfloat es el radius de esquinas
    private static float[]getRandomFloatArray(int radius){
        Random rnd=new Random();
        float[]buffer=new float[8];
        for(int i=0;i<buffer.length;i++)
        {
            buffer[i]=radius;
        }
        return buffer;
    }

}
