package com.example.didiervaldez.testraspberry

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by didiervaldez on 01/12/17.
 */
/*****************************************************/
/*  This is a background process for connecting      */
/*   to the arduino server and sending               */
/*    the GET request withe the added data           */
/*****************************************************/

class Background_get : AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg p0: String?): String {


        try {
            var url = URL("http://192.168.1.132/control.php?${p0[0]}")
            var connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            var br = BufferedReader(InputStreamReader(connection.inputStream))
            var result = StringBuilder()
            while (br.readLine() != null) {
                result.append(br.readLine()).append("\n")
            }
            br.close()
            connection.disconnect()
            return result.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return ""
    }
}

//private class Background_get extends AsyncTask<String, Void, String> {
//    @Override
//    protected String doInBackground(String... params) {
//        try {
//            /*********************************************************/
//            /* Change the IP to the IP you set in the arduino sketch */
//            /*********************************************************/
//            URL url = new URL("http://192.168.1.177/?" + params[0]);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder result = new StringBuilder();p
//            String inputLine;
//            while ((inputLine = in.readLine()) != null)
//                result.append(inputLine).append("\n");
//
//            in.close();
//            connection.disconnect();
//            return result.toString();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}