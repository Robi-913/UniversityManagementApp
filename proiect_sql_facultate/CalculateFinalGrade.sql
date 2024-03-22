DELIMITER //

CREATE PROCEDURE UpdateFinalGrades(IN materie_id INT)
BEGIN
  DECLARE curs_pct INT;
  DECLARE seminar_pct INT;
  DECLARE laborator_pct INT;

  -- Get the percentages for the specified materie_id
  SELECT procentCurs, procentSeminar, procentLaborator
  INTO curs_pct, seminar_pct, laborator_pct
  FROM materie
  WHERE id = materie_id;

  -- Update final grades for students in the specified materie_id
  UPDATE catalog
  SET notaFinala = ROUND((notaCurs * curs_pct / 100) + (notaSeminar * seminar_pct / 100) + (notaLaborator * laborator_pct / 100), 2)
  WHERE id_materie = materie_id;

END //

DELIMITER ;


