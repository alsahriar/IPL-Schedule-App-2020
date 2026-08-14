package com.softtechbd.iplschedule2020.Extra_code;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.softtechbd.iplschedule2020.BuildConfig;
import com.softtechbd.iplschedule2020.R;

import java.util.Objects;

public class Social_code {
    private Activity activity;
    private String stringFeedback;
    private String userName;
    private float mRating;

    public void Share_method(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\uD83D\uDE4B🙋‍♂️ Hi friends, Please download this application! \n \uD83D\uDC49  "+activity.getResources().getString(R.string.app_name)+" :- ");
        stringBuilder.append("https://play.google.com/store/apps/details?id="+activity.getPackageName());
        stringBuilder.append("\n✅ And don't forget to send your feedback \uD83D\uDE01\uD83D\uDE0A");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        String string = stringBuilder.toString();
        intent.putExtra("android.intent.extra.TEXT",string);
        activity.startActivity(Intent.createChooser((Intent)intent,(CharSequence)"Share via"));
    }
    public void Rating_method(){
        try{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("market://details?id=");
            stringBuilder.append(activity.getPackageName());
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));

        }catch (ActivityNotFoundException activityNotFoundException){
            Toast.makeText(activity,"Couldn't launch",Toast.LENGTH_SHORT).show();
        }
    }
    public void  submit_feedback(){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.feed_back_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        Button send =(Button)dialog.findViewById(R.id.submit_btn);
        Button cancel =(Button)dialog.findViewById(R.id.cancel_btn);
        RatingBar ratingBar  =(RatingBar)dialog.findViewById(R.id.ratingId);
        final TextInputEditText name = (TextInputEditText)dialog.findViewById(R.id.name_txt);
        final TextInputEditText feedback = (TextInputEditText)dialog.findViewById(R.id.feedback_txt);
        dialog.show();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Rating_method();
                mRating=rating;
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Please write your name");
                    name.requestFocus();
                }else if(TextUtils.isEmpty(feedback.getText().toString())){
                    feedback.setError("Please write your feedback");
                    feedback.requestFocus();
                }else{
                    userName = name.getText().toString();
                    stringFeedback = feedback.getText().toString();
                    sendFeedback();
                    dialog.dismiss();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
    public void FeedBack_method(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Write your feedback");

        final EditText input = new EditText(activity);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
            /*final Typeface typeface = ResourcesCompat.getFont(getApplicationContext(),R.font.bowlby_one);
            input.setTypeface(typeface);*/
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(activity,"Please, Type your feedback",Toast.LENGTH_SHORT).show();
                }
                else {
                    stringFeedback = input.getText().toString();
                    sendFeedback();
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }public void sendFeedback(){
        Intent intent= new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto: "));
        intent.putExtra("android.intent.extra.EMAIL", new String[]{"mdaasahriar2002@gmail.com"});
        intent.putExtra("android.intent.extra.SUBJECT","FeedBack about "+activity.getResources().getString(R.string.app_name));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Feedback          : "+"  "+stringFeedback);
        stringBuilder.append("\n\n\nName                 : "+userName);
        stringBuilder.append("\nRating                : "+mRating);
        stringBuilder.append("\nDevice                : "+ Build.DEVICE);
        stringBuilder.append("\nApp Version      : "+ BuildConfig.VERSION_NAME);
        stringBuilder.append("\nManufacturer   : "+Build.MANUFACTURER);
        stringBuilder.append("\nBrand                 : "+Build.BOARD);
        stringBuilder.append("\nOS VERSION     : "+Build.VERSION.RELEASE);
        stringBuilder.append("\nModel                 : "+Build.MODEL+" "+Build.VERSION.RELEASE);
        stringBuilder.append("\n"+getAndroidVersion());
        stringBuilder.append("\nProduct              : "+Build.PRODUCT);
        intent.putExtra("android.intent.extra.TEXT",stringBuilder.toString());
        try {
            activity.startActivity(Intent.createChooser((Intent)intent,(CharSequence)"Send email via"));

        } catch (ActivityNotFoundException e){
            Toast.makeText(activity, "G-mail app not isn't available in your device", Toast.LENGTH_SHORT).show();
        } catch (Exception exception){
            Toast.makeText(activity,"Failed to send feedback",Toast.LENGTH_SHORT).show();
        }
    }public String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK      : " + sdkVersion + " (" + release +")";
    }

    public Social_code(Activity activity) {
        this.activity = activity;
    }

    public Social_code() {
    }
}
