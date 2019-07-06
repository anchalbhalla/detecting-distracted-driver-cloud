package com.anchal.distraction_driver;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.android.library.camera.CameraHelper;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTextView;
    private TextView t_login;
    private Button btn_capture;


    private VisualRecognition mVisualRecognition;
    private CameraHelper mCameraHelper;
    private File photoFile;
    private static final String TAG = "Main";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mTextView = (TextView) findViewById(R.id.text_view_main);
        t_login = (TextView) findViewById(R.id.t_login);
        mCameraHelper = new CameraHelper(this);
        mImageView = findViewById(R.id.imageView5);

        btn_capture = findViewById(R.id.btn_capture);
        auth();


        captureImage();

    }
    public void onBackPressed(){
        finish();

    }

    private void auth(){
        IamOptions options = new IamOptions.Builder()
                .apiKey(getString(R.string.api_key))
                .build();
        mVisualRecognition = new VisualRecognition("2018-03-19", options);
    }

    public void captureImage(){

        Button button = (Button) findViewById(R.id.btn_capture);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraHelper.dispatchTakePictureIntent();
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CameraHelper.REQUEST_IMAGE_CAPTURE) {
            final Bitmap photo = mCameraHelper.getBitmap(resultCode);
            photoFile = mCameraHelper.getFile(resultCode);
            mImageView.setImageBitmap(photo);
            mImageView.setRotation(90);
            t_login.setVisibility(View.VISIBLE);

            backgroundThread();

        }
    }

    private void backgroundThread(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                InputStream imagesStream = null;
                try {
                    imagesStream = new FileInputStream(photoFile);



                    ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                            .imagesFile(imagesStream)
                            .imagesFilename(photoFile.getName())
                            .threshold((float) 0.6)
                            .classifierIds(Arrays.asList("DistractionDetection_38457485"))
                            .build();
                    ClassifiedImages result = mVisualRecognition.classify(classifyOptions).execute();
                    Gson gson = new Gson();
                    String json = gson.toJson(result);
                    Log.d("json", json);
                    String name = null;
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray jsonArray = jsonObject.getJSONArray("images");
                        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("classifiers");
                        JSONObject jsonObject2 = jsonArray1.getJSONObject(0);
                        JSONArray jsonArray2 = jsonObject2.getJSONArray("classes");
                        JSONObject jsonObject3 = jsonArray2.getJSONObject(0);
                        name = jsonObject3.getString("class");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    final String finalName = name;




                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            Log.d(TAG, "Ans: " + finalName);

                            if(finalName.equals("c0")) {
                                t_login.setText("The driver is driving safely");
                            }

                            else if(finalName.equals("c1")) {
                                t_login.setTextColor(Color.RED);
                                t_login.setText("WARNING: The driver is texting while driving");
                            }

                            else if(finalName.equals("c2")) {
                                t_login.setTextColor(Color.RED);
                                t_login.setText("WARNING: The driver is talking on the phone while driving");
                            }

                            else if(finalName.equals("c3")) {
                                t_login.setTextColor(Color.RED);
                                t_login.setText("WARNING: The driver is texting while driving");
                            }

                            else if(finalName.equals("c4")) {
                                t_login.setTextColor(Color.RED);
                                t_login.setText("WARNING: The driver is talking on the phone while driving");
                            }

                            else if(finalName.equals("c5")) {
                                t_login.setTextColor(Color.RED);
                                t_login.setText("WARNING: The driver is operating the radio while driving");
                            }

                            else if(finalName.equals("c6")) {
                                t_login.setTextColor(Color.RED);
                                t_login.setText("WARNING: The driver is drinking while driving");
                            }

                            else if(finalName.equals("c7")) {
                                t_login.setTextColor(Color.RED);
                                t_login.setText("WARNING: The driver is reaching behind while driving");
                            }

                            else if(finalName.equals("c8")) {
                                t_login.setTextColor(Color.RED);
                                t_login.setText("WARNING: The driver is fixing their hair and makeup while driving");
                            }

                            else if(finalName.equals("c9")) {
                                t_login.setTextColor(Color.RED);
                                t_login.setText("WARNING: The driver is talking to the passenger while driving");
                            }

                            else
                                t_login.setText(finalName);



                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();



                }



            }



        });

    }
}
