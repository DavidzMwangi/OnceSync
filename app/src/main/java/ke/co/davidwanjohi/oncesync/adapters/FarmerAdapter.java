package ke.co.davidwanjohi.oncesync.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.FarmerActivity;
import ke.co.davidwanjohi.oncesync.R;
import ke.co.davidwanjohi.oncesync.models.Farmer;

public class FarmerAdapter  extends RecyclerView.Adapter<FarmerAdapter.FarmerViewHolder> {
    Context context;
    List<Farmer> farmerList;
    public FarmerAdapter(Context c, List<Farmer> farmerList){
        this.context=c;
        this.farmerList=farmerList;

    }

    @NonNull
    @Override
    public FarmerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_farmer_adapter, parent, false);
        return new FarmerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmerViewHolder holder, int position) {

        Farmer farmer=farmerList.get(position);
        holder.name.setText(farmer.name);
        holder.gender.setText(farmer.gender==0?"Male":"Female");
        holder.telephoneNo.setText(farmer.telephoneNo);
        holder.accountNo.setText(farmer.accountNo);
        holder.location.setText(farmer.location);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch the single fragment on farmer profile

                Intent intent=new Intent(context, FarmerActivity.class);
                intent.putExtra("farmer_id",farmer.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return farmerList.size();
    }

    public void updateData(List<Farmer> farmerList) {

        this.farmerList=farmerList;
        this.notifyDataSetChanged();
    }

    public class FarmerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.name) TextView name;
        @BindView(R.id.gender) TextView gender;
        @BindView(R.id.telephone_no) TextView telephoneNo;
        @BindView(R.id.account_number) TextView accountNo;
        @BindView(R.id.location) TextView location;
        @BindView(R.id.cardview)
        CardView cardView;


        public FarmerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
