package br.univali.desgarra.pelucia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import br.univali.desgarra.R;
import br.univali.desgarra.database.DatabaseHelper;
import br.univali.desgarra.menu.FeedFragment;
import br.univali.desgarra.usuario.Pelucia;


public class AdicionarInicioPeluciaFragment extends Fragment {

    private EditText etNome;
    private EditText etDescricao;
    private EditText etPreco;
    private EditText etCor;

    public AdicionarInicioPeluciaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_adicionar_inicio_pelucia, container, false);

        AppCompatImageButton btnSair = v.findViewById(R.id.voltar);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, new FeedFragment()).commit();
            }
        });

        Button btnContinuar = v.findViewById(R.id.continuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, new AdicionarFimPeluciaFragment()).commit();
            }
        });

        return v;
    }

    private void adicionar () {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (etDescricao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a descrição!", Toast.LENGTH_LONG).show();
        } else if (etPreco.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o preco!", Toast.LENGTH_LONG).show();
        } else if (etCor.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a cor!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Pelucia p = new Pelucia();
            p.setNome(etNome.getText().toString());
            p.setDescricao(etDescricao.getText().toString());
            p.setValor(Float.parseFloat(etPreco.getText().toString()));
            p.setCor(etCor.getText().toString());
            databaseHelper.createPelucia(p);
            Toast.makeText(getActivity(), "Pelucia salva!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framePelucia, new ListarPeluciaFragment()).commit();

        }
    }
}