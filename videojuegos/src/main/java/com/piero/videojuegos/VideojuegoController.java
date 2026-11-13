/*https://humble-spoon-7v9grqwwj579cv49-8080.app.github.dev/api/videojuegos*/

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private final List<Videojuego> videojuegos = new CopyOnWriteArrayList<>();
    private final AtomicLong contador = new AtomicLong();

    public VideojuegoController() {
        videojuegos.add(new Videojuego(contador.incrementAndGet(), "Hollow Knight", "Metroidvania", "PC", 54.90, 2017));
        videojuegos.add(new Videojuego(contador.incrementAndGet(), "Minecraft", "Sandbox", "Multiplataforma", 99.90, 2011));
    }

    @GetMapping
    public List<Videojuego> listar() {
        return videojuegos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> buscarPorId(@PathVariable Long id) {
        return videojuegos.stream()
                .filter(v -> v.id().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Videojuego> crear(@RequestBody Videojuego datos) {
        if (datos.titulo() == null || datos.titulo().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Videojuego nuevo = new Videojuego(
                contador.incrementAndGet(),
                datos.titulo(),
                datos.genero(),
                datos.plataforma(),
                datos.precio(),
                datos.año()
        );
        videojuegos.add(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}