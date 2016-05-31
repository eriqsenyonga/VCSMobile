package ug.dataplus_systems.vcsmobile;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;

import nl.changer.polypicker.Config;
import nl.changer.polypicker.ImagePickerActivity;
import nl.changer.polypicker.utils.ImageInternalFetcher;

import com.kbeanie.imagechooser.api.ChooserType;
import com.kbeanie.imagechooser.api.ChosenImage;
import com.kbeanie.imagechooser.api.ChosenImages;
import com.kbeanie.imagechooser.api.ImageChooserListener;
import com.kbeanie.imagechooser.api.ImageChooserManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPapPhotosFrag extends Fragment implements View.OnClickListener,
        ImageChooserListener {

    private String mediaPath;
    View v;
    ImageView ivMainPapPhoto;
    ImageButton bAddOtherPhotos;
    //  private ViewGroup mOtherImagesContainer;
    HashSet<Uri> otherPhotos = new HashSet<Uri>();
    Uri mainPhotoUri;
    private static final int INTENT_REQUEST_GET_PAP_PHOTO = 100;
    private static final int INTENT_REQUEST_GET_OTHER_PHOTOS = 200;
    ImageChooserManager imageChooserManager;

    public NewPapPhotosFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_new_pap_photos, container, false);
        ivMainPapPhoto = (ImageView) v.findViewById(R.id.iv_main_pap_photo);
        bAddOtherPhotos = (ImageButton) v.findViewById(R.id.b_add_other_pap_photos);
        //   mOtherImagesContainer = (ViewGroup) v.findViewById(R.id.other_photos_container);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ivMainPapPhoto.setOnClickListener(this);
        bAddOtherPhotos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ivMainPapPhoto) {
            //if the ivMainPapPhoto is clicked, open the photo capture thing and then set the pic

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setItems(new String[]{"Take picture", "Choose image"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (which == 0) {

                        takePapPhoto();

                    }
                    if (which == 1) {

                        getPapPhoto();
                    }

                }
            });

            Dialog d = builder.create();
            d.show();


        }

        if (v == bAddOtherPhotos) {

            getOtherPhotos();

        }
    }

    private void takePapPhoto() {

        imageChooserManager = new ImageChooserManager(this,
                ChooserType.REQUEST_CAPTURE_PICTURE);
        imageChooserManager.setImageChooserListener(this);

        try {
            mediaPath = imageChooserManager.choose();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void getOtherPhotos() {
        imageChooserManager = new ImageChooserManager(this, ChooserType.REQUEST_PICK_PICTURE);


        Bundle extras = new Bundle();
        extras.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, true);
        imageChooserManager.setExtras(extras);

        imageChooserManager.setImageChooserListener(this);

        try {
            mediaPath = imageChooserManager.choose();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getPapPhoto() {

        imageChooserManager = new ImageChooserManager(this, ChooserType.REQUEST_PICK_PICTURE);
        imageChooserManager.setImageChooserListener(this);

        try {
            mediaPath = imageChooserManager.choose();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(getClass().getName(), requestCode + "");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (imageChooserManager == null) {
                imageChooserManager = new ImageChooserManager(this, requestCode, true);
                imageChooserManager.setImageChooserListener(this);
                imageChooserManager.reinitialize(mediaPath);
            }
            imageChooserManager.submit(requestCode, data);
        }
    }

    private Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        Log.d(getClass().getName(), "onAttach: ");
    }

    @Override
    public void onImageChosen(final ChosenImage image) {

        Log.d(getClass().getName(), "onImageChosen: " + image.getFilePathOriginal());
        //      finalPath = image.getFilePathOriginal();
        //   thumbPath = image.getFileThumbnail();
        // thumbPathSmall = image.getFileThumbnailSmall();
        this.activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // pbar.setVisibility(View.GONE);
                if (image != null) {
                    //      textViewFile.setText(image.getFilePathOriginal());

                    ivMainPapPhoto.setImageURI(Uri.parse(new File(image
                            .getFileThumbnail()).toString()));

                    int wdpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
                    int htpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
                    ivMainPapPhoto.setLayoutParams(new LinearLayout.LayoutParams(wdpx, htpx));

                    //      imageViewThumbSmall.setImageURI(Uri.parse(new File(image
                    //             .getFileThumbnailSmall()).toString()));
                }
            }
        });

    }

    @Override
    public void onError(String s) {

    }

    @Override
    public void onImagesChosen(ChosenImages chosenImages) {

        ivMainPapPhoto.setImageURI(Uri.parse(new File(chosenImages.getImage(0)
                .getFileThumbnail()).toString()));

    }
}





