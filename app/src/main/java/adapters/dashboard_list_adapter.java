package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ignite.R;

import java.util.ArrayList;

import Models.Post;

public class dashboard_list_adapter extends RecyclerView.Adapter<dashboard_list_adapter.viewHolde> {


    ArrayList<Post> list;
    Context context;
    public dashboard_list_adapter(ArrayList<Post> list, Context context) {
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

         Post model = list.get(position);

         holder.namev.setText(model.getName());
         holder.billv.setText(model.getBill());
         holder.emailv.setText(model.getEmail());
         holder.phonev.setText(model.getPhone_number());
         holder.remarkv.setText(model.getRemark());

         holder.billv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(context, "working", Toast.LENGTH_SHORT).show();
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
