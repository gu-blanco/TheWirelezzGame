package com.usp.thewirelezzgame;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class Tela_Login extends ActionBarActivity {

	Button btLogin;
	
	String mIP = "200.144.255.42";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__login);
        
        if(ServiceRunning()) {	
			
        	Intent trocatela = new
        	Intent(Tela_Login.this,Tela_Partida.class);
        	Tela_Login.this.startActivity(trocatela);	
		}
        
        btLogin = (Button)
        findViewById(R.id.button_login);
        
        btLogin.setOnClickListener(new View.OnClickListener() {
        	 
        	@Override
        	public void onClick(View arg0) {
        		
                if(!ServiceRunning()) {	
        			
        			stopService(new Intent(getApplicationContext(), ChatService.class));
        		}
                
        		Intent i = new Intent(getApplicationContext(), ChatService.class);
    			i.putExtra("ip", mIP);
    			startService(i);
        		
	        	Intent trocatela = new
	        	Intent(Tela_Login.this,Tela_Partida.class);
	        	Tela_Login.this.startActivity(trocatela);	 
        	}
        	});
    }
    
	private boolean ServiceRunning() {
	    
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (ChatService.class.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
}
