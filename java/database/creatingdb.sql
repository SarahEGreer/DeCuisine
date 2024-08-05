START TRANSACTION;

DROP TABLE IF EXISTS user_tracked_recipes, recipe_tags, recipes_ingredients, ingredients, recipe, tag_id, users CASCADE;
DROP SEQUENCE IF EXISTS sec_ingredient_id, sec_recipe_id, sec_tag_id;

CREATE SEQUENCE sec_recipe_id
 INCREMENT BY 1
 START WITH 2001
 NO MAXVALUE;

CREATE SEQUENCE sec_ingredient_id
  INCREMENT BY 1
  START WITH 50
  NO MAXVALUE;
  
CREATE SEQUENCE sec_tag_id
  INCREMENT BY 1
  START WITH 50
  NO MAXVALUE;    


CREATE TABLE users
(
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('ROLE_ADMIN', 'ROLE_USER'))
);

CREATE TABLE recipe
(
    recipe_id int PRIMARY KEY DEFAULT nextval('sec_recipe_id'),
    created_by_user_id INT NOT NULL,
    recipe_name VARCHAR(200) NOT NULL,
    description VARCHAR(1000),
    instructions VARCHAR(6500) NOT NULL,
    prep_time VARCHAR(100),
    cook_time VARCHAR(100),
    servings VARCHAR(100),
    CONSTRAINT fk_recipe_users FOREIGN KEY (created_by_user_id) REFERENCES users(user_id)
);

CREATE TABLE ingredients
(
    ingredient_id int PRIMARY KEY DEFAULT nextval('sec_ingredient_id'),
    ingredient_name VARCHAR(100) NOT NULL
);

CREATE TABLE recipes_ingredients
(
    recipe_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    amount NUMERIC NOT NULL,
    unit_type VARCHAR(100) NOT NULL,
    system_of_measurement VARCHAR(100) NOT NULL,
    CONSTRAINT pk_recipes_ingredients PRIMARY KEY (recipe_id, ingredient_id),
    CONSTRAINT fk_recipes_ingredients_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
    CONSTRAINT fk_recipes_ingredients_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id)
);

CREATE TABLE tag_id
(
    tag_id INT PRIMARY KEY DEFAULT nextval('sec_tag_id'),
    tag_name VARCHAR(50) NOT NULL
);

CREATE TABLE recipe_tags
(
    recipe_id INT NOT NULL,
    tag_id INT NOT NULL,
    CONSTRAINT pk_recipe_tags PRIMARY KEY (recipe_id, tag_id),
    CONSTRAINT fk_recipe_tags_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id),
    CONSTRAINT fk_recipe_tags_tag FOREIGN KEY (tag_id) REFERENCES tag_id(tag_id)
);

CREATE TABLE user_tracked_recipes
(
    recipes_id INT NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT pk_user_tracked_recipes PRIMARY KEY (recipes_id, user_id),
    CONSTRAINT fk_user_tracked_recipes_recipe FOREIGN KEY (recipes_id) REFERENCES recipe(recipe_id),
    CONSTRAINT fk_user_tracked_recipes_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

COMMIT TRANSACTION;

-- rollback;