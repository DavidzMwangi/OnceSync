package ke.co.davidwanjohi.oncesync.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.davidwanjohi.oncesync.R;
import ke.co.davidwanjohi.oncesync.models.Pickup;

public class PickupAdapter  extends RecyclerView.Adapter<PickupAdapter.PickupViewHolder>{
Context context;
List<Pickup> pickups;

    public PickupAdapter(Context c, List<Pickup> pickupList){

        this.context=c;
        this.pickups=pickupList;
    }
    @NonNull
    @Override
    public PickupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_pickup_adapter, parent, false);
        return new PickupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PickupViewHolder holder, int position) {

        Pickup pickup=pickups.get(position);
        holder.noOfLitres.setText(String.valueOf(pickup.noOfLitres));
        holder.date.setText(pickup.date);
        holder.accountNumber.setText(pickup.accountNumber);

    }

    @Override
    public int getItemCount() {
        return pickups.size();
    }

    public void updateData(List<Pickup> updatedList){

        this.pickups=updatedList;
        this.notifyDataSetChanged();
    }

    public class PickupViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.no_of_litres) TextView noOfLitres;
        @BindView(R.id.account_number) TextView accountNumber;
        @BindView(R.id.date) TextView date;



        public PickupViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
