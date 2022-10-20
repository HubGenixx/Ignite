package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ignite.NotificationActivity;
import com.example.ignite.R;

import java.util.ArrayList;

import Models.AddUser;

public class dashboard_list_adapter extends RecyclerView.Adapter<dashboard_list_adapter.viewHolde> {


    ArrayList<AddUser> list;
    Context context;

    public dashboard_list_adapter(ArrayList<AddUser> list, Context context) {
        this.list = list;
        this.context = context;
    }




    @NonNull
    @Override
    public viewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.customer_detail,parent,false);
        return new viewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolde holder, int position) {

         AddUser model = list.get(position);

         holder.namev.setText(model.getName());
         holder.billv.setText(model.getBill());
         holder.emailv.setText(model.getEmail());
         holder.phonev.setText(model.getPhone_number());
         holder.remarkv.setText(model.getRemark());

         holder.billv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context, NotificationActivity.class);
                 intent.putExtra("name",model.getName());
                 intent.putExtra("email",model.getEmail());
                 intent.putExtra("phone_number",model.getPhone_number());
                 intent.putExtra("bill",model.getBill());
                 intent.putExtra("remark",model.getRemark());
                 context.startActivity(intent);

             }
         });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolde extends RecyclerView.ViewHolder{

        TextView namev, phonev, emailv, remarkv, billv;

        public viewHolde(@NonNull View itemView) {
            super(itemView);

            namev = itemView.findViewById(R.id.idCustomerName);
            phonev = itemView.findViewById(R.id.idPhone);
            emailv = itemView.findViewById(R.id.idEmail);
            remarkv = itemView.findViewById(R.id.idremark);
            billv = itemView.findViewById(R.id.bill_id);

        }
    }



}
