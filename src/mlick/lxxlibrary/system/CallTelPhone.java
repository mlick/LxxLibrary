package mlick.lxxlibrary.system;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class CallTelPhone {
	/**
	 * ²¦´òµç»°
	 */
	public static void callTelphone(Context context, String phoneNumber) {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ phoneNumber));
		context.startActivity(intent);
	}

}
