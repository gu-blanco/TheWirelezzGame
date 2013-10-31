package com.usp.thewirelezzgame;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
	
public class Tela_Login extends Activity {

	Button btLogin;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__login);
        
        btLogin = (Button)
        findViewById(R.id.button_login);
        
        btLogin.setOnClickListener(new View.OnClickListener() {
        	 
        	@Override
        	public void onClick(View arg0) {
        	 
        	Intent trocatela = new
        	Intent(Tela_Login.this,Tela_partida.class);
        	Tela_Login.this.startActivity(trocatela);
        	Tela_Login.this.finish();
        	 
        	}
        	});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela__login, menu);
        return true;
    }
    
}
