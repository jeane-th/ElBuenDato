-- 1. Tabla de Distritos (Maestra)
CREATE TABLE distritos (
    id_distrito INT AUTO_INCREMENT PRIMARY KEY,
    nombre_distrito VARCHAR(100) NOT NULL UNIQUE
);

-- 2. Tabla de Categorías (Maestra)
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(50) NOT NULL UNIQUE
);

-- 3. Tabla de Usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol ENUM('USER', 'ADMIN') DEFAULT 'USER',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Tabla de Locales
CREATE TABLE locales (
    id_local INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    id_distrito INT NOT NULL,
    pagina_web VARCHAR(255),
    especialidad VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_local_distrito FOREIGN KEY (id_distrito) 
        REFERENCES distritos(id_distrito) ON DELETE RESTRICT
);

-- 5. Tabla Intermedia LOCAL_CATEGORIA (Relación N:M)
CREATE TABLE local_categoria (
    id_local INT NOT NULL,
    id_categoria INT NOT NULL,
    PRIMARY KEY (id_local, id_categoria),
    CONSTRAINT fk_lc_local FOREIGN KEY (id_local) 
        REFERENCES locales(id_local) ON DELETE CASCADE,
    CONSTRAINT fk_lc_categoria FOREIGN KEY (id_categoria) 
        REFERENCES categorias(id_categoria) ON DELETE CASCADE
);

-- 6. Tabla de Reseñas (Relación con Usuario y Local)
CREATE TABLE resenas (
    id_resena INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_local INT NOT NULL,
    puntuacion INT NOT NULL CHECK (puntuacion BETWEEN 1 AND 5),
    comentario TEXT,
    fecha_publicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_resena_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_resena_local FOREIGN KEY (id_local) 
        REFERENCES locales(id_local) ON DELETE CASCADE
);

-- 7. REGISTRO DE CATEGORIAS
INSERT INTO categorias (nombre_categoria) VALUES ('Restaurante'), ('Bar'), ('Cafetería');

-- 8. REGISTRO DE DISTRITOS
INSERT INTO distritos (nombre_distrito) VALUES 
('Ancón'), ('Ate'), ('Barranco'), ('Breña'), ('Carabayllo'), 
('Chaclacayo'), ('Chorrillos'), ('Cieneguilla'), ('Comas'), ('El Agustino'), 
('Independencia'), ('Jesús María'), ('La Molina'), ('La Victoria'), ('Lima (Cercado)'), 
('Lince'), ('Los Olivos'), ('Lurigancho-Chosica'), ('Lurín'), ('Magdalena del Mar'), 
('Miraflores'), ('Pachacámac'), ('Pucusana'), ('Pueblo Libre'), ('Puente Piedra'), 
('Punta Hermosa'), ('Punta Negra'), ('Rímac'), ('San Bartolo'), ('San Borja'), 
('San Isidro'), ('San Juan de Lurigancho'), ('San Juan de Miraflores'), ('San Luis'), ('San Martín de Porres'), 
('San Miguel'), ('Santa Anita'), ('Santa María del Mar'), ('Santa Rosa'), ('Santiago de Surco'), 
('Surquillo'), ('Villa El Salvador'), ('Villa María del Triunfo');

-- 9. REGISTRO DE LOCALES DE PRUEBA
INSERT INTO locales (nombre, direccion, id_distrito, pagina_web, especialidad) VALUES 
('Central', 'Av. Pedro de Osma 301', 3, 'https://centralrestaurante.com', 'Cocina de Altura'),
('Maido', 'Calle San Martín 399', 21, 'https://www.maido.pe', 'Cocina Nikkei'),
('Isolina Taberna', 'Av. San Martin 101', 3, 'https://isolina.pe', 'Comida Criolla'),
('Astrid y Gastón', 'Av. Paz Soldán 290', 31, 'https://astridygaston.com', 'Alta Cocina'),
('La Lucha Sanguchería', 'Diagonal 308', 21, 'https://lalucha.com.pe', 'Sánguches'),
('Ayahuasca Restobar', 'Av. Prolongacion San Martin 130', 3, 'http://ayahuascarestobar.com', 'Coctelería'),
('Punto Azul', 'Calle San Martín 595', 21, 'https://puntoazul.com.pe', 'Pescados y Mariscos'),
('Gran Hotel Bolívar', 'Jr. de la Unión 958', 15, 'https://hotelbolivar.pe', 'Pisco Sour'),
('Tostaduría Bisetti', 'Av. Pedro de Osma 116', 3, 'https://cafebisetti.com', 'Café de Especialidad'),
('La Picantería', 'Calle Francisco Moreno 388', 41, 'https://picanteriasdelperu.com', 'Tradicional');

-- 10. REGISTRO DE CATEGORIAS DE LOCALES DE PRUEBA
INSERT INTO local_categoria (id_local, id_categoria) VALUES 
(1, 1), -- Central -> Restaurante
(2, 1), -- Maido -> Restaurante
(3, 1), -- Isolina -> Restaurante
(4, 1), -- Astrid y Gastón -> Restaurante
(5, 1), -- La Lucha -> Restaurante
(6, 2), -- Ayahuasca -> Bar
(6, 1), -- Ayahuasca -> También Restaurante (N:M)
(7, 1), -- Punto Azul -> Restaurante
(8, 2), -- Hotel Bolívar -> Bar
(9, 3), -- Bisetti -> Cafetería
(10, 1); -- La Picantería -> Restaurante




-- Listar locales con categoria y distrito
SELECT 
    l.nombre,
    l.direccion,
    l.pagina_web,
    d.nombre_distrito,
    GROUP_CONCAT(c.nombre_categoria SEPARATOR ', ') AS categoria,
    l.especialidad,
    l.fecha_registro  -- truncamos fecha
FROM locales l
INNER JOIN distritos d ON l.id_distrito = d.id_distrito
INNER JOIN local_categoria lc ON l.id_local = lc.id_local
INNER JOIN categorias c ON lc.id_categoria = c.id_categoria
GROUP BY l.id_local 

-- agregar locales de hace en 2 pasos agreagas el local y luego asocias las categorias

INSERT INTO locales (nombre, direccion, id_distrito, pagina_web, especialidad, fecha_registro) 
VALUES ('La Lucha Sanguchería', 'Av. Santa Cruz 847', 1, 'https://lalucha.com.pe', 'Sánguches Criollos', NOW());

-- Suponiendo que la categoría 1 es 'Restaurante' y la 2 es 'Cafetería'
INSERT INTO local_categoria (id_local, id_categoria) VALUES (11, 1);
INSERT INTO local_categoria (id_local, id_categoria) VALUES (11, 2);



SELECT * FROM distritos d 


