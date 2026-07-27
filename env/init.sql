CREATE TABLE IF NOT EXISTS lrts (
    id SERIAL PRIMARY KEY,
    lrt_number VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    operator VARCHAR(50) NOT NULL,
    max_speed INT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- insert initial data
INSERT INTO lrts (id, lrt_number, type, operator, max_speed, created_at, updated_at) VALUES
(1, 'IC690', 'INTERCIDADES', 'CP', 200, NOW(), NOW()),
(2, 'F51234', 'FREIGHT', 'MEDWAY', 100, NOW(), NOW()),
(3, 'IN421', 'INTERNACIONAL', 'CP', 160, NOW(), NOW()),
(4, 'AP130', 'ALFA_PENDULAR', 'CP', 220, NOW(), NOW());