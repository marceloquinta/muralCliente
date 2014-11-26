package muralufg.fabrica.inf.ufg.br.Classificado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import muralufg.fabrica.inf.ufg.br.centralufg.R;

public class SplashActivity extends Activity implements Runnable {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Handler h = new Handler();
		h.postDelayed(this, 3000);
	}

	@Override
	public void run() {
		startActivity(new Intent(this, LoginActivity.class));
		finish();	
	}

}
