package com.usp.thewirelezzgame;

import java.util.ArrayList;

import brorlandi.client.Client;
import brorlandi.client.ClientCallbackInterface;
import brorlandi.client.ClientInterface;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class ChatService extends Service implements ClientCallbackInterface {
	
	public static final String ACTION_CHAT = "WIRELEZZ_CHATZ";
	
    private final IBinder mBinder = new LocalBinder();
    
    private ArrayList<String> mMessages = new ArrayList<String>();
	private Handler mHandler;
	private ClientInterface mClient;
	private String mIp;
	
	@Override
	public int onStartCommand(Intent it, int flags, int startId) {
		
		mHandler = new Handler();
		
		mIp = it.getStringExtra("ip");
		Log.d("ChatService", mIp);
		mClient = new Client(mIp, 8002, this, null);
		return Service.START_REDELIVER_INTENT;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.d("ChatService", "OnCreate()");
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		Log.d("ChatService", "OnDestroy()");
	}
	
	@Override
	public IBinder onBind(Intent arg0) {

		return mBinder;
	}

	@Override
	public void onClientConnected(ClientInterface client) {
		mClient = client;
		mHandler.post(new updateUiThread("Conectado."));
		Log.d("ChatService", "Connected");
	}

	@Override
	public void onServerDisconnected() {
		mHandler.post(new updateUiThread("Desconectado."));	
		Log.d("ChatService", "Disconnected");
	}

	@Override
	public void onException(Exception e) {
		e.printStackTrace();
		mHandler.post(new updateUiThread(e.getMessage()));		
		
	}

	@Override
	public void onClientInput(String input) {
		// não é usado
	}

	@Override
	public void onMessageReceive(String message) {
		mHandler.post(new updateUiThread(message));
		Log.d("ChatService", message);
	}
	
	class updateUiThread implements Runnable{

		String message;
		
		public updateUiThread(String m){
			message = m;
		}
		
		@Override
		public void run() {
			
			mMessages.add(message + "\n");
			BroadcastLastMessage();
		}
		
	}
	
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
    	ChatService getService() {

            return ChatService.this;
        }
    }
    
    /** Metodos de clientes abaixo */
	
    public void sendMessage(String message){
		mClient.sendMessage(message);
	}
    
    public void BroadcastMessages() {
    	
    	String message = "";
    	
    	for(String s: mMessages) {
    		
    		message += s;
    	}
    	
    	Intent i = new Intent(ACTION_CHAT);
    	
    	i.putExtra("message", message);
    	sendBroadcast(i);
    }
    
    public void BroadcastLastMessage() {
    	
    	Intent i = new Intent(ACTION_CHAT);
    	
    	i.putExtra("message", mMessages.get(mMessages.size()-1));
    	sendBroadcast(i);
    }
}
