package com.example.fixawy.Worker.WorkerSettingPage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fixawy.Helper.LocaleHelper;
import com.example.fixawy.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerSettingActivity extends AppCompatActivity {
    private final static String Address_Tag ="AIzaSyDQXjfWZEb_U1kWfGsKlzfjk9sL6iSgV3s";
    /* ------- Components Initialization ------- */
    TextInputEditText textInputEditTextName,textInputEditTextEmail,textInputEditTextPhone,
            textInputEditTextPassword,textInputEditTextRepassword;
    TextView textViewAddress,job_title;
    Button buttonCancel,buttonApply;
    ImageButton imageButtonLanguages;
    Spinner spinner;
    String[] jobs;
    int spinner_position;
    String selected_item,spinner_item;
    DatabaseReference ref;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_setting);
        String myString = "Jobs";
        /*----- Action Bar -----*/
        // Calling action bar
        ActionBar actionBar = getSupportActionBar();
        // Showing back button on action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        /* ----- Components Assigning ----- */
        textInputEditTextName = findViewById(R.id.editText_name_settings_worker);
        textInputEditTextEmail = findViewById(R.id.editText_email_settings_worker);
        textInputEditTextPhone = findViewById(R.id.editText_phone_settings_worker);
        spinner = findViewById(R.id.spinner_job_settings_worker);
        job_title = findViewById(R.id.textView_job_settings);
        textViewAddress = findViewById(R.id.editText_address_settings_worker);
        textInputEditTextPassword = findViewById(R.id.editText_password_settings_worker);
        textInputEditTextRepassword = findViewById(R.id.editText_repassword_settings_worker);
        buttonCancel = findViewById(R.id.btn_cancel_settings_worker);
        buttonApply = findViewById(R.id.btn_apply_settings_worker);
        imageButtonLanguages = findViewById(R.id.imageBtn_languages_settings_worker);

        /*----- Spinner -----*/
        jobs = getResources().getStringArray(R.array.jobs_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(WorkerSettingActivity.this,android.R.layout.simple_spinner_dropdown_item,jobs);
        spinner_position = adapter.getPosition(myString);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("--Select Job--");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selected_item = spinner.getSelectedItem().toString();
                if(! selected_item.equals("Jobs"))  spinner_item = selected_item;
                System.out.println(selected_item);
                setID();
            }
            private void setID() {
                spinner.setSelection(spinner_position);
                job_title.setText(spinner_item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        job_title.setText(selected_item);
        ref = FirebaseDatabase.getInstance().getReference().child("Worker").child("Carpenter").child("Data");
        Places.initialize(getApplicationContext(),Address_Tag);

        /* ----- Methods Implementation ----- */
        // 1-GetDefaultData
        setDefaultData();
        // 2-Address API
        textViewAddress.setFocusable(false);
        textViewAddress.setOnClickListener(v -> {
            // 1-Initialize Place Field
            List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
            // 2-Create intent
            Intent addressIntent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(WorkerSettingActivity.this);
            // 3-Start activity result
            startActivityForResult(addressIntent, 100);
        });
        // 3-ApplyChanges
        //                                   1-Check Validation        2-Update the data successfully
        buttonApply.setOnClickListener(v -> {if (checkDataValidation() == true) updateUserData();});
        //4-Cancel
        buttonCancel.setOnClickListener(v -> {setResult(Activity.RESULT_CANCELED);   finish(); });
        // 5-Locale
        imageButtonLanguages.setOnClickListener(v -> {
            PopupMenu popupMenu= new PopupMenu(WorkerSettingActivity.this,imageButtonLanguages);
            popupMenu.getMenuInflater().inflate(R.menu.main_menu3, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                Toast.makeText(WorkerSettingActivity.this, "" + item.getTitle(),Toast.LENGTH_SHORT).show();
                if(item.getTitle()=="En")
                {
                    context = LocaleHelper.setLocale(WorkerSettingActivity.this,"en");
                    resources = context.getResources();
                    buttonApply.setText(resources.getString(R.string.language));
                }
                else if(item.getTitle()=="عربي")
                {
                    context = LocaleHelper.setLocale(WorkerSettingActivity.this,"ar");
                    resources = context.getResources();
                    buttonApply.setText(resources.getString(R.string.language));
                }
                return true;
            });
            popupMenu.show();
        });
    }

    public void setDefaultData()
    {
        /* ----- Showing data from firebase ----- */
        ref.child("01225699594").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    /* ----- 1-Retrieve the data from firebase ----- */
                    Map<String,Object> map = (Map<String, Object>) snapshot.getValue();
                    String name = (String) map.get("userName");
                    String email =(String) map.get("email");
                    Object phone = map.get("phone");
                    String jobTitle = (String) map.get("jobTitle");
                    String address = (String) map.get("address");
                    String password = (String) map.get("password");
                    String repeat_password =(String) map.get("password");

                    /* ----- 2-Set the values in the text fields ----- */
                    textInputEditTextName.setText(name);
                    textInputEditTextEmail.setText(email);
                    textInputEditTextPhone.setText(""+phone);
                    job_title.setText(jobTitle);
                    textViewAddress.setText(address);
                    textInputEditTextPassword.setText(password);
                    textInputEditTextRepassword.setText(repeat_password);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    public void updateUserData()
    {
        String name = textInputEditTextName.getText().toString();
        String email = textInputEditTextEmail.getText().toString();
        String phone = textInputEditTextPhone.getText().toString();
        String jobTitle = job_title.getText().toString();
        String address = textViewAddress.getText().toString();
        String password = textInputEditTextPassword.getText().toString();
        String repeat_password = textInputEditTextRepassword.getText().toString();

        HashMap hashMap = new HashMap();
        hashMap.put("userName",name);
        hashMap.put("email",email);
        hashMap.put("phone",phone);
        hashMap.put("jobTitle",jobTitle);
        hashMap.put("address",address);
        hashMap.put("password",password);

        ref.child("01225699594").updateChildren(hashMap).addOnSuccessListener(o -> Toast.makeText(WorkerSettingActivity.this,
                "Your Data is Successfully Updated",Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Case 1 (Successfully found)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            // when success initialize place
            Place address = Autocomplete.getPlaceFromIntent(data);
            // set address on EditText
            textViewAddress.setText(address.getAddress());
        }
        // Case 2 (Error)
        else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            // Initialize status
            Status status = Autocomplete.getStatusFromIntent(data);
            // Display Toast
            Toast.makeText(getApplicationContext(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkDataValidation(){
        String name = textInputEditTextName.getText().toString().trim();
        String email = textInputEditTextEmail.getText().toString().trim();
        String phone = textInputEditTextPhone.getText().toString().trim();
        String address = textViewAddress.getText().toString().trim();
        String password = textInputEditTextPassword.getText().toString().trim();
        String repeat_password = textInputEditTextRepassword.getText().toString().trim();
        boolean flag = false;
        // 1-Checking if all fields are not empty
        if(TextUtils.isEmpty(name))  {Toast.makeText(WorkerSettingActivity.this,"Enter Your Name",Toast.LENGTH_SHORT).show();};
        if(TextUtils.isEmpty(email)) {Toast.makeText(WorkerSettingActivity.this,"Enter Your Email",Toast.LENGTH_SHORT).show();};
        if(TextUtils.isEmpty(phone)) {Toast.makeText(WorkerSettingActivity.this,"Enter Your Phone Number",Toast.LENGTH_SHORT).show();}
        if(TextUtils.isEmpty(address)) {Toast.makeText(WorkerSettingActivity.this,"Enter Your Address",Toast.LENGTH_SHORT).show();}
        if(TextUtils.isEmpty(password)) {Toast.makeText(WorkerSettingActivity.this, "Enter password", Toast.LENGTH_SHORT).show();}
        if(TextUtils.isEmpty(repeat_password)) {Toast.makeText(WorkerSettingActivity.this, "Repeat password", Toast.LENGTH_SHORT).show();}

        // 2-Checking if the phone number is valid
        if (textInputEditTextPhone.getText().length()<=11)
        {Toast.makeText(WorkerSettingActivity.this,"Incorrect Mobile Number",Toast.LENGTH_SHORT).show();}

        // 3-Checking if the password == repeat password
        if (TextUtils.isEmpty(repeat_password) || !(password.equals(repeat_password)) )
        {Toast.makeText(WorkerSettingActivity.this,"Mismatched passwords", Toast.LENGTH_SHORT).show();}

        else flag = true;
        return flag;
    }

    // this event will enable the back function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}