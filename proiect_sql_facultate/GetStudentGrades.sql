create
    definer = root@localhost procedure GetStudentGrades(IN student_id int)
BEGIN
    -- Temporary table to store the results
    CREATE TEMPORARY TABLE temp_results (
                                            materie_nume VARCHAR(45),
                                            notaCurs INT,
                                            notaSeminar INT,
                                            notaLaborator INT,
                                            notaFinala FLOAT(3, 2)
    );

    -- Inserting data into the temporary table
    INSERT INTO temp_results
    SELECT m.nume AS materie_nume, c.notaCurs, c.notaSeminar, c.notaLaborator, c.notaFinala
    FROM catalog c
             JOIN materie m ON c.id_materie = m.id
             JOIN student s ON c.id_student = s.id
    WHERE s.id = student_id;

    -- Selecting the results from the temporary table
    SELECT * FROM temp_results;

    -- Dropping the temporary table
    DROP TEMPORARY TABLE IF EXISTS temp_results;
END;