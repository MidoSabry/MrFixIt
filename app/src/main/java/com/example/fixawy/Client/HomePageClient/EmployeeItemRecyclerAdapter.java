package com.example.fixawy.Client.HomePageClient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixawy.Pojos.EmployeeData;
import com.example.fixawy.R;

import java.util.List;

public class EmployeeItemRecyclerAdapter extends RecyclerView.Adapter<EmployeeItemRecyclerAdapter.EmployeeItemViewHolder> {

    private Context context;
    private List<EmployeeData> employeeDataItemList;

    public EmployeeItemRecyclerAdapter(Context context, List<EmployeeData> employeeDataItemList) {
        this.context = context;
        this.employeeDataItemList = employeeDataItemList;
    }
    @NonNull
    @Override
    public EmployeeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeItemViewHolder(LayoutInflater.from(context).inflate(R.layout.employee_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeItemViewHolder holder, int position) {
        holder.empNameTv.setText(employeeDataItemList.get(position).getEmp_name());
        holder.empAddTv.setText(employeeDataItemList.get(position).getEmp_address());
        holder.empPhoneTv.setText(employeeDataItemList.get(position).getEmp_phone());
        //holder.ratingBar.setText(employeeDataItemList.get(position).getEmp_rate());

    }

    @Override
    public int getItemCount() {
        if(employeeDataItemList == null){
            return 0;
        } else {
            return employeeDataItemList.size();
        }
    }

    public class EmployeeItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView empNameTv;
        private TextView empAddTv;
        private TextView empPhoneTv;
        private RatingBar ratingBar;
        public EmployeeItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.emp_img);
            empNameTv = itemView.findViewById(R.id.emp_name);
            empAddTv = itemView.findViewById(R.id.emp_address);
            empPhoneTv = itemView.findViewById(R.id.emp_phone);
            ratingBar = itemView.findViewById(R.id.emp_rate);
        }
    }
}
