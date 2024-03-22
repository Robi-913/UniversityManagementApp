DELIMITER //

CREATE PROCEDURE StergeStudentDinCurs(
    IN p_idMaterie INT,
    IN p_idProfesor INT,
    IN p_idStudent INT
)
BEGIN
    DECLARE v_idActivitate INT;

    -- Verifică dacă există o activitate didactică pentru materia și profesorul specificat
    SELECT id INTO v_idActivitate
    FROM activitate_didactica
    WHERE idMaterie = p_idMaterie AND idProfesor = p_idProfesor;

    -- Dacă există, șterge studentul
    IF v_idActivitate IS NOT NULL THEN
        DELETE FROM inscris_activitate_didactica
        WHERE idActivitateDidactica = v_idActivitate AND idStudent = p_idStudent;

        SELECT 'Studentul a fost șters cu succes din activitate!' AS Message;
    ELSE
        SELECT 'Nu există o activitate didactică pentru materia și profesorul specificat.' AS ErrorMessage;
    END IF;
END //

DELIMITER ;
