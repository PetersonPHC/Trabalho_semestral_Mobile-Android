package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.R;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.controller.CarroController;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Carro;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence.CarroDao;

public class fragment_carro extends Fragment {
    private View view;
    private EditText etChassiCarro, etNomeCarro, etMontadoraCarro, etNumeroDeRodasCarro, etQtdPortasCarro;
    private Button btnCadastrarCarro, btnAtualizarCarro, btnBuscarCarro, btnExcluirCarro;
    private TextView tvResultadoCarro;
    private CarroController carroController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_carro, container, false);
        etChassiCarro = view.findViewById(R.id.etChassiCarro);
        etNomeCarro = view.findViewById(R.id.etNomeCarro);
        etMontadoraCarro = view.findViewById(R.id.etMontadoraCarro);
        etNumeroDeRodasCarro = view.findViewById(R.id.etNumeroDeRodasCarro);
        etQtdPortasCarro = view.findViewById(R.id.etQtdPortasCarro);

        btnCadastrarCarro = view.findViewById(R.id.btnCadastrarCarro);
        btnAtualizarCarro = view.findViewById(R.id.btnAtualizarCarro);
        btnBuscarCarro = view.findViewById(R.id.btnBuscarCarro);
        btnExcluirCarro = view.findViewById(R.id.btnExcluirCarro);

        tvResultadoCarro = view.findViewById(R.id.tvResultadoCarro);
        tvResultadoCarro.setMovementMethod(new ScrollingMovementMethod());

        carroController = new CarroController(new CarroDao(view.getContext()));

        btnCadastrarCarro.setOnClickListener(click -> acaoCadastrar());
        btnAtualizarCarro.setOnClickListener(click -> acaoAtualizar());
        btnExcluirCarro.setOnClickListener(click -> acaoExcluir());
        btnBuscarCarro.setOnClickListener(click -> acaoBuscar());

        return view;
    }

    private void acaoCadastrar() {
        Carro carro = montaCarro();
        try {
            carroController.inserir(carro);
            Toast.makeText(view.getContext(), "Carro Inserido!", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
        tvResultadoCarro.setText("");
    }

    private void acaoAtualizar() {
        Carro carro = montaCarro();
        try {
            carroController.atualizar(carro);
            Toast.makeText(view.getContext(), "Carro Atualizado!", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
        tvResultadoCarro.setText("");
    }

    private void acaoExcluir() {
        Carro carro = montaCarro();
        try {
            carroController.deletar(carro);
            Toast.makeText(view.getContext(), "Carro Deletado!", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
        tvResultadoCarro.setText("");
    }

    private void acaoBuscar() {
        Carro carro = montaCarro();
        try {
            carro = carroController.buscar(carro);
            if (carro != null && carro.getNome() != null && !carro.getNome().isEmpty()) {
                tvResultadoCarro.setText(carro.toString());
                limpaCampos();
            } else {
                Toast.makeText(view.getContext(), "Carro não Encontrado", Toast.LENGTH_LONG).show();
                tvResultadoCarro.setText("");
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private Carro montaCarro() {
        Carro c = new Carro();
        c.setNome(etNomeCarro.getText().toString());
        c.setMontadora(etMontadoraCarro.getText().toString());
        c.setChassi(Integer.parseInt(etChassiCarro.getText().toString()));
        if(!etNumeroDeRodasCarro.getText().toString().isEmpty()){
            c.setNumeroDeRodas(Integer.parseInt(etNumeroDeRodasCarro.getText().toString()));
        }
        if(!etNumeroDeRodasCarro.getText().toString().isEmpty()){
            c.setQtdPortas(Integer.parseInt(etQtdPortasCarro.getText().toString()));
        }
        return c;
    }

    private void limpaCampos() {
        etChassiCarro.setText("");
        etNomeCarro.setText("");
        etMontadoraCarro.setText("");
        etNumeroDeRodasCarro.setText("");
        etQtdPortasCarro.setText("");
    }

}