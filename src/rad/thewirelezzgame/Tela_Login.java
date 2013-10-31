package rad.thewirelezzgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class Tela_Login extends FragmentActivity {

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
        	Intent(Tela_Login.this,Tela_Partida.class);
        	Tela_Login.this.startActivity(trocatela);	 
        	}
        	});   
    }
    
}
