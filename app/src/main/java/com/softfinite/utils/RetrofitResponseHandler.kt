package com.softfinite.utils

import android.app.Activity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

abstract class RetrofitResponseHandler(private val context: Activity) : Callback<ResponseBody> {

    abstract fun onStart()

    abstract fun onSuccess(content: String)

    abstract fun onFinish()

    abstract fun onFailure(e: Throwable, content: String)

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

        onFinish()

        if (response.isSuccessful) {

            val inputAsString = response.body()!!.source().inputStream().bufferedReader().use { it.readText() }

            Debug.e("Input", inputAsString)

            val sb = StringBuilder()
            sb.append(inputAsString)
            val br = BufferedReader(InputStreamReader(response.body()!!.source().inputStream()))
            var read: String? = null


//            try {
//                while ( br.readLine() != null )
//                {
//                    sb.append(read)
//                }
//                while ((read == br.readLine()) != null) {
//                    sb.append(read)
//                }
//
//                br.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }

            //            if (res.St == 506) {
            //
            //                Utils.clearLoginCredetials(context);
            //
            //                LocalBroadcastManager.getInstance(context)
            //                        .sendBroadcast(
            //                                new Intent(Constant.FINISH_ACTIVITY));
            //
            //                Intent intent = new Intent(context,
            //                        LoginActivity.class);
            //                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
            //                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //                context.startActivity(intent);
            //                return;
            //            }

            try {
                onSuccess(sb.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else {


            val inputAsString: String
            try {
                inputAsString = response.body()!!.source().inputStream().bufferedReader().use { it.readText() }
                Debug.e("Input", inputAsString)

                val sb = StringBuilder()
                sb.append(inputAsString)
                onFailure(Throwable(""), "" + sb.toString())
            } catch (e: Exception) {
            }


//            var read: String? = null
//
//            try {
//                val br = BufferedReader(InputStreamReader(response.body()!!.source().inputStream()))
//                while ( br.readLine() != null) {
//                    sb.append(read)
//                }
//
//                br.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }


        }

    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        onFailure(t, "")
    }

}

