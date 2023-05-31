package br.univali.desgarra.pelucia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import br.univali.desgarra.R;
import br.univali.desgarra.menu.FeedFragment;


public class AdicionarFimPeluciaFragment extends Fragment {

    //private EditText etNome;

    public AdicionarFimPeluciaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_adicionar_fim_pelucia, container, false);

        AppCompatImageButton btnSair = v.findViewById(R.id.voltar);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, new AdicionarInicioPeluciaFragment()).commit();
            }
        });

        Button btnContinuar = v.findViewById(R.id.continuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, new FeedFragment()).commit();
            }
        });

        return v;
    }

/*
    private void adicionar () {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (etCep.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o CEP!", Toast.LENGTH_LONG).show();
        } else if (etLogradouro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o logradouro!", Toast.LENGTH_LONG).show();
        } else if (etNumero.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do logradouro!", Toast.LENGTH_LONG).show();
        } else if (etBairro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o bairro!", Toast.LENGTH_LONG).show();
        } else if (etCidade.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a cidade!", Toast.LENGTH_LONG).show();
        } else if (etCelular.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
        } else if (etDataNascimento.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a data de nascimento!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Mae m = new Mae();
            m.setNome(etNome.getText().toString());
            m.setLogradouro(etLogradouro.getText().toString());
            m.setCep(etCep.getText().toString());
            m.setNumero(Integer.parseInt(etNumero.getText().toString()));
            m.setBairro(etBairro.getText().toString());
            m.setCidade(etCidade.getText().toString());
            m.setFixo(etFixo.getText().toString());
            m.setCelular(etCelular.getText().toString());
            m.setComercial(etComercial.getText().toString());
            m.setData_nascimento(etDataNascimento.getText().toString());
            databaseHelper.createMae(m);
            Toast.makeText(getActivity(), "Mãe salva!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMae, new ListarFragment()).commit();
        }
    }*/
}