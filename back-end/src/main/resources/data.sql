INSERT INTO grupos (id, name) VALUES (1, 'Família');
INSERT INTO grupos (id, name) VALUES (2, 'Trabalho');
INSERT INTO grupos (id, name) VALUES (3, 'Amigos');
INSERT INTO grupos (id, name) VALUES (4, 'Faculdade');

INSERT INTO contatos (id, name, nickname, email, phonenumber, address, occupation, grupo_id, birthday) 
VALUES (1, 'João Silva', 'Jão', 'joao.silva@email.com', '11987654321', 'Rua A, 10', 'Engenheiro', 2, '1985-10-25');

INSERT INTO contatos (id, name, nickname, email, phonenumber, address, occupation, grupo_id, birthday) 
VALUES (2, 'Maria Souza', 'Maroca', 'maria.souza@email.com', '21912345678', 'Avenida B, 20', 'Professora', 1, '1990-03-15');

INSERT INTO contatos (id, name, nickname, email, phonenumber, address, occupation, grupo_id, birthday) 
VALUES (3, 'Carlos Lima', 'Carlão', 'carlos.lima@email.com', '31955554444', 'Travessa C, 30', 'Estudante', 3, '2001-07-08');