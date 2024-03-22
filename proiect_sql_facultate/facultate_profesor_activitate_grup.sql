CREATE DATABASE  IF NOT EXISTS facultate;
USE facultate;
CREATE TABLE profesor_activitate_grup (
  id int NOT NULL AUTO_INCREMENT,
  id_profesor int DEFAULT NULL,
  id_activitate_grup int DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_profesor_activitate_grup_profesor_idx (id_profesor),
  KEY fk_profesor_activitate_grup_activitate_grup_idx (id_activitate_grup),
  CONSTRAINT fk_profesor_activitate_grup_profesor FOREIGN KEY (id_profesor) REFERENCES profesor (id),
  CONSTRAINT fk_profesor_activitate_grup_activitate_grup FOREIGN KEY (id_activitate_grup) REFERENCES activitate_grup (id)
);