BEGIN TRANSACTION;

INSERT INTO users (user_id, username, password, role) VALUES
(1, 'chefjohn', 'password123', 'ROLE_USER'),
(2, 'bakerbeth', 'cookieLover', 'ROLE_USER');
--  Recipe Table
INSERT INTO recipe (recipe_id, created_by_user_id, recipe_name, description, instructions, prep_time, cook_time, servings) VALUES
(1, 1, 'Spaghetti Carbonara', 'A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper.', '1. Cook spaghetti according to package instructions. 2. In a bowl, whisk eggs and cheese together. 3. In a pan, cook pancetta until crispy. 4. Combine spaghetti, pancetta, and egg mixture. 5. Serve with pepper and extra cheese.', '10 minutes', '15 minutes', '4 servings'),
(2, 2, 'Chocolate Chip Cookies', 'Delicious homemade chocolate chip cookies.', '1. Preheat oven to 350°F (175°C). 2. Cream together butter and sugars. 3. Add eggs and vanilla extract. 4. Mix in dry ingredients and chocolate chips. 5. Scoop dough onto baking sheets and bake for 10-12 minutes.', '15 minutes', '12 minutes', '24 cookies');
-- Ingredients Table
INSERT INTO ingredients (ingredient_name) VALUES
('Spaghetti'),
('Eggs'),
('Parmesan Cheese'),
('Pancetta'),
('Black Pepper'),
('Butter'),
('Sugar'),
('Brown Sugar'),
('Eggs'),
('Vanilla Extract'),
('Flour'),
('Baking Soda'),
('Salt'),
('Chocolate Chips');
-- Recipes_Ingredients Table
INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
(1, 50, 400, 'grams', 'Metric'),
(1, 51, 4, 'pieces', 'Metric'),
(1, 52, 100, 'grams', 'Metric'),
(1, 53, 150, 'grams', 'Metric'),
(1, 54, 5, 'grams', 'Metric'),
(2, 55, 1, 'cup', 'Imperial'),
(2, 56, 0.5, 'cup', 'Imperial'),
(2, 57, 0.5, 'cup', 'Imperial'),
(2, 58, 2, 'pieces', 'Imperial'),
(2, 59, 1, 'tsp', 'Imperial'),
(2, 60, 2.5, 'cups', 'Imperial'),
(2, 61, 1, 'tsp', 'Imperial'),
(2, 62, 0.5, 'tsp', 'Imperial'),
(2, 63, 2, 'cups', 'Imperial');
-- Recipe_Tags Table
-- INSERT INTO recipe_tags (recipe_id, tag_id) VALUES
-- (1, 3),
-- (1, 5),
-- (2, 4),
-- (2, 8);
-- User_Tracked_Recipes Table
INSERT INTO user_tracked_recipes (user_id, recipes_id) VALUES
(1, 1),
(2, 2);

COMMIT;

-- ROLLBACK;