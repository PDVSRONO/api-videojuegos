package pe.edu.uls.demospringdb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLibro {

    @Autowired
    RepositoryLibro repoLibro;

    public Libro registrar(Libro nuevo) {
        nuevo.setId(null);
        return repoLibro.save(nuevo);
    }

    public List<Libro> consultarTodos() {
        return repoLibro.findAll();
    }
}
