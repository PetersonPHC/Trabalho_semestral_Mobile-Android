package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.controller;

import java.sql.SQLException;
import java.util.List;

public interface IController<T> {

    public void inserir(T t) throws SQLException;
    public void atualizar(T t) throws SQLException;
    public void deletar(T t) throws SQLException;
    public T buscar(T t) throws SQLException;
    public List<T> listar() throws SQLException;
}
