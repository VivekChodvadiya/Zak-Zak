package com.softfinite.utils

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.location.LocationManager
import android.media.MediaScannerConnection
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.preference.PreferenceManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Base64
import android.util.TypedValue
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.view.inputmethod.InputMethodManager
import android.webkit.MimeTypeMap
import com.afollestad.materialdialogs.MaterialDialog
import com.softfinite.R
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.utils.L
import com.nostra13.universalimageloader.utils.StorageUtils
import com.rey.material.app.Dialog
import com.rey.material.app.DialogFragment
import com.rey.material.app.SimpleDialog
import okhttp3.MediaType
import okhttp3.RequestBody
import org.apache.commons.lang3.StringUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.math.BigDecimal
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 5/8/2018.
 */

internal object Utils {
    fun setPref(c: Context, pref: String, `val`: String) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.putString(pref, `val`)
        e.apply()
    }

    fun getPref(c: Context, pref: String, `val`: String): String? {
        return PreferenceManager.getDefaultSharedPreferences(c).getString(pref,
                `val`)
    }

    fun setPref(c: Context, pref: String, `val`: Boolean) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.putBoolean(pref, `val`)
        e.apply()
    }

    fun getPref(c: Context, pref: String, `val`: Boolean): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(c).getBoolean(
                pref, `val`)
    }

    fun delPref(c: Context, pref: String) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.remove(pref)
        e.apply()
    }

    fun setPref(c: Context, pref: String, `val`: Int) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.putInt(pref, `val`)
        e.apply()

    }

    fun getPref(c: Context, pref: String, `val`: Int): Int {
        return PreferenceManager.getDefaultSharedPreferences(c).getInt(pref,
                `val`)
    }

    fun setPref(c: Context, pref: String, `val`: Long) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.putLong(pref, `val`)
        e.apply()
    }

    fun getPref(c: Context, pref: String, `val`: Long): Long {
        return PreferenceManager.getDefaultSharedPreferences(c).getLong(pref,
                `val`)
    }

    fun setPref(c: Context, file: String, pref: String, `val`: String) {
        val settings = c.getSharedPreferences(file,
                Context.MODE_PRIVATE)
        val e = settings.edit()
        e.putString(pref, `val`)
        e.apply()

    }

    fun getPref(c: Context, file: String, pref: String, `val`: String): String? {
        return c.getSharedPreferences(file, Context.MODE_PRIVATE).getString(
                pref, `val`)
    }

    fun validateEmail(target: CharSequence): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                    .matches()
        }
    }

    fun validate(target: String, pattern: String): Boolean {
        if (TextUtils.isEmpty(target)) {
            return false
        } else {
            val r = Pattern.compile(pattern)
            return r.matcher(target).matches()
        }
    }

    fun isAlphaNumeric(target: String): Boolean {
        if (TextUtils.isEmpty(target)) {
            return false
        } else {
            val r = Pattern.compile("^[a-zA-Z0-9]*$")
            return r.matcher(target)
                    .matches()
        }
    }

    fun isNumeric(target: String): Boolean {
        if (TextUtils.isEmpty(target)) {
            return false
        } else {
            val r = Pattern.compile("^[0-9]*$")
            return r.matcher(target)
                    .matches()
        }
    }

    fun getDeviceWidth(context: Context): Int {
        try {
            val metrics = context.resources.displayMetrics
            return metrics.widthPixels
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return 480
    }

    fun getDeviceHeight(context: Context): Int {
        try {
            val metrics = context.resources.displayMetrics
            return metrics.heightPixels
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return 800
    }

    fun isInternetConnected(mContext: Context?): Boolean {
        var outcome = false

        try {
            if (mContext != null) {
                val cm = mContext
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val networkInfos = cm.allNetworkInfo

                for (tempNetworkInfo in networkInfos) {
                    if (tempNetworkInfo.isConnected) {
                        outcome = true
                        break
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return outcome
    }

    fun getDeviceId(c: Context): String {
        var aid: String?
        try {
            aid = ""
            aid = Settings.Secure.getString(c.contentResolver,
                    "android_id")
            if (aid == null) {
                aid = "No DeviceId"
            } else if (aid.length <= 0) {
                aid = "No DeviceId"
            }
        } catch (e: Exception) {
            sendExceptionReport(e)
            aid = "No DeviceId"
        }
        return aid!!
    }

    fun random(min: Float, max: Float): Float {
        return (min + Math.random() * (max - min + 1)).toFloat()
    }

    fun random(min: Int, max: Int): Int {
        return Math.round((min + Math.random() * (max - min + 1)).toFloat())
    }

    fun hasFlashFeature(context: Context): Boolean {
        return context.packageManager.hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FLASH)
    }

    fun hasCameraFeature(context: Context): Boolean {
        return context.packageManager.hasSystemFeature(
                PackageManager.FEATURE_CAMERA)
    }

    fun hideKeyBoard(c: Context, v: View) {
        val imm = c
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }


    fun getBold(c: Context): Typeface? {
        try {
            return Typeface.createFromAsset(c.assets,
                    "pt_sans_bold.ttf")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun getNormal(c: Context): Typeface? {
        try {
            return Typeface.createFromAsset(c.assets,
                    "Gabriela-Regular.ttf")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun passwordValidator(password: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        //        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        val PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{6,15}$"


        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun formatNo(str: String): String? {
        val number = removeComma(nullSafe(str))
        return if (!StringUtils.isEmpty(number)) {

            //            if (!finalStr.startsWith("$"))
            //                finalStr = "$" + finalStr;
            formatToComma(number)
        } else number

    }

    fun `formatNo$`(str: String): String {
        val number = removeComma(nullSafe(str))
        if (!StringUtils.isEmpty(number)) {

            var finalStr = formatToComma(number)

            if (!finalStr!!.startsWith("$"))
                finalStr = "$$finalStr"
            return finalStr
        }

        return number

    }

    fun formatToComma(str: String): String? {
        var number: String? = removeComma(nullSafe(str))
        if (!StringUtils.isEmpty(number)) {

            var finalStr: String
            if (number!!.contains(".")) {
                number = truncateUptoTwoDecimal(number)
                val decimalFormat = DecimalFormat("#.##")
                finalStr = decimalFormat.format(BigDecimal(number!!))
            } else {
                finalStr = number
            }

            finalStr = NumberFormat.getNumberInstance(Locale.US).format(java.lang.Double.valueOf(finalStr))
            return finalStr
        }

        return number
    }

    fun truncateUptoTwoDecimal(doubleValue: String): String? {
        if (doubleValue != null) {
            var result: String = doubleValue
            val decimalIndex = result.indexOf(".")
            if (decimalIndex != -1) {
                val decimalString = result.substring(decimalIndex + 1)
                if (decimalString.length > 2) {
                    result = doubleValue.substring(0, decimalIndex + 3)
                } else if (decimalString.length == 1) {
                    //                    result = String.format(Locale.ENGLISH, "%.2f",
                    //                            Double.parseDouble(value));
                }
            }
            return result
        }

        return doubleValue
    }

    fun removeComma(str: String): String {
        var str = str
        try {
            if (!StringUtils.isEmpty(str)) {
                str = str.replace(",".toRegex(), "")
                try {
                    val format = NumberFormat.getCurrencyInstance()
                    val number = format.parse(str)
                    return number.toString()
                } catch (e: ParseException) {
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Debug.e("removeComma", "" + str)
        return str
    }

    fun getRowFadeSpeedAnimation(c: Context): LayoutAnimationController {
        val anim = AnimationUtils.loadAnimation(c,
                R.anim.raw_fade) as AlphaAnimation
        return LayoutAnimationController(
                anim, 0.3f)
    }

    fun sendExceptionReport(e: Exception) {
        e.printStackTrace()

        try {
            // Writer result = new StringWriter();
            // PrintWriter printWriter = new PrintWriter(result);
            // e.printStackTrace(printWriter);
            // String stacktrace = result.toString();
            // new CustomExceptionHandler(c, URLs.URL_STACKTRACE)
            // .sendToServer(stacktrace);
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

    }


    fun getAndroidId(c: Context): String {
        var aid: String?
        try {
            aid = ""
            aid = Settings.Secure.getString(c.contentResolver,
                    "android_id")

            if (aid == null) {
                aid = "No DeviceId"
            } else if (aid.length <= 0) {
                aid = "No DeviceId"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            aid = "No DeviceId"
        }

        Debug.e("", "aid : " + aid!!)

        return aid

    }

    fun getAppVersionCode(c: Context): Int {
        try {
            return c.packageManager.getPackageInfo(c.packageName, 0).versionCode
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return 0

    }

    fun getPhoneModel(c: Context): String {

        try {
            return Build.MODEL
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return ""
    }

    fun getPhoneBrand(c: Context): String {

        try {
            return Build.BRAND
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return ""
    }

    fun getOsVersion(c: Context): String {

        try {
            return Build.VERSION.RELEASE
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return ""
    }

    fun getOsAPILevel(c: Context): Int {

        try {
            return Build.VERSION.SDK_INT
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return 0
    }

    fun parseCalendarFormat(c: Calendar, pattern: String): String {
        val sdf = SimpleDateFormat(pattern,
                Locale.getDefault())
        return sdf.format(c.time)
    }

    fun parseTime(time: Long, pattern: String): String {
        val sdf = SimpleDateFormat(pattern,
                Locale.getDefault())
        return sdf.format(Date(time))
    }

    fun parseTime(time: String, pattern: String): Date {
        val sdf = SimpleDateFormat(pattern,
                Locale.getDefault())
        try {
            return sdf.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return Date()
    }

    fun parseTimeForDate(time: Long, pattern: String): Date {
        Debug.e("timezone", TimeZone.getDefault().id)
        val sdf = SimpleDateFormat(pattern,
                Locale.getDefault())

        try {
            return sdf.parse(sdf.format(Date(time)))

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return Date()
    }


    fun parseTime(time: String, fromPattern: String,
                  toPattern: String): String {
        var sdf = SimpleDateFormat(fromPattern,
                Locale.getDefault())
        try {
            val d = sdf.parse(time)
            sdf = SimpleDateFormat(toPattern, Locale.getDefault())
            return sdf.format(d)
        } catch (e: Exception) {
            Debug.i("parseTime", "" + e.message)
        }

        return ""
    }

    fun parseTimeUTCtoDefault(time: String, pattern: String): Date {

        var sdf = SimpleDateFormat(pattern,
                Locale.getDefault())
        try {
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val d = sdf.parse(time)
            sdf = SimpleDateFormat(pattern, Locale.getDefault())
            sdf.timeZone = Calendar.getInstance().timeZone
            return sdf.parse(sdf.format(d))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Date()
    }

    fun parseTimeUTCtoDefault(time: Long): Date {

        try {
            val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            cal.timeInMillis = time
            val d = cal.time
            val sdf = SimpleDateFormat()
            sdf.timeZone = Calendar.getInstance().timeZone
            return sdf.parse(sdf.format(d))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Date()
    }

    fun parseTimeUTCtoDefault(time: String, fromPattern: String, toPattern: String): String {
        var sdf = SimpleDateFormat(fromPattern,
                Locale.getDefault())
        try {
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val d = sdf.parse(time)
            sdf = SimpleDateFormat(toPattern, Locale.getDefault())
            sdf.timeZone = Calendar.getInstance().timeZone
            return sdf.format(d)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    fun parseTimeUTCtoDefaultGerman(time: String, fromPattern: String, toPattern: String): String {
        var sdf = SimpleDateFormat(fromPattern,
                Locale.getDefault())
        try {
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val d = sdf.parse(time)
            sdf = SimpleDateFormat(toPattern, Locale.GERMAN)
            sdf.timeZone = Calendar.getInstance().timeZone
            return sdf.format(d)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    fun parseTimeUTCtoDefaultEnglish(time: String, fromPattern: String, toPattern: String): String {

        var sdf = SimpleDateFormat(fromPattern,
                Locale.getDefault())
        try {
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val d = sdf.parse(time)
            sdf = SimpleDateFormat(toPattern, Locale.ENGLISH)
            sdf.timeZone = Calendar.getInstance().timeZone
            return sdf.format(d)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    fun parseTimeUTCtoDefaultTurkey(time: String, fromPattern: String, toPattern: String): String {
        var sdf = SimpleDateFormat(fromPattern,
                Locale.getDefault())
        try {
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val d = sdf.parse(time)
            sdf = SimpleDateFormat(toPattern, Locale("tr", "TR"))
            sdf.timeZone = Calendar.getInstance().timeZone
            return sdf.format(d)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun convertToTimeZone(date: String, frompattern: String, topattern: String, defaultzoneid: String, convertzoneid: String): String {
        //Debug.e("TAG", "convertToTimeZone() called with: date = [" + date + "], frompattern = [" + frompattern + "], topattern = [" + topattern + "], defaultzoneid = [" + defaultzoneid + "], convertzoneid = [" + convertzoneid + "]");
        var returnDate = date
        try {
            TimeZone.setDefault(TimeZone.getTimeZone(convertzoneid))
            //Debug.e("Convert time zone", TimeZone.getDefault().getID());
            val inputDate = SimpleDateFormat(frompattern).parse(date)
            //Debug.e("Input Time", inputDate.toString());
            TimeZone.setDefault(TimeZone.getTimeZone(defaultzoneid))
            //Debug.e("Output time zone", TimeZone.getDefault().getID());
            val dateFormatGmt = SimpleDateFormat(topattern)
            dateFormatGmt.timeZone = TimeZone.getTimeZone(defaultzoneid)
            val dateFormatDefautl = SimpleDateFormat(topattern)
            val date1 = dateFormatDefautl.parse(dateFormatGmt.format(inputDate))
            val dateFormat = SimpleDateFormat(topattern)
            returnDate = dateFormat.format(date1)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return returnDate
        }
    }

    fun daysBetween(startDate: Date, endDate: Date): Long {
        val sDate = getDatePart(startDate)
        val eDate = getDatePart(endDate)
        var daysBetween: Long = 0
        while (sDate.before(eDate)) {
            sDate.add(Calendar.DAY_OF_MONTH, 1)
            daysBetween++
        }
        return daysBetween
    }

    fun getFullDayName(day: Int): String {
        val c = Calendar.getInstance()
        // date doesn't matter - it has to be a Monday
        // I new that first August 2011 is one ;-)
        c.set(2011, 7, 1, 0, 0, 0)
        c.add(Calendar.DAY_OF_MONTH, day)
        return String.format("%tA", c)
    }

    fun getDatePart(date: Date): Calendar {
        val cal = Calendar.getInstance()       // get calendar instance
        cal.time = date
        cal.set(Calendar.HOUR_OF_DAY, 0)            // set hour to midnight
        cal.set(Calendar.MINUTE, 0)                 // set minute in hour
        cal.set(Calendar.SECOND, 0)                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0)            // set millisecond in second
        return cal                                  // return the date part
    }

    fun nullSafe(content: String?): String {
        if (content == null) {
            return ""
        }
        return if (content.equals("null", ignoreCase = true)) {
            ""
        } else content
    }

    fun nullSafe(content: String, defaultStr: String): String {
        if (StringUtils.isEmpty(content)) {
            return defaultStr
        }
        return if (content.equals("null", ignoreCase = true)) {
            defaultStr
        } else content

    }

    fun nullSafeDash(content: String): String {
        if (StringUtils.isEmpty(content)) {
            return "-"
        }
        return if (content.equals("null", ignoreCase = true)) {
            "-"
        } else content
    }

    fun nullSafe(content: Int, defaultStr: String): String {
        return if (content == 0) {
            defaultStr
        } else "" + content
    }

    fun initImageLoader(mContext: Context): ImageLoader? {
        var imageLoader: ImageLoader? = null
        try {
            val cacheDir = StorageUtils.getOwnCacheDirectory(mContext,
                    Constant.CACHE_DIR)

            val defaultOptions = DisplayImageOptions.Builder()
                    .cacheOnDisk(true).cacheInMemory(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565).build()
            val builder = ImageLoaderConfiguration.Builder(
                    mContext).defaultDisplayImageOptions(defaultOptions)
                    .diskCache(UnlimitedDiskCache(cacheDir))
                    .memoryCache(WeakMemoryCache())

            val config = builder.build()
            imageLoader = ImageLoader.getInstance()
            imageLoader!!.init(config)
            L.writeLogs(false)

            return imageLoader
        } catch (e: Exception) {
            sendExceptionReport(e)
        }
        try {
            val defaultOptions = DisplayImageOptions.Builder()
                    .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565).build()
            val builder = ImageLoaderConfiguration.Builder(
                    mContext).defaultDisplayImageOptions(defaultOptions)
                    .memoryCache(WeakMemoryCache())

            val config = builder.build()
            imageLoader = ImageLoader.getInstance()
            imageLoader!!.init(config)
            L.writeLogs(false)
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return imageLoader
    }

    fun isSDcardMounted(): Boolean {
        val state = Environment.getExternalStorageState()
        return if (state == Environment.MEDIA_MOUNTED && state != Environment.MEDIA_MOUNTED_READ_ONLY) {
            true
        } else false
    }

    fun isGPSProviderEnabled(context: Context): Boolean {
        val locationManager = context
                .getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun isNetworkProviderEnabled(context: Context): Boolean {
        val locationManager = context
                .getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    fun isLocationProviderEnabled(context: Context): Boolean {
        return isGPSProviderEnabled(context) || isNetworkProviderEnabled(context)
    }

    fun isLocationProviderRequired(context: Context): Boolean {
        val lang = getPref(context, Constant.USER_LONGITUDE, "")
        val lat = getPref(context, Constant.USER_LATITUDE, "")
        if (lat != null) {
            if (lang != null) return !(lat.length > 0 && lang.length > 0)
        }
        return false
    }

    fun isUserLoggedIn(c: Context): Boolean {
        return if (getUserToken(c)!!.length > 0) true else false
    }

    fun getUid(c: Context): String? {
        return getPref(c, RequestParamsUtils.USER_ID, "")!!
    }

//    fun getLoginDetails(c: Context): LogIn? {
//        val response = Utils.getPref(c, Constant.LOGIN_INFO, "")
//
//        if (response != null && response.length > 0) {
//
//            //            LogIn login = new Gson().fromJson(
//            //                    response, new TypeToken<LogIn>() {
//            //                    }.getType());
//            var login: LogIn? = null
//            try {
//                login = Gson().fromJson<LogIn>(JSONObject(response).toString(), object : TypeToken<LogIn>() {
//
//                }.type)
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//
//            if (login!!.responseCode === 200) {
//                return login
//            }
//
//        }
//        return null
//    }

//    fun getCartData(c: Context): CartData? {
//        val response = Utils.getPref(c, Constant.CART_DATA, "")
//
//        if (response != null && response.length > 0) {
//
//            //            LogIn login = new Gson().fromJson(
//            //                    response, new TypeToken<LogIn>() {
//            //                    }.getType());
//            var cartData: CartData? = null
//            try {
//                cartData = Gson().fromJson<CartData>(JSONObject(response).toString(), object : TypeToken<CartData>() {
//
//                }.type)
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//
//            if (cartData!!.responseCode === 200) {
//                return cartData
//            }
//
//        }
//        return null
//    }

//    fun getProfileDetails(c: Context): MyProfile? {
//        val response = Utils.getPref(c, Constant.PROFILE_INFO, "")
//
//        if (response != null && response.length > 0) {
//
//            //            LogIn login = new Gson().fromJson(
//            //                    response, new TypeToken<LogIn>() {
//            //                    }.getType());
//            var login: MyProfile? = null
//            try {
//                login = Gson().fromJson<MyProfile>(JSONObject(response).toString(), object : TypeToken<MyProfile>() {
//
//                }.type)
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//
//            if (login!!.responseCode === 200) {
//                return login
//            }
//
//        }
//        return null
//    }


    fun clearLoginCredetials(c: Activity) {
//        Utils.delPref(c, RequestParamsUtils.USER_ID)
//        Utils.delPref(c, RequestParamsUtils.SESSION_ID)
        delPref(c, Constant.LOGIN_INFO)
//        Utils.delPref(c, Constant.USER_LATITUDE)
//        Utils.delPref(c, Constant.USER_LONGITUDE)
//        Utils.delPref(c, RequestParamsUtils.TOKEN)
//        Utils.delPref(c, Constant.PROFILE_INFO)
//        Utils.delPref(c, Constant.COUNTRY_CODE)
//        Utils.delPref(c, Constant.CART_DATA)
//        Utils.delPref(c, Constant.ZIP_CODE)
//        Utils.delPref(c, Constant.IS_EMAIL_CONFIRM)
//        Utils.delPref(c, Constant.IS_ZIP_AVAILABLE)
//        Utils.delPref(c, Constant.ADDRESS_PRESELECT)
        val nMgr = c.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nMgr.cancelAll()
    }

    fun showDialog(c: Activity, title: String, message: String) {

        val builder = MaterialDialog.Builder(c)
                .title(title)
                .content(message)
                .positiveText(R.string.btn_ok)
                .onPositive(MaterialDialog.SingleButtonCallback { materialDialog, dialogAction -> }).onNegative(MaterialDialog.SingleButtonCallback { materialDialog, dialogAction -> })

        val dialog = builder.build()
        dialog.show()
    }

    fun showDialog(c: Context, title: String, message: String,
                   onClickListener: View.OnClickListener) {
        var builder: Dialog.Builder? = null
        builder = object : SimpleDialog.Builder(R.style.SimpleDialogLight) {
            override fun onPositiveActionClicked(fragment: DialogFragment) {
                super.onPositiveActionClicked(fragment)
                onClickListener.onClick(null)
            }
            override fun onNegativeActionClicked(fragment: DialogFragment) {
                super.onNegativeActionClicked(fragment)
            }
        }
        (builder as SimpleDialog.Builder).message("" + message)
                .title("" + title)
                .positiveAction("" + c.getString(R.string.btn_ok))
        builder.build(c).show()
    }

//    fun showDialog(c: Context, title: String, message: String,
//                   btnPos: String, btnNeg: String,
//                   onPosClickListener: View.OnClickListener,
//                   onNegClickListener: View.OnClickListener) {
//
//        var builder: Dialog.Builder? = null
//
//        builder = object : SimpleDialog.Builder(R.style.SimpleDialogLight) {
//
//            fun onPositiveActionClicked(fragment: DialogFragment) {
//                super.onPositiveActionClicked(fragment)
//                onPosClickListener.onClick(null)
//            }
//
//            fun onNegativeActionClicked(fragment: DialogFragment) {
//                super.onNegativeActionClicked(fragment)
//                onNegClickListener.onClick(null)
//            }
//
//        }
//
//        (builder as SimpleDialog.Builder).message("" + message)
//                .title("" + title)
//                .positiveAction("" + btnPos)
//                .negativeAction("" + btnNeg)
//
//        builder!!.build(c).show()
//    }

    fun asList(str: String): ArrayList<String?> {
        return ArrayList<String?>(Arrays.asList(*str
                .split("\\s*,\\s*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
    }

    fun implode(data: ArrayList<String>): String {
        try {
            var devices = ""
            for (iterable_element in data) {
                devices = "$devices,$iterable_element"
            }
            if (devices.length > 0 && devices.startsWith(",")) {
                devices = devices.substring(1)
            }
            return devices
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * Create a File for saving an image or video
     *
     * @return Returns file reference
     */
    fun getOutputMediaFile(): File? {
        try {
            // To be safe, you should check that the SDCard is mounted
            // using Environment.getExternalStorageState() before doing this.
            val mediaStorageDir: File
            if (isSDcardMounted()) {
                mediaStorageDir = File(Constant.FOLDER_RIDEINN_PATH)
            } else {
                mediaStorageDir = File(Environment.getRootDirectory(),
                        Constant.FOLDER_NAME)
            }

            // This location works best if you want the created images to be
            // shared
            // between applications and persist after your app has been
            // uninstalled.
            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    return null
                }
            }
            // Create a media file name
            val mediaFile = File(mediaStorageDir.path
                    + File.separator + Date().time + ".jpg")
            mediaFile.createNewFile()
            return mediaFile
        } catch (e: Exception) {
            return null
        }
    }

    fun scanMediaForFile(context: Context, filePath: String) {
        resetExternalStorageMedia(context, filePath)
        notifyMediaScannerService(context, filePath)
    }

    fun resetExternalStorageMedia(context: Context, filePath: String): Boolean {
        try {
            if (Environment.isExternalStorageEmulated())
                return false
            val uri = Uri.parse("file://" + File(filePath))
            val intent = Intent(Intent.ACTION_MEDIA_MOUNTED, uri)
            context.sendBroadcast(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    fun notifyMediaScannerService(context: Context, filePath: String) {
        MediaScannerConnection.scanFile(context, arrayOf(filePath),
                null) { path, uri ->
            Debug.i("ExternalStorage", "Scanned $path:")
            Debug.i("ExternalStorage", "-> uri=$uri")
        }
    }

    fun cancellAllNotication(context: Context) {
        val notificationManager = context
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }

    fun toInitCap(param: String?): String? {
        try {
            if (param != null && param.length > 0) {
                val charArray = param.toCharArray() // convert into char
                // array
                charArray[0] = Character.toUpperCase(charArray[0]) // set
                // capital
                // letter to
                // first
                // position
                return String(charArray) // return desired output
            } else {
                return ""
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return param
    }

    fun encodeTobase64(image: Bitmap): String {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        val imageEncoded = Base64.encodeToString(b, Base64.DEFAULT)

        Debug.e("LOOK", imageEncoded)
        return imageEncoded
    }

    fun decodeBase64(input: String): Bitmap {
        val decodedByte = Base64.decode(input, 0)
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.size)
    }

    fun getExtenstion(urlPath: String): String {
        if (urlPath.contains(".")) {
            val extension = urlPath.substring(urlPath.lastIndexOf(".") + 1)
            return urlPath.substring(urlPath.lastIndexOf(".") + 1)
        }
        return ""
    }

    fun getFileName(urlPath: String): String {
        return if (urlPath.contains(".")) {
            urlPath.substring(urlPath.lastIndexOf("/") + 1)
        } else ""
    }

    fun dpToPx(context: Context, `val`: Int): Float {
        val r = context.resources
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, `val`.toFloat(), r.displayMetrics)
    }

    fun spToPx(context: Context, `val`: Int): Float {
        val r = context.resources
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, `val`.toFloat(), r.displayMetrics)
    }

    fun noInternet(a: Activity) {
        showDialog(a, a.getString(R.string.connection_title), a.getString(R.string.connection_not_available))
    }

    fun getMimeType(url: String): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        Debug.e("type", "" + type!!)
        return type
    }

    fun isJPEGorPNG(url: String): Boolean {
        try {
            val type = getMimeType(url)
            val ext = type!!.substring(type.lastIndexOf("/") + 1)
            if (ext.equals("jpeg", ignoreCase = true) || ext.equals("jpg", ignoreCase = true) || ext.equals("png", ignoreCase = true)) {
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return true
        }
        return false
    }

    fun getFileSize(url: String): Double {
        val file = File(url)
        // Get length of file in bytes
        val fileSizeInBytes = file.length().toDouble()
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        val fileSizeInKB = fileSizeInBytes / 1024
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        val fileSizeInMB = fileSizeInKB / 1024

        Debug.e("fileSizeInMB", "" + fileSizeInMB)
        return fileSizeInMB
    }

    fun getAsteriskName(str: String): String? {
        var str = str
        val n = 4
        str = nullSafe(str)
        val fStr = StringBuilder()
        if (!TextUtils.isEmpty(str)) {
            if (str.length > n) {
                fStr.append(str.substring(0, n))
                for (i in 0 until str.length - n) {
                    fStr.append("*")
                }

                return fStr.toString()
            } else {
                fStr.append(str.substring(0, str.length - 1))
            }
        }
        return str
    }

    fun getUserToken(c: Context): String? {
        return getPref(c, RequestParamsUtils.TOKEN, "")
    }

    internal lateinit var dialog: android.app.Dialog

    fun twoDecimal(rate: String): String {
        if (!StringUtils.isEmpty(rate)) {
            val df = DecimalFormat("0.00", DecimalFormatSymbols(Locale.ENGLISH))
            return "CHF " + df.format(java.lang.Float.parseFloat(rate).toDouble())
        }
        return ""
    }

    fun twoDecimalWithoutChf(rate: String): String {
        if (!StringUtils.isEmpty(rate)) {
            val df = DecimalFormat("0.00", DecimalFormatSymbols(Locale.ENGLISH))
            return df.format(java.lang.Float.parseFloat(rate).toDouble())
        }
        return ""
    }

    fun toRequestBody(value: String): RequestBody {
        val body = RequestBody.create(MediaType.parse("multipart/form-data"), value)
        return RequestBody.create(MediaType.parse("multipart/form-data"), value)
    }


    fun getHashKey(c: Context) {
        // Add code to print out the key hash
        try {
            val info = c.packageManager.getPackageInfo(
                    c.packageName,
                    PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Debug.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }
    }

    fun getUserCountryCode(context: Context): String? {
        try {
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (tm != null) {
                val simCountry = tm.simCountryIso
                // String networkOperator = tm.getNetworkOperator();

                if (simCountry != null && simCountry.length == 2) { // SIM country code is available
                    /* if (isEuropeCountry(simCountry.toLowerCase(Locale.getDefault()), context)) {
                        Log.e("trueeeeee ", "trueeeee     &#8364");
                        return "&#8364";
                    } else {*/
                    return simCountry.toLowerCase(Locale.getDefault())
                    //   }
                } else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                    val networkCountry = tm.networkCountryIso
                    if (networkCountry != null && networkCountry.length == 2) { // network country code is available

                        /*if (isEuropeCountry(networkCountry.toLowerCase(Locale.getDefault()), context)) {
                            Log.e("trueeeeee ", "trueeeee     &#8364");
                            return "&#8364";
                        } else {*/
                        return networkCountry.toLowerCase(Locale.getDefault())
                        //  }
                        //  return networkCountry.toLowerCase(Locale.getDefault());
                    }
                }
            } else {
                return Locale.getDefault().country
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    object UtilCurrency {
        var currencyLocaleMap: SortedMap<Currency, Locale>

        init {
            currencyLocaleMap = TreeMap(Comparator { c1, c2 -> c1.currencyCode.compareTo(c2.currencyCode) })
            for (locale in Locale.getAvailableLocales()) {
                try {
                    val currency = Currency.getInstance(locale)
                    currencyLocaleMap[currency] = locale
                } catch (e: Exception) {
                }

            }
        }

        fun getCurrencySymbol(currencyCode: String): String {
            val currency = Currency.getInstance(currencyCode)
            var symbol = ""
            symbol = currency.getSymbol(currencyLocaleMap[currency])
            if (symbol.contains("$")) {
                symbol = "$"
            }
            //return currency.getSymbol(currencyLocaleMap.get(currency));
            return symbol
        }
    }

    fun getCurrencyCode(countryCode: String): String {
        return Currency.getInstance(Locale("", countryCode)).currencyCode
    }

    fun isToday(timeInMillis: Long): Boolean {
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val date = dateFormat.format(timeInMillis)
        return date == dateFormat.format(System.currentTimeMillis())
    }

    fun formatDate(timeInMillis: Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return dateFormat.format(timeInMillis)
    }

    /* public static boolean compareDate(Date date1, Date date2) {

//        if (date1.compareTo(date2) > 0) {
//            System.out.println("Date1 is after Date2");
//        } else if (date1.compareTo(date2) < 0) {
//            System.out.println("Date1 is before Date2");
//        } else if (date1.compareTo(date2) == 0) {
//            System.out.println("Date1 is equal to Date2");
//        } else {
//            System.out.println("How to get here?");
//        }
        return date1.compareTo(date2) == 0;
    }*/


    fun compareDate(date1: Date, date2: Date): Boolean {
        var date1 = date1
        var date2 = date2

        //        if (date1.compareTo(date2) > 0) {
        //            System.out.println("Date1 is after Date2");
        //        } else if (date1.compareTo(date2) < 0) {
        //            System.out.println("Date1 is before Date2");
        //        } else if (date1.compareTo(date2) == 0) {
        //            System.out.println("Date1 is equal to Date2");
        //        } else {
        //            System.out.println("How to get here?");

        //        }

        date1 = parseTime(date1, "yyyy-MM-dd")
        Debug.e("date 1", "" + date1.time)
        date2 = parseTime(date2, "yyyy-MM-dd")
        Debug.e("date 2", "" + date2.time)

        return date1.compareTo(date2) == 0
    }

    fun compareDateTommorrow(date1: Date): Boolean {
        //        if (date1.compareTo(date2) > 0) {
        //            System.out.println("Date1 is after Date2");
        //        } else if (date1.compareTo(date2) < 0) {
        //            System.out.println("Date1 is before Date2");
        //        } else if (date1.compareTo(date2) == 0) {
        //            System.out.println("Date1 is equal to Date2");
        //        } else {
        //            System.out.println("How to get here?");

        //        }
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time
        Debug.e("tomorrow", tomorrow.toString())
        return compareDate(date1, tomorrow)
    }

    fun parseTime(time: Date, pattern: String): Date {
        val sdf = SimpleDateFormat(pattern,
                Locale.getDefault())
        try {
            return sdf.parse(sdf.format(time))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return time
    }
}
