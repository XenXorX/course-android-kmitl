package kmitl.lab03.chanapat58070024.simplemydot.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import kmitl.lab03.chanapat58070024.simplemydot.R;
import kmitl.lab03.chanapat58070024.simplemydot.model.Dot;
import kmitl.lab03.chanapat58070024.simplemydot.model.DotList;
import kmitl.lab03.chanapat58070024.simplemydot.view.DotView;

public class MainFragment extends Fragment implements DotList.DotListChangedListener, DotView.DotViewListener {
    private DotList dotList;
    private DotView dotView;
    private Random random;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button buttonR = (Button) rootView.findViewById(R.id.buttonR);
        Button buttonC = (Button) rootView.findViewById(R.id.buttonC);
        Button buttonU = (Button) rootView.findViewById(R.id.buttonU);
        Button buttonS = (Button) rootView.findViewById(R.id.buttonS);

        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dot dot = createDot(random.nextInt(dotView.getWidth())
                        , random.nextInt(dotView.getHeight())
                        , random.nextInt(256)
                        , random.nextInt(256)
                        , random.nextInt(256));
                dotList.addDot(dot);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotList.clearDot();
            }
        });

        buttonU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotList.undoDot();
            }
        });

        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(requestWriteExternalStoragePermission()) {
                    View screen = v.getRootView();
                    screen.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Bitmap.createBitmap(screen.getDrawingCache());
                    screen.setDrawingCacheEnabled(false);

                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    String path = MediaStore.Images.Media.insertImage(getActivity().getApplicationContext().getContentResolver(), bitmap, "Title", null);

                    Uri uriToImage = Uri.parse(path);

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                    startActivity(Intent.createChooser(intent, getResources().getText(R.string.share)));
                }
            }
        });

        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dotView.setListener(this);
        dotView.setDotList(dotList);
        dotView.invalidate();
        random = new Random();

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dotList = getArguments().getParcelable("dotList");
        if(dotList != null) {
            dotList.setListener(this);
        }
    }

    public static MainFragment newInstance(DotList dotList) {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        args.putParcelable("dotList", dotList);
        fragment.setArguments(args);
        return fragment;
    }

    public Dot createDot(int x, int y, int r, int g, int b) {
        Dot dot = new Dot(50);
        dot.setCenterX(x);
        dot.setCenterY(y);
        dot.setColorR(r);
        dot.setColorG(g);
        dot.setColorB(b);
        return dot;
    }

    public boolean requestWriteExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            return false;
        } else {
            return true;
        }
    }

    public boolean isPointerInCircle(float pointX, float pointY, Dot dot) {
        return dot.getRadius() >= Math.sqrt(Math.pow(dot.getCenterX() - pointX, 2)
                + Math.pow(dot.getCenterY() - pointY, 2));
    }

    @Override
    public void onDotListChanged(DotList dotList) {
        dotView.setDotList(dotList);
        dotView.invalidate();
    }

    @Override
    public void onDotViewTouched(float x, float y) {
        boolean isRemoved = false;

        LinkedList<Dot> dots = dotList.getDotList();
        Collections.reverse(dots);
        for(Dot dot: dots) {
            if(isPointerInCircle(x, y, dot)) {
                dotList.removeDot(dot);
                isRemoved = true;
                break;
            }
        }
        Collections.reverse(dots);

        if(!isRemoved) {
            Dot dot = createDot((int) x, (int) y
                    , random.nextInt(256)
                    , random.nextInt(256)
                    , random.nextInt(256));
            dotList.addDot(dot);
        }
    }

    @Override
    public void onDotViewHeld(float x, float y) {
        LinkedList<Dot> dots = dotList.getDotList();
        Collections.reverse(dots);
        for(Dot dot: dots) {
            if(isPointerInCircle(x, y, dot)) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer
                        , new EditDotFragment().newInstance(dot, dotView.getWidth(), dotView.getHeight()));
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            }
        }
        Collections.reverse(dots);
    }
}
