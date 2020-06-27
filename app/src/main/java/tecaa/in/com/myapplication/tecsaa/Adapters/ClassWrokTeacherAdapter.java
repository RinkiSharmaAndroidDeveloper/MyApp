package tecaa.in.com.myapplication.tecsaa.Adapters;

import android.content.Context;
import android.content.Intent;

import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import tecaa.in.com.myapplication.tecsaa.AsyncResult;
import tecaa.in.com.myapplication.tecsaa.R;

import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.Data;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.HomeWorkModel;

import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.UpdatedData;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ClassWrokTeacherAdapter extends RecyclerView.Adapter<ClassWrokTeacherAdapter.MyViewHolder> {

    private List<HomeWorkModel> homeWorkModelList;
    List<Data> dataList,dataList1,dataList2;
    Context context;
    int section,subject,classStr;
    AsyncResult<String> delete;
    AsyncResult<UpdatedData> asyncResult;
    List<String> class_data,section_data,subject_data;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, time, Name, link, textSubject;
        ImageView imageView, shareIcon;
        RelativeLayout relativeLayout;
        Spinner classSpinner,sectionSpinner,subjectSpinner;
        TextView classSpinnerText,sectionSpinnerText,subjectSpinnerText;
        Button Edit;
        ImageView delete;
        LinearLayout textLayout,spinnerLayout;
        EditText titleEdit,desEdit;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            titleEdit = view.findViewById(R.id.title_edit);
            desEdit = view.findViewById(R.id.text_edit);
            date = (TextView) view.findViewById(R.id.date);
            Name = (TextView) view.findViewById(R.id.name);
            time = (TextView) view.findViewById(R.id.time);
            shareIcon = (ImageView) view.findViewById(R.id.share);
            link = (TextView) view.findViewById(R.id.link);
            textSubject = (TextView) view.findViewById(R.id.text);
            imageView = (ImageView) view.findViewById(R.id.image);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.layout_relative);
            classSpinner = view.findViewById(R.id.class_spin);
            sectionSpinner =view.findViewById(R.id.section_spin);
            subjectSpinner = view.findViewById(R.id.subject_spin);

            classSpinnerText = view.findViewById(R.id.class_spin_text);
            sectionSpinnerText =view.findViewById(R.id.section_spin_text);
            subjectSpinnerText = view.findViewById(R.id.subject_spin_text);
            Edit = view.findViewById(R.id.button_edit);
            delete = view.findViewById(R.id.delete);
            textLayout = view.findViewById(R.id.text_spinner);
            spinnerLayout = view.findViewById(R.id.spinner_Layout);

        }
    }


    public ClassWrokTeacherAdapter(List<HomeWorkModel> homeWorkModelList, Context context, List<Data> dataList, List<Data>  dataList1, List<Data>  dataList2,
                                   List<String> class_data, List<String> section_data, List<String> subject_data, AsyncResult<UpdatedData> asyncResult, AsyncResult<String> delete ) {
        this.homeWorkModelList = homeWorkModelList;
        this.context = context;
        this.dataList = dataList;
        this.dataList1 = dataList1;
        this.dataList2 = dataList2;
        this.class_data = class_data;
        this.section_data = section_data;
        this.subject_data = subject_data;
        this.asyncResult = asyncResult;
        this.delete = delete;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_home_work, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final HomeWorkModel homeWorkModel = homeWorkModelList.get(position);
      /*  if (movie.getDate() != null && !movie.getDate().equals("")) {
            holder.date.setText(movie.getDate() + " | " + movie.getDay());
        } else {
            holder.relativeLayout.setVisibility(View.GONE);
        }*/
       /* if (movie.getLink() != null) {
            holder.link.setText(movie.getLink());
        } else {
            holder.link.setVisibility(View.GONE);
        }
*/
      //  holder.time.setText(movie.getTime());
        holder.Name.setText(homeWorkModel.getFirstName()+" "+homeWorkModel.getLastName());
       // holder.textSubject.setText(movie.getSubjectText());
        holder.title.setText(homeWorkModel.getTitle());
        holder.textSubject.setText(homeWorkModel.getDesc());

        holder.textSubject.setClickable(true);

        //Handlle click event
        holder.textSubject.setMovementMethod(LinkMovementMethod.getInstance());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.textSubject.setText(Html.fromHtml(homeWorkModel.getDesc(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.textSubject.setText(Html.fromHtml(homeWorkModel.getDesc()));
        }

        holder.classSpinnerText.setText(homeWorkModel.getClassDataList().getName());
        holder.sectionSpinnerText.setText(homeWorkModel.getSchoolDataList().getName());
        holder.subjectSpinnerText.setText(homeWorkModel.getSubjectect().getName());
        holder.spinnerLayout.setVisibility(View.GONE);
        holder.textLayout.setVisibility(View.VISIBLE);
        holder.textSubject.setVisibility(View.VISIBLE);
        holder.title.setVisibility(View.VISIBLE);
        if(homeWorkModel.getFilePath()!=null){
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(homeWorkModel.getFilePath())
                    .fitCenter()
                    .into(holder.imageView);
        }else {
            holder.imageView.setVisibility(View.GONE);

        }


        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.Edit.getText().equals("Save")) {
                    holder.spinnerLayout.setVisibility(View.GONE);
                    holder.textLayout.setVisibility(View.VISIBLE);
                    holder.Edit.setText("Edit");
                    holder.titleEdit.setVisibility(View.GONE);
                    holder.desEdit.setVisibility(View.GONE);

                    if(TextUtils.isEmpty(holder.titleEdit.getText())){
                        holder.titleEdit.setError("Please fill the title.");
                        return;
                    }
                    if(TextUtils.isEmpty(holder.textSubject.getText())){
                        holder.textSubject.setError("Please give the description.");
                        return;
                    }

                    if(section==0){
                        Toast.makeText(context,"Please select section",Toast.LENGTH_LONG);
                        return;
                    }

                    if(subject==0){

                        Toast.makeText(context
                                ,"Please select the subject.",Toast.LENGTH_LONG);
                        return;
                    }
                    if(classStr==0){
                        Toast.makeText(context,"Please select class",Toast.LENGTH_LONG);
                        return;
                    }
                    //String title, String desc, int classId, int subjectId, int sectionId
                   UpdatedData updatedData =new UpdatedData(String.valueOf(homeWorkModel.getId()),holder.titleEdit.getText().toString(),holder.textSubject.getText().toString(),classStr,subject,section);
                    asyncResult.success(updatedData);

                }else{
                holder.spinnerLayout.setVisibility(View.VISIBLE);
                holder.textLayout.setVisibility(View.GONE);
                holder.titleEdit.setVisibility(View.VISIBLE);
                holder.desEdit.setVisibility(View.VISIBLE);

                holder.title.setVisibility(View.GONE);
                holder.textSubject.setVisibility(View.GONE);
                holder.Edit.setText("Save");
                    holder.titleEdit.setText(homeWorkModel.getTitle());
                    holder.desEdit.setText(homeWorkModel.getDesc());
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, class_data);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.classSpinner.setAdapter(arrayAdapter);
                holder.classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        if (parent.getItemAtPosition(position).equals("Class")) {

                        } else {
                            classStr = dataList.get(position - 1).getId();

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


                ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter(context, android.R.layout.simple_list_item_1, section_data);
                arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.sectionSpinner.setAdapter(arrayAdapter1);
                holder.sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        if (parent.getItemAtPosition(position).equals("Section")) {

                        } else {

                            section = dataList2.get(position - 1).getId();

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter(context, android.R.layout.simple_list_item_1, subject_data);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.subjectSpinner.setAdapter(arrayAdapter2);
                holder.subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        if (parent.getItemAtPosition(position).equals("Subject")) {

                        } else {
                            subject = dataList1.get(position - 1).getId();

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

            }
            }
        });
/*

        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(movie.getLink()));
                context.startActivity(i);
            }
        });
*/

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete.success(String.valueOf(homeWorkModelList.get(position).getId()));
            }
        });

        holder.shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return homeWorkModelList.size();
    }
}