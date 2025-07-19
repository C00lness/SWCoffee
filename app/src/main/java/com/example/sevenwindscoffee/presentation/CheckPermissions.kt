import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

private val LOCATION_PERMISSION_REQUEST_CODE = 1

fun coffeeCheckPermissions(activity: Activity, context: Context) {

    if (!hasLocationPermissions(context)) {
        requestLocationPermissions(activity)
        return
    }
}

private fun requestLocationPermissions(activity: Activity) {
    ActivityCompat.requestPermissions(
        activity,
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
        LOCATION_PERMISSION_REQUEST_CODE
    )
}

private fun hasLocationPermissions(context: Context): Boolean {
    return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION, context) &&
            hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION, context)
}

private fun hasPermission(permission: String, context: Context): Boolean {
    val result = ActivityCompat.checkSelfPermission(context, permission);

    return result == PackageManager.PERMISSION_GRANTED;
}