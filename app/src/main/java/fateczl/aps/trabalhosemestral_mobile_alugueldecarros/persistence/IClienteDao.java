package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence;

import java.sql.SQLException;

public interface IClienteDao {
    public ClienteDao open() throws SQLException;
    public void close();
}
