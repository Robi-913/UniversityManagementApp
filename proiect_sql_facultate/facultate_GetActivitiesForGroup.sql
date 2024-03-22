DELIMITER //

CREATE PROCEDURE GetActivitiesForGroup(
    IN student_id INT
)
BEGIN
    DECLARE group_id INT;

    -- Obține id-ul grupului studentului
    SELECT id_grup INTO group_id
    FROM inscris_grup
    WHERE id_student = student_id;

    -- Verifică dacă studentul este înscris într-un grup
    IF group_id IS NOT NULL THEN
        -- Obține numele activităților la care sunt înscriși colegii din aceeași grupă
        SELECT DISTINCT a.nume
        FROM activitate_grup a
        JOIN inscris_activitate_grup iag ON a.id = iag.id_activitate
        JOIN inscris_grup ig ON iag.id_student = ig.id_student
        WHERE ig.id_grup = group_id
        AND iag.id_student != student_id
        AND iag.id_activitate NOT IN (
            -- Excludem activitățile la care studentul însuși este deja înscris
            SELECT id_activitate
            FROM inscris_activitate_grup
            WHERE id_student = student_id
        );
    ELSE
        -- Dacă studentul nu este înscris într-un grup, nu există activități de afișat
        SELECT 'Studentul nu este înscris într-un grup.';
    END IF;
END //

DELIMITER ;

