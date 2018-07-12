package com.example.razu.newcsitproject.Home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.razu.newcsitproject.Filepath;
import com.example.razu.newcsitproject.R;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.UUID;


public class Feedback extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbars;
    EditText medit;
    Button mchoose,mupload;
    TextView text_toolbars;
    ImageView btn_back;
    //Pdf request code
    private int PICK_PDF_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;


    //Uri to store the image uri
    private Uri filePath;
    final String UPLOAD_URL="http://192.168.0.100/restpai/csitproject_fileupload.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_files);
        toolbars =(Toolbar)findViewById(R.id.ToolBars);
        //text_toolbars = (TextView)findViewById(R.id.tolbar_textview);
        //btn_back = (ImageView)findViewById(R.id.back_btn);
        //btn_back.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View view) {
                //finish();

           // }
       // });
        //String names = getIntent().getExtras().getString("name");
        //Log.d("errors", "onCreate: kkkkkkkkkkkkkkkkkkkkk"+names);
        //text_toolbars.setText(names);

        setSupportActionBar(toolbars);
        setTitle("Upload Files");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbars.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //toolbars.setTitle("Feedback");
        mchoose = (Button)findViewById(R.id.buttonChoose);
        mupload = (Button)findViewById(R.id.buttonUpload);
        medit = (EditText)findViewById(R.id.editTextname);
        mchoose.setOnClickListener(this);
        mupload.setOnClickListener(this);

        requeststoragepermission();




    }
    public void uploadmultipart(){
        //String s = medit.getText().toString();
        String path = Filepath.getPath(this,filePath);

        Log.d("File", "uploadmultipart: uuuuuuuuuuuuuuu"+path+"''");
        if (path == null) {

            Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
        } else {

            //
            String name ="demoss";

            try {
                String uploadid = UUID.randomUUID().toString();
                new MultipartUploadRequest(this,uploadid,UPLOAD_URL)
                        .addFileToUpload(path,"pdf")
                        //.addParameter("name", name) //Adding text parameter to the request
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();

        }
    }
    }


    private void showfilechooser(){
        Intent ntent = new Intent();
        ntent.setType("application/pdf");
        ntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(ntent,"Slelect Pdf"),PICK_PDF_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            filePath = data.getData();
            Log.d("Files", "onActivityResult: nnnnnnnnnnnnnnnnnnn"+filePath);
        }
    }

    private void requeststoragepermission(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mchoose) {
            showfilechooser();
        }
        if (view == mupload) {
            uploadmultipart();
        }
    }
}

