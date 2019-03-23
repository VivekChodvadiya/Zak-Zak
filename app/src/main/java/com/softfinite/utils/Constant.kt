package com.softfinite.utils

import android.os.Environment
import java.io.File

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 11-Jun-18.
 */

internal object Constant {
    val FINISH_ACTIVITY = "finish_activity"

    val FOLDER_NAME = ".kotdemo"
    val CACHE_DIR = ".kotdemo/Cache"

    val TMP_DIR = (Environment
            .getExternalStorageDirectory().absolutePath
            + File.separator
            + FOLDER_NAME + "/tmp")

    val PATH = Environment.getExternalStorageDirectory()
            .absolutePath + File.separator + "" + FOLDER_NAME

    val FOLDER_RIDEINN_PATH = (Environment
            .getExternalStorageDirectory().absolutePath
            + File.separator
            + "kotdemo")


    val API_KEY = "AIzaSyCsCzYT1hkwtjiJThhbi9m5Y3B179_-zgY"

    val USER_LATITUDE = "lat"
    val USER_LONGITUDE = "longi"
    val APP_JSON = "application/json"

    val LOGIN_INFO = "login_info"
    val PROFILE_INFO = "profile_info"
    val MASTER_DATA = "master_data"
    val COUNTRY = "country"

    val ROLE = "role"
    val ROLE_SELLER = 1
    val ROLE_BUYER = 2
    val ROLE_GENERAL = 3


    val PROFILE = "profile"

    val ANDROID_TYPE = 1

    val TIMEOUT = 5 * 60 * 1000

    //
    val LOCATION_UPDATED = "location_updated"

    val REQ_CODE_SETTING = 555

    val TAG_ADD = "add"
    val TAG_CANCEL = "cancel"
    val TAG_REMOVE = "remove"
    val TAG_REJECT = "reject"
    val TAG_BLOCK = "block"
    val TAG_UNBLOCK = "unblock"
    val TAG_ACCEPT = "accept"
    val ZIP_CODE = "zipcode"
    val CART_DATA = "cart_data"
    val LANGUAGE = "language"
    val REFERSH_HOMEPAGE = "refersh_homepage"
    val COUNTRY_CODE = "country_code"
    val IS_EMAIL_CONFIRM = "is_email_confirm"
    val IS_ZIP_AVAILABLE = "is_zip_available"
    val ADDRESS_PRESELECT = "address_preselect"

}