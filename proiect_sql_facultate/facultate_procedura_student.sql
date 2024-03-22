-- Use the correct database
USE `facultate`;

-- Drop the procedure if it exists
DROP PROCEDURE IF EXISTS GetStudentGrades;

-- Recreate the stored procedure
DELIMITER //
CREATE PROCEDURE GetStudentGrades(IN student_id INT)
BEGIN
    -- Create a table to store the results
    CREATE TABLE IF NOT EXISTS temp_results (
        materie_nume VARCHAR(45),
        notaCurs INT,
        notaSeminar INT,
        notaLaborator INT,
        notaFinala FLOAT(3, 2)
    );

    -- Truncate the table in case it already has data
    TRUNCATE TABLE temp_results;

    -- Inserting data into the table
    INSERT INTO temp_results
    SELECT m.nume AS materie_nume, c.notaCurs, c.notaSeminar, c.notaLaborator, c.notaFinala
    FROM catalog c
    JOIN materie m ON c.id_materie = m.id
    WHERE c.id_student = student_id;

    -- Selecting the results from the table
    SELECT * FROM temp_results;

    -- Dropping the table
    DROP TABLE IF EXISTS temp_results;
END //
DELIMITER ;

-- Call the stored procedure for student with ID 7
CALL GetStudentGrades(40);
