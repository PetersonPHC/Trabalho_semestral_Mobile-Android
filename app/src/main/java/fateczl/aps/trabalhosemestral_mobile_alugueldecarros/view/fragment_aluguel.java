package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.view;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.sql.SQLException;
import java.util.List;

import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.R;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.controller.AluguelController;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.controller.CarroController;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.controller.ClienteController;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Aluguel;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Carro;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Cliente;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence.AluguelDao;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence.CarroDao;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence.ClienteDao;

public class fragment_aluguel extends Fragment {
    private View view;
    private EditText etNumeroAluguel, etDataInicioAluguel, etDataFinalAluguel;
    private Spinner spClientesAluguel, spCarrosAluguel;
    private Button btnCadastrarAluguel, btnAtualizarAluguel, btnBuscarAluguel, btnExcluirAluguel, btnListarAluguel;
    private TextView tvResultadoAluguel;

    private AluguelController aluguelController;

    private ClienteController clienteController;
    private List<Cliente> clientes;

    private CarroController carroController;
    private List<Carro> carros;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aluguel, container, false);
        etNumeroAluguel = view.findViewById(R.id.etNumeroAluguel);
        etDataInicioAluguel = view.findViewById(R.id.etDataInicioAluguel);
        etDataFinalAluguel = view.findViewById(R.id.etDataFinalAluguel);
        spClientesAluguel = view.findViewById(R.id.spClientesAluguel);
        spClientesAluguel.setSelection(0);
        spCarrosAluguel = view.findViewById(R.id.spCarrosAluguel);
        spCarrosAluguel.setSelection(0);
        btnCadastrarAluguel = view.findViewById(R.id.btnCadastrarAluguel);
        btnAtualizarAluguel = view.findViewById(R.id.btnAtualizarAluguel);
        btnBuscarAluguel = view.findViewById(R.id.btnBuscarAluguel);
        btnExcluirAluguel = view.findViewById(R.id.btnExcluirAluguel);
        btnListarAluguel = view.findViewById(R.id.btnListarAluguel);
        tvResultadoAluguel = view.findViewById(R.id.tvResultadoAluguel);
        tvResultadoAluguel.setMovementMethod(new ScrollingMovementMethod());

        clienteController = new ClienteController(new ClienteDao(view.getContext()));
        carroController = new CarroController(new CarroDao(view.getContext()));
        aluguelController = new AluguelController(new AluguelDao(view.getContext()));

        btnCadastrarAluguel.setOnClickListener(click -> acaoCadastrar());
        btnAtualizarAluguel.setOnClickListener(click -> acaoAtualizar());
        btnBuscarAluguel.setOnClickListener(click -> acaoBuscar());
        btnExcluirAluguel.setOnClickListener(click -> acaoExcluir());
        btnListarAluguel.setOnClickListener(click -> acaoListar());

        preencheSpinners();
        return view;
    }

    private void acaoCadastrar() {
        int spClientePosicao = spClientesAluguel.getSelectedItemPosition();
        int spCarroPosicao = spCarrosAluguel.getSelectedItemPosition();

        if (spClientePosicao > 0) {
            if (spCarroPosicao > 0) {
                Aluguel aluguel = montaAluguel();
                try {
                    aluguelController.inserir(aluguel);
                    Toast.makeText(view.getContext(), "Aluguel Cadastrado", Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(view.getContext(), "Insira Um Carro", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(view.getContext(), "Insira Um Cliente", Toast.LENGTH_LONG).show();
        }
        limpaCampos();
        tvResultadoAluguel.setText("");
    }


    private void acaoAtualizar() {
        int spClientePosicao = spClientesAluguel.getSelectedItemPosition();
        int spCarroPosicao = spCarrosAluguel.getSelectedItemPosition();

        if (spClientePosicao > 0) {
            if (spCarroPosicao > 0) {
                Aluguel aluguel = montaAluguel();
                try {
                    aluguelController.atualizar(aluguel);
                    Toast.makeText(view.getContext(), "Aluguel Atualizado", Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(view.getContext(), "Insira Um Carro", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(view.getContext(), "Insira Um Cliente", Toast.LENGTH_LONG).show();
        }
        limpaCampos();
        tvResultadoAluguel.setText("");
    }

    private void acaoBuscar() {
        Aluguel aluguel = montaAluguel();
        try { //para Reforço
            clientes = clienteController.listar();
            carros = carroController.listar();
            aluguel = aluguelController.buscar(aluguel);
            if (String.valueOf(aluguel.getNumero()) != null && (aluguel.getCarro().getChassi() != 0 && aluguel.getCliente().getCPF() != 0)) {
                tvResultadoAluguel.setText(aluguel.toString());
            } else {
                Toast.makeText(view.getContext(), "Aluguel Não Encontrado", Toast.LENGTH_LONG).show();
                tvResultadoAluguel.setText("");
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoExcluir() {
        Aluguel aluguel = montaAluguel();
        try {
            if(String.valueOf(aluguel.getNumero()) != null){
                aluguelController.deletar(aluguel);
                Toast.makeText(view.getContext(), "Aluguel Deletado", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(view.getContext(), "Aluguel Não Encontrado", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
        tvResultadoAluguel.setText("");
    }

    private void acaoListar() {
        try {
            List<Aluguel> alugueis = aluguelController.listar();
            StringBuffer buffer = new StringBuffer();
            for(Aluguel A : alugueis){
                buffer.append(A.toString() + "\n");
            }
            tvResultadoAluguel.setText(buffer.toString());
            limpaCampos();
        }catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void limpaCampos() {
        etNumeroAluguel.setText("");
        etDataInicioAluguel.setText("");
        etDataFinalAluguel.setText("");
        spClientesAluguel.setSelection(0);
        spCarrosAluguel.setSelection(0);
    }

    private Aluguel montaAluguel() {
        Aluguel a = new Aluguel();
        a.setNumero(Integer.parseInt(etNumeroAluguel.getText().toString()));
        a.setDataInicio(etDataInicioAluguel.getText().toString());
        a.setDataFinal(etDataFinalAluguel.getText().toString());
        a.setCliente((Cliente) spClientesAluguel.getSelectedItem());
        a.setCarro((Carro) spCarrosAluguel.getSelectedItem());

        return a;
    }


    private void preencheSpinners() {

        //Spinner de Clientes
        Cliente cliente0 = new Cliente(0, "Selecione um Cliente");
        try {
            clientes = clienteController.listar();
            clientes.add(0, cliente0);

            ArrayAdapter adapterCliente = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, clientes);
            adapterCliente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spClientesAluguel.setAdapter(adapterCliente);

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        //Spinner de Carros
        Carro carro0 = new Carro(0, "Selecione um Carro", "Selecione um Carro", 0, 0);
        try {
            carros = carroController.listar();
            carros.add(0, carro0);

            ArrayAdapter adapterCarro = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, carros);
            adapterCarro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spCarrosAluguel.setAdapter(adapterCarro);

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}