package kmitl.lab03.chanapat58070024.simplemydot.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import kmitl.lab03.chanapat58070024.simplemydot.R;
import kmitl.lab03.chanapat58070024.simplemydot.model.Dot;
import kmitl.lab03.chanapat58070024.simplemydot.view.DotPreview;

public class EditDotFragment extends Fragment implements DotPreview.DotPreviewChangedListener {
    private Dot dot;
    private DotPreview dotPreview;
    private int maxWidth;
    private int maxHeight;

    public EditDotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);

        final SeekBar SEEKBAR = (SeekBar) rootView.findViewById(R.id.seekBar);
        final SeekBar SEEKBAR2 = (SeekBar) rootView.findViewById(R.id.seekBar2);
        final SeekBar SEEKBAR3 = (SeekBar) rootView.findViewById(R.id.seekBar3);

        SEEKBAR.setProgress(dot.getColorR());
        SEEKBAR2.setProgress(dot.getColorG());
        SEEKBAR3.setProgress(dot.getColorB());

        SEEKBAR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dotPreview.setColorR(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SEEKBAR2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dotPreview.setColorG(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SEEKBAR3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dotPreview.setColorB(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final EditText EDITTEXT = (EditText) rootView.findViewById(R.id.editText);
        final EditText EDITTEXT2 = (EditText) rootView.findViewById(R.id.editText2);
        final EditText EDITTEXT3 = (EditText) rootView.findViewById(R.id.editText3);

        EDITTEXT.setText(String.valueOf(dot.getCenterX()));
        EDITTEXT2.setText(String.valueOf(dot.getCenterY()));
        EDITTEXT3.setText(String.valueOf(dot.getRadius()));

        EDITTEXT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = String.valueOf(s);
                if(text.equals("")) {
                    EDITTEXT.setText("0");
                    text = "0";
                }

                int height = Integer.parseInt(text);
                if(height < 0) {
                    EDITTEXT.setText("0");
                } else if(height > maxWidth) {
                    EDITTEXT.setText(String.valueOf(maxWidth));
                }
            }
        });

        EDITTEXT2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = String.valueOf(s);
                if(text.equals("")) {
                    EDITTEXT2.setText("0");
                    text = "0";
                }

                int width = Integer.parseInt(text);
                if(width < 0) {
                    EDITTEXT2.setText("0");
                } else if(width > maxHeight) {
                    EDITTEXT2.setText(String.valueOf(maxHeight));
                }
            }
        });

        EDITTEXT3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = String.valueOf(s);
                if(text.equals("")) {
                    EDITTEXT3.setText("1");
                    text = "1";
                }

                int radius = Integer.parseInt(text);
                if(radius < 1) {
                    EDITTEXT3.setText("1");
                    radius = 1;
                } else if (radius > 200){
                    EDITTEXT3.setText("200");
                    radius = 200;
                }

                dotPreview.setRadius(radius);
            }
        });

        Button btnDone = (Button) rootView.findViewById(R.id.btnDone);
        Button btnCancel = (Button) rootView.findViewById(R.id.btnCancel);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dot.setRadius(Integer.parseInt(String.valueOf(EDITTEXT3.getText())));
                dot.setCenterX(Integer.parseInt(String.valueOf(EDITTEXT.getText())));
                dot.setCenterY(Integer.parseInt(String.valueOf(EDITTEXT2.getText())));
                dot.setColorR(SEEKBAR.getProgress());
                dot.setColorG(SEEKBAR2.getProgress());
                dot.setColorB(SEEKBAR3.getProgress());
                getFragmentManager().popBackStack();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        dotPreview = (DotPreview) rootView.findViewById(R.id.dotPreview);
        dotPreview.setListener(this);
        dotPreview.setRadius(dot.getRadius());
        dotPreview.setColorR(dot.getColorR());
        dotPreview.setColorG(dot.getColorG());
        dotPreview.setColorB(dot.getColorB());

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dot = getArguments().getParcelable("dot");
        maxWidth = getArguments().getInt("maxWidth");
        maxHeight = getArguments().getInt("maxHeight");
    }

    public static EditDotFragment newInstance(Dot dot, int maxHeight, int maxWidth) {
        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        args.putParcelable("dot", dot);
        args.putInt("maxHeight", maxHeight);
        args.putInt("maxWidth", maxWidth);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDotChanged() {
        dotPreview.invalidate();
    }
}
