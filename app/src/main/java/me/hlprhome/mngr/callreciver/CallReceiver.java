package me.hlprhome.mngr.callreciver;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;

import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



import static android.content.Context.MODE_PRIVATE;

public class CallReceiver extends PhonecallReceiver{

    TextView tvResults;




    @Override
    protected void onIncomingCallReceived(Context ctx, String number, Date start)
    {
        //
        Log.d("Call *****Recived","Number: " + number +" - "+ start);


    }

    @Override
    protected void onIncomingCallAnswered(Context ctx, String number, Date start)
    {
        //
        Log.d("Call Answrd","Number: "+number);
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end)
    {
        //
        Log.d("Call endd","Number: "+number);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start)
    {
        //
        Log.d("Call strtd","Number: "+number);
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end)
    {
        //
        Log.d("Call Out call ended","Number: "+number);
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start)
    {
        //
        Log.d("Call ******mssd","Number: " + number +" - "+ start);

        SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yy");
        SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm");
        String dateString = dateformatter.format(start);
        String timeString = timeformatter.format(start);


        //Check in or out
        String check = "Check In";
        try {
        Date checkTime = timeformatter.parse(timeString);
        Date midday = timeformatter.parse("11:00");
            if (checkTime.compareTo(midday)>0){
                check = "Check Out";
            }
        } catch (ParseException e){
            // Exception handling goes here
            e.printStackTrace();
        }


        //Save to file
        try{
            FileOutputStream file = ctx.openFileOutput("Data.txt",MODE_PRIVATE);
            OutputStreamWriter outputfile = new OutputStreamWriter(file);
            outputfile.write(number + "," + check + "," + timeString + "," + dateString +"\n");
            outputfile.flush();
            outputfile.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        //send HTTP
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("entry.442378818", number) //number
                .add("entry.375324555", check) //call type
                .add("entry.775427152", timeString) //call Duration
                .add("entry.1136970274", dateString) //call Date
                .build();

        String url = "https://docs.google.com/forms/d/e/1FAIpQLSd28woTN9bTT_3Ks7vNqS0xXKgj4czyVecAKqK9BrIEQT9Ubg";

        Request request = new Request.Builder()
                .url(url + "/formResponse")
                .post(formBody)
                .build();

        RemoteViews view = new RemoteViews("me.hlprhome.mngr.callreciver",R.layout.activity_main);
        view.setTextViewText(R.id.tvResults, number +" - "+ start);


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    Log.d("Call ******mssd","Number: " + myResponse +" - "+ "Done");




                }
            }
        });

    }

}
