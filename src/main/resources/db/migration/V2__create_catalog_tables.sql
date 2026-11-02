--1.Movies table
CREATE TABLE IF NOT EXISTS movies(
id BIGSERIAL PRIMARY KEY,
title VARCHAR(255) NOT NULL,
description TEXT,
duration_mins INT NOT NULL,
language VARCHAR(50) NOT NULL,
genre VARCHAR(50) NOT NULL,
release_date DATE,
created_at TIMESTAMPTZ NOT NULL,
updated_at TIMESTAMPTZ NOT NULL);

-- 2. Venues table
CREATE TABLE IF NOT EXISTS venues (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    address VARCHAR(500) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

-- 3. Screens table
CREATE TABLE IF NOT EXISTS screens (
    id BIGSERIAL PRIMARY KEY,
    venue_id BIGINT NOT NULL REFERENCES venues(id) ON DELETE RESTRICT,
    name VARCHAR(100) NOT NULL,
    total_seats INT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

-- 4. Physical Seats table
CREATE TABLE IF NOT EXISTS seats (
    id BIGSERIAL PRIMARY KEY,
    screen_id BIGINT NOT NULL REFERENCES screens(id) ON DELETE CASCADE,
    row_label VARCHAR(10) NOT NULL,
    seat_number INT NOT NULL,
    seat_type VARCHAR(20) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT uk_screen_seat UNIQUE(screen_id, row_label, seat_number)
);

-- 5. Shows table
CREATE TABLE IF NOT EXISTS shows (
    id BIGSERIAL PRIMARY KEY,
    movie_id BIGINT NOT NULL REFERENCES movies(id) ON DELETE RESTRICT,
    screen_id BIGINT NOT NULL REFERENCES screens(id) ON DELETE RESTRICT,
    start_time TIMESTAMPTZ NOT NULL,
    end_time TIMESTAMPTZ NOT NULL,
    base_price NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

-- 6. Show Seats table (The Core concurrency table)
CREATE TABLE IF NOT EXISTS show_seats (
    id BIGSERIAL PRIMARY KEY,
    show_id BIGINT NOT NULL REFERENCES shows(id) ON DELETE CASCADE,
    seat_id BIGINT NOT NULL REFERENCES seats(id) ON DELETE RESTRICT,
    status VARCHAR(20) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    locked_until TIMESTAMPTZ,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT uk_show_seat UNIQUE(show_id, seat_id)
);

-- Performance Indexes
CREATE INDEX IF NOT EXISTS idx_venues_city ON venues(city);
CREATE INDEX IF NOT EXISTS idx_shows_movie_id ON shows(movie_id);
CREATE INDEX IF NOT EXISTS idx_shows_start_time ON shows(start_time);
CREATE INDEX IF NOT EXISTS idx_show_seats_show_status ON show_seats(show_id, status);

