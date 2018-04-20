package com.ademir.moview.commons

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.SharedPreferences
import android.os.Parcel
import android.os.Parcelable
import android.preference.PreferenceManager
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ademir.moview.BuildConfig
import com.ademir.moview.R
import com.ademir.moview.SessionController
import com.ademir.moview.model.User
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.load(path: String,
                   placeholder: Int = R.drawable.ic_image_gray_24dp,
                   transformation: Transformation? = null) {
    if (path.isNotBlank()) {
        Picasso.get().apply { isLoggingEnabled = BuildConfig.DEBUG }
                .load(path)
                .placeholder(placeholder)
                .apply { transformation?.let { transform(it) } }
                .into(this)

    } else {
        setImageResource(placeholder)
    }
}

inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.func()
    editor.apply()
}

fun SharedPreferences.Editor.set(pair: Pair<String, String?>) =
        putString(pair.first, pair.second)!!

fun SharedPreferences.putUser(user: User) {
    edit {
        set(Constants.PREF_SESSION_TOKEN to user.token)
        set(Constants.PREF_SESSION_USERNAME to user.username)
        set(Constants.PREF_SESSION_NAME to user.name)
        set(Constants.PREF_SESSION_EMAIL to user.email)
        putInt(Constants.PREF_SESSION_FOLLOWING_COUNT, user.followingCount)
        putInt(Constants.PREF_SESSION_FOLLOWERS_COUNT, user.followingCount)
    }
}

fun SharedPreferences.loadUser(): Boolean {
    if (contains(Constants.PREF_SESSION_TOKEN)) {
        SessionController.user = User(this)
        return true
    } else {
        return false
    }
}

fun Context.getPreferences() = PreferenceManager.getDefaultSharedPreferences(this)!!

fun releaseDateFormatted(date: String?): String {
    date?.let {
        val split = date.split("-")
        val text = split[2] + '/' + split[1] + '/' + split[0]
        return text
    }
    return ""
}

fun <T : RecyclerView.ViewHolder> RecyclerView.prepare(adapter: RecyclerView.Adapter<T>,
                                                       layoutManager: RecyclerView.LayoutManager,
                                                       hasFixedSize: Boolean = true) {
    setAdapter(adapter)
    setLayoutManager(layoutManager)
    setHasFixedSize(hasFixedSize)
}

fun isToday(timeInMillis: Long): Boolean {
    val sCalendar = Calendar.getInstance()
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeInMillis
    return calendar.get(Calendar.YEAR) == sCalendar.get(Calendar.YEAR)
            && calendar.get(Calendar.MONTH) == sCalendar.get(Calendar.MONTH)
            && calendar.get(Calendar.DAY_OF_MONTH) == sCalendar.get(Calendar.DAY_OF_MONTH)
}

inline fun <T> Call<T>.enqueue(crossinline onResponse: (T?) -> Unit,
                               crossinline onFailure: (Throwable) -> Unit) {

    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>?, t: Throwable) {
            onFailure(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            onResponse(response.body())
        }

    })

}

// Paging Library
private fun getErrorMessage(report: PagingRequestHelper.StatusReport): String {
    return PagingRequestHelper.RequestType.values().mapNotNull {
        report.getErrorFor(it)?.message
    }.first()
}

fun PagingRequestHelper.createStatusLiveData(): LiveData<NetworkState> {
    val liveData = MutableLiveData<NetworkState>()
    addListener { report ->
        when {
            report.hasRunning() -> liveData.postValue(NetworkState.LOADING)
            report.hasError() -> liveData.postValue(
                    NetworkState.error(getErrorMessage(report)))
            else -> liveData.postValue(NetworkState.LOADED)
        }
    }
    return liveData
}

inline fun <reified T : Parcelable> createParcel(
        crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }