package com.itech.miratabletbox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;

public class MainActivity extends Activity {
    Firebase ref;
TextView text ;
    private Firebase help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text= (TextView) findViewById(R.id.text);
        Firebase.setAndroidContext(this);

        ref = new Firebase("https://miraapp.firebaseio.com/android/saving-data/miraPills");

        help = new Firebase("https://miraapp.firebaseio.com/android/saving-data/miraHelp");

        Query queryRef = ref.limitToLast(1);

        queryRef.addChildEventListener(new ChildEventListener() {

            ArrayList<Medecine> listMedecines = new ArrayList<Medecine>();

            boolean dataRetrieved = false;
          //  int childCount =0 ;

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //
              //  if (!dataRetrieved) {
               // childCount ++ ;
                    Medecine changedMedecine = (Medecine) dataSnapshot.getValue(Medecine.class);
                    listMedecines.add(changedMedecine);
              //    if(childCount==(int)dataSnapshot.getChildrenCount())
                    Log.v("dataChanged", changedMedecine.getName());
                text.setText(changedMedecine.getName());
                //    dataRetrieved = true;

                //}
                //Log.v("name", dataSnapshot.getValue().toString());
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
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }


    public void pushHelp(){
        help.setValue(1);

    }

    public void help(View view){
        pushHelp();
    }
}
