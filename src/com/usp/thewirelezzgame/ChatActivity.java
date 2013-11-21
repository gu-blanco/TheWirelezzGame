package com.usp.thewirelezzgame;

import com.usp.thewirelezzgame.ChatService.LocalBinder;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChatActivity extends Activity{

	private ChatService mService;
    private boolean mBound = false;

	private EditText mMessage;
	private TextView mChat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		
		Log.d("ChatActivity", "OnCreate()");
		
		mMessage = (EditText) findViewById(R.id.chatMessage);
		mChat = (TextView) findViewById(R.id.chatTextView);
		mChat.setText("");
		
		ImageButton bt = (ImageButton)findViewById(R.id.sendImageButton);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendButton();
			}
		});
		
		mChat.setMovementMethod(new ScrollingMovementMethod());
		
		registerReceiver(mMessageReceiver, new IntentFilter(ChatService.ACTION_CHAT));	
	}

	@Override
	protected void onResume() {

		super.onResume();
		bindService(new Intent(this, ChatService.class), mConnection, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onDestroy() {

		super.onDestroy();
		
		unregisterReceiver(mMessageReceiver);
		unbindService(mConnection);
	}
	
	public void sendButton(){
		String message = mMessage.getText().toString();
		mMessage.setText("");
		mService.sendMessage(message);
	}
	
	private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			
			Log.d("ChatActivity-Onreceive()", intent.getStringExtra("message"));
			mChat.append(intent.getStringExtra("message"));
		}
	};
	
	/** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            
            Log.d("ChatActvitity", "We're bound together forever");
            
            mService.BroadcastMessages();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
