package br.univali.desgarra.pelucia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.univali.desgarra.R;
import br.univali.desgarra.usuario.Pelucia;

public class PeluciaAdapter extends RecyclerView.Adapter<PeluciaAdapter.PeluciaViewHolder>{
    private final List<Pelucia> pelucias;
    private int id_pelucia;
    private final FragmentActivity activity;

    PeluciaAdapter(List<Pelucia> pelucias, FragmentActivity activity){
        this.pelucias = pelucias;
        this.activity = activity;
    }

    static class PeluciaViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomeView;
        private final TextView corView;

        PeluciaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeView = itemView.findViewById(R.id.tvListPeluciaNome);
            corView = itemView.findViewById(R.id.tvListPeluciaCor);
        }
    }

    @NonNull
    @Override
    public PeluciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelucia_item, parent, false);
        return new PeluciaViewHolder(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(PeluciaViewHolder viewHolder, int i) {
        viewHolder.nomeView.setText(pelucias.get(i).getNome());
        viewHolder.corView.setText(pelucias.get(i).getCor());
        id_pelucia = pelucias.get(i).getId();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("id", id_pelucia);

                EditarPeluciaFragment editarFragment = new EditarPeluciaFragment();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                editarFragment.setArguments(b);
                ft.replace(R.id.framePelucia, editarFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pelucias.size();
    }
}
