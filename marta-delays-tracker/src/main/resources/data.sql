INSERT INTO stations (station_name, station_identifier, active)
VALUES ('Five Points', 'FIVE_POINTS', true)
    ON CONFLICT (station_identifier) DO NOTHING;

INSERT INTO stations (station_name, station_identifier, active)
VALUES ('Airport', 'AIRPORT', true)
    ON CONFLICT (station_identifier) DO NOTHING;