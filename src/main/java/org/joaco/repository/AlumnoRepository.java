package org.joaco.repository;

import org.joaco.model.AlumnoEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlumnoRepository implements Repository<AlumnoEntity> {

    private static final AlumnoRepository Instance = new AlumnoRepository();

    private AlumnoRepository() {
    }

    public static AlumnoRepository getInstance() {
        return Instance;
    }

    @Override
    public void save(AlumnoEntity alumno) throws SQLException {
        try (Connection connection = SqlLiteConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO alumnos(nombre,apellido,edad,email) values(?,?,?,?)")
        ) {
            preparedStatement.setString(1, alumno.getNombre());
            preparedStatement.setString(2, alumno.getApellido());
            preparedStatement.setInt(3, alumno.getEdad());
            preparedStatement.setString(4, alumno.getEmail());
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public Optional<List<AlumnoEntity>> findAll() throws SQLException {
        List<AlumnoEntity> alumnoEntityList = new ArrayList<>();
        try (Connection connection = SqlLiteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT * FROM alumnos")
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next())
               {
                  alumnoEntityList.add(resultSetToAlumno(resultSet));
               }
            }
        }
        return Optional.of(alumnoEntityList);

    }

    public Optional<AlumnoEntity> resultSetToAlumnoOp(ResultSet rs) throws SQLException
    {
        if (rs.next()) {
            return Optional.of(new AlumnoEntity
                    (rs.getInt("id")
                            , rs.getString("nombre")
                            , rs.getString("apellido")
                            , rs.getInt("edad")
                            , rs.getString("email")
                    ));
        }
        else
        {
            return Optional.empty();

        }
    }
    public AlumnoEntity resultSetToAlumno(ResultSet rs) throws SQLException
    {
            return new AlumnoEntity
                    (rs.getInt("id")
                            , rs.getString("nombre")
                            , rs.getString("apellido")
                            , rs.getInt("edad")
                            , rs.getString("email")
                    );
    }


    @Override
    public Optional<AlumnoEntity> findById(int id)throws SQLException {

        try (Connection connection = SqlLiteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT * FROM alumnos where id = ?")
        ) {
            preparedStatement.setInt(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSetToAlumnoOp(resultSet);
            }
        }
    }

    @Override
    public void update(AlumnoEntity alumnoModify) throws SQLException {
        try (Connection connection = SqlLiteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("UPDATE alumnos set nombre =  ?, apellido = ? , edad = ?,email = ? where id = ?")
        ) {
            preparedStatement.setString(1, alumnoModify.getNombre());
            preparedStatement.setString(2, alumnoModify.getApellido());
            preparedStatement.setInt(3, alumnoModify.getEdad());
            preparedStatement.setString(4, alumnoModify.getEmail());
            preparedStatement.setInt(5, alumnoModify.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean delete(AlumnoEntity alumno) throws SQLException {
        try (Connection connection = SqlLiteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("DELETE FROM alumnos where id = ?")
        ) {
            preparedStatement.executeQuery();
            return true;
        }
    }

}
