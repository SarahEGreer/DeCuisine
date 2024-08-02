START TRANSACTION;


CREATE SEQUENCE ingredient_id;
CREATE TABLE "ingredients" ( "ingredient_id" INTEGER NOT NULL,
"ingredient_name" TEXT NOT NULL, PRIMARY KEY("ingredient_id"));



CREATE SEQUENCE recipe_id;
CREATE TABLE "recipe" ( "recipe_id" INTEGER, "created_by_user_id"
INTEGER, "recipe_name" TEXT NOT NULL, "description" TEXT,
"instructions" TEXT NOT NULL, "prep_time" TEXT, "servings"
INTEGER, "picture" TEXT, PRIMARY KEY("recipe_id") );

CREATE TABLE "recipers_ingredients" ( "recipe_id" INTEGER NOT
NULL, "ingredient_id" INTEGER NOT NULL, "amount" INTEGER NOT
NULL, "unit_type" TEXT NOT NULL, "system_of_measurement" INTEGER
NOT NULL, FOREIGN KEY("recipe_id") REFERENCES
"recipe"("recipe_id"), FOREIGN KEY("ingredient_id") REFERENCES
"ingredients"("ingredient_id") );


CREATE TABLE "user_tracked_recipes" ( "recipe_id" INTEGER NOT
NULL, "user_id" INTEGER NOT NULL, FOREIGN KEY("user_id")
REFERENCES "recipe"("recipe_id"), FOREIGN KEY("recipe_id")
REFERENCES "users"("user_id") );


COMMIT;
