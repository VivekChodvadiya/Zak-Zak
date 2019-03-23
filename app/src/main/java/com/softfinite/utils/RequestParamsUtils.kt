package com.softfinite.utils

import android.content.Context
import okhttp3.FormBody

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

internal object RequestParamsUtils {

    val USER_ID = "user_id"
    val SESSION_ID = "session_id"
    val APIKEY = "APIKey"

    val DEVICE_TYPE = "device_type"
    val DEVICE_ID = "device_id"
    val GCMKEY = "gcmkey"
    val DEVICE_TOKEN = "device_token"
    val TOKEN = "token"

    val USERNAME = "username"
    val NAME = "name"
    val MOBILE = "mobile"
    val PASSWORD = "password"
    val EMAIL = "email"
    val REFCODE = "refcode"
    val PAGE = "page"

    val CLIENTSECRET = "clientSecret"
    val CLIENTID = "clientId"
    val ZIPCODE = "zipCode"
    val TIMEZONE = "timeZone"


    val CUSTOMER = "customer"
    val REFERENCE_AGENT = "reference_agent"
    val TITLE_OPERATOR = "title_operator"
    val TITLE_PAPER = "title_paper"
    val TITLE_STATUS = "title_status"
    val FRONT_PAGE_COLOR = "front_page_color"
    val BACK_PAGE_COLOR = "back_page_color"
    val INNER_OPERATOR = "inner_operator"
    val INNER_PAPER = "inner_paper"
    val INNER_STATUS = "inner_status"
    val INNER_PAGE_COLOR = "inner_page_color"
    val INNER_PAGE_4_COLOR_TYPE = "inner_page_4_color_type"
    val LAMINATION_TYPE = "lamination_type"
    val BINDING = "binding"
    val QUANTITY = "quantity"
    val MEDIUM = "medium"
    val ACCESS_TOKEN = "access-token"
    val CONTENT_TYPE = "Content-Type"
    val NAME_ON_CARD = "name_on_card"
    val CARD_NUMBER = "card_number"
    val MONTH = "month"
    val YEAR = "year"

    val USERINFO = "userinfo"
    val USERINFO_IMAGE = "userinfo_image"
    val STUDENT_TBL = "student_tbl"
    val STUDENT_TBL_IMAGE = "student_tbl_image/"
    val STUDENT_TBL_DETAILS = "student_tbl_details"


    //    public static RequestParams newRequestParams(Context c) {
    //        RequestParams params = new RequestParams();
    //        params.put(USER_ID, "" + Utils.getPref(c, RequestParamsUtils.USER_ID, ""));
    //        params.put(SESSION_ID, "" + Utils.getPref(c, RequestParamsUtils.SESSION_ID, ""));
    ////        params.put(APIKEY, Constant.API_KEY);
    //
    //        return params;
    //    }
    fun newRequestFormBody(c: Context): FormBody.Builder {

        return FormBody.Builder()
    }


}