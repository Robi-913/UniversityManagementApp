DELIMITER //

CREATE PROCEDURE StergeProfesorDinActivitate(IN p_id_activitate_grup INT, IN p_id_profesor INT)
BEGIN
    DECLARE v_count INT;

    -- Check if the professor is associated with the activity
    SELECT COUNT(*) INTO v_count FROM profesor_activitate_grup
    WHERE id_activitate_grup = p_id_activitate_grup AND id_profesor = p_id_profesor;

    IF v_count = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Profesorul nu este asociat la această activitate.';
    ELSE
        -- Delete the association between professor and activity
        DELETE FROM profesor_activitate_grup
        WHERE id_activitate_grup = p_id_activitate_grup AND id_profesor = p_id_profesor;

        SELECT 'Profesorul a fost șters din activitate cu succes.' AS message;
    END IF;
END //

DELIMITER ;