package rad.thewirelezzgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class Tela_Partida extends FragmentActivity{
	
	Button btFechar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__partida);
        
		btFechar = (Button)
        findViewById(R.id.button_fechar);
        
        btFechar.setOnClickListener(new View.OnClickListener() {
       	 
        	@Override
        	public void onClick(View arg0) {
        	 
        	Intent trocatela = new
        	Intent(Tela_Partida.this,Tela_Login.class);
        	Tela_Partida.this.startActivity(trocatela);
        	Tela_Partida.this.finish();
        	 
        	}
        	});
    }
}