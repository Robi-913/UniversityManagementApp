DELIMITER //

CREATE PROCEDURE GetStudentActivities(IN student_id INT)
BEGIN
    SELECT
        s2.id AS id_student,
        s2.nume AS nume_student,
        s2.prenume AS prenume_student,
        MAX(ag.nume) AS nume_activitate
    FROM
        inscris_activitate_grup iag
    INNER JOIN student s1 ON iag.id_student = s1.id AND s1.id = student_id
    INNER JOIN activitate_grup ag ON iag.id_activitate = ag.id
    INNER JOIN inscris_activitate_grup iag2 ON iag.id_activitate = iag2.id_activitate AND iag.id_student != iag2.id_student
    INNER JOIN student s2 ON iag2.id_student = s2.id
    WHERE iag.id_student = student_id
    GROUP BY s2.id, s2.nume, s2.prenume
    HAVING COUNT(DISTINCT iag.id_activitate) >= 0.5 * COUNT(DISTINCT iag2.id_activitate);
END //

DELIMITER ;

CALL GetStudentActivities(1); -- înlocuiește 1 cu id-ul studentului dorit
