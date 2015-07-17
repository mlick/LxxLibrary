package mlick.lxxlibrary.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

/**
 */
public class BluetoothUtils {

    /**
     * @param activity
     * @param mBtAdapter
     * @param resultId
     */
    public static void checkEnableBt(Activity activity,
            BluetoothAdapter mBtAdapter, int resultId) {
        if (mBtAdapter == null || !mBtAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, resultId);
        }
    }
}
