
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE reports (
    id SERIAL PRIMARY KEY,
    user_id INT,
    image_url TEXT,
    location TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE emergencies (
    id SERIAL PRIMARY KEY,
    report_id INT,
    type VARCHAR(50),
    confidence FLOAT,
    assigned_service VARCHAR(50)
);
