package muralufg.fabrica.inf.ufg.br.centralufg.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.log4j.Logger;

import muralufg.fabrica.inf.ufg.br.centralufg.model.NotificacaoPush;

public class GcmIntentService extends IntentService {

	private static final Logger LOGGER = Logger.getLogger("GcmIntentService");

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification(extras.toString().substring(17,extras.toString().indexOf(",")));
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification(extras.toString().substring(17,extras.toString().indexOf(",")));
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                sendNotification(extras.toString().substring(17,extras.toString().indexOf(",")));
            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String msg) {
        NotificacaoPush notificacaoPush = new NotificacaoPush();
        notificacaoPush.mostraNotificacao(msg,this);
        LOGGER.info("Notificacao: " + msg);

    }
}
