INSERT INTO stations (station_name, station_identifier, active) VALUES
        ('Airport', 'AIRPORT', true),
        ('Arts Center', 'ARTS_CENTER', true),
        ('Ashby', 'ASHBY', true),
        ('Avondale', 'AVONDALE', true),
        ('Bankhead', 'BANKHEAD', true),
        ('Brookhaven / Oglethorpe', 'BROOKHAVEN_OGLETHORPE', true),
        ('Buckhead', 'BUCKHEAD', true),
        ('Chamblee', 'CHAMBLEE', true),
        ('Civic Center', 'CIVIC_CENTER', true),
        ('College Park', 'COLLEGE_PARK', true),
        ('Decatur', 'DECATUR', true),
        ('Doraville', 'DORAVILLE', true),
        ('Dunwoody', 'DUNWOODY', true),
        ('East Lake', 'EAST_LAKE', true),
        ('East Point', 'EAST_POINT', true),
        ('Edgewood / Candler Park', 'EDGEWOOD_CANDLER_PARK', true),
        ('Five Points', 'FIVE_POINTS', true),
        ('Garnett', 'GARNETT', true),
        ('Georgia State', 'GEORGIA_STATE', true),
        ('GWCC / CNN Center', 'GWCC_CNN_CENTER', true),
        ('Hamilton E. Holmes', 'HAMILTON_E_HOLMES', true),
        ('Indian Creek', 'INDIAN_CREEK', true),
        ('Inman Park / Reynoldstown', 'INMAN_PARK_REYNOLDSTOWN', true),
        ('Kensington', 'KENSINGTON', true),
        ('King Memorial', 'KING_MEMORIAL', true),
        ('Lakewood / Ft. McPherson', 'LAKEWOOD_FT_MCPHERSON', true),
        ('Lenox', 'LENOX', true),
        ('Lindbergh Center', 'LINDBERGH_CENTER', true),
        ('Medical Center', 'MEDICAL_CENTER', true),
        ('Midtown', 'MIDTOWN', true),
        ('North Ave', 'NORTH_AVE', true),
        ('North Springs', 'NORTH_SPRINGS', true),
        ('Oakland City', 'OAKLAND_CITY', true),
        ('Peachtree Center', 'PEACHTREE_CENTER', true),
        ('Sandy Springs', 'SANDY_SPRINGS', true),
        ('Vine City', 'VINE_CITY', true),
        ('West End', 'WEST_END', true),
        ('West Lake', 'WEST_LAKE', true)
    ON CONFLICT (station_identifier) DO NOTHING;



-- INSERT INTO stations (station_name, station_identifier, active)
-- VALUES ('Five Points', 'FIVE_POINTS', true)
--     ON CONFLICT (station_identifier) DO NOTHING;
--
-- INSERT INTO stations (station_name, station_identifier, active)
-- VALUES ('Airport', 'AIRPORT', true)
--     ON CONFLICT (station_identifier) DO NOTHING;