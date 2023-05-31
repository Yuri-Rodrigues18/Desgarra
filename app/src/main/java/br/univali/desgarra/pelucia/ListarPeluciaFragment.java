package br.univali.desgarra.pelucia;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.univali.desgarra.R;
import br.univali.desgarra.database.DatabaseHelper;
import br.univali.desgarra.usuario.Pelucia;


public class ListarPeluciaFragment extends Fragment {


    public ListarPeluciaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listar_pelucia, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

        RecyclerView recyclerViewMaes = v.findViewById(R.id.recyclerViewPelucias);

        LinearLayoutManager manager = new LinearLayoutManager(v.getContext());
        recyclerViewMaes.setLayoutManager(manager);
        recyclerViewMaes.addItemDecoration(new DividerItemDecoration(v.getContext(), LinearLayoutManager.VERTICAL));
        recyclerViewMaes.setHasFixedSize(true);

        Cursor dataPelucia = databaseHelper.getAllPelucia();
        List<Pelucia> pelucias = new ArrayList<Pelucia>();
        while (dataPelucia.moveToNext()) {
            Pelucia p = new Pelucia();
            int idColumnIndex = dataPelucia.getColumnIndex("_id");
            p.setId(Integer.parseInt(dataPelucia.getString(idColumnIndex)));
            int nameColumnIndex = dataPelucia.getColumnIndex("nome");
            p.setNome(dataPelucia.getString(nameColumnIndex));
            int descricaoColumnIndex = dataPelucia.getColumnIndex("descricao");
            p.setDescricao(dataPelucia.getString(descricaoColumnIndex));
            int precoColumnIndex = dataPelucia.getColumnIndex("preco");
            p.setValor(Float.parseFloat(getString(precoColumnIndex)));
            int corColumnIndex = dataPelucia.getColumnIndex("cor");
            p.setCor(dataPelucia.getString(corColumnIndex));
            pelucias.add(p);
        }
        dataPelucia.close();
        //No método getAllMae() apenas abre a conexão
        databaseHelper.closeDBConnection();

        PeluciaAdapter adapterPelucias = new PeluciaAdapter(pelucias, getActivity());
        recyclerViewMaes.setAdapter(adapterPelucias);
        return v;
    }
}