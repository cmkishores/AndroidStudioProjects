package com.group14.cse.arshoppingapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

<<<<<<< HEAD
public class ARFRAG extends AppCompatActivity
 {
    private ArFragment arFragment1;
=======
public class ARFRAG extends AppCompatActivity {
    private static final String TAG = ARFRAG.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;

    ArFragment arFragment;
    ModelRenderable modelRenderable;
>>>>>>> 990aac619a9416d8b591d66c5d585e52e50947ca

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }
        setContentView(R.layout.activity_arfrag);
<<<<<<< HEAD
        arFragment1 = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        assert arFragment1 != null;
        arFragment1.setOnTapArPlaneListener((hitResult, plane, motionEvent) ->
        {
            Anchor anchor = hitResult.createAnchor();
            ModelRenderable.builder().setSource(ARFRAG.this, Uri.parse("model.sfb")).build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                    .exceptionally(throwable ->
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ARFRAG.this);
                        builder.setMessage(throwable.getMessage())
                                .show();
                        return null;
                    });
        });
                Intent intent = new Intent(ARFRAG.this, ARFRAG.class);
        startActivity(intent);
    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable)
    {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arFragment1.getTransformationSystem());
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment1.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
=======
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragmentId);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("model.sfb"))
                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast toast =
                            Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
                });

        arFragment.setOnTapArPlaneListener(
                (HitResult hitresult, Plane plane, MotionEvent motionevent) -> {
                    if (modelRenderable == null){
                        return;
                    }

                    Anchor anchor = hitresult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    TransformableNode model = new TransformableNode(arFragment.getTransformationSystem());
                    model.setParent(anchorNode);
                    model.setRenderable(modelRenderable);
                    model.select();
                }
        );

    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
>>>>>>> 990aac619a9416d8b591d66c5d585e52e50947ca
    }
}