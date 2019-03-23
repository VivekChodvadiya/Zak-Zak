package com.softfinite.gcm

import android.app.IntentService
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.google.android.gms.gcm.GoogleCloudMessaging
import com.softfinite.R
import com.softfinite.utils.Debug
import java.util.*


/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 14-Jun-18.
 */


class GCMIntentService : IntentService("GCMIntentService") {

    override fun onHandleIntent(i: Intent?) {
        val extras = i!!.extras
        val gcm = GoogleCloudMessaging.getInstance(this)
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        val messageType = gcm.getMessageType(i)

        if (!extras!!.isEmpty) { // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that
			 * GCM will be extended in the future with new message types, just
			 * ignore any message types you're not interested in, or that you
			 * don't recognize.
			 */
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR == messageType) {
                // generateNotification(this, "Send error", extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED == messageType) {
                // generateNotification(this, "Deleted messages on server",
                // extras.toString());
                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE == messageType) {

                Debug.e("", "" + extras.getString("message")!!)

                try {
                    //                    GCMres notificationResponse = new Gson().fromJson(
                    //                            extras.getString("message"), new TypeToken<GCMres>() {
                    //                            }.getType());
                    //
                    //                    if (notificationResponse != null) {
                    //                        Intent intent = new Intent(this, NotificationActivity.class);
                    //                        int notyType = notificationResponse.ntype;
                    //
                    //                        if (notyType == 1 && notificationResponse.handleType == 1) {
                    //
                    //                            MessageUser.Datum datum = new MessageUser.Datum();
                    //                            datum.FullName = "";
                    //                            datum.MemberCode = notificationResponse.MemberCode;
                    //                            datum.profileurl = "";
                    //                            datum.FromUserID = notificationResponse.fromUserId;
                    //
                    //                            intent = new Intent(this, MessageActivity.class);
                    //                            intent.putExtra("data", new Gson().toJson(
                    //                                    datum, new TypeToken<MessageUser.Datum>() {
                    //                                    }.getType()));
                    //                        }
                    //                        if (notyType == 1 && notificationResponse.handleType == 2) {
                    //
                    //                            intent = new Intent(this, OfferDetailsActivity.class);
                    //                            intent.putExtra("offerid", notificationResponse.redirect_id);
                    //                            if (notificationResponse.actiontype.equalsIgnoreCase("0")) {
                    //                                intent.putExtra("offertype", 2);
                    //
                    //                            } else if (notificationResponse.actiontype.equalsIgnoreCase("1")) {
                    //
                    //                                intent.putExtra("offertype", 1);
                    //                            }
                    //                        }
                    //                        if (notyType == 1 && notificationResponse.handleType == 3) {
                    //
                    //                            intent = new Intent(this, SentInquiryActivity.class);
                    //                            if (notificationResponse.actiontype.equalsIgnoreCase("0")) {
                    //                                intent.putExtra("type", 2);
                    //
                    //                            } else if (notificationResponse.actiontype.equalsIgnoreCase("1")) {
                    //
                    //                                intent.putExtra("type", 1);
                    //                            }
                    //                        }
                    //                        if (notyType == 2 && notificationResponse.handleType == 5) {
                    //
                    //                            intent = new Intent(this, OfferDetailsActivity.class);
                    //                            intent.putExtra("offerid", notificationResponse.redirect_id);
                    //                            if (notificationResponse.actiontype.equalsIgnoreCase("0")) {
                    //                                intent.putExtra("offertype", 2);
                    //
                    //                            } else if (notificationResponse.actiontype.equalsIgnoreCase("1")) {
                    //
                    //                                intent.putExtra("offertype", 1);
                    //                            }
                    //                        }
                    //                        if (notyType == 2 && notificationResponse.handleType == 6) {
                    //
                    //                            intent = new Intent(this, SentInquiryActivity.class);
                    //                            if (notificationResponse.actiontype.equalsIgnoreCase("0")) {
                    //                                intent.putExtra("type", 2);
                    //
                    //                            } else if (notificationResponse.actiontype.equalsIgnoreCase("1")) {
                    //
                    //                                intent.putExtra("type", 1);
                    //                            }
                    //                        }
                    //                        if (notyType == 2 && notificationResponse.handleType == 7) {
                    //
                    //                            intent = new Intent(this, ReceivedHoldReqActivity.class);
                    //                        }
                    //                        if (notyType == 2 && notificationResponse.handleType == 9) {
                    //
                    //                            intent = new Intent(this, MyConnectionsActivity.class);
                    //                        }
                    //                        if (notyType == 2 && notificationResponse.handleType == 10) {
                    //
                    //                            intent = new Intent(this, SentHoldReqActivity.class);
                    //                        }
                    //                        if (notyType == 2 && notificationResponse.handleType == 11) {
                    //
                    //                            intent = new Intent(this, OfferDetailsActivity.class);
                    //                            intent.putExtra("offerid", notificationResponse.redirect_id);
                    //                            if (notificationResponse.actiontype.equalsIgnoreCase("0")) {
                    //                                intent.putExtra("offertype", 2);
                    //
                    //                            } else if (notificationResponse.actiontype.equalsIgnoreCase("1")) {
                    //
                    //                                intent.putExtra("offertype", 1);
                    //
                    //                            }
                    //                        }

                    //                        generateNotification(this, intent, notificationResponse.ntitle, notificationResponse.ndesc);
                    //                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

        // Release the wake lock provided by the WakefulBroadcastReceiver.
//        GCMBroadcastReceiver.completeWakefulIntent(i)
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private fun generateNotification(context: Context, notificationIntent: Intent, title: String,
                                     message: String) {

        val icon = R.mipmap.ic_launcher
        val notificationManager = context
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val id = Math.abs(Date().hashCode())

        notificationIntent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        val intent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context)
                .setContentTitle("" + title)
                .setContentText(message)
                .setSmallIcon(icon)
                .setTicker("" + title)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(intent)
                .setStyle(NotificationCompat.BigTextStyle().setBigContentTitle("" + title).bigText(message))
                .build()
        notificationManager.notify(id, notification)

    }
}