package br.univali.desgarra.pelucia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.univali.desgarra.R;
import br.univali.desgarra.database.DatabaseHelper;
import br.univali.desgarra.usuario.Pelucia;

public class EditarPeluciaFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private Pelucia p;
    private EditText etNome;
    private EditText etDescricao;
    private EditText etPreco;
    private EditText etCor;

    public EditarPeluciaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_editar_pelucia, container, false);
        Bundle b = getArguments();
        int id_pelucia = b != null ? b.getInt("id") : null;

        etNome = v.findViewById(R.id.editTextNome);
        etDescricao = v.findViewById(R.id.editTextDescricao);
        etPreco = v.findViewById(R.id.editTextPreco);
        etCor = v.findViewById(R.id.editTextCor);

        databaseHelper = new DatabaseHelper(getActivity());
        p = databaseHelper.getByIdPelucia(id_pelucia);
        etNome.setText(p.getNome());
        etDescricao.setText(p.getDescricao());
        etPreco.setText(String.valueOf(p.getValor()));
        etCor.setText(p.getCor());

        Button btnEditar = v.findViewById(R.id.buttonEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_pelucia);
            }
        });

        return v;
    }

    private void editar (int id) {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (etDescricao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a descrição!", Toast.LENGTH_LONG).show();
        } else if (etPreco.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o preço!", Toast.LENGTH_LONG).show();
        } else if (etCor.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a cor!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Pelucia p = new Pelucia();
            p.setId(id);
            p.setNome(etNome.getText().toString());
            p.setDescricao(etDescricao.getText().toString());
            p.setValor(Float.parseFloat(etPreco.getText().toString()));
            p.setCor(etCor.getText().toString());
            databaseHelper.updatePelucia(p);
            Toast.makeText(getActivity(), "Pelucia atualizada!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framePelucia, new ListarPeluciaFragment()).commit();
        }
    }
}