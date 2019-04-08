package com.group14.cse.arshoppingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ARFRAG3 extends AppCompatActivity
 {
    private ArFragment arFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arfrag);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        assert arFragment != null;
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) ->
        {
            Anchor anchor = hitResult.createAnchor();
            ModelRenderable.builder().setSource(ARFRAG3.this, Uri.parse("model3.sfb")).build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                    .exceptionally(throwable ->
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ARFRAG3.this);
                        builder.setMessage(throwable.getMessage())
                                .show();
                        return null;
                    });
        });
                Intent intent = new Intent(ARFRAG3.this, ARFRAG3.class);
        startActivity(intent);
    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable)
    {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }
 }
