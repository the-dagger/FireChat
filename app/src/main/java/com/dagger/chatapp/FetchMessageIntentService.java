package com.dagger.chatapp;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FetchMessageIntentService extends IntentService {

    public static String TAG = "FetchMessageIntentService";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ChildEventListener childEventListener;

    public FetchMessageIntentService() {
        super("FetchMessageIntentService");
        setIntentRedelivery(false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("messages");
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Intent intent1 = new Intent(TAG);
                intent1.putExtra("resultCode", Activity.RESULT_OK);
                FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                intent1.putExtra("messages", friendlyMessage);
                LocalBroadcastManager.getInstance(FetchMessageIntentService.this).sendBroadcast(intent1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            if (childEventListener == null) {
                childEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Intent intent1 = new Intent(TAG);
                        intent1.putExtra("resultCode", Activity.RESULT_OK);
                        FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                        intent1.putExtra("messages", friendlyMessage);
                        LocalBroadcastManager.getInstance(FetchMessageIntentService.this).sendBroadcast(intent1);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                };
            }
            databaseReference.addChildEventListener(childEventListener);
        }

    }

}
