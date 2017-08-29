package com.hq.job;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import com.anton46.stepsview.StepsView;
import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.VerticalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    StepsView mStepsView;
    HorizontalStepView horizontalStepView;
    private final String[] steps = {"Step 1", "Step 2", "Step 3"};
    Button button;
    Spinner city,state;
    int RESULT_LOAD_IMAGE=1;
    String picturePath;
    EditText logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        logo=(EditText)findViewById(R.id.logo);
        state=(Spinner)findViewById(R.id.state);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });
        state.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.state_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        state.setAdapter(adapter);

        button=(Button)findViewById(R.id.button);
        horizontalStepView=(HorizontalStepView) findViewById(R.id.bstep_view);
        List<StepBean> stepsBeanList  = new ArrayList<>();
        StepBean stepBean0 = new StepBean("Step 1",1);
        StepBean stepBean1 = new StepBean("Step 2",1);
        StepBean stepBean2 = new StepBean("Step 3",1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        horizontalStepView
                .setStepViewTexts(stepsBeanList)//总步骤
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getApplicationContext(),  android.R.color.white))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getApplicationContext(), R.color.uncompleted_text_color))
                .setStepViewComplectedTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.white))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.uncompleted_text_color))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.grn))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_icon))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.attention));
//
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main3Activity.this,Start.class);
                startActivity(intent);

            }
        });
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (pos){
            case 0:
                city=(Spinner)findViewById(R.id.district);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.keralaCities_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                city.setAdapter(adapter);
                break;
            case 1:
                city=(Spinner)findViewById(R.id.district);
                ArrayAdapter<CharSequence> adapte = ArrayAdapter.createFromResource(this,
                        R.array.tamilnaduCities_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
                adapte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                city.setAdapter(adapte);
                break;
            case 2:
                city=(Spinner)findViewById(R.id.district);
                ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this,
                        R.array.karnatakaCities_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
                adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                city.setAdapter(adapt);
                break;
        }
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_LOAD_IMAGE&&resultCode==RESULT_OK&&null!=data)
        {
            Uri selectedImage=data.getData();
            String[] filePathcolumn={MediaStore.Images.Media.DATA};
            Cursor cursor=getContentResolver().query(selectedImage,filePathcolumn,null,null,null);
            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(filePathcolumn[0]);
            picturePath=cursor.getString(columnIndex);
            cursor.close();
            logo.setText(picturePath);
        }

    }

}
