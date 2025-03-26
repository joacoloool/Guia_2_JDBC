package org.joaco.services;

import org.joaco.model.AlumnoEntity;
import org.joaco.repository.AlumnoRepository;

import java.sql.SQLException;

public class AlumnoService {

    protected static AlumnoRepository alumnoRepository;

    public AlumnoService()
    {
        alumnoRepository= AlumnoRepository.getInstance();
    }



    public void guardar(AlumnoEntity alumno)
    {
        try {
            alumnoRepository.save(alumno);
        }
        catch (SQLException e)
        {
            e.getMessage();
        }

    }




}
