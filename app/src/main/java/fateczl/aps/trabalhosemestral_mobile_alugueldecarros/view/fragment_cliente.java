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
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.controller.ClienteController;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Cliente;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence.ClienteDao;

public class fragment_cliente extends Fragment {
    private View view;
    private TextView tvResultadoCliente;
    private EditText etCpfCliente, etNomeCliente;
    private Button btnCadastrarCliente, btnAtualizarCliente, btnBuscarCliente, btnExcluirCliente;

    private ClienteController clienteController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cliente, container, false);
        tvResultadoCliente = view.findViewById(R.id.tvResultadoCliente);
        tvResultadoCliente.setMovementMethod(new ScrollingMovementMethod());

        etCpfCliente = view.findViewById(R.id.etCpfCliente);
        etNomeCliente = view.findViewById(R.id.etNomeCliente);
        btnAtualizarCliente = view.findViewById(R.id.btnAtualizarCliente);
        btnCadastrarCliente = view.findViewById(R.id.btnCadastrarCliente);
        btnBuscarCliente = view.findViewById(R.id.btnBuscarCliente);
        btnExcluirCliente = view.findViewById(R.id.btnExcluirCliente);

        clienteController = new ClienteController(new ClienteDao(view.getContext()));

        btnAtualizarCliente.setOnClickListener(click -> acaoAtualizar());
        btnBuscarCliente.setOnClickListener(click -> acaoBuscar());
        btnCadastrarCliente.setOnClickListener(click -> acaoCadastrar());
        btnExcluirCliente.setOnClickListener(click -> acaoExcluir());

        return view;
    }


    private void acaoAtualizar() {
        Cliente cliente = montaCliente();
        try {
            clienteController.atualizar(cliente);
            Toast.makeText(view.getContext(), "Cliente Atualizado!", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
        tvResultadoCliente.setText("");
    }

    private void acaoBuscar() {
        Cliente cliente = montaCliente();
        try {
            cliente = clienteController.buscar(cliente);
            if(cliente != null && cliente.getNome() != null && !cliente.getNome().isEmpty()){
                tvResultadoCliente.setText(cliente.toString());
                limpaCampos();
            }else {
                Toast.makeText(view.getContext(), "Cliente não Encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
                tvResultadoCliente.setText("");
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoCadastrar() {
        Cliente cliente = montaCliente();
        try {
            clienteController.inserir(cliente);
            Toast.makeText(view.getContext(), "Cliente Inserido!", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
        tvResultadoCliente.setText("");
    }

    private void acaoExcluir() {
        Cliente cliente = montaCliente();
        try {
            clienteController.deletar(cliente);
            Toast.makeText(view.getContext(), "Cliente Deletado!", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
        tvResultadoCliente.setText("");
    }

    private Cliente montaCliente() {
        Cliente c = new Cliente();
        c.setCPF(Integer.parseInt(etCpfCliente.getText().toString()));
        c.setNome(etNomeCliente.getText().toString());
        return c;
    }

    private void limpaCampos() {
        etCpfCliente.setText("");
        etNomeCliente.setText("");
    }
}