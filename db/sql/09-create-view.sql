CREATE VIEW "public".breed_count AS
SELECT c.id, c.breed, ( SELECT COUNT("breedId") FROM dog WHERE a."breedId" = "breedId" GROUP BY "breedId" )
FROM dog as a
JOIN "breedLookup" as c ON c.id = a."breedId"
GROUP BY c.id, c.breed, a."breedId"
ORDER BY c.id;
