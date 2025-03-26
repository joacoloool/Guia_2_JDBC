package org.joaco.repository;
import org.joaco.model.DireccionEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DireccionRepository implements Repository<DireccionEntity> {

private final static DireccionRepository instance = new DireccionRepository();

    private DireccionRepository() {
    }

    public static DireccionRepository getInstance()
    {
        return instance;
    }


    @Override
    public void save(DireccionEntity direccionEntity) throws SQLException {
        try (Connection connection = SqlLiteConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO direcciones(calle,altura) values(?,?)")
        ) {
            preparedStatement.setString(1, direccionEntity.getCalle());
            preparedStatement.setInt(2, direccionEntity.getAltura());
            preparedStatement.executeUpdate();

        }
    }


    @Override
    public Optional<List<DireccionEntity>> findAll() throws SQLException {
        List<DireccionEntity> direccionEntities = new ArrayList<>();
        try (Connection connection = SqlLiteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT * FROM direcciones")
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                {
                    direccionEntities.add(new DireccionEntity(resultSet.getInt("id"),resultSet.getString("calle"),resultSet.getInt("altura")));
                }
            }
        }
        return Optional.of(direccionEntities);
    }

    @Override
    public void update(DireccionEntity direccionEntity) throws SQLException {
        try (Connection connection = SqlLiteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("UPDATE direcciones set calle =  ?, altura = ? where id = ?")
        ) {
            preparedStatement.setString(1, direccionEntity.getCalle());
            preparedStatement.setInt(2, direccionEntity.getAltura());
            preparedStatement.setInt(3,direccionEntity.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean delete(DireccionEntity direccionEntity) throws SQLException {
        try (Connection connection = SqlLiteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE from direcciones where id = ?")
        ){
                preparedStatement.setInt(1,direccionEntity.getId());
                preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public Optional<DireccionEntity> findById(int id) throws SQLException {

        try (Connection connection = SqlLiteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT * FROM direcciones where id = ?")
        ) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new DireccionEntity(resultSet.getInt("id"), resultSet.getString("calle"), resultSet.getInt("altura")));
            } else {
                return Optional.empty();
            }
        }
    }
}
