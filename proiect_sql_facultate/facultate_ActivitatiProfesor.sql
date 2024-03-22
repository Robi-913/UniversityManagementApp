DELIMITER //

CREATE PROCEDURE ActivitatiProfesor(IN p_id_profesor INT)
BEGIN
    -- Verifică dacă profesorul există
    DECLARE v_count INT;
    SELECT COUNT(*) INTO v_count FROM profesor WHERE id = p_id_profesor;

    IF v_count = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Profesorul specificat nu există.';
    ELSE
        -- Selectează activitățile la care profesorul este înscris
        SELECT ag.nume AS activitate
        FROM activitate_grup ag
        JOIN profesor_activitate_grup pag ON ag.id = pag.id_activitate_grup
        WHERE pag.id_profesor = p_id_profesor;
    END IF;
END //

DELIMITER ;