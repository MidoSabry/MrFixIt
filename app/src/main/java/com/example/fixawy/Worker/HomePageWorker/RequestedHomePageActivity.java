package com.example.fixawy.Worker.HomePageWorker;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Firebase.FirebaseHandlerClient;
import com.example.fixawy.Pojos.JobTitleCategory;
import com.example.fixawy.Pojos.MakeOrder;
import com.example.fixawy.Pojos.User;
import com.example.fixawy.R;
import com.example.fixawy.Worker.DetailsJobPage.DetailsJobActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.fixawy.Share.LoginPage.LoginActivity.EXTRA_DIS_LIKE;
import static com.example.fixawy.Share.LoginPage.LoginActivity.EXTRA_LIKE;
import static com.example.fixawy.Share.LoginPage.LoginActivity.EXTRA_NUM_OF_JOB;
import static com.example.fixawy.Share.LoginPage.LoginActivity.EXTRA_RATING;
import static com.example.fixawy.Share.LoginPage.LoginActivity.EXTRA_WORKER_IMAGE;
import static com.example.fixawy.Share.VerifyCode.VerificationCode.EXTRA_JOB_TITLE;
import static com.example.fixawy.Share.VerifyCode.VerificationCode.EXTR_PHONE_NUM;
import static com.example.fixawy.Share.VerifyCode.VerificationCode.EXTR_USER_NAME;


public class RequestedHomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RequestedItemRecyclerAdapter.onItemClickListener {


    //DrawLayout side-menubar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    //textview which apear on side menue bar
    TextView textViewWorkerName,textViewWorkerPhone,textViewWorkerJobTitle;
    ImageButton change_image_button;

    String w_name,w_phone,w_job,w_type,w_address,w_email,w_password,w_image;
    String w_likes,w_numOfJob,w_disLike,w_rating;

    private Uri imageUri;
    private CircleImageView profileImageView;
    private StorageReference storageProfilePictureRef;
    private DatabaseReference mDatabaseRef;
    private  StorageTask mUploadTask;
    public static final String EXTRA_ORDER_PHONE ="phonid";
    public static final String EXTRA_ORDER_JOB_TITLE ="titleJobid";

    public static final String EXTRA_WORKER_NAME = "wName";
    public static final String EXTRA_WORKER_ADDRESS = "wAddress";
    public static final String EXTRA_WORKER_PHONE = "wPhone";

    public static final String EXTRA_WORKER_NUM_OF_JOB = "wNumJob";
    public static final String EXTRA_WORKER_LIKE = "wLike";
    public static final String EXTRA_WORKER_DIS_LIKE = "wDisLike";
    public static final String EXTRA_WORKER_RATING = "wRating";



    private RequestedHomePageViewModel requestedHomePageViewModel;



    //recyclerView
    RecyclerView recyclerView_worker_requested;
    FirebaseHandlerClient firebaseHandlerClient;
    static RequestedItemRecyclerAdapter myAdapter;
    List<MakeOrder> list;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_home_page);

        list = new ArrayList<>();

        //get data from verifiaction code page
        Intent intent = getIntent();
        String worker_phone_num = intent.getStringExtra(EXTR_PHONE_NUM);
        String  worker_user_name = intent.getStringExtra(EXTR_USER_NAME);
        String worker_job_title = intent.getStringExtra(EXTRA_JOB_TITLE);
        String worker_image = intent.getStringExtra(EXTRA_WORKER_IMAGE);



        Toast.makeText(this, worker_phone_num, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, worker_user_name, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, worker_job_title, Toast.LENGTH_SHORT).show();



        storageProfilePictureRef = FirebaseStorage.getInstance().getReference("WorkerImages");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Worker").child(worker_job_title).child("Data").child(worker_phone_num);

        // mDatabaseRef = FirebaseDatabase.getInstance().getReference("Worker").child("Carpenter").child("Data").child("01225699594");


        //DrawLayout sidemenu-bar
        drawerLayout = findViewById(R.id.drawer_layout2);
        navigationView = findViewById(R.id.nav_view2);
        toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        //Hide or show items
//        Menu menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_logout).setVisible(false);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.primaryColor));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home2);

        //show username and phone on side menuebar
        View headerView2= navigationView.getHeaderView(0);
        textViewWorkerName = headerView2.findViewById(R.id.header_worker_name);
        textViewWorkerPhone = headerView2.findViewById(R.id.header_worker_phone);
        textViewWorkerJobTitle = headerView2.findViewById(R.id.header_worker_job_title);
        textViewWorkerName.setText(worker_user_name);
        textViewWorkerPhone.setText(worker_phone_num);
        textViewWorkerJobTitle.setText(worker_job_title);

        //CircleImageView profileImageView = headerView2.findViewById(R.id.worker_profile_image);
        profileImageView = headerView2.findViewById(R.id.worker_profile_image);
        change_image_button = headerView2.findViewById(R.id.change_image);
        Picasso.get().load(worker_image).placeholder(R.drawable.profile).into(profileImageView);
        Log.d("nnnnn","user=="+worker_image);


        //retrive image of profile
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    w_name = dataSnapshot.child("userName").getValue().toString();
                    w_phone = dataSnapshot.child("phone").getValue().toString();
                    w_type = dataSnapshot.child("type").getValue().toString();
                    w_job = dataSnapshot.child("jobTitle").getValue().toString();
                    w_password = dataSnapshot.child("password").getValue().toString();
                    w_address = dataSnapshot.child("address").getValue().toString();
                    w_email = dataSnapshot.child("email").getValue().toString();

                    w_numOfJob = dataSnapshot.child("numOfJob").getValue().toString();
                    w_likes = dataSnapshot.child("like").getValue().toString();
                    w_disLike = dataSnapshot.child("disLike").getValue().toString();
                    w_rating = dataSnapshot.child("rating").getValue().toString();

                    w_image = dataSnapshot.child("image").getValue().toString();


                    if(dataSnapshot.child("image").exists())
                    {
                        String image=dataSnapshot.child("image").getValue().toString();
                        Log.d("looooooo","user=="+image);

                        Picasso.get().load(image).placeholder(R.drawable.profile).into(profileImageView);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //image change
        change_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFileChooser();
            }

        });
        //Recycler data requested
        recyclerView_worker_requested = findViewById(R.id.recyclerviewRequestedOwner);

        recyclerView_worker_requested.setHasFixedSize(true);
        recyclerView_worker_requested.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new RequestedItemRecyclerAdapter(this,list);
        recyclerView_worker_requested.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(RequestedHomePageActivity.this);



        database = FirebaseDatabase.getInstance().getReference("Worker").child(worker_job_title).child("order Details");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MakeOrder makeOrder = dataSnapshot.getValue(MakeOrder.class);
                    list.add(makeOrder);
                }
                RequestedHomePageActivity.myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //requestedHomePageViewModel = new ViewModelProvider(this).get(RequestedHomePageViewModel.class);
//        requestedHomePageViewModel = new RequestedHomePageViewModel(worker_job_title);
//        requestedHomePageViewModel.getRequestedListModelData(worker_job_title).observe(this, new Observer<List<MakeOrder>>() {
//            @Override
//            public void onChanged(List<MakeOrder> makeOrders) {
//                myAdapter.setListMakeOrders(makeOrders);
//                myAdapter.notifyDataSetChanged();
//
//            }
//        });


//        movieViewModel.getQuizListModelData().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(List<Movie> movies) {
//                recyclerViewadapter.setListMovies(movies);
//                recyclerViewadapter.notifyDataSetChanged();
//            }
//        });

    }


    //Change profile worker image start
    private void OpenFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resulCode, Intent data){
        super.onActivityResult(requestCode,resulCode,data);

        if(requestCode==1 && resulCode==RESULT_OK && data !=null && data.getData()!=null){
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
            if(mUploadTask != null && mUploadTask.isInProgress()){
                Toast.makeText(this, "Upload in aprogress", Toast.LENGTH_SHORT).show();
            }else {
                uploadPicture();
            }

        }


    }
    //to get file extension of image
    private String getFileExtension(Uri uri){
        ContentResolver CR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(CR.getType(uri));
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("uploading Image.......");
        pd.show();

        if(imageUri != null){
            StorageReference fileReference = storageProfilePictureRef.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
            mUploadTask = fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Snackbar.make(findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_LONG).show();
                    Toast.makeText(RequestedHomePageActivity.this, "upload Successfully", Toast.LENGTH_SHORT).show();
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            User user = new User(w_name.trim(),w_email.trim(),w_phone.trim(),w_address.trim(),w_type.trim(),w_password.trim(),w_job.trim(),url);
                            //User user = new User(url);
                            mDatabaseRef.setValue(user);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RequestedHomePageActivity.this, "Faild To Upload", Toast.LENGTH_SHORT).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    pd.setMessage("Percentage"+(int)progressPercent+"%");
                }
            });
        }else{
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }
//end of change profile image of worker







    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    //To click on row of requested


    @Override
    public void onItemClick(int position) {
        Intent detailsIntent = new Intent(this, DetailsJobActivity.class);
        MakeOrder clickedItem = list.get(position);
        User user = new User();
        detailsIntent.putExtra(EXTRA_ORDER_PHONE,clickedItem.getRequestedPhone());
        detailsIntent.putExtra(EXTRA_ORDER_JOB_TITLE,clickedItem.getJobTitle());
        detailsIntent.putExtra(EXTRA_WORKER_NAME,w_name);
        detailsIntent.putExtra(EXTRA_WORKER_ADDRESS,w_address);
        detailsIntent.putExtra(EXTRA_WORKER_PHONE,w_phone);
        detailsIntent.putExtra(EXTRA_WORKER_NUM_OF_JOB,w_numOfJob);
        detailsIntent.putExtra(EXTRA_WORKER_LIKE,w_likes);
        detailsIntent.putExtra(EXTRA_WORKER_DIS_LIKE,w_disLike);
        detailsIntent.putExtra(EXTRA_WORKER_RATING,w_rating);
        detailsIntent.putExtra(EXTRA_WORKER_IMAGE,w_image);

        startActivity(detailsIntent);
    }
}