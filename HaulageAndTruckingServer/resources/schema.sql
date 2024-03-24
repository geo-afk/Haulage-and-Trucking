CREATE TABLE credentials (
    id INT NOT NULL,
    staff_id INT NOT NULL,
    staff_password VARCHAR(500),
    FOREIGN KEY (staff_id) REFERENCES staff(id)
);
