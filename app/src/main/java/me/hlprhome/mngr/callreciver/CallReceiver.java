package me.hlprhome.mngr.callreciver;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;



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




        //Send to Slack

        OkHttpClient slackClient = new OkHttpClient();

        JSONObject json = new JSONObject();
        try{
        json.put("text", number +" | " + check +" | "+ start );
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String slackUrl = "https://hooks.slack.com/services/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";

        String jsonString = json.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);

        Request slackRequest = new Request.Builder()
                .header("X-Client-Type", "Android")
                .url(slackUrl)
                .post(body)
                .build();

        slackClient.newCall(slackRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Send To Slack","Error: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    Log.d("Send To Slack","Number: " + myResponse +" - "+ "Done");

                }
            }
        });


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

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Send To Sheet","Error: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    Log.d("Send To Sheet", myResponse );




                }
            }
        });

    }

}
