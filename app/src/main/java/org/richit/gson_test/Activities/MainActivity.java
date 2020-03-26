package org.richit.gson_test.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.richit.gson_test.Adapters.UserAdapter;
import org.richit.gson_test.ImgBBModels.Response;
import org.richit.gson_test.Models.User;
import org.richit.gson_test.Others.ServerCalling;
import org.richit.gson_test.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String TAG = this.getClass().getSimpleName();

    RecyclerView recyclerView;
    UserAdapter adapter;
    ArrayList<User> users = new ArrayList<>();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        progressDialog = new ProgressDialog( this );
        progressDialog.setTitle( "Loading" );
        progressDialog.setMessage( "Please wait" );

        AndroidNetworking.initialize( getApplicationContext() );

        recyclerView = findViewById( R.id.recyclerview );
        adapter = new UserAdapter( this, users );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        recyclerView.setAdapter( adapter );

        populateList();
    }

    private void populateList() {
        progressDialog.show();
        ServerCalling.getAllUsers( new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Log.d( TAG, "onResponse: " + response );
                progressDialog.dismiss();

                User[] usersArray = new Gson().fromJson( response, User[].class );
                users = new ArrayList<>( Arrays.asList( usersArray ) );
                adapter.setUsers( users );
            }

            @Override
            public void onError(ANError anError) {
                Log.d( TAG, "onError: " + anError.getErrorBody() );
                Log.d( TAG, "onError: " + anError.getErrorCode() );
                progressDialog.dismiss();
            }
        } );
    }

    int counter = 0;

    public void createUserFromMain(final View view) {
        User user = new User();

        user.setName( "Rayhan" + counter++ );
        user.setEmail( "Rayhan@email.com" + counter++ );
        progressDialog.show();
        ServerCalling.createUser( user, new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Log.d( TAG, "onResponse: " + response );
                progressDialog.dismiss();
                populateList();

                View viewDialog = LayoutInflater.from( MainActivity.this ).inflate( R.layout.user_item, null );
                TextView textViewName, textViewEmail;

                textViewName = viewDialog.findViewById( R.id.nameTv );
                textViewEmail = viewDialog.findViewById( R.id.emailTv );

                new AlertDialog.Builder( MainActivity.this )
                        .setView( viewDialog )
                        .show();

                User userAfterCreation = new Gson().fromJson( response, User.class );
                textViewName.setText( "" + userAfterCreation.getName() );
                textViewEmail.setText( "" + userAfterCreation.getEmail() );


            }

            @Override
            public void onError(ANError anError) {
                Log.d( TAG, "onError: " + anError );
                progressDialog.dismiss();
            }
        } );
    }


    public void getImgBBdata(View view) {
        AndroidNetworking.get( "https://api.myjson.com/bins/qbs0y" )
                .build()
                .getAsString( new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.d( TAG, "onResponse: " + response );

                        Response imgBBresponse = new Gson().fromJson( response, Response.class );
                        Log.d( TAG, "onResponse: HEHHLA" );
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d( TAG, "onError: " + anError.getErrorBody() );
                    }
                } );
    }
}
