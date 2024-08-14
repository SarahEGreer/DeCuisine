START TRANSACTION;

DROP TABLE IF EXISTS user_tracked_recipes, recipe_tags, recipes_ingredients, ingredients, recipe, tag_id, user_grocery_list CASCADE;
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
  

CREATE TABLE recipe
(
    recipe_id int PRIMARY KEY DEFAULT nextval('sec_recipe_id'),
    created_by_user_id INT NOT NULL,
    recipe_name VARCHAR(200) NOT NULL,
    description VARCHAR(1000),
    instructions VARCHAR(6500) NOT NULL,
    prep_time NUMERIC,
    cook_time NUMERIC,
    servings NUMERIC,
	photo_url VARCHAR(500),
    CONSTRAINT fk_recipe_users FOREIGN KEY (created_by_user_id) REFERENCES users(user_id)
);

CREATE TABLE ingredients
(
    ingredient_id int PRIMARY KEY DEFAULT nextval('sec_ingredient_id'),
    ingredient_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE recipes_ingredients
(
    recipe_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    amount NUMERIC NOT NULL,
    unit_type VARCHAR(100) NOT NULL,
    system_of_measurement VARCHAR(100),
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
CREATE TABLE user_grocery_list
(
	user_id int NOT NULL,
	item_name VARCHAR(100) NOT NULL,
	amount NUMERIC NOT NULL,
	unit_type VARCHAR(100) NOT NULL,
	system_of_measurement VARCHAR(100),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT fk_item_name FOREIGN KEY (user_id) REFERENCES users(user_id)
);


--test data
INSERT INTO recipe (created_by_user_id, recipe_name, description, instructions, prep_time, cook_time, servings, photo_url) VALUES
(3, 'Caesar Salad', 'A classic Caesar salad with a rich, creamy dressing.', 
'1. Prepare dressing.
2. Toss lettuce with dressing.
3. Add croutons and parmesan cheese.
4. Serve immediately.', 15, 0, 4, 'https://i.postimg.cc/Zq3LmZGF/Caesar-Salad-Recipe-3.jpg'),

(3, 'Grilled Chicken', 'Juicy grilled chicken breasts.', 
'1. Marinate chicken.
2. Grill over medium heat until fully cooked.
3. Serve hot.', 30, 15, 4, 'https://i.postimg.cc/sx1QV4Bd/grilled-chicken-recipe-25.jpg'),

(3, 'Pancakes', 'Fluffy homemade pancakes.', 
'1. Mix ingredients.
2. Cook on a hot griddle.
3. Serve with syrup.', 10, 5, 4, 'https://i.postimg.cc/L57qhgfq/wholesomeyum-Low-Carb-Keto-Pancakes-Recipe-21.jpg'),

(3, 'Chocolate Chip Cookies', 'Delicious homemade chocolate chip cookies.', 
'1. Preheat oven to 350°F (175°C).
2. Cream together butter and sugars.
3. Add eggs and vanilla extract.
4. Mix in dry ingredients and chocolate chips.
5. Scoop dough onto baking sheets and bake for 10-12 minutes.', 15, 12, 24, 'https://i.postimg.cc/1tvVhj3r/classic-chocolate-chip-cookies.jpg'),

(3, 'Spaghetti Carbonara', 'A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper.', 
'1. Cook spaghetti according to package instructions.
2. In a bowl, whisk eggs and cheese together.
3. In a pan, cook pancetta until crispy.
4. Combine spaghetti, pancetta, and egg mixture.
5. Serve with pepper and extra cheese.', 10, 15, 4, 'https://i.postimg.cc/6ppSB7cV/11973-spaghetti-carbonara-ii-DDMFS-4x3-6edea51e421e4457ac0c3269f3be5157.jpg'),

(3, 'Tacos', 'Traditional Mexican tacos with beef, lettuce, and cheese.', 
'1. Cook beef with spices.
2. Warm tortillas.
3. Assemble tacos with beef, lettuce, and cheese.', 20, 10, 8, 'https://i.postimg.cc/j5cPqjZN/chicken-tinga-tinga-de-pollo-4773239-Hero-01-1bd1d960c02a4fdb812323b8c60fd55b.jpg');

-- Insert ingredients for the new recipes
INSERT INTO ingredients (ingredient_name) VALUES 
('lettuce'), 
('croutons'), 
('parmesan cheese'), 
('chicken breast'), 
('flour'), 
('milk'), 
('eggs'), 
('butter'), 
('sugar'),
('chocolate chips'),
('spaghetti'),
('pancetta'),
('black pepper'),
('beef'),
('tortillas'),
('cheese')
ON CONFLICT (ingredient_name) DO NOTHING;

-- Recipes_Ingredients Table
INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Caesar Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'lettuce'), 1, 'head', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Caesar Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'croutons'), 0.5, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Caesar Salad'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'parmesan cheese'), 0.25, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Grilled Chicken'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'chicken breast'), 2, 'pieces', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Pancakes'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'flour'), 2, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Pancakes'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'milk'), 1.5, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Pancakes'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'eggs'), 2, 'pieces', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Chip Cookies'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'butter'), 1, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Chip Cookies'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'sugar'), 0.5, 'cup', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Chocolate Chip Cookies'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'chocolate chips'), 2, 'cups', 'Imperial'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Carbonara'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'spaghetti'), 400, 'grams', 'Metric'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Carbonara'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'pancetta'), 150, 'grams', 'Metric'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Spaghetti Carbonara'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'black pepper'), 5, 'grams', 'Metric'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'beef'), 500, 'grams', 'Metric'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'tortillas'), 8, 'pieces', 'Metric'),
((SELECT recipe_id FROM recipe WHERE recipe_name = 'Tacos'), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'cheese'), 100, 'grams', 'Metric');



-- INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type, system_of_measurement) VALUES
--need to change to just ingredient_name
-- (1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Spaghetti'), 400, 'grams', 'Metric'),
-- (1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Eggs'), 4, 'pieces', 'Metric'),
-- (1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Parmesan Cheese'), 100, 'grams', 'Metric'),
-- (1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Pancetta'), 150, 'grams', 'Metric'),
-- (1, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Black Pepper'), 5, 'grams', 'Metric'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Butter'), 1, 'cup', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Sugar'), 0.5, 'cup', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Brown Sugar'), 0.5, 'cup', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Eggs'), 2, 'pieces', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Vanilla Extract'), 1, 'tsp', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Flour'), 2.5, 'cups', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Baking Soda'), 1, 'tsp', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Salt'), 0.5, 'tsp', 'Imperial'),
-- (2, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = 'Chocolate Chips'), 2, 'cups', 'Imperial');

-- Recipe_Tags Table
-- INSERT INTO recipe_tags (recipe_id, tag_id) VALUES
-- (1, 3),
-- (1, 5),
-- (2, 4),
-- (2, 8);
-- User_Tracked_Recipes Table

INSERT INTO user_tracked_recipes (user_id, recipes_id) 
SELECT 3, recipe_id FROM recipe WHERE recipe_name IN ('Chocolate Chip Cookies', 'Spaghetti Carbonara', 'Tacos');

INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type, system_of_measurement) 
SELECT 3, 'lettuce', 1, 'head', 'Imperial' 
UNION ALL
SELECT 3, 'chicken breast', 2, 'pieces', 'Imperial'
UNION ALL
SELECT 3, 'spaghetti', 400, 'grams', 'Metric'
UNION ALL
SELECT 3, 'beef', 500, 'grams', 'Metric';


COMMIT TRANSACTION;

-- rollback;

