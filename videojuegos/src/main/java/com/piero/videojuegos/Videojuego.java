package com.piero.videojuegos;

public record Videojuego(
        Long id,
        String titulo,
        String genero,
        String plataforma,
        double precio,
        int año
) {}