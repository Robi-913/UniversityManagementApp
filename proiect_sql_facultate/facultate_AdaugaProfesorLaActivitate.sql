DELIMITER //

CREATE PROCEDURE AdaugaProfesorLaActivitate(IN p_id_activitate_grup INT, IN p_id_profesor INT)
BEGIN
    DECLARE v_count INT;

    -- Verifică dacă activitatea și profesorul există
    SELECT COUNT(*) INTO v_count FROM activitate_grup WHERE id = p_id_activitate_grup;
    IF v_count = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Activitatea specificată nu există.';
    END IF;

    SELECT COUNT(*) INTO v_count FROM profesor WHERE id = p_id_profesor;
    IF v_count = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Profesorul specificat nu există.';
    END IF;

    -- Verifică dacă există deja o înregistrare pentru această pereche activitate-profesor
    SELECT COUNT(*) INTO v_count FROM profesor_activitate_grup
    WHERE id_activitate_grup = p_id_activitate_grup AND id_profesor = p_id_profesor;

    IF v_count = 0 THEN
        -- Inserează o nouă înregistrare
        INSERT INTO profesor_activitate_grup (id_activitate_grup, id_profesor)
        VALUES (p_id_activitate_grup, p_id_profesor);

        SELECT 'Profesorul a fost adăugat la activitate cu succes.' AS message;
    ELSE
        SELECT 'Profesorul este deja asociat la această activitate.' AS message;
    END IF;
END //

DELIMITER ;