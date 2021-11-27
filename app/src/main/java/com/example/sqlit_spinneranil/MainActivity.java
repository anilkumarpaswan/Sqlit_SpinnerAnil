package com.example.sqlit_spinneranil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editText;
    Button Item_btn;
    Spinner Spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        editText = findViewById ( R.id.editText );
        Item_btn = findViewById ( R.id.Item_btn );
        Spinner = findViewById ( R.id.Spinner );

        Spinner.setOnItemSelectedListener ( this );


        loadSpinnerData ();

       Item_btn.setOnClickListener ( new View.OnClickListener () {
           @Override
           public void onClick(View v) {
               String s= editText.getText ().toString ();
               if (s.trim ().length ()>0){

                   DatabaseHandler db = new DatabaseHandler (getApplicationContext ());
                   db.insertAnil ( s );

                   editText.setText ( "" );
                   //Hiding the keyboard

                   InputMethodManager imm= (InputMethodManager)getSystemService ( Context.INPUT_METHOD_SERVICE );
                   imm.hideSoftInputFromWindow (editText.getWindowToken (),0  );


                   //loading spinner with newly added data
                   loadSpinnerData();
               }else {
                   Toast.makeText ( getApplicationContext (),"Plase enter label name",Toast.LENGTH_SHORT ).show ();
               }

           }
       } );



    }

    private void loadSpinnerData() {
        DatabaseHandler db = new DatabaseHandler (getApplicationContext ()  );
        List<String>s = db.getAlls ();
        //creating adapter for spinner
        //ArrayAdapter<String>dataAdapter = new ArrayAdapter<String> ( this,R.layout.support_simple_spinner_dropdown_item,s );
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource ( this,R.array.Items,R.layout.support_simple_spinner_dropdown_item );
        //Drop down layout-list view radio button
        adapter.setDropDownViewResource ( R.layout.support_simple_spinner_dropdown_item );

        //Attaching data adapter to spinner
        Spinner.setAdapter ( adapter );

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Showing selected spinner item
        String s = parent.getItemAtPosition ( position ).toString ();

      Toast.makeText ( parent.getContext (),"You select: "+s,Toast.LENGTH_LONG ).show ();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}