START TRANSACTION; 

-- Users Table
INSERT INTO users (user_id, username, password, role) VALUES
(1, 'chefjohn', 'password123', 'ROLE_USER'),
(2, 'bakerbeth', 'cookieLover', 'ROLE_USER');

-- Recipe Table
INSERT INTO recipe (recipe_id, created_by_user_id, recipe_name, description, instructions, prep_time, cook_time, servings) VALUES
(1, 1, 'Spaghetti Carbonara', 'A classic Italian pasta dish made with eggs, cheese, pancetta, and pepper.', '1. Cook spaghetti according to package instructions. 2. In a bowl, whisk eggs and cheese together. 3. In a pan, cook pancetta until crispy. 4. Combine spaghetti, pancetta, and egg mixture. 5. Serve with pepper and extra cheese.', '10 minutes', '15 minutes', '4 servings'),
(2, 2, 'Chocolate Chip Cookies', 'Delicious homemade chocolate chip cookies.', '1. Preheat oven to 350°F (175°C). 2. Cream together butter and sugars. 3. Add eggs and vanilla extract. 4. Mix in dry ingredients and chocolate chips. 5. Scoop dough onto baking sheets and bake for 10-12 minutes.', '15 minutes', '12 minutes', '24 cookies');

-- Ingredients Table
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES
(1, 'Spaghetti'),
(2, 'Eggs'),
(3, 'Parmesan Cheese'),
(4, 'Pancetta'),
(5, 'Black Pepper'),
(6, 'Butter'),
(7, 'Sugar'),
(8, 'Brown Sugar'),
(9, 'Eggs'),
(10, 'Vanilla Extract'),
(11, 'Flour'),
(12, 'Baking Soda'),
(13, 'Salt'),
(14, 'Chocolate Chips');

-- Recipes_Ingredients Table
INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES
(1, 1, 400, 'grams', 'Metric'),
(1, 2, 4, 'pieces', 'Metric'),
(1, 3, 100, 'grams', 'Metric'),
(1, 4, 150, 'grams', 'Metric'),
(1, 5, 5, 'grams', 'Metric'),
(2, 6, 1, 'cup', 'Imperial'),
(2, 7, 0.5, 'cup', 'Imperial'),
(2, 8, 0.5, 'cup', 'Imperial'),
(2, 9, 2, 'pieces', 'Imperial'),
(2, 10, 1, 'tsp', 'Imperial'),
(2, 11, 2.5, 'cups', 'Imperial'),
(2, 12, 1, 'tsp', 'Imperial'),
(2, 13, 0.5, 'tsp', 'Imperial'),
(2, 14, 2, 'cups', 'Imperial');

--tag_id Table
INSERT INTO tag_id (tag_id, tag_name) VALUES
(1, dinner),
(2, savory),
(3, lunch),
(4, breakfast),
(5, sweet);

-- -- Recipe_Tags Table
INSERT INTO recipe_tags (recipe_id, tag_id) VALUES
(1, 3),
(1, 5),
(2, 4),
(2, 8);

-- User_Tracked_Recipes Table
INSERT INTO user_tracked_recipes (user_id, recipes_id) VALUES
(1, 1),
(2, 2);

COMMIT;